// Name: Franklan Taylor
// Date: 11/11/2025
// Course: COMP360-001
// Description: A Seat Reservation program where the user can select a date, time, seat and either reserve that seat
// or return an exception saying the seat is full.


/**
 * <p>A Seat Reservation program where the user can select a date, time, seat and either reserve that seat or return an
 * exception saying the seat is full.</p>
 */
public class Main
{
    public static void main(String[] args)
    {
        Main app = new Main();
        // app.throwException();

        WestTheaterReservationSystem wsrSystem = new WestTheaterReservationSystem();
        wsrSystem.addMovie("Chicken Broth", 12, 5, 12,30, 200);
        wsrSystem.addMovie("Candy Corn", 10, 31, 1,15, 150);

        wsrSystem.showMovieList();
    }

    /**
     * <p>Throws the  {@link WestTheaterFullException} as a test.</p>
     */
    public void throwException() throws WestTheaterFullException
    {
        throw new WestTheaterFullException();
    }
}