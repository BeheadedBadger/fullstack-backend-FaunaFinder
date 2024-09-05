package nl.novi.FaunaFinder.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "images")
public class Image {

    @Id
    private String fileName;

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image() {
    }
}
