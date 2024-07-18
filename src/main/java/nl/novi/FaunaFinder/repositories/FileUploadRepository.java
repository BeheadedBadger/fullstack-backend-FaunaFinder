package nl.novi.FaunaFinder.repositories;
import nl.novi.FaunaFinder.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FileUploadRepository extends JpaRepository<Image, String> {
}
