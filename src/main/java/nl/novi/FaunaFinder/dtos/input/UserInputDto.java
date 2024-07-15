package nl.novi.FaunaFinder.dtos.input;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.FaunaFinder.models.Role;

public class UserInputDto {
    public String username;
    public String password;
    @Enumerated(value = EnumType.STRING)
    public Role role;
}
