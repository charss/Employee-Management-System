import java.util.*;
import java.io.*;

// Employee Management System
// operators , control structures and arrays , object oriented programming , encapsulation and inheritance , polymorphism and abstraction , exception , collections, input and output stream , swing api , threads

public class App {

    public static class Employee {
        private int employeeID;
        private String name;
        private String employmentStatus;
        private String startingDate;
        private String position;

        public Employee(int id, String name, String status, String date, String position) {
            this.employeeID = id;
            this.name = name;
            this.employmentStatus = status;
            this.startingDate = date;
            this.position = position;
        }
    }

    public static void checkEmployeeList(FileReader employeeFile, String checkStatus) {
        String id, name, status, date, position;
        int temp = 1;
        id = name = status = date = position = "";
        Scanner sc = new Scanner(employeeFile);
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", "ID", "NAME", "STATUS", "DATE STARTED", "POSITION");
        System.out.println("-------------------------------------------------------------------------------------------------");
        while (sc.hasNextLine()) {
            // to store data from file depending on the file
            if (temp == 1) {
                id = sc.nextLine();
            } else if (temp == 2) {
                name = sc.nextLine();
            } else if (temp == 3) {
                status = sc.nextLine();
            } else if (temp == 4) {
                date = sc.nextLine();
            } else if (temp == 5) {
                position = sc.nextLine();
            }
            if (temp == 5) {
                if (status.equals(checkStatus) || checkStatus.equals("None")) { // Check if function is called for all, active, or inactive
                    System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", id, name, status, date, position); // table look formatting
                }
                temp = 0;
            }
            temp++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        sc.close();
    }

    public static void updateFile() {

    }

    public static void addToFile() {

    }

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        FileReader employeeFile = null;
        try {
            employeeFile = new FileReader("./src/employee.txt"); // open file
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        while (true) {
            System.out.println("------------------------------");
            System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("[1] Check Complete Employee List");
            System.out.println("[2] Check Active Employee List");
            System.out.println("[3] Check Inactive Employee List");
            System.out.println("[4] Add New Employee");
            System.out.println("[5] Fire An Employee");
            System.out.println("[6] Update Employee Details");
            System.out.println("[0] Exit");
            
            System.out.print("-> Input: ");
            String user = scan.next();
            int answer;
            try {
                answer = Integer.parseInt(user);
            } catch (NumberFormatException e) {
                
                System.out.println("Invalid Input!");
                continue;
            }

            if (answer == 0) {
                System.out.println("Thank you for using the Employee Management System!!!");
                break;
            } else if (answer == 1) {
                System.out.println("### Complete Employee List ###");
                checkEmployeeList(employeeFile, "None");
            } else if (answer == 2) {
                System.out.println("### Active Employee List ###");
                checkEmployeeList(employeeFile, "Active");
            } else if (answer == 3) {
                System.out.println("### Inactive Employee List ###");
                checkEmployeeList(employeeFile, "Inactive");
            } else if (answer == 4) {

            } else if (answer == 5) {

            } else if (answer == 6) {

            }

 
            
            
        }
        scan.close();
        
        
        // scan.close();

    }
}
