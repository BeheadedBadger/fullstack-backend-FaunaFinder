package nl.novi.FaunaFinder.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Donation;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.models.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class UserOutputDto {
    Long id;
    String username;
    Set<Animal> favouriteAnimals;
    List<Donation> donations;
    Role role;
    String userPhoto;
    String speciality;
    List<Animal> shelterAnimals;
}
