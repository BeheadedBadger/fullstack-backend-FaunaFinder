package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "shelters")
public class Shelter {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String name;
    String speciality;
    String city;
    String postalCode;
    String address;
    int phoneNumber;
    @OneToMany(mappedBy = "shelter")
    private List<Animal> animals;
    @OneToMany(mappedBy = "shelter")
    List<Donation> donations;
}
