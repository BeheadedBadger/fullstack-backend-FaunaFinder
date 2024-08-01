package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "donations")
public class Donation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    int amount;
    @OneToMany
    Set<User> shelters;
    @ManyToOne
    User user;
}
