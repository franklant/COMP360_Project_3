// Name: Franklan Taylor
// Date: 11/20/2025
// Course: COMP360-001
// Description: Thrown to indicate that the number of seats requested to construct the Movie object exceeds
// the max amount of seats available in the theater.


/**
 * <p>Thrown to indicate that the number of seats requested to construct the {@link Movie} object exceeds
 * the max amount of seats available in the theater.</p>
 * @since 0.1
 */
public class MaxNumberOfSeatsException extends IllegalArgumentException{
    /**
     * <p>Constructs a {@code MaxNumberOfSeatsException} with a detailed message.</p>
     */
    public MaxNumberOfSeatsException()
    {
        super("The number of seats requested exceeds the max amount of seats in the theater!");
    }

    /**
     * <p>Constructs a {@code MaxNumberOfSeatsException} with a custom detailed message</p>
     * @param message The user-specified message shown when this exception is thrown.
     */
    public MaxNumberOfSeatsException(String message)
    {
        super(message);
    }
}
