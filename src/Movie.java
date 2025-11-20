// Name: Franklan Taylor
// Date: 11/18/2025
// Course: COMP360-001
// Description: Movie is an object that represents a movie to be selected and reserved in the Seat Reservation System.
// This object stores the movie name, date, time, and numOfSeats. This will be used to display information
// for the theater section including the seats available and the max number of seats available to be reserved.

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <p>{@code Movie} is an object that represents a movie to be selected and reserved in the Seat Reservation System.
 * This object stores the movie {@code name}, {@code date}, {@code time}, and {@code numOfSeats}. This
 * will be used to display information for the theater section including the seats available and the max number of seats
 * available to be reserved.</p>
 */
public class Movie {
    protected String _name;
    protected LocalDate _date;
    protected LocalTime _time;
    protected int _numOfSeats;

    /**
     * <p>Constructs an empty {@code Movie} object.</p>
     */
    public Movie()
    {
        _name = null;
        _date = LocalDate.now();
        _time = LocalTime.of(0,0);
        _numOfSeats = 0;
    }

    /**
     * <p>Constructs a {@code Movie} object with user-specified details.</p>
     * @param name the name of the movie.
     * @param month the month the movie is being shown.
     * @param day the day the movie is being shown.
     * @param hour the hour of time the movie is being shown.
     * @param min the minute of time the movie is being shown.
     * @param numOfSeats the number of seats available to be reserved.
     */
    public Movie(String name, int month, int day, int hour, int min, int numOfSeats)
    {
        _name = name;
        _date = LocalDate.of(2025, month, day);
        _time = LocalTime.of(hour, min, 0, 0);
        _numOfSeats = numOfSeats;
    }

    /**
     * <p>Prints the {@code _date} and {@code _time} of the {@link Movie} object.</p>
     */
    void getDateTime()
    {
        System.out.println(_date + " " + _time);
    }

    /**
     * <p>Print the attributes of this {@code Movie} instance onto the screen within a special format.</p>
     */
    void printInfo()
    {
        System.out.println("Movie Name: " + _name);
        System.out.println("Movie Date: " + _date.toString());
        System.out.println("Movie Time: " + _time.toString());
        System.out.println("Total Number of Seats: " + _numOfSeats);
    }
}
