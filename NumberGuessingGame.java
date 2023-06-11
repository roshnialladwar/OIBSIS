import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


//This class extends the JFrame class and represents the main frame of the number guessing game GUI.
public class NumberGuessingGameGUI extends JFrame {
    private JTextField guessTextField;//The class includes instance variables guessTextField and guessButton, 
    private JButton guessButton;//which represent the text field for entering guesses and the guess button, respectively.
    private int randomNumber;//The class also has two instance variables, randomNumber and attempts, 
    private int attempts;//to store the randomly generated number and the number of attempts made by the user.
    

//The constructor NumberGuessingGameGUI initializes the frame and sets its properties.
//It sets the title of the frame, sets the default close operation, sets the size, and centers the frame on the screen.
//The layout of the frame is set to BorderLayout.
    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout());

        
// The constructor creates a JPanel called topPanel and sets its layout to FlowLayout.
// It also creates a label guessLabel with the text "Enter your guess:", a text field guessTextField with a width of 10 characters, and a button guessButton with the label "Guess".
// The label, text field, and button are added to the topPanel in the order they should appear.
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel guessLabel = new JLabel("Enter your guess:");
        guessTextField = new JTextField(10);
        guessButton = new JButton("Guess");
        topPanel.add(guessLabel);
        topPanel.add(guessTextField);
        topPanel.add(guessButton);
        add(topPanel, BorderLayout.NORTH);

        
//An action listener is added to the guessButton using an anonymous inner class.
//When the button is clicked, the makeGuess() method is called.
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });  

        initializeGame();
    }

    
//This method initializes the game by generating a random number between 1 and 100 (inclusive) and displaying a 
//        welcome message using JOptionPane.
//The number of attempts is set to 0.
    private void initializeGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        JOptionPane.showMessageDialog(this, "Welcome to the Number Guessing Game!\n"
                + "I have selected a random number between 1 and 100.\n"
                + "Try to guess the number within 10 attempts.");
    }

    
//This method is called when the user clicks the guess button.
//It retrieves the guess entered by the user from the guessTextField and converts it to an integer using Integer.parseInt().
//The text field is then cleared.
//The method increments the number of attempts.
//It compares the guess with the randomly generated number and displays a message using JOptionPane based on the result.
//If the guess is correct, a congratulatory message is shown with the number of attempts made. The input is disabled after a 
    //correct guess.
//If the guess is too low, a message is displayed to try a higher number.
//If the guess is too high, a message is displayed to try a lower number.
//If the number of attempts exceeds 10, a message is shown that the user didn't guess the number within the allowed attempts, 
    //along with the correct number. The input is disabled after 10 attempts.
    private void makeGuess() {
        int guess = Integer.parseInt(guessTextField.getText());
        guessTextField.setText("");
        attempts++;

        if (guess == randomNumber) {
            JOptionPane.showMessageDialog(this, "Congratulations! You guessed the correct number.\n"
                    + "You took " + attempts + " attempts to guess the number.");
            disableInput();
        } else if (guess < randomNumber) {
            JOptionPane.showMessageDialog(this, "Too low! Try a higher number.");
        } else {
            JOptionPane.showMessageDialog(this, "Too high! Try a lower number.");
        }

        if (attempts >= 10) {
            JOptionPane.showMessageDialog(this, "Sorry, you did not guess the number within 10 attempts.\n"
                    + "The number was: " + randomNumber);
            disableInput();
        }
    }

    
//This method is called to disable the input components (guessTextField and guessButton) when the game ends.
    private void disableInput() {
        guessTextField.setEnabled(false);
        guessButton.setEnabled(false);
    }

    
//The main method is the entry point of the application.
//It creates an instance of NumberGuessingGameGUI, sets it visible, and centers it on the screen using 
    //SwingUtilities.invokeLater() and setLocationRelativeTo(null)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NumberGuessingGameGUI game = new NumberGuessingGameGUI();
                game.setVisible(true);
                game.setLocationRelativeTo(null); // Center the frame
            }
        });
    }
}
