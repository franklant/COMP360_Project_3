// Name: Franklan Taylor
// Date: 11/18/2025
// Course: COMP360-001
// Description: Thrown to indicate that the theater no longer has seats available to the customer.

/**
 * <p>Thrown to indicate that the theater no longer has seats available to the customer.</p>
 * @since 0.0
 */
public class WestTheaterFullException extends IllegalArgumentException
{
    /**
     * <p>Constructs an {@code WestTheaterFullException} with a detailed message</p>
     */
    public WestTheaterFullException()
    {
        super("No Seat Available.");
    }

    /**
    * Constructs an {@code WestTheaterFullException} with a <i>CUSTOM</i> detailed message
    * @param message the message sent to the console once the exception is thrown.
    */
    public WestTheaterFullException(String message)
    {
        super(message);
    }
}
