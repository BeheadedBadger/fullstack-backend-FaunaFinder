package nl.novi.FaunaFinder.dtos.input;
import nl.novi.FaunaFinder.models.Animal;
import nl.novi.FaunaFinder.models.Sex;
import nl.novi.FaunaFinder.models.User;

public class AnimalInputDto {
    public String name;
    public int age;
    public Sex sex;
    public String commonSpeciesName;
    public String scientificSpeciesName;
    public Boolean warning;
    public String warningExplanation;
    public String description;
    public String category;

    public User shelter;
}
