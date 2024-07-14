package nl.novi.FaunaFinder.dtos.output;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.User;

public class AnimalOutputDto {
    private Long id;
    private String name;
    private int age;
    private Animal.Sex sex;
    private String commonSpeciesName;
    private String scientificSpeciesName;
    private Boolean warning;
    private String warningExplanation;
    private String description;
    private User shelter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Animal.Sex getSex() {
        return sex;
    }

    public void setSex(Animal.Sex sex) {
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
}
