package nl.novi.FaunaFinder.dtos.input;
import nl.novi.FaunaFinder.models.Animal;

public class AnimalInputDto {
    public String name;
    public int age;
    public Animal.Sex sex;
    public String commonSpeciesName;
    public String scientificSpeciesName;
    public Boolean warning;
    public String warningExplanation;
    public String description;
}
