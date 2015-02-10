package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.ebi.spot.goci.model.Role;

/**
 * Created by emma on 10/02/15.
 *
 * @author emma
 *         <p/>
 *         Repository accessing Role entity object
 */
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
}
