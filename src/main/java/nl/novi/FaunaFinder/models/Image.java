package nl.novi.FaunaFinder.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Image {

    @Id
    @Getter
    @Setter
    private String fileName;

    @Getter
    @Setter
    @OneToOne(mappedBy = "animalPhoto")
    private Animal animalOwner;


    @Getter
    @Setter
    @OneToOne(mappedBy = "userPhoto")
    private User userOwner;

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image() {

    }
}
