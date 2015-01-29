package uk.ac.ebi.spot.goci.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ebi.spot.goci.model.Association;
import uk.ac.ebi.spot.goci.model.Locus;
import uk.ac.ebi.spot.goci.model.RiskAllele;
import uk.ac.ebi.spot.goci.model.SingleNucleotidePolymorphism;
import uk.ac.ebi.spot.goci.model.Study;
import uk.ac.ebi.spot.goci.repository.AssociationRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Javadocs go here!
 *
 * @author Tony Burdett
 * @date 16/01/15
 */
@Service
public class AssociationService {
    private AssociationRepository associationRepository;

    private StudyService studyService;
    private SingleNucleotidePolymorphismService snpService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public AssociationService(AssociationRepository associationRepository,
                              StudyService studyService,
                              SingleNucleotidePolymorphismService snpService) {
        this.associationRepository = associationRepository;
        this.studyService = studyService;
        this.snpService = snpService;
    }

    protected Logger getLog() {
        return log;
    }

    /**
     * A facade service around a {@link uk.ac.ebi.spot.goci.repository.AssociationRepository} that retrieves all
     * associations, and then within the same datasource transaction additionally loads other objects referenced by this
     * association (so Genes and Regions).
     * <p>
     * Use this when you know you will need deep information about a association and do not have an open session that
     * can be used to lazy load extra data.
     *
     * @return a list of Associations
     */
    @Transactional(readOnly = true)
    public List<Association> deepFindAll() {
        List<Association> allAssociations = associationRepository.findAll();
        // iterate over all Associations and grab region info
        getLog().info("Obtained " + allAssociations.size() + " associations, starting deep load...");
        allAssociations.forEach(this::loadAssociatedData);
        return allAssociations;
    }

    @Transactional(readOnly = true)
    public List<Association> deepFindAll(Sort sort) {
        List<Association> allAssociations = associationRepository.findAll(sort);
        // iterate over all Associations and grab region info
        getLog().info("Obtained " + allAssociations.size() + " associations, starting deep load...");
        allAssociations.forEach(this::loadAssociatedData);
        return allAssociations;
    }

    @Transactional(readOnly = true)
    public Page<Association> deepFindAll(Pageable pageable) {
        Page<Association> allAssociations = associationRepository.findAll(pageable);
        // iterate over all Associations and grab region info
        getLog().info("Obtained " + allAssociations.getSize() + " associations, starting deep load...");
        allAssociations.forEach(this::loadAssociatedData);
        return allAssociations;
    }

    public void loadAssociatedData(Association association) {
        int traitCount = association.getEfoTraits().size();
        Study study = studyService.deepFetchOne(association.getStudy());
        int reportedGeneCount = 0;
        Collection<SingleNucleotidePolymorphism> snps = new HashSet<>();
        association.getLoci().forEach(
                locus -> snps.addAll(
                        locus.getStrongestRiskAlleles()
                                .stream()
                                .map(RiskAllele::getSnp)
                                .collect(Collectors.toList())));
        getLog().info("Association '" + association.getId() + "' is mapped to " +
                              "" + traitCount + " EFO traits, " + reportedGeneCount + " genes, " +
                              "study id = " + study.getId() + " and " + snps.size() + " SNPs");
    }
}