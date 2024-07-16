package nl.novi.FaunaFinder.dtos.input;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.FaunaFinder.models.Role;

public class UserInputDto {
    public String username;
    public String password;
    public Role role;
}
