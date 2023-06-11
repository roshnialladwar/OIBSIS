

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//The Library_ManGUI class is defined, extending the JFrame class. 
//        This class represents the main GUI window for the library management system.
public class Library_ManGUI extends JFrame {
    
//usernameField and passwordField: Text fields for entering the username and password.
//outputArea: A text area for displaying output messages.
//loginButton and exitButton: Buttons for login and exiting the application.
//books: An array to store book titles.
//bookCount: A variable to track the number of books.
//members: An array to store member names.
//memberCount: A variable to track the number of members.
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea outputArea;
    private JButton loginButton;
    private JButton exitButton;
    
    private String[] books = new String[100];
    private int bookCount = 0;
    private String[] members = new String[100]; // Array to store members
    private int memberCount = 0; // Variable to track the number of members


//The constructor Library_ManGUI() is defined. It sets up the main GUI window by setting its title, size, and layout. 
//        It also creates the necessary panels and components, adds event listeners to the buttons, and sets the content pane.
    public Library_ManGUI() {
        super("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(exitButton);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Output panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Event listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                performLogin(username, password);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setContentPane(mainPanel);
    }
    

//The performLogin() method is called when the login button is clicked. 
//It takes the entered username and password as parameters and checks if they match the predefined admin or user credentials. 
//If the login is successful, it calls either the showAdminMenu() or showUserMenu() method.
    private void performLogin(String username, String password) {
        if (username.equals("admin") && password.equals("admin123")) 
        {
            // Show admin menu
            showAdminMenu();
        } 
        else if(username.equals("user") && password.equals("user123"))
        {
            // Show user menu
            showUserMenu();
        }
    }

    
//The showAdminMenu() method is called when an admin logs in. 
//It removes the current content pane, creates a new panel for the admin menu, 
//adds buttons for different administrative operations (such as adding/deleting books and members), and sets the content pane. 
//Event listeners are added to the admin menu buttons to perform the corresponding actions.
    private void showAdminMenu() 
    {
        remove(getContentPane());

        JPanel adminPanel = new JPanel(new BorderLayout());

        // Admin menu panel
        JPanel menuPanel = new JPanel(new GridLayout(5, 1));
        JButton addBookButton = new JButton("Add Book");
        JButton deleteBookButton = new JButton("Delete Book");
        JButton addMemberButton = new JButton("Add Member");
        JButton deleteMemberButton = new JButton("Delete Member");
        JButton logoutButton = new JButton("Logout");

        menuPanel.add(addBookButton);
        menuPanel.add(deleteBookButton);
        menuPanel.add(addMemberButton);
        menuPanel.add(deleteMemberButton);
        menuPanel.add(logoutButton);

        adminPanel.add(menuPanel, BorderLayout.CENTER);

        // Output panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        adminPanel.add(scrollPane, BorderLayout.SOUTH);

        // Event listeners
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(adminPanel, "Enter book title:");
                if (bookTitle != null && !bookTitle.isEmpty()) {
                    // Check if the book already exists
                    boolean bookExists = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].equals(bookTitle)) {
                            bookExists = true;
                            break;
                        }
                    }
                    if (!bookExists) {
                        // Add the book
                        books[bookCount] = bookTitle;
                        bookCount++;
                        JOptionPane.showMessageDialog(adminPanel, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(adminPanel, "Book already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(adminPanel, "Invalid book title!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookToDelete = JOptionPane.showInputDialog(adminPanel, "Enter book title to delete:");
                if (bookToDelete != null && !bookToDelete.isEmpty()) {
                    boolean bookFound = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].equals(bookToDelete)) {
                            bookFound = true;
                            for (int j = i; j < bookCount - 1; j++) {
                                books[j] = books[j + 1];
                            }
                            bookCount--;
                            JOptionPane.showMessageDialog(adminPanel, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!bookFound) {
                        JOptionPane.showMessageDialog(adminPanel, "Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(adminPanel, "Invalid book title!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberName = JOptionPane.showInputDialog(adminPanel, "Enter member name:");
                if (memberName != null && !memberName.isEmpty()) {
                    members[memberCount] = memberName;
                    memberCount++;
                    JOptionPane.showMessageDialog(adminPanel, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(adminPanel, "Invalid member name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }); 



        deleteMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberToDelete = JOptionPane.showInputDialog(adminPanel, "Enter member name to delete:");
                if (memberToDelete != null && !memberToDelete.isEmpty()) {
                    boolean memberFound = false;
                    for (int i = 0; i < memberCount; i++) {
                        if (members[i].equals(memberToDelete)) {
                            memberFound = true;
                            for (int j = i; j < memberCount - 1; j++) {
                                members[j] = members[j + 1];
                            }
                            memberCount--;
                            JOptionPane.showMessageDialog(adminPanel, "Member deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!memberFound) {
                        JOptionPane.showMessageDialog(adminPanel, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(adminPanel, "Invalid member name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
  


        logoutButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Show login panel
                showLoginPanel();
            }
        });

        setContentPane(adminPanel);
        revalidate();
        
    }

    
//The showUserMenu() method is called when a user logs in. 
//It removes the current content pane, creates a new panel for the user menu, 
//adds buttons for various user operations (such as viewing books, searching books, issuing/returning books), 
//and sets the content pane. Event listeners are added to the user menu buttons to perform the corresponding actions.
    private void showUserMenu() {
        getContentPane().removeAll();

        JPanel userPanel = new JPanel(new BorderLayout());

        // User menu panel
        JPanel menuPanel = new JPanel(new GridLayout(6, 1));
        JButton viewBooksButton = new JButton("View Books");
        JButton searchBookButton = new JButton("Search Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton issueBookButton = new JButton("Issue Book");
        JButton contactLibraryButton = new JButton("Contact Library");
        JButton logoutButton = new JButton("Logout");

        menuPanel.add(viewBooksButton);
        menuPanel.add(searchBookButton);
        menuPanel.add(returnBookButton);
        menuPanel.add(issueBookButton);
        menuPanel.add(contactLibraryButton);
        menuPanel.add(logoutButton);

        userPanel.add(menuPanel, BorderLayout.CENTER);

        // Output panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        userPanel.add(scrollPane, BorderLayout.SOUTH);

        // Event listeners
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder message = new StringBuilder();
                message.append("Books available in the library:\n");
                if (bookCount == 0) {
                    message.append("No books available!");
                } else {
                    for (int i = 0; i < bookCount; i++) {
                        message.append(books[i]).append("\n");
                    }
                }

                JOptionPane.showMessageDialog(null, message.toString(), "Available Books", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookToSearch = JOptionPane.showInputDialog(userPanel, "Enter book title to search:");
                if (bookToSearch != null && !bookToSearch.isEmpty()) {
                    boolean bookFound = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].equals(bookToSearch)) {
                            bookFound = true;
                            JOptionPane.showMessageDialog(userPanel, "Book found!", "Book Search", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!bookFound) {
                        JOptionPane.showMessageDialog(userPanel, "Book not found!", "Book Search", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(userPanel, "Invalid book title!", "Book Search", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookToReturn = JOptionPane.showInputDialog(userPanel, "Enter book title to return:");
                if (bookToReturn != null && !bookToReturn.isEmpty()) {
                    boolean bookReturned = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].equals(bookToReturn)) {
                            bookReturned = true;
                            JOptionPane.showMessageDialog(userPanel, "Book returned successfully!", "Return Book", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!bookReturned) {
                        JOptionPane.showMessageDialog(userPanel, "Book not found!", "Return Book", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(userPanel, "Invalid book title!", "Return Book", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookToIssue = JOptionPane.showInputDialog(userPanel, "Enter book title to issue:");
                if (bookToIssue != null && !bookToIssue.isEmpty()) {
                    boolean bookIssued = false;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].equals(bookToIssue)) {
                            bookIssued = true;
                            JOptionPane.showMessageDialog(userPanel, "Book issued successfully!", "Issue Book", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    if (!bookIssued) {
                        JOptionPane.showMessageDialog(userPanel, "Book not found!", "Issue Book", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(userPanel, "Invalid book title!", "Issue Book", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        contactLibraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = JOptionPane.showInputDialog(userPanel, "Enter your email:");
                if (userEmail != null && !userEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(userPanel, "Sending Email... Please wait.", "Contact Library", JOptionPane.INFORMATION_MESSAGE);

                    // Code for sending an email to the library using the user's email

                    JOptionPane.showMessageDialog(userPanel, "Email sent successfully!", "Contact Library", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(userPanel, "Invalid email!", "Contact Library", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show login panel
                showLoginPanel();
            }
        });

        setContentPane(userPanel);
        revalidate();
        
    }

    
//The showLoginPanel() method is called to display the login panel. 
//It removes the current content pane, creates a new panel for the login screen, 
//adds the necessary components, and sets the content pane. Event listeners are added to the login and exit buttons.
    private void showLoginPanel() {
        getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(exitButton);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Output panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                performLogin(username, password);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setContentPane(mainPanel);
        revalidate();

    }

    
//The main() method creates an instance of the Library_ManGUI class and makes it visible, starting the application.
    public static void main(String[] args) {
        Library_ManGUI libraryGUI = new Library_ManGUI();
        libraryGUI.setVisible(true);
    }
}
