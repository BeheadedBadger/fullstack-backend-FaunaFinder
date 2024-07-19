package nl.novi.FaunaFinder.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Image {

    @Id
    private String fileName;
    @OneToOne(mappedBy = "animalPhoto")
    private Animal animalOwner;

    @OneToOne(mappedBy = "userPhoto")
    private User userOwner;

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image() {

    }

    public String getFileName;
}
