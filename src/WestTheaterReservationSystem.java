// Name: Franklan Taylor
// Date: 11/20/2025
// Course: COMP360-001
// Description: WestTheaterReservationSystem is responsible for the overall functionality of the movie reservation
// system. Here we can reserve a seat and check whether the seats are filled.

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <p>{@code WestTheaterReservationSystem} is responsible for the overall functionality of the movie reservation
 * system. Here we can reserve a seat and check whether the seats are filled.</p>
 */
public class WestTheaterReservationSystem {
    protected final int MAX_COLUMNS = 20;
    protected final int MAX_ROWS = 10;
    protected final int MAX_PEOPLE = MAX_COLUMNS * MAX_ROWS;
    protected Dictionary<String, Movie> _movieManager;          // stores the movie in the dictionary by title.

    /**
     * <p>Constructs an empty {@code WestTheaterReservationSystem} object.</p>
     */
    public WestTheaterReservationSystem()
    {
        _movieManager = new Hashtable<>();
    }

    /**
     * <p>Checks if the movie theater is full. If it is, the method will throw an exception.</p>
     * @throws WestTheaterFullException
     */
    public void westTheaterFull() throws WestTheaterFullException
    {
        throw new WestTheaterFullException();
    }

    /**
     * <p>Creates a new {@link Movie} object based on the user-specified parameters and then adds the new movie to
     * the {@code _movieManager} by the {@code name}. Throws an exception if the number of seats ({@code numOfSeats})
     * specified is greater than the max number of seats available to the user ({@code MAX_PEOPLE}).</p>
     * @param name the name of the movie.
     * @param month the month the movie is being shown.
     * @param day the day the movie is being shown.
     * @param hour the hour of time the movie is being shown.
     * @param min the minute of time the movie is being shown.
     * @param numOfSeats the number of seats available to be reserved.
     * @throws MaxNumberOfSeatsException
     */
    public void addMovie(String name, int month, int day, int hour, int min, int numOfSeats)
        throws MaxNumberOfSeatsException
    {
        if (numOfSeats > MAX_PEOPLE)
            throw new MaxNumberOfSeatsException();

        Movie newMovie = new Movie(name, month, day, hour, min, numOfSeats);
        _movieManager.put(name, newMovie);
    }

    /**
     * <p>Used to grab the current movie list of the {@code WestTheaterReservationSystem}.</p>
     * @return A {@link Dictionary} with the currently added movies.
     */
    public Dictionary<String, Movie> getMovieManager()
    {
        return _movieManager;
    }

    /**
     * <p>Checks the {@code _movieManager} dictionary for a movie with an identical title, date, and time as the
     * one trying to be created.</p>
     * @return {@code true} if the movie has NOT been created already; {@code false} if it HAS been created.
     */
    public boolean verifyMovieList(String name, int month, int day, int hour, int min)
    {
        Enumeration<Movie> movies = _movieManager.elements();
        while (movies.hasMoreElements())
        {
            Movie currentMovie = movies.nextElement();

            // movie already exists
            if (
                    currentMovie._name.equals(name)
                    && currentMovie._date.isEqual(LocalDate.of(2025, month, day))
                    && currentMovie._time.equals(LocalTime.of(hour, min))
            )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Print all of the information for every {@link Movie} within {@code _movieManager} onto the screen.
     * Throws an exception if the {@code _movieManager} is empty.</p>
     * @throws MovieManagerEmptyException
     */
    public void showMovieList() throws MovieManagerEmptyException
    {
        if (_movieManager.isEmpty())
            throw new MovieManagerEmptyException();

        int count = 0;      // movie count

        Enumeration<Movie> movies = _movieManager.elements();
        while (movies.hasMoreElements()) {
            count += 1;
            Movie movie = movies.nextElement();

            System.out.println("------ Movie " + count + " ------");
            movie.printInfo();
            System.out.println("---------------------");
            System.out.println();
        }
    }
}
