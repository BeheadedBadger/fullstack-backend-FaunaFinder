package nl.novi.FaunaFinder.dtos.output;

import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Donation;

import java.util.List;

public class UserOutputDto {
    Long id;
    String userName;
    String password;
    String postalCode;
    Boolean isModerator;
    List<Animal> favouriteAnimals;
    List<Donation> donations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Boolean getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(Boolean moderator) {
        this.isModerator = moderator;
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
}
