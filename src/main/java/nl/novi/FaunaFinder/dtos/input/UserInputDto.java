package nl.novi.FaunaFinder.dtos.input;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.FaunaFinder.models.Image;
import nl.novi.FaunaFinder.models.Role;
import org.springframework.web.multipart.MultipartFile;

public class UserInputDto {
    public String username;
    public String password;
    public Role role;

    //Shelter
    public String speciality;
}
