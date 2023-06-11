import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//The User class represents a user with a username and password. 
//It has a constructor to initialize the username and password and getter methods to retrieve them.
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}


//This class is the main class that contains the GUI implementation.
//It has four instance variables loginFrame, mainFrame, reservationFrame, and cancelFrame, 
//which represent the frames used in the application.
//It also has usernameField and passwordField to capture user input for login.
public class OnlineReservationSystemGUI {
    private JFrame loginFrame;
    private JFrame mainFrame;
    private JFrame reservationFrame;
    private JFrame cancelFrame;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public OnlineReservationSystemGUI() {
        createLoginFrame();
    }

    
//This method creates the login frame.
//It sets up the layout using BorderLayout and creates a panel with a grid layout to hold the login components.
//The login panel consists of two JLabels (for username and password), two input fields (JTextField and JPasswordField), 
//and a login button (JButton).
//An action listener is added to the login button to handle the login process.
//If the login is successful (matching username and password), it hides the login frame and creates the main frame. 
//Otherwise, it shows an error message dialog using JOptionPane.
    private void createLoginFrame() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = login(username, password);
                if (user != null) {
                    loginFrame.setVisible(false);
                    createMainFrame();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password. Access denied.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null); // Center the frame

    }

    
//This method performs the login process by checking the provided username and password against a predefined array of User objects.
//If a match is found, it returns the corresponding User object; otherwise, it returns null.
    private User login(String username, String password) {
        User[] users = {
                new User("user1", "password1"),
                new User("user2", "password2"),
                new User("user3", "password3")
        };

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

//This method creates the main frame after a successful login.
//It sets up the layout using BorderLayout and creates a panel with a grid layout to hold the main menu buttons 
    //(reservation and cancellation).
//Two buttons (JButton) are added to the main panel, each with its own action listener.
//When the reservation button is clicked, the main frame is hidden, and the reservation frame is created.
//When the cancellation button is clicked, the main frame is hidden, and the cancellation frame is created.
    private void createMainFrame() {
        mainFrame = new JFrame("Main");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton reservationButton = new JButton("Reservation");
        JButton cancelButton = new JButton("Cancel");

        mainPanel.add(reservationButton);
        mainPanel.add(cancelButton);

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                createReservationFrame();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                createCancelFrame();
            }
        });

        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null); // Center the frame

    }

    
//This method creates the reservation frame.
//It sets up the layout using BorderLayout and creates a panel with a grid layout to hold the reservation form components.
//The reservation form consists of labels and text fields for train number, train name, class type, date of journey, source, 
    //destination, and a reservation button.
//An action listener is added to the reservation button to handle the reservation process.
//After performing the reservation logic (which is not implemented in the provided code), a message dialog is shown using 
    //JOptionPane to display the reservation details.
    private void createReservationFrame() {
        reservationFrame = new JFrame("Make Reservation");
        reservationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationFrame.setSize(400, 300);
        reservationFrame.setLayout(new BorderLayout());

        JPanel reservationPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        JLabel trainNumberLabel = new JLabel("Train Number:");
        JTextField trainNumberField = new JTextField();
        JLabel trainNameLabel = new JLabel("Train Name:");
        JTextField trainNameField = new JTextField();
        JLabel classTypeLabel= new JLabel("Class Type:");
        JTextField classTypeField = new JTextField();
        JLabel dateOfJourneyLabel = new JLabel("Date of Journey:");
        JTextField dateOfJourneyField = new JTextField();
        JLabel sourceLabel = new JLabel("Source:");
        JTextField sourceField = new JTextField();
        JLabel destinationLabel = new JLabel("Destination:");
        JTextField destinationField = new JTextField();
            JButton reserveButton = new JButton("Reserve");

    reservationPanel.add(trainNumberLabel);
    reservationPanel.add(trainNumberField);
    reservationPanel.add(trainNameLabel);
    reservationPanel.add(trainNameField);
    reservationPanel.add(classTypeLabel);
    reservationPanel.add(classTypeField);
    reservationPanel.add(dateOfJourneyLabel);
    reservationPanel.add(dateOfJourneyField);
    reservationPanel.add(sourceLabel);
    reservationPanel.add(sourceField);
    reservationPanel.add(destinationLabel);
    reservationPanel.add(destinationField);
    reservationPanel.add(new JLabel());
    reservationPanel.add(reserveButton);

    reserveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String trainNumber = trainNumberField.getText();
            String trainName = trainNameField.getText();
            String classType = classTypeField.getText();
            String dateOfJourney = dateOfJourneyField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();

            // Perform reservation logic here

            String pnr = generatePNR();
            JOptionPane.showMessageDialog(reservationFrame, "Reservation successful. Your PNR is: " + pnr, "Reservation", JOptionPane.INFORMATION_MESSAGE);
        }
    });

    reservationFrame.add(reservationPanel, BorderLayout.CENTER);
    reservationFrame.setVisible(true);
        reservationFrame.setLocationRelativeTo(null); // Center the frame

    }

    
//This method creates the cancellation frame.
//It sets up the layout using BorderLayout and creates a panel with a border layout to hold the cancellation form components.
//The cancellation form consists of a label, a text field for the PNR (Passenger Name Record) number, and a cancel reservation button.
//An action listener is added to the cancel button to handle the cancellation process.
//After performing the cancellation logic (which is not implemented in the provided code), a message dialog is shown using 
    //JOptionPane to confirm the cancellation.
    private void createCancelFrame() {
        cancelFrame = new JFrame("Cancel Reservation");
        cancelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelFrame.setSize(300, 200);
        cancelFrame.setLayout(new BorderLayout());

        JPanel cancelPanel = new JPanel(new BorderLayout());
        JLabel pnrLabel = new JLabel("Enter your PNR number:");
        JTextField pnrField = new JTextField();
        JButton cancelButton = new JButton("Cancel Reservation");

        cancelPanel.add(pnrLabel, BorderLayout.NORTH);
        cancelPanel.add(pnrField, BorderLayout.CENTER);
        cancelPanel.add(cancelButton, BorderLayout.SOUTH);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pnr = pnrField.getText();

                // Perform cancellation logic here

                JOptionPane.showMessageDialog(cancelFrame, "Reservation with PNR " + pnr + " has been canceled.", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cancelFrame.add(cancelPanel, BorderLayout.CENTER);
        cancelFrame.setVisible(true);
            cancelFrame.setLocationRelativeTo(null); // Center the frame

    }

    
//This method generates a unique PNR number by appending the current system time to the string "PNR".
    private String generatePNR() {
        return "PNR" + System.currentTimeMillis();
    }

    
//The main method is the entry point of the application.
//It invokes the SwingUtilities.invokeLater method to ensure that the GUI components are created and modified on the 
    //Event Dispatch Thread (EDT), which is the dedicated thread for GUI operations.
//It creates an instance of the OnlineReservationSystemGUI class, which triggers the creation of the login frame 
    //and starts the application.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnlineReservationSystemGUI();
            }
        });
    }

}
