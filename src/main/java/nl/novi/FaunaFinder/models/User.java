package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    String userName;
    @Column(nullable = false, length = 255)
    String password;
    String postalCode;
    @ManyToOne
    Authority authority;
    @ManyToMany(mappedBy = "favourites")
    List<Animal> favouriteAnimals;
    @OneToMany(mappedBy = "user")
    List<Donation> donations;

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

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
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
