import java.util.Scanner;
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


    }

    public static String readFile() {
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
        String fileData = readFile();
        System.out.println(fileData);
        // System.out.println("Enter username");
        // String userName = scan.nextLine();
        // System.out.println("Username is: " + userName);
    }
}
