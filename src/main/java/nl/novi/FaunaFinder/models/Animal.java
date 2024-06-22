package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "animals")
public class Animal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private int age;
    private Animal.Sex sex;
    private String commonSpeciesName;
    private String scientificSpeciesName;
    private Boolean warning;
    private String warningExplanation;
    private Long description;
    @ManyToOne
    private Shelter shelter;
    @ManyToMany
    private List<User> favourites;

    public enum Sex {
        F,
        M,
        X
    }
}
