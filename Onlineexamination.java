import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


  public class Onlineexamination {  
    private static String username = "admin";
    private static String password = "password123";
    private static boolean examStarted = false;
    private static boolean autoSubmitted = false;

    public static boolean login(String user, String pass) {
        if (user.equals(username) && pass.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }

    public static void updatePassword(Scanner scanner) {
        System.out.print("Enter old password: ");
        String oldPass = scanner.nextLine();

        if (oldPass.equals(password)) {
            System.out.print("Enter new password: ");
            String newPass = scanner.nextLine();
            password = newPass;
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Incorrect old password. Try again.");
        }
    }

    public static void takeExam(Scanner scanner) {
        String[] questions = {
            "1. What is the capital of France?\n a) Paris\n b) Rome\n c) London",
            "2. What is 2 + 2?\n a) 3\n b) 4\n c) 5"
        };
        String[] answers = {"a", "b"};

        int score = 0;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (!examStarted) {
                    return;
                }
                autoSubmitted = true;
                System.out.println("\nTime is up! Auto-submitting your exam...");
                System.exit(0); // For auto submission, program terminates
            }
        }, 60000); 

        examStarted = true;
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Enter your answer: ");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(answers[i])) {
                score++;
            }
            if (autoSubmitted) {
                break;
            }
        }

        examStarted = false;
        System.out.println("Exam completed. Your score: " + score + "/" + questions.length);
        timer.cancel();
    }

    public static void logout() {
        System.out.println("Logging out... Goodbye!");
        System.exit(0); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;


        while (!isLoggedIn) {
            System.out.println("Welcome to Online Examination Portal");
            System.out.print("Enter Username: ");
            String user = scanner.nextLine();

            System.out.print("Enter Password: ");
            String pass = scanner.nextLine();

            isLoggedIn = login(user, pass);
        }

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Update Profile & Password");
            System.out.println("2. Start Exam (MCQs)");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    updatePassword(scanner);
                    break;
                case 2:
                    takeExam(scanner);
                    break;
                case 3:
                    logout();
                    isRunning = false; 
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
    }
}
    
