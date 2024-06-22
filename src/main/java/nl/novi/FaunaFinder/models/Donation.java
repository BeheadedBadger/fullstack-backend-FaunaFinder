package nl.novi.FaunaFinder.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "donations")
public class Donation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    int amount;
    @ManyToOne
    Shelter shelter;
    @ManyToOne
    User user;
}
