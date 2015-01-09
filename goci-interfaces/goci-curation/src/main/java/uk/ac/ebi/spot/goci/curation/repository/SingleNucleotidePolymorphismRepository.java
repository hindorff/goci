package uk.ac.ebi.spot.goci.curation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.ebi.spot.goci.curation.model.SingleNucleotidePolymorphism;


/**
 * Created by emma on 21/11/14.
 *
 * @author emma
 *         <p/>
 *         Repository accessing Single Nucloetide Polymorphism entity object
 */

@RepositoryRestResource
public interface SingleNucleotidePolymorphismRepository extends JpaRepository<SingleNucleotidePolymorphism, Long> {
    SingleNucleotidePolymorphism findByRsId(String rsId);
}
