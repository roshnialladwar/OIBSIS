import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


//This class represents a user of the online examination system.
//It has private instance variables username, password, and profile.
//The constructor User initializes the user with a username, password, and profile.
//It provides getter and setter methods for accessing and modifying the user's information.
class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}


//This class represents a single question in the exam.
//It has private instance variables question, options, and correctAnswer.
//The constructor Question initializes the question with the question text, options, and the index of the correct answer.
//It provides getter methods to access the question, options, and correct answer.
class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}


//This class contains the main method where the online examination system is implemented.
public class OnlineExamination {


//The code defines two constant variables: EXAM_DURATION_MINUTES (the duration of the exam in minutes) 
//        and SECONDS_PER_MINUTE (the number of seconds in a minute).
    private static final int EXAM_DURATION_MINUTES = 5; // Exam duration in minutes
    private static final int SECONDS_PER_MINUTE = 60; // Number of seconds in a minute

    
//The main method is the entry point of the program.
//It creates a Scanner object to read user input.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
//The login method is responsible for authenticating the user.
//It prompts the user to enter their username and password.
//It compares the entered credentials with a set of predefined user records stored in the users array.
//If a match is found, the corresponding User object is returned; otherwise, it returns null.
        User currentUser = login(scanner);

        if (currentUser != null) {
            System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
            boolean loggedIn = true;

            
//After successful login, a menu is displayed to the user using the displayMenu method.
//The user is prompted to enter their choice.
            while (loggedIn) {
                displayMenu();

                
//The program uses a loop to keep the user logged in until they choose to close the session.
//Depending on the user's choice, different actions are performed:
//Option 1: Update Profile - The updateProfile method is called to allow the user to update their profile.
//Option 2: Update Password - The updatePassword method is called to allow the user to update their password.
//Option 3: Start Exam - The startExam method is called to initiate the exam.
//Option 4: Close Session and Logout - The loop is terminated, and the program displays a logout message.
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        updateProfile(scanner, currentUser);
                        break;
                    case 2:
                        updatePassword(scanner, currentUser);
                        break;
                    case 3:
                        startExam(scanner);
                        break;
                    case 4:
                        loggedIn = false;
                        System.out.println("Session closed. Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid username or password. Access denied.");
        }

        scanner.close();
    }

    

    private static User login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Simulating database records
        User[] users = {
            new User("user1", "password1", "Profile 1"),
            new User("user2", "password2", "Profile 2"),
            new User("user3", "password3", "Profile 3")
        };

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    

    private static void displayMenu() {
        System.out.println("\n========= Online Examination System =========");
        System.out.println("1. Update Profile");
        System.out.println("2. Update Password");
        System.out.println("3. Start Exam");
        System.out.println("4. Close Session and Logout");
        System.out.println("============================================");
    }

    
//The updateProfile method allows the user to update their profile.
//The current profile is displayed, and the user is prompted to enter a new profile.
//The user's profile is updated with the new value.
    private static void updateProfile(Scanner scanner, User user) {
        System.out.println("\nCurrent Profile: " + user.getProfile());
            System.out.print("Enter new profile: ");
    String newProfile = scanner.nextLine();
    user.setProfile(newProfile);
    System.out.println("Profile updated successfully.");
    }

    
//The updatePassword method allows the user to update their password.
//The current password is displayed, and the user is prompted to enter a new password.
//The user's password is updated with the new value.
    private static void updatePassword(Scanner scanner, User user) {
        System.out.println("\nCurrent Password: " + user.getPassword());

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        user.setPassword(newPassword);
        System.out.println("Password updated successfully.");
    }

    
//The startExam method initiates the exam.
//It displays the exam instructions and presents a set of questions stored in the questions array.
//A timer is started using the Timer and TimerTask classes to limit the exam duration.
//The user is prompted to answer each question, and their responses are evaluated.
//The score is calculated by comparing the user's answers with the correct answers.
//After all the questions are answered or the time is up, the exam evaluation is displayed.
    private static void startExam(Scanner scanner) {
        System.out.println("\n========= Exam =========");
        System.out.println("Instructions: Answer the following questions.");

        Question[] questions = {
            new Question("Question 1: What is the capital of France?", new String[]{"A. London", "B. Paris", "C. Rome", "D. Madrid"}, 1),
            new Question("Question 2: What is the currency of Japan?", new String[]{"A. Yen", "B. Euro", "C. Dollar", "D. Pound"}, 0),
            new Question("Question 3: Which planet is known as the Red Planet?", new String[]{"A. Mars", "B. Venus", "C. Jupiter", "D. Saturn"}, 0),
        };

        final int[] result = {0, questions.length}; // Array to hold score and totalQuestions

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int timeLeft = EXAM_DURATION_MINUTES * SECONDS_PER_MINUTE;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    System.out.println("Time Left: " + timeLeft / SECONDS_PER_MINUTE + " minutes " + timeLeft % SECONDS_PER_MINUTE + " seconds");
                    timeLeft--;
                } else {
                    System.out.println("Time's up! Submitting your answers...");
                    timer.cancel();
                    timer.purge();
                    evaluateExam(result[0], result[1]);
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000); // Run the timer task every second

        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            System.out.println("\n" + question.getQuestion());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            System.out.print("Enter your answer (A/B/C/D): ");
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals(String.valueOf((char) ('A' + question.getCorrectAnswer())))) {
                result[0]++; // Increment the score
            }
        }

        timer.cancel();
        timer.purge();

        evaluateExam(result[0], result[1]);
    }

    
//The evaluateExam method is called to display the exam completion message and the user's score.
    private static void evaluateExam(int score, int totalQuestions) {
        System.out.println("\nExam completed.");
        System.out.println("Your score: " + score + "/" + totalQuestions);
    }
}
