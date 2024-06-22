package nl.novi.FaunaFinder.repositories;
import nl.novi.FaunaFinder.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
