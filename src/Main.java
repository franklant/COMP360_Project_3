// Name: Franklan Taylor
// Date: 11/11/2025
// Course: COMP360-001
// Description: A Seat Reservation program where the user can select a date, time, seat and either reserve that seat
// or return an exception saying the seat is full.


import com.sun.source.tree.StringTemplateTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <p>A Seat Reservation program where the user can select a date, time, seat and either reserve that seat or return an
 * exception saying the seat is full.</p>
 */
public class Main extends JFrame implements ActionListener
{
    Dictionary<String, JPanel> _sceneManager;                    // manages the JPanels responsible for different pages of the app.
    Dictionary<String, Movie> _movieManager;                  // manages a list of users inorder to retrieve payment info.
    Dictionary<String, ArrayList<String>> _reservedList;
    static WestTheaterReservationSystem _wsrSystem;
    GridBagConstraints _gridBagConstraints;

    // -- MAIN MENU COMPONENTS -- //
    JLabel _menuTitleLabel;
    JButton _addMovieButton;
    JButton _reserveSeatButton;

    // -- ADD MOVIE COMPONENTS -- //
    // title, month, day, hour, min
    // add button
    JLabel _sceneTitleLabel;
    JLabel _movieTitleLabel;
    JLabel _movieMonthLabel;
    JLabel _movieDayLabel;
    JLabel _movieHourLabel;
    JLabel _movieMinLabel;
    JLabel _movieSeatsLabel;
    JTextArea _movieTitleArea;
    JTextArea _movieMonthArea;
    JTextArea _movieDayArea;
    JTextArea _movieHourArea;
    JTextArea _movieMinArea;
    JTextArea _movieSeatsArea;
    JButton _backButton;
    JButton _addButton;
    // .. add more later

    // -- RESERVE SEAT MENU COMPONENTS -- //
    JLabel _reserveSeatTitleLabel;
    JLabel _seatingTitleLabel;
    JButton _reserveBackButton;
    JButton _findButton;
    JButton _seatingBackButton;
    JComboBox _reserveMovieBox;
    ArrayList<JButton> _seatButtons;
    JPanel _seatPanel;
    // .. add more later

    public static void main(String[] args)
    {
        Main app = new Main();
        app.configureWindow();
    }

