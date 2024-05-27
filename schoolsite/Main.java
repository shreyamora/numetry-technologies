package schoolSite;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static final Map<String, Map<String, String>> studentData = new HashMap<>();
    private static final Map<String, Map<String, String>> staffData = new HashMap<>();
    private static final Map<String, Map<String, String>> parentData = new HashMap<>();
    private static final Map<String, LocalDate> studentAttendance = new HashMap<>();
    private static final Map<String, LocalDate> staffAttendance = new HashMap<>();
    private static final Map<String, Double> feeStructure = new HashMap<>();
    private static final Map<LocalDate, String> notices = new TreeMap<>();
    private static final Map<String, String> queries = new HashMap<>();
    private static final Map<String, String> answeredQueries = new HashMap<>();

    public static void main(String[] args) 
    {
    	Map<String, String> student1 = new HashMap<>();
        student1.put("name", "Krishna");
        student1.put("rollNumber", "101");
        student1.put("password", "1234");
        studentData.put("krishna123", student1);

        Map<String, String> student2 = new HashMap<>();
        student2.put("name", "Radha");
        student2.put("rollNumber", "102");
        student2.put("password", "1234");
        studentData.put("radha123", student2);

        Map<String,String> parent1 = new HashMap<>();
        parent1.put("name", "ashok");
        parent1.put("password", "1234");
        parentData.put("ashok123", parent1);

        Map<String, String> parent2 = new HashMap<>();
        parent2.put("name", "navya");
        parent2.put("password", "1234");
        parentData.put("navya123", parent2);

        
        Map<String, String> staff1 = new HashMap<>();
        staff1.put("name", "Shreya");
        staff1.put("id", "S001");
        staff1.put("role", "Teacher");
        staff1.put("password", "1234");
        staffData.put("shreya123", staff1);

        Map<String, String> staff2 = new HashMap<>();
        staff2.put("name", "Virat");
        staff2.put("id", "S002");
        staff2.put("role", "Teacher");
        staff2.put("password", "1234");
        staffData.put("virat123", staff2);

        
        studentAttendance.put("krishna123", LocalDate.now().minusDays(1));
        studentAttendance.put("radha123", LocalDate.now());

        staffAttendance.put("shreya123", LocalDate.now().minusDays(1));
        staffAttendance.put("virat123", LocalDate.now());

        String adminUsername = "admin";
        String adminPassword = "admin";
        
        feeStructure.put("Tuition Fee", 15000.0);
        feeStructure.put("Library Fee", 500.0);
        feeStructure.put("Examination Fee", 700.0);

        Scanner scanner = new Scanner(System.in);
        boolean continueToMenu = true;

        while (continueToMenu) 
        {
            System.out.println("1. Student Login");
            System.out.println("2. Staff Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Parent Login");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentLogin(scanner);
                    break;
                case 2:
                    staffLogin(scanner);
                    break;
                case 3:
                    adminLogin(adminUsername, adminPassword, scanner);
                    break;
                case 4:
                    parentLogin(scanner);
                case 5:
                    continueToMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
    private static void parentLogin(Scanner scanner){
        System.out.println("\nParent Login");
        System.out.println("1. Have an account");
        System.out.println("2. Register");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.print("Enter parent username: ");
                String username = scanner.nextLine();
                System.out.print("Enter parent password: ");
                String password = scanner.nextLine();

                if (parentData.containsKey(username)) {
                    if (parentData.get(username).get("password").equals(password)) {
                        System.out.println("\nParent login successful.");
                        parentMenu(username, scanner);
                    } else {
                        System.out.println("Incorrect password.");
                        System.out.println("1. Retry");
                        System.out.println("2. Forgot password");
                        System.out.print("Choose an option: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                parentLogin(scanner);
                                break;
                            case 2:
                                forgotPassword(username, parentData);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                } else {
                    System.out.println("User does not exist. Please try again.");
                    parentLogin(scanner);
                }
                break;
            case 2:
                registerParent(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;

        }
    }

    private static void registerParent(Scanner scanner) {
        System.out.println("\nRegister Parent");
        System.out.print("Enter parent username: ");
        String username = scanner.nextLine();
        System.out.print("Enter parent password: ");
        String password = scanner.nextLine();
        parentData.put(username, Map.of("name", username, "password", password));
        System.out.println("Parent registered successfully.");
    }

    private static void parentMenu(String username, Scanner scanner) {
        boolean continueToParentMenu = true;
        System.out.println("Welcome " + username + "!");
        while (continueToParentMenu) {
            System.out.println("\nParent Menu");
            System.out.println("1. Home ");
            System.out.println("2. School Details");
            System.out.println("3. Awards & Certifications");
            System.out.println("4. Colleges And University's");
            System.out.println("5. Reviews & Ratings");
            System.out.println("6. Contact Us");
            System.out.println("7. View Notice/Event");
            System.out.println("8. Support Chatbot");
            System.out.println("9. Career Guidance");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    displayHome();
                    break;
                case 2:
                    displaySchoolDetails();
                    break;
                case 3:
                    displayAwardsAndCertifications();
                    break;
                case 4:
                    displayCollegesAndUniversities();
                    break;
                case 5:
                    displayReviewsAndRatings();
                    break;
                case 6:
                    displayContactUs();
                    break;
                case 7:
                    displayNotices();
                    break;
                case 8:
                    displaychatbot();
                    break;
                case 9:
                    displayCareerGuidance();
                    break;
                case 10:
                    continueToParentMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displaychatbot() {
        JFrame frame = new JFrame("Chatbot");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JTextField textField = new JTextField();
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            String userInput = textField.getText().trim();
            textField.setText("");
            if (userInput.equalsIgnoreCase("exit")) {
                frame.dispose();
                return;
            }
            String response = getResponse(userInput);
            textArea.append("You: " + userInput + "\n");
            textArea.append("Bot: " + response + "\n");
        });

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.add(sendButton, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }

    public static String getResponse(String input) {
        input = input.toLowerCase();

        if (input.contains("school timings")) {
            return "School timings are from 8:00 AM to 3:00 PM, Monday to Friday.";
        } else if (input.contains("holidays")) {
            return "The school holidays include: \n- Winter Break: December 20th to January 2nd\n- Spring Break: March 15th to March 22nd\n- Summer Break: June 1st to August 31st";
        } else if (input.contains("contact details") || input.contains("contact information")) {
            return "You can contact us at: \nPhone: (123) 456-7890\nEmail: contact@school.edu";
        } else {
            return "I'm sorry, I can only help with questions about school timings, holidays, and contact details.";
        }
    }



    private static void displayHome() {
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new GridLayout(1, 3));

        // Create and add image panels
        frame.add(createImagePanel("home.jpg"));
        //frame.add(createImagePanel("image2.jpg"));
        //frame.add(createImagePanel("image3.jpg"));

        // Pack the components within the JFrame
        frame.pack();
        // Set the frame to be visible
        frame.setVisible(true);
    }

    private static void displayCareerGuidance() {
        System.out.println("\nCareer Guidance");
        System.out.println("1. Apply for Jobs");
        System.out.println("2. Internships");
        System.out.println("3. Placements");
    }

    private static void displayReviewsAndRatings() {
        System.out.println("\nReviews & Ratings");
        System.out.println("Rating: 4.5/5");
        System.out.println("Total Reviews: 100");

    }

    private static void displayCollegesAndUniversities() {
        System.out.println("\nColleges And University's");
        System.out.println("1. College A");
        System.out.println("2. University B");
        System.out.println("3. Institute C");

    }

    private static void displayAwardsAndCertifications() {
        JFrame frame = new JFrame("Awards & Certifications");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new GridLayout(1, 3));

        // Create and add image panels
        frame.add(createImagePanel("image3.jpg"));
        //frame.add(createImagePanel("image2.jpg"));
        //frame.add(createImagePanel("image3.jpg"));

        // Pack the components within the JFrame
        frame.pack();
        // Set the frame to be visible
        frame.setVisible(true);
    }

    private static JPanel createImagePanel(String imagePath) {
        // Create a new JPanel
        JPanel panel = new JPanel();
        // Set the layout manager for the panel
        panel.setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon(imagePath);
        // Create a JLabel with the ImageIcon
        JLabel label = new JLabel(imageIcon);

        // Add the JLabel to the JPanel
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }


    private static void displaySchoolDetails() {
        System.out.println("\nSchool Details");
        System.out.println("Location: XYZ Street");
        System.out.println("Established: 1990");
        System.out.println("Total Students: 1000");

    }

    private static void displayNotices() {
        System.out.println("\nNotices/Events");
        for (Map.Entry<LocalDate, String> entry : notices.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void displayStaffDetails() {
        System.out.println("\nStaff Details");
        for (Map.Entry<String, Map<String, String>> entry : staffData.entrySet()) {

            Map<String, String> details = entry.getValue();
            System.out.println("Name: " + details.get("name"));
            System.out.println();
        }
    }

    private static void studentLogin(Scanner scanner)
    {
        System.out.println("\nStudent Login");
        System.out.println("1. Have an account");
        System.out.println("2. Register");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) 
        {
            case 1:
                System.out.print("Enter student username: ");
                String username = scanner.nextLine();
                System.out.print("Enter student password: ");
                String password = scanner.nextLine();

                if (studentData.containsKey(username)) 
                {
                    if (studentData.get(username).get("password").equals(password)) 
                    {
                        System.out.println("\nStudent login successful.");
                        studentMenu(username, scanner);
                    } 
                    else 
                    {
                        System.out.println("Incorrect password.");
                        System.out.println("1. Retry");
                        System.out.println("2. Forgot password");
                        System.out.print("Choose an option: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                studentLogin(scanner);
                                break;
                            case 2:
                                forgotPassword(username, studentData);
                                break;
                            default:
                                System.out.println("Invalid choice. Exiting...");
                                break;
                        }
                    }
                } 
                else 
                {
                    System.out.println("User does not exist. Exiting...");
                }
                break;
            case 2:
                createStudentAccount(scanner);
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                break;
        }
    }

    private static void staffLogin(Scanner scanner) 
    {
        System.out.println("\nStaff Login");
        System.out.println("1. Have an account");
        System.out.println("2. Register");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) 
        {
            case 1:
                System.out.print("Enter staff username: ");
                String username = scanner.nextLine();
                System.out.print("Enter staff password: ");
                String password = scanner.nextLine();

                if (staffData.containsKey(username)) 
                {
                    Map<String, String> staffDetails = staffData.get(username);
                    if (staffDetails.get("password").equals(password)) 
                    {
                        String role = staffDetails.get("role");
                        System.out.println("\nStaff login successful. Role: " + role);
                        staffMenu(username, role, scanner);
                    } 
                    else 
                    {
                        System.out.println("Incorrect password.");
                        System.out.println("1. Retry");
                        System.out.println("2. Forgot password");
                        System.out.print("Choose an option: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) 
                        {
                            case 1:
                                staffLogin(scanner);
                                break;
                            case 2:
                                forgotPassword(username, staffData);
                                break;
                            default:
                                System.out.println("Invalid choice. Exiting...");
                                break;
                        }
                    }
                } 
                else 
                {
                    System.out.println("User does not exist. Exiting...");
                }
                break;
            case 2:
                createStaffAccount(scanner);
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                break;
        }
    }

    private static void forgotPassword(String username, Map<String, Map<String, String>> userData) 
    {
        if (userData.containsKey(username)) 
        {
            System.out.print("Enter a new password: ");
            Scanner scanner = new Scanner(System.in);
			String newPassword = scanner .nextLine();
            Map<String, String> userDetails = userData.get(username);
            userDetails.put("password", newPassword);
            userData.put(username, userDetails);
            System.out.println("Password updated successfully.");
        } 
        else 
        {
            System.out.println("User does not exist.");
        }
    }

    private static void adminLogin(String adminUsername, String adminPassword, Scanner scanner) 
    {
        System.out.print("Enter admin username: ");
        String username = scanner.next();
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        if (username.equals(adminUsername) && password.equals(adminPassword)) 
        {
            System.out.println("Admin login successful.");
            adminMenu(scanner);
        } 
        else 
        {
            System.out.println("Incorrect username or password. Exiting...");
        }
    }

    private static void adminMenu(Scanner scanner) 
    {
        boolean continueToAdminMenu = true;

        while (continueToAdminMenu) 
        {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View all staff details");
            System.out.println("2. View all student details");
            System.out.println("3. View attendance");
            System.out.println("4. Add notice/event for staff");
            System.out.println("5. Edit fee structure");
            System.out.println("6. Answer Queries");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1:
                    viewStaffDetails();
                    break;
                case 2:
                    viewStudentDetails();
                    break;
                case 3:
                    viewAttendanceMenu(scanner);
                    break;
                case 4:
                    addNoticeEvent(scanner);
                    break;
                case 5:
                    editFeeStructure(scanner);
                    break;
                case 6:
                    answerQueries(scanner);
                    break;
                case 7:
                    continueToAdminMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    private static void viewAnsweredQueries(String username, String userType) 
    {
        System.out.println("Answered Queries:");
        if (userType.equals("student")) 
        {
            if (answeredQueries.containsKey(username)) {
                System.out.println("Your Query: " + queries.get(username));
                System.out.println("Answer: " + answeredQueries.get(username));
            } 
            else 
            {
                System.out.println("You have not raised any queries or your queries have not been answered yet.");
            }
        } 
        else if (userType.equals("staff")) 
        {
            for (Map.Entry<String, String> entry : answeredQueries.entrySet()) 
            {
                System.out.println("Username: " + entry.getKey());
                System.out.println("Query: " + queries.get(entry.getKey()));
                System.out.println("Answer: " + entry.getValue());
                System.out.println();
            }
        }
    }
    
    private static void answerQueries(Scanner scanner) 
    {
        System.out.println("List of Queries:");
        for (Map.Entry<String, String> entry : queries.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Enter username of a query to answer:");
        String username = scanner.nextLine();
        if (queries.containsKey(username)) 
        {
            System.out.println("Enter your answer:");
            String answer = scanner.nextLine();
            answeredQueries.put(username, answer);
            System.out.println("Query answered successfully.");
        } 
        else {
            System.out.println("Query not found.");
        }
    }
    
    private static void editFeeStructure(Scanner scanner) 
    {
        boolean continueEditing = true;

        while (continueEditing) 
        {
            System.out.println("\nCurrent Fee Structure:");
            for (Map.Entry<String, Double> entry : feeStructure.entrySet()) 
            {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("\nEdit Fee Structure:");
            System.out.println("1. Edit an existing fee item");
            System.out.println("2. Add a new fee item");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) 
            {
                case 1:
                    editExistingFeeItem(scanner);
                    break;
                case 2:
                    addNewFeeItem(scanner);
                    break;
                case 3:
                    continueEditing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    
    private static void addNoticeEvent(Scanner scanner) 
    {
        System.out.println("Enter the date of the event or notice (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter the role of staff (or type 'all' to show to all staff):");
        String role = scanner.nextLine();

        System.out.println("Enter the description of the notice/event:");
        String description = scanner.nextLine();

        notices.put(date, role + ": " + description);
        System.out.println("Notice/Event added successfully.");
    }

    private static void editExistingFeeItem(Scanner scanner) 
    {
        System.out.print("Enter the fee item to edit (or type 'exit' to go back): ");
        String feeItem = scanner.nextLine();

        if (feeItem.equalsIgnoreCase("exit")) 
        {
            return;
        }

        if (!feeStructure.containsKey(feeItem)) 
        {
            System.out.println("Invalid fee item. Please try again.");
            return;
        }

        System.out.print("Enter the new fee amount: ");
        double newFeeAmount = scanner.nextDouble();
        scanner.nextLine();

        feeStructure.put(feeItem, newFeeAmount);
        System.out.println("Fee structure updated successfully.");
    }

    private static void addNewFeeItem(Scanner scanner) 
    {
        System.out.print("Enter the new fee item: ");
        String newFeeItem = scanner.nextLine();

        if (feeStructure.containsKey(newFeeItem)) 
        {
            System.out.println("Fee item already exists. Please enter a different fee item.");
            return;
        }

        System.out.print("Enter the fee amount for " + newFeeItem + ": ");
        double feeAmount = scanner.nextDouble();
        scanner.nextLine();

        feeStructure.put(newFeeItem, feeAmount);
        System.out.println("New fee item added successfully.");
    }

    private static void studentMenu(String username, Scanner scanner) {
        boolean continueToStudentMenu = true;

        while (continueToStudentMenu) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. About");
            System.out.println("2. Contact Us");
            System.out.println("3. Teaching Staff");
            //System.out.println("4. Give Attendance");
            System.out.println("4. View Fee Structure");
            System.out.println("5. Raise Query");
            System.out.println("6. View Answered Queries");
            System.out.println("6. Logout");
            System.out.println("8. Go back to the main menu");
            System.out.print("Choose an option: ");

            int studentChoice = scanner.nextInt();
            scanner.nextLine();

            switch (studentChoice) {
                case 1:
                    displayAbout();
                    break;
                case 2:
                    displayContactUs();
                    break;
                case 3:
                    displayTeachingStaff();
                    break;
               /* case 4:
                    giveStudentAttendance(username);
                    break;*/
                case 4:
                    viewFeeStructure();
                    break;
                case 5:
                    raiseQuery(username, "student", scanner);
                    break;
                case 6:
                    viewAnsweredQueries(username, "student");
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                case 8:
                    continueToStudentMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    private static void displayAbout() 
    {
    	System.out.println("About Us:");
        System.out.println("School Name: MS School");
        System.out.println("Location: Hyderabad, India");
        System.out.println("Mission: To provide quality education to all students.");
        System.out.println("Vision: To become a leading educational institution in the region.");
        System.out.println("Values: Excellence, Integrity, Diversity");
        System.out.println("Facilities: Library, Science Lab, Sports Complex");
        System.out.println("Achievements: Winner of Best School Award 2023");
        System.out.println("Contact: helpline@msschool.com");
        System.out.println();
    }

    private static void displayContactUs() {
        JFrame frame = new JFrame("Contact Us");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create an ImageIcon with the path to your background image
        //ImageIcon backgroundIcon = new ImageIcon("contactus.jpg");

        // Create a JLabel with the ImageIcon as its icon
        //JLabel backgroundLabel = new JLabel(backgroundIcon);
        //backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());

        // Set the JLabel as the content pane of the JFrame
        //frame.setContentPane(backgroundLabel);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel queryLabel = new JLabel("Query:");
        JTextArea queryField = new JTextArea(5, 20);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(queryLabel);
        panel.add(queryField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String query = queryField.getText();
            // Process the form data here
            System.out.println("Name: " + name);
            System.out.println("Query: " + query);
            // You can save the data to a file or send it to a server
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private static void displayTeachingStaff() 
    {
    	System.out.println("Teaching Staff:");
        System.out.println("1. Mathematics - Shreya Mora");
        System.out.println("2. Science - Mis. Javed");
        System.out.println("3. English - Marshal pirera");
        System.out.println("4. History - Ravi Lethal");
        System.out.println("5. Geography - Raju Rasterio");
        System.out.println("6. Computer Science - Sarah Lee");
        System.out.println("7. Physical Education - James Taylor");
        System.out.println();
    }
    
    private static void viewFeeStructure() 
    {
        System.out.println("\nFee Structure:");
        for (Map.Entry<String, Double> entry : feeStructure.entrySet()) 
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    private static void staffMenu(String username, String role, Scanner scanner) 
    {
        boolean continueToStaffMenu = true;

        while (continueToStaffMenu) {
            System.out.println("\nStaff Menu:");
            System.out.println("1. Give Attendance");
            System.out.println("2. Take Attendance");
            System.out.println("3. View Notices/Events");
            System.out.println("4. Raise Query");
            System.out.println("5. View Answered Queries");
            System.out.println("6. view Student Attendance");
            System.out.println("7. Go back to the main menu");
            System.out.print("Choose an option: ");

            int staffChoice = scanner.nextInt();
            scanner.nextLine();

            switch (staffChoice) 
            {
                case 1:
                    giveStaffAttendance(username);
                    break;
                case 2:
                	takeAttendance(scanner, role);
                    break;
                case 3:
                    viewNotices(role);
                    break;
                case 4:
                    raiseQuery(username, "staff", scanner);
                    break;
                case 5:
                    viewAnsweredQueries(username, "staff");
                    break;
                case 6:
                    viewStudentAttendance();
                    break;
                case 7:
                    continueToStaffMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    private static void takeAttendance(Scanner scanner, String role) 
    {
        if (role.equals("Teacher")) 
        { 
            System.out.print("Enter the roll number of the student: ");
            String rollNumber = scanner.nextLine();

            LocalDateTime now = LocalDateTime.now();
            LocalTime time = now.toLocalTime();
            LocalDate date = now.toLocalDate();

            if (time.isBefore(LocalTime.of(10, 0)) || time.isAfter(LocalTime.of(17, 0))) 
            {
                System.out.println("Attendance cannot be taken at this time.");
                return;
            }

            String username = ""; 

          
            for (Map.Entry<String, Map<String, String>> entry : studentData.entrySet()) 
            {
                Map<String, String> studentDetails = entry.getValue();
                if (studentDetails.get("rollNumber").equals(rollNumber)) 
                {
                    username = entry.getKey(); 
                    break;
                }
            }

            if (username.isEmpty()) 
            { 
                System.out.println("Student not found.");
                return;
            }

            if (!studentAttendance.containsKey(username) || !studentAttendance.get(username).equals(date)) 
            {
                studentAttendance.put(username, date);
                System.out.println("Attendance is taken successfully for today.");
            } 
            else 
            {
                System.out.println("Attendance is already taken for today.");
            }
        } 
        else 
        {
            System.out.println("You do not have permission to take attendance.");
        }
    }
    
    private static void raiseQuery(String username, String userType, Scanner scanner) 
    {
        System.out.println("Enter your query:");
        String query = scanner.nextLine();
        queries.put(username, query);
        System.out.println("Query raised successfully.");
    }

    
    private static void viewNotices(String staffRole) 
    {
        System.out.println("Notices/Events:");

        notices.entrySet().stream()
                .filter(entry -> entry.getKey().isAfter(LocalDate.now())) 
                .filter(entry -> {
                    String[] parts = entry.getValue().split(":");
                    String role = parts[0].trim();
                    return role.equalsIgnoreCase("all") || role.equalsIgnoreCase(staffRole);
                })
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    private static void viewStaffDetails() 
    {
        System.out.println("\nStaff Details:");
        for (Map.Entry<String, Map<String, String>> entry : staffData.entrySet()) 
        {
            System.out.println("Username: " + entry.getKey());
            Map<String, String> details = entry.getValue();
            System.out.println("Name: " + details.get("name"));
            System.out.println("ID: " + details.get("id"));
            System.out.println("Role: " + details.get("role"));
            System.out.println();
        }
    }

    private static void viewStudentDetails() 
    {
        System.out.println("\nStudent Details:");
        for (Map.Entry<String, Map<String, String>> entry : studentData.entrySet()) 
        {
            System.out.println("Username: " + entry.getKey());
            Map<String, String> details = entry.getValue();
            System.out.println("Name: " + details.get("name"));
            System.out.println("Roll Number: " + details.get("rollNumber"));
            System.out.println();
        }
    }

    private static void viewAttendanceMenu(Scanner scanner) 
    {
        boolean continueToAttendanceMenu = true;

        while (continueToAttendanceMenu) 
        {
            System.out.println("\nAttendance Menu:");
            System.out.println("1. View staff attendance");
            System.out.println("2. View student attendance");
            System.out.println("3. Go back to the admin menu");
            System.out.print("Choose an option: ");

            int attendanceChoice = scanner.nextInt();
            scanner.nextLine();

            switch (attendanceChoice) 
            {
                case 1:
                    viewStaffAttendance();
                    break;
                case 2:
                    viewStudentAttendance();
                    break;
                case 3:
                    continueToAttendanceMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewStaffAttendance() 
    {
        System.out.println("\nStaff Attendance:");
        for (Map.Entry<String, LocalDate> entry : staffAttendance.entrySet()) 
        {
            String username = entry.getKey();
            LocalDate date = entry.getValue();
            Map<String, String> staffDetails = staffData.get(username);

            System.out.println("Name: " + staffDetails.get("name"));
            System.out.println("ID: " + staffDetails.get("id"));
            System.out.println("Role: " + staffDetails.get("role"));
            System.out.println("Date: " + date);
            System.out.println();
        }
    }

    private static void viewStudentAttendance() 
    {
        System.out.println("\nStudent Attendance:");
        for (Map.Entry<String, LocalDate> entry : studentAttendance.entrySet()) 
        {
            String username = entry.getKey();
            LocalDate date = entry.getValue();
            Map<String, String> studentDetails = studentData.get(username);

            if (studentDetails != null) {
                System.out.println("Name: " + studentDetails.get("name"));
                System.out.println("Roll Number: " + studentDetails.get("rollNumber"));
                System.out.println("Date: " + date);
                System.out.println();
            } 
            else 
            {
                System.out.println("Student details aren't found for username: " + username);
            }
        }
    }

    private static void createStudentAccount(Scanner scanner) 
    {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter student username: ");
        String username = scanner.nextLine();
        if(studentData.containsKey(username)) 
        {
            System.out.println("Username already exists. Please choose another username.");
            return;
        }
        System.out.print("Enter student password: ");
        String password = scanner.nextLine();

        Map<String, String> studentDetails = new HashMap<>();
        studentDetails.put("name", name);
        studentDetails.put("rollNumber", rollNumber);
        studentDetails.put("password", password);

        studentData.put(username, studentDetails);
        System.out.println("Student account created successfully.");
    }

    private static void createStaffAccount(Scanner scanner) 
    {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter a staff role: ");
        String role = scanner.nextLine();
        System.out.print("Enter staff username: ");
        String username = scanner.nextLine();
        if(staffData.containsKey(username)) 
        {
            System.out.println("Username already exists. Please choose another username.");
            return;
        }
        System.out.print("Enter staff password: ");
        String password = scanner.nextLine();

        Map<String, String> staffDetails = new HashMap<>();
        staffDetails.put("name", name);
        staffDetails.put("id", id);
        staffDetails.put("role", role);
        staffDetails.put("password", password);

        staffData.put(username, staffDetails);
        System.out.println("Staff account created successfully.");
    }

    private static void giveStudentAttendance(String username) 
    {
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = now.toLocalTime();
        LocalDate date = now.toLocalDate();
        if (time.isBefore(LocalTime.of(10, 0)) || time.isAfter(LocalTime.of(18, 0))) 
        {
            System.out.println("Attendance cannot be given at this time.");
            return;
        }

        if (!studentAttendance.containsKey(username) || !studentAttendance.get(username).equals(date)) {
            studentAttendance.put(username, date);
            System.out.println("Attendance given successfully for today.");
        } 
        else 
        {
            System.out.println("Attendance is already given for today.");
        }
    }

    private static void giveStaffAttendance(String username) 
    {
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = now.toLocalTime();
        LocalDate date = now.toLocalDate();
        if (time.isBefore(LocalTime.of(10, 0)) || time.isAfter(LocalTime.of(18, 0))) 
        {
            System.out.println("Attendance cannot be given at this time.");
            return;
        }

        if (!staffAttendance.containsKey(username) || !staffAttendance.get(username).equals(date)) {
            staffAttendance.put(username, date);
            System.out.println("Attendance given successfully for today.");
        } 
        else 
        {
            System.out.println("Attendance is already given for today.");
        }
    }
}
