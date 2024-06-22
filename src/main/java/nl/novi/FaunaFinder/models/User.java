package nl.novi.FaunaFinder.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String userName;
    String password;
    String postalCode;
    Boolean moderator;
    @ManyToMany(mappedBy = "favourites")
    List<Animal> favourites;
    //@OneToMany(mappedBy = "user")
    //List<Donation> donations;
}
