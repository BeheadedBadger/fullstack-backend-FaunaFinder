package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Table(name = "animals")
public class Animal {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    @Setter
    private String name;
    @Setter
    private int age;
    @Setter
    @Enumerated(EnumType.STRING)
    private Animal.Sex sex;
    @Setter
    private String commonSpeciesName;
    @Setter
    private String scientificSpeciesName;
    @Setter
    private String speciesCategory;
    @Setter
    private Boolean warning;
    @Setter
    private String warningExplanation;
    @Setter
    @Column(length = 2048)
    private String description;
    @Setter
    @ManyToOne
    private User shelter;
    @Setter
    @ManyToMany
    private List<User> favourites;
    @Setter
    @OneToOne
    private Image animalPhoto;

    public enum Sex {
        F,
        M,
        X
    }
}
