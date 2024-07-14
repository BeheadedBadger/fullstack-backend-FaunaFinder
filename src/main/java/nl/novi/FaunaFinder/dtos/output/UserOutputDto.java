package nl.novi.FaunaFinder.dtos.output;

import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Donation;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserOutputDto {
    String username;
    String password;
    String postalCode;
    List<Animal> favouriteAnimals;
    List<Donation> donations;
    Collection<? extends GrantedAuthority> authorities;
    boolean isPresent;

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<Animal> getFavouriteAnimals() {
        return favouriteAnimals;
    }

    public void setFavouriteAnimals(List<Animal> favouriteAnimals) {
        this.favouriteAnimals = favouriteAnimals;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities;}

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
