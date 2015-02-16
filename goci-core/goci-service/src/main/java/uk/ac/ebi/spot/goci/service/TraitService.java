package uk.ac.ebi.spot.goci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.goci.model.DiseaseTrait;
import uk.ac.ebi.spot.goci.model.EfoTrait;
import uk.ac.ebi.spot.goci.repository.DiseaseTraitRepository;
import uk.ac.ebi.spot.goci.repository.EfoTraitRepository;

import java.util.Collection;

/**
 * Javadocs go here!
 *
 * @author Tony Burdett
 * @date 14/02/15
 */
@Service
public class TraitService {
    private DiseaseTraitRepository diseaseTraitRepository;
    private EfoTraitRepository efoTraitRepository;

    @Autowired
    public TraitService(DiseaseTraitRepository diseaseTraitRepository,
                        EfoTraitRepository efoTraitRepository) {
        this.diseaseTraitRepository = diseaseTraitRepository;
        this.efoTraitRepository = efoTraitRepository;
    }

    public Collection<DiseaseTrait> findReportedTraitByStudyId(Long studyId) {
        return null;
    }

    public Collection<EfoTrait> findMappedTraitByStudyId(Long studyId) {
        return null;
    }

    public Collection<EfoTrait> findMappedTraitBySnpId(Long snpId) {
        return null;
    }

    public Collection<EfoTrait> findMappedTraitByAssociationId(Long associationId) {
        return null;
    }
}