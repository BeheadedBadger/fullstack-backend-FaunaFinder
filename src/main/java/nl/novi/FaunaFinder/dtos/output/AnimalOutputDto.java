package nl.novi.FaunaFinder.dtos.output;
import lombok.Getter;
import lombok.Setter;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Sex;
import nl.novi.FaunaFinder.models.User;

@Setter
@Getter
public class AnimalOutputDto {
    private Long id;
    private String name;
    private int age;
    private Sex sex;
    private String commonSpeciesName;
    private String scientificSpeciesName;
    private Boolean warning;
    private String warningExplanation;
    private String description;
    private User shelter;
    private String category;
    private String animalPhoto;
}
