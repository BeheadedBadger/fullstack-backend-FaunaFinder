package nl.novi.FaunaFinder.repositories;
import nl.novi.FaunaFinder.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShelterRepository extends JpaRepository<Shelter, String> {

}
