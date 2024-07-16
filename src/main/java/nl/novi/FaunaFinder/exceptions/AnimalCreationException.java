package nl.novi.FaunaFinder.exceptions;

import java.io.Serial;

public class AnimalCreationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AnimalCreationException(Throwable cause) {
        super("Submission failed. Details: " + cause);
    }
}
