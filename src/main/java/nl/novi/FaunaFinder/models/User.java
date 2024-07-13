package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    String userName;
    @Column(nullable = false, length = 255)
    String password;
    String postalCode;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

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


    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authority) { this.authorities = authority;}

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
