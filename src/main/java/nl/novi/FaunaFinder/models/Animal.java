package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int age;
    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Animal.Sex sex;
    @Setter
    @Getter
    private String commonSpeciesName;
    @Setter
    @Getter
    private String scientificSpeciesName;
    @Setter
    @Getter
    private String speciesCategory;
    @Setter
    @Getter
    private Boolean warning;
    @Setter
    @Getter
    private String warningExplanation;
    @Setter
    @Getter
    @Column(length = 2048)
    private String description;
    @Setter
    @Getter
    @ManyToOne
    private User shelter;
    @Setter
    @Getter
    @ManyToMany
    private List<User> favourites;
    @Setter
    @Getter
    @OneToOne
    private Image animalPhoto;

    public enum Sex {
        F,
        M,
        X
    }
}
