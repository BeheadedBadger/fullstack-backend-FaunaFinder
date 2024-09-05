package nl.novi.FaunaFinder.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "animals")
public class Animal {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String commonSpeciesName;
    private String scientificSpeciesName;
    private String speciesCategory;
    private Boolean warning;
    private String warningExplanation;
    @Column(length = 2048)
    private String description;
    @JsonBackReference (value="shelterAnimals")
    @ManyToOne
    private User shelter;
    @JsonBackReference (value="favouriteAnimals")
    @ManyToMany (mappedBy = "favouriteAnimals")
    private Set<User> favourites;
    @OneToOne
    private Image animalPhoto;
}
