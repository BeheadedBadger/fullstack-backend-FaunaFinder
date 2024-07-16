package nl.novi.FaunaFinder.exceptions;
import java.io.Serial;

public class AnimalNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AnimalNotFoundException(Long id) {
        super("Cannot find animal with ID " + id);
    }
}