    public void configureWindow()
    {
        this.setTitle("Project 2 Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(720 + (720 / 2), 430 + (430 / 2)));
        this.setResizable(false);

        interfaceContents();

        this.pack();                                            // pack all contents
        this.setVisible(true);                                  // make the window visible
        this.setLocationRelativeTo(null);                       // place the window at the center of the screen
    } // configureWindow()

    public void interfaceContents()
    {
        // responsible for containing all the GUI/UI components and layout
        this.setLayout(new GridBagLayout());                    // instance the layout for all components

        _wsrSystem = new WestTheaterReservationSystem();
        _wsrSystem.addMovie("Chicken Broth", 12, 5, 12,30, 200);
        _wsrSystem.addMovie("Candy Corn", 10, 31, 1,15, 150);

        _wsrSystem.showMovieList();

        // LAYOUT FUNCTIONS LIKE A GRID. KEEP THAT IN MIND
        // Position components based off position on a grid. Should be pretty simple in theory.
        _sceneManager = new Hashtable<>();                      // instantiate the dictionary
        _movieManager = _wsrSystem.getMovieManager();
        _reservedList = new Hashtable<>();

        createScenes();

        // -- MAIN SCENE -- //
        mainScene();

        // -- ADD MOVIE SCENE -- //
        addMovieScene();

        // -- RESERVE SEAT SCENE -- //
        reserveSeatScene();



    } // interfaceContents()

    /**
     * <p>Creates the scenes necessary for the GUI. Sets the main scene to visible.</p>
     */
    public void createScenes()
    {
        _sceneManager.put("MainScene", new JPanel());
        _sceneManager.put("AddMovieScene", new JPanel());
        _sceneManager.put("ReserveSeatScene", new JPanel());
        _sceneManager.put("SeatingScene", new JPanel());

        Enumeration<JPanel> scenes = _sceneManager.elements();
        while (scenes.hasMoreElements())
        {
            JPanel currentScene = scenes.nextElement();
            currentScene.setPreferredSize(new Dimension(720, 480));

            if (_sceneManager.get("MainScene") != currentScene) {
                currentScene.setVisible(false);
            } // any scene that isn't the main scene shouldn't be visible

            this.add(currentScene);
        }
    } // createScenes()

    /**
     * <p>Switches to the scene specified by setting every other scene to NOT be visible/</p>
     * @param sceneName The name of the scene to switch to.
     */
    public void switchToScene(String sceneName)
    {
        Enumeration<JPanel> scenes = _sceneManager.elements();
        while (scenes.hasMoreElements())
        {
            JPanel currentScene = scenes.nextElement();

            // if the scene name matches a scene
            if (_sceneManager.get(sceneName) == currentScene)
            {
                // make the scene visible if not already
                if (!_sceneManager.get(sceneName).isVisible())
                {
                    currentScene.setVisible(true);
                }
            }
            // if the current scene does not match the scene specified, hide the scene.
            else if (_sceneManager.get(sceneName) != currentScene) {
                currentScene.setVisible(false);
            } // any scene that isn't the scene specified by sceneName, scene shouldn't be visible
        }
    } // switchToScene();

    /**
     * <p>Contains all of the components for the Main Scene.</p>
     */
    public void mainScene()
    {
        _gridBagConstraints = new GridBagConstraints();
        _gridBagConstraints.fill = GridBagConstraints.BOTH;

        _sceneManager.get("MainScene").setLayout(new GridBagLayout());      // important to set the layout

        _menuTitleLabel = new JLabel("West Theater Reservation System");
        _gridBagConstraints.gridwidth = 2;
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 0;
        _gridBagConstraints.insets = new Insets(1, 0, 1, 0);
        _sceneManager.get("MainScene").add(_menuTitleLabel, _gridBagConstraints);

        _addMovieButton = new JButton("Add a Movie");
        _addMovieButton.addActionListener(this);
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 1;
        _sceneManager.get("MainScene").add(_addMovieButton, _gridBagConstraints);

        _reserveSeatButton = new JButton("Reserve a Seat");
        _reserveSeatButton.addActionListener(this);
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 2;
        _sceneManager.get("MainScene").add(_reserveSeatButton, _gridBagConstraints);

        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 3;
        _sceneManager.get("MainScene").add(
                new JLabel("@ Franklan Taylor | COMP 360 | Project 3"),
                _gridBagConstraints
        );
    } // mainMenu()

    /**
     * <p>Contains all of the components for the Add Movie Scene.</p>
     */
    public void addMovieScene()
    {
        _gridBagConstraints = new GridBagConstraints();
        _gridBagConstraints.fill = GridBagConstraints.BOTH;

        _sceneManager.get("AddMovieScene").setLayout(new GridBagLayout());      // important to set the layout
        //_sceneManager.get("AddMovieScene").setBackground(Color.DARK_GRAY);      // important to set the layout

        _backButton = new JButton("Back");
        _backButton.addActionListener(this);
        _gridBagConstraints.gridwidth = 2;
        _gridBagConstraints.insets = new Insets(1, 0, 1, 0);
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 0;
        _sceneManager.get("AddMovieScene").add(_backButton, _gridBagConstraints);

        _sceneTitleLabel = new JLabel("Add a new Movie");
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 1;
        _sceneManager.get("AddMovieScene").add(_sceneTitleLabel, _gridBagConstraints);

        _movieTitleLabel = new JLabel("Movie Title: ");
        //_gridBagConstraints.gridwidth = 1;
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 2;
        _gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        _sceneManager.get("AddMovieScene").add(_movieTitleLabel, _gridBagConstraints);

        _movieMonthLabel = new JLabel("Movie Release Month (MM): ");
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 4;
        _gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        _sceneManager.get("AddMovieScene").add(_movieMonthLabel, _gridBagConstraints);

        _movieDayLabel = new JLabel("Movie Release Day (DD): ");
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 6;
        _sceneManager.get("AddMovieScene").add(_movieDayLabel, _gridBagConstraints);

        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 8;
        _sceneManager.get("AddMovieScene").add(
                new JLabel("Release Time (HH:MM): "),
                _gridBagConstraints
        );

        _movieSeatsLabel = new JLabel("Number of Seats: ");
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 10;
        _sceneManager.get("AddMovieScene").add(_movieSeatsLabel, _gridBagConstraints);


        _movieTitleArea = new JTextArea();
        _movieTitleArea.setEditable(true);
        _movieTitleArea.setBorder(BorderFactory.createBevelBorder(1));
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 3;
        _sceneManager.get("AddMovieScene").add(_movieTitleArea, _gridBagConstraints);

        _movieMonthArea = new JTextArea();
        _movieMonthArea.setEditable(true);
        _movieMonthArea.setBorder(BorderFactory.createBevelBorder(1));
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 5;
        _sceneManager.get("AddMovieScene").add(_movieMonthArea, _gridBagConstraints);

        _movieDayArea = new JTextArea();
        _movieDayArea.setEditable(true);
        _movieDayArea.setBorder(BorderFactory.createBevelBorder(1));
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 7;
        _sceneManager.get("AddMovieScene").add(_movieDayArea, _gridBagConstraints);


        _gridBagConstraints.gridwidth = 1;
        _gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        _movieHourArea = new JTextArea();
        _movieHourArea.setEditable(true);
        _movieHourArea.setBorder(BorderFactory.createBevelBorder(1));
        _gridBagConstraints.weightx = 0.5;
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 9;
        _sceneManager.get("AddMovieScene").add(_movieHourArea, _gridBagConstraints);

        _movieMinArea = new JTextArea();
        _movieMinArea.setEditable(true);
        _movieMinArea.setBorder(BorderFactory.createBevelBorder(1));
        _gridBagConstraints.weightx = 0.5;
        _gridBagConstraints.gridx = 1;
        _gridBagConstraints.gridy = 9;
        _sceneManager.get("AddMovieScene").add(_movieMinArea, _gridBagConstraints);

        _movieSeatsArea = new JTextArea();
        _movieSeatsArea.setEditable(true);
        _movieSeatsArea.setBorder(BorderFactory.createBevelBorder(1));
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 11;
        _sceneManager.get("AddMovieScene").add(_movieSeatsArea, _gridBagConstraints);

        _addButton = new JButton("Submit");
        _addButton.addActionListener(this);
        _gridBagConstraints.gridwidth = 2;
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 12;
        _gridBagConstraints.insets = new Insets(10, 0, 0,0);
        _sceneManager.get("AddMovieScene").add(_addButton, _gridBagConstraints);
    } // addMovieScene()

    /**
     * <p>Properly format the date or time based off optional methods of recording time. 01:00 -> 1:00.</p>
     * @param dateOrTime The date or time text given to be formatted.
     * @return An integer of the correctly formatted and processed date/time text.
     */
    int formatDateTime(String dateOrTime)
    {
        if (dateOrTime.charAt(0) == '0')
        {
            return Integer.parseInt(dateOrTime.substring(1));
        }
        return Integer.parseInt(dateOrTime);
    } // formatDateTime()

    /**
     * <p>Submits the title, month, day, hour, and min time fields and creates a new {@link Movie} object with the
     * specified properties. This object is already added into the {@code _movieManager}.</p>
     */
    void submitMovieInfo()
    {
        String title = _movieTitleArea.getText();

        if (
                _movieTitleArea.getText().isEmpty()
                || _movieMinArea.getText().isEmpty()
                || _movieMinArea.getText().isEmpty()
                || _movieMonthArea.getText().isEmpty()
                || _movieDayArea.getText().isEmpty()
        )
        {
            JOptionPane.showMessageDialog(this, "One of more of the following fields is empty!");
            return;
        }

        if (
                _movieMinArea.getText().length() > 2 || _movieMinArea.getText().length() > 2
                || _movieMonthArea.getText().length() > 2 || _movieDayArea.getText().length() > 2
        )
        {
            JOptionPane.showMessageDialog(
                    this,
                    "month, day, hour, or min time field(s) contain more than 2 numbers!"
            );
            return;
        }

        int month = formatDateTime(_movieMonthArea.getText());
        int day = formatDateTime(_movieDayArea.getText());
        int hour = formatDateTime(_movieHourArea.getText());
        int min = formatDateTime(_movieMinArea.getText());
        int numOfSeats = formatDateTime(_movieSeatsArea.getText());

        if (_wsrSystem.verifyMovieList(title, month, day, hour, min))
        {
            _wsrSystem.addMovie(title, month, day, hour, min, numOfSeats);
        } else {
            JOptionPane.showMessageDialog(this, "The movie already exists!");
            return;
        }

        JOptionPane.showMessageDialog(this,"Successfully added '" + title + "'!");

        _movieManager = _wsrSystem.getMovieManager();
        _wsrSystem.showMovieList();
    }

    ArrayList<String> _movieList = new ArrayList<>();
    /**
     * <p>Fetches the names of the movies within the {@code _movieManager} and places them into the
     * {@code _reserveMovieBox}.</p>
     */
    public void fetchMovieTitles()
    {
        _movieList.clear();
        Enumeration<Movie> movies = _movieManager.elements();
        while (movies.hasMoreElements())
        {
            Movie currentMovie = movies.nextElement();
            _movieList.add(currentMovie._name);
        }

        // remove and readd the component
        if (_reserveMovieBox != null)
        {
            _sceneManager.get("ReserveSeatScene").remove(_reserveMovieBox);

            _reserveMovieBox = new JComboBox(_movieList.toArray());
            _gridBagConstraints.gridx = 0;
            _gridBagConstraints.gridy = 2;
            _gridBagConstraints.insets = new Insets(10, 0, 0, 0);
            _sceneManager.get("ReserveSeatScene").add(_reserveMovieBox, _gridBagConstraints);
        }
    }

    /**
     * <p>Populate the screen with buttons representing each seat available for the movie.</p>
     */
    public void seatButtonScene(String movieName)
    {
        if (_reservedList.get(movieName) == null)
        {
            _reservedList.put(movieName, new ArrayList<>());
        }

        System.out.println("Clearing Scene");
        for (Component i : _sceneManager.get("SeatingScene").getComponents())
        {
            _sceneManager.get("SeatingScene").remove(i);
        }

        _sceneManager.get("SeatingScene").setLayout(new GridBagLayout());
        _gridBagConstraints = new GridBagConstraints();
        _gridBagConstraints.fill = GridBagConstraints.BOTH;
        _gridBagConstraints.gridwidth = 1;

        _seatingBackButton = new JButton("Back");
        _seatingBackButton.addActionListener(this);

        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 0;
        _gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        _sceneManager.get("SeatingScene").add(_seatingBackButton, _gridBagConstraints);


        _seatingTitleLabel = new JLabel("Reserve A Seat!");
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 1;
        _gridBagConstraints.gridwidth = 4;
        _sceneManager.get("SeatingScene").add(_seatingTitleLabel, _gridBagConstraints);

        // Get the selected movie
        Movie selectedMovie = _movieManager.get(movieName);
        int buttonCount = 1;
        _gridBagConstraints.insets = new Insets(0, 0, 0, 0);

        char[] rowLetter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 0; i < _wsrSystem.MAX_ROWS; i++){
            for (int j = 0; j < _wsrSystem.MAX_COLUMNS; j++)
            {
                JButton seatButton = new JButton(rowLetter[i] + String.valueOf(j));
                //JButton seatButton = new JButton(" ");
                seatButton.addActionListener(this);
//                seatButton.setPreferredSize(new Dimension(30, 30))
                _gridBagConstraints.gridx = j;
                _gridBagConstraints.gridy = 2 + i;
                _gridBagConstraints.ipadx = 0;
                _gridBagConstraints.gridwidth = 1;

                seatButton.setPreferredSize(new Dimension(32, 32));
                seatButton.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 10));

                _sceneManager.get("SeatingScene").add(seatButton, _gridBagConstraints);
                _seatButtons.add(seatButton);

                // if the number of buttons is higher than the movies number of seats.
                if (buttonCount > selectedMovie._numOfSeats)
                {
                    seatButton.setEnabled(false);
                }

                buttonCount += 1;
            }
        }

        switchToScene("SeatingScene");
    }

    /**
     * <p>This method will check to see if the seat is reserved. If it is, it will throw an exception.</p>
     * @param movieName the name of the movie to reserve a seat for.
     * @param seatName the name of the seat to be reserved.
     * @throws WestTheaterFullException
     */
    public void reserveSeat(String movieName, String seatName) throws WestTheaterFullException
    {
        Movie reservedMovie = _movieManager.get(movieName);
        if (_reservedList.get(movieName).contains(seatName))
        {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Seat is already reserved! Cancel reservation?"
            );

            // cancel reservation if result is yes
            if (result == 0)
            {
                _reservedList.get(movieName).remove(seatName);
                JOptionPane.showMessageDialog(
                        this,
                        "Reservation Canceled. Seat is now available!"
                );
                return;
            }
            _wsrSystem.westTheaterFull();   // WestTheaterFullException
        }

        int result = JOptionPane.showConfirmDialog(this, "Reserve seat " + seatName + "?");

        // only add if the option seleted was 'yes'
        if (result == 0)
        {
            _reservedList.get(movieName).add(seatName);
            JOptionPane.showMessageDialog(
                    this,
                    "Reserved [" + seatName +
                            "] for:\n'" + movieName +
                            "'\nDate: " + reservedMovie._date.toString() +
                            "\nTime: " + reservedMovie._time.toString()
            );
        }
        // System.out.println(_reservedList.get(movieName).toString());
    }

    /**
     * <p>Contains all of the format and components for the Reserve Seat Scene</p>
     */
    public void reserveSeatScene()
    {
        _gridBagConstraints = new GridBagConstraints();
        _gridBagConstraints.fill = GridBagConstraints.BOTH;

        _sceneManager.get("ReserveSeatScene").setLayout(new GridBagLayout());      // important to set the layout

        _reserveBackButton = new JButton("Back");
        _reserveBackButton.addActionListener(this);
        _gridBagConstraints.gridwidth = 2;
        _gridBagConstraints.insets = new Insets(1, 0, 1, 0);
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 0;
        _sceneManager.get("ReserveSeatScene").add(_reserveBackButton, _gridBagConstraints);

        _reserveSeatTitleLabel = new JLabel("Pick a Movie!");
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 1;
        _sceneManager.get("ReserveSeatScene").add(_reserveSeatTitleLabel, _gridBagConstraints);

        //fetchMovieTitles();
        _reserveMovieBox = new JComboBox(_movieList.toArray());
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 2;
        _gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        _sceneManager.get("ReserveSeatScene").add(_reserveMovieBox, _gridBagConstraints);

        _findButton = new JButton("Find Seat");
        _findButton.addActionListener(this);
        _gridBagConstraints.gridx = 0;
        _gridBagConstraints.gridy = 3;
        _gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        _sceneManager.get("ReserveSeatScene").add(_findButton, _gridBagConstraints);

        _seatButtons = new ArrayList<>();

    } // reserveSeatScene()

    /**
     * <p>Throws the  {@link WestTheaterFullException} as a test.</p>
     */
    public void throwException() throws WestTheaterFullException
    {
        throw new WestTheaterFullException();
    }

    /**
     * <p>Clears the {@link JTextArea} components of all the scenes. Used when returning to the main menu.</p>
     */
    void clearInfoFields()
    {
        // ADD MOVIE COMPONENTS
        _movieTitleArea.setText("");
        _movieMonthArea.setText("");
        _movieDayArea.setText("");
        _movieHourArea.setText("");
        _movieMinArea.setText("");
        _movieSeatsArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // listens for actions from the user
        // reads it as source input
        JButton buttonSource = (JButton) e.getSource();             // Get which button is pressed based on source
        System.out.println("\u001B[33m" + "ACTION HEARD" + "\u001B[0m");

        if (buttonSource.equals(_addMovieButton))
        {
            System.out.println("\u001B[32m" + "SWITCHING TO 'Add Movie Scene'" + "\u001B[0m");
            switchToScene("AddMovieScene");
        } else if (buttonSource.equals(_reserveSeatButton))
        {
            System.out.println("\u001B[32m" + "SWITCHING TO 'Reserve Seat Scene'" + "\u001B[0m");

            if (_movieManager.isEmpty())
                throw new MovieManagerEmptyException();

            // refresh the combox box
//            _reserveMovieBox = new JComboBox(fetchMovieTitles().toArray());
            fetchMovieTitles();
            switchToScene("ReserveSeatScene");
        } else if (buttonSource.equals(_reserveBackButton) || buttonSource.equals(_backButton))
        {
            System.out.println("\u001B[32m" + "SWITCHING TO 'MainScene'" + "\u001B[0m");
            clearInfoFields();
            switchToScene("MainScene");
        }
        else if (buttonSource.equals(_seatingBackButton))
        {
            System.out.println("\u001B[32m" + "SWITCHING TO 'Reserve Seat Scene'" + "\u001B[0m");
            switchToScene("ReserveSeatScene");
        }
        else if (buttonSource.equals(_addButton))
        {
            System.out.println("\u001B[32m" + "SUBMITTING MOVIE INFO . . ." + "\u001B[0m");
            submitMovieInfo();
        }
        else if (buttonSource.equals(_findButton))
        {
            System.out.println("\u001B[32m" + "POPULATING SEATS . . ." + "\u001B[0m");
            seatButtonScene(_reserveMovieBox.getSelectedItem().toString());
        }
        else {
            int found = 0;
            for (JButton b : _seatButtons)
            {
                if (buttonSource.equals(b))
                {
                    found = 1;
                    System.out.println("\u001B[32m" + "RESERVING SEAT " + b.getText() + ". . ." + "\u001B[0m");
                    reserveSeat(_reserveMovieBox.getSelectedItem().toString(), b.getText());
                    break;
                }
            }

            if (found == 0)
            {
                System.out.println("\u001B[31m" + "Input Source NOT Recognized (or set up)." + "\u001B[0m");
            }
        }
    }
}