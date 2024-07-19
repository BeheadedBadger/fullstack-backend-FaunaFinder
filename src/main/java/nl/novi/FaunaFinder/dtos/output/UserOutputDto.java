package nl.novi.FaunaFinder.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Donation;
import nl.novi.FaunaFinder.models.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class UserOutputDto {
    String username;
    List<Animal> favouriteAnimals;
    List<Donation> donations;
    Role role;
    String speciality;
    List<Animal> shelterAnimals;
}
