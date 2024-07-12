package nl.novi.FaunaFinder.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "shelters")
public class Shelter {
    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    String userName;
    @Column(nullable = false, length = 255)
    String password;
    String name;
    String speciality;
    String city;
    String postalCode;
    String address;
    String phoneNumber;
    @JsonSerialize
    @ManyToOne
    Authority authority;
    @OneToMany(mappedBy = "shelter")
    private List<Animal> animals;
    @OneToMany(mappedBy = "shelter")
    List<Donation> donations;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {this.userName = username;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}
