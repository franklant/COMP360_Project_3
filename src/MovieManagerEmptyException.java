// Name: Franklan Taylor
// Date: 11/20/2025
// Course: COMP360-001
// Description: Thrown to indicate that the movie manager is empty.


/**
 * <p>Thrown to indicate that the {@code _movieManager} is empty.</p>
 * @since 0.1
 */
public class MovieManagerEmptyException extends RuntimeException
{
    /**
     * <p>Constructs a {@code MovieManagerEmptyException} with a detailed message.</p>
     */
    public MovieManagerEmptyException()
    {
        super("There are no movies in the movie manager!");
    }

    /**
     * <p>Constructs a {@code MovieManagerEmptyException} with a custom detailed message.</p>
     * @param message The user-specified message shown when this exception is thrown.
     */
    public MovieManagerEmptyException(String message)
    {
        super(message);
    }
}
