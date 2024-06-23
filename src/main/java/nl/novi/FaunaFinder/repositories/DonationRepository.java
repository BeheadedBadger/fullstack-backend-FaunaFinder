package nl.novi.FaunaFinder.repositories;
import nl.novi.FaunaFinder.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DonationRepository extends JpaRepository<Donation, Long> {

}
