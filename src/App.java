import java.util.*;
import java.io.*;

// Employee Management System
// operators , control structures and arrays , object oriented programming , encapsulation and inheritance , polymorphism and abstraction , exception , collections, input and output stream , swing api , threads

public class App {

    public static class Employee {
        private int employeeID;
        private String name;
        private boolean employmentStatus;
        private String startingDate;
        private String position;

        public Employee() {

        }
    }

    public static String employeeList() {
        String fileData = "";
        try {
            File employeeFile = new File("./src/employee.txt"); // open file
            Scanner fileReader = new Scanner(employeeFile); // scanner for reading content of files
            while (fileReader.hasNextLine()) {
                fileData += fileReader.nextLine() + "\n";
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        return fileData;
    } 

    public static void updateFile() {

    }

    public static void main(String[] args) throws Exception {

        // System.out.format("%32s%10d%16s", string1, int1, string2);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("------------------------------");
            System.out.println("Employee Management System");
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
                System.out.println("----------------------------------");
                System.out.print("### Complete Employee List ###")
                System.out.println("Invalid Input!");
                continue;
            }

            if (answer == 0) {
                System.out.println("Thank you for using the Employee Management System!!!");
                break;
            } else if (answer == 1) {
                String employees = employeeList();
                System.out.println(employees);
            } else if (answer == 2) {

            } else if (answer == 3) {

            } else if (answer == 4) {

            } else if (answer == 5) {

            } else if (answer == 6) {

            }

 
            
            
        }
        scan.close();

        // System.out.format("%32s%10d%16s", string1, int1, string2);
        
        // scan.close();

    }
}
