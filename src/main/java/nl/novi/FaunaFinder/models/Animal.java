package nl.novi.FaunaFinder.models;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Animal.Sex sex;
    private String commonSpeciesName;
    private String scientificSpeciesName;
    private String speciesCategory;
    private Boolean warning;
    private String warningExplanation;
    @Column(length = 2048)
    private String description;
    @ManyToOne
    private User shelter;
    @ManyToMany
    private List<User> favourites;

    public enum Sex {
        F,
        M,
        X
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCommonSpeciesName() {
        return commonSpeciesName;
    }

    public void setCommonSpeciesName(String commonSpeciesName) {
        this.commonSpeciesName = commonSpeciesName;
    }

    public String getScientificSpeciesName() {
        return scientificSpeciesName;
    }

    public void setScientificSpeciesName(String scientificSpeciesName) {
        this.scientificSpeciesName = scientificSpeciesName;
    }

    public String getSpeciesCategory() { return speciesCategory; }

    public void setSpeciesCategory(String speciesCategory) {
        this.speciesCategory = speciesCategory;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public String getWarningExplanation() {
        return warningExplanation;
    }

    public void setWarningExplanation(String warningExplanation) {
        this.warningExplanation = warningExplanation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getShelter() {
        return shelter;
    }

    public void setShelter(User shelter) {
        this.shelter = shelter;
    }

    public List<User> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<User> favourites) {
        this.favourites = favourites;
    }
}
