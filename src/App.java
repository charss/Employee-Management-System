import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;  

// Employee Management System
// operators , control structures and arrays , object oriented programming , encapsulation and inheritance , polymorphism and abstraction , exception , collections, input and output stream , swing api , threads

public class App {

    public static class ClassEmployee {
        private int employeeID;
        private String name;
        private String employmentStatus;
        private String startingDate;
        private String position;

        public ClassEmployee() {
            this.employeeID = 0;
            this.name = "";
            this.employmentStatus = "";
            this.startingDate = "";
            this.position = "";
        }

        public void setID(int id) {
            this.employeeID = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStatus(String status) {
            this.employmentStatus = status;
        }

        public void setDate(String date) {
            this.startingDate = date;
        }

        public void setPosition(String position) {
            this.position = position;
        }
        
        public int getID() {
            return this.employeeID;
        }

        public String getName() {
            return this.name;
        }

        public String getStatus() {
            return this.employmentStatus;
        }

        public String getDate() {
            return this.startingDate;
        }

        public String getPosition() {
            return this.position;
        }
    }

    public static void checkEmployeeList(FileReader employeeFile, String checkStatus) {
        String id, name, status, date, position;
        int temp = 1;
        id = name = status = date = position = "";
        Scanner sc = new Scanner(employeeFile);
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", "ID", "NAME", "STATUS", "DATE STARTED", "POSITION");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
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
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        sc.close();
    }

    public static void updateFile() {

    }

    public static void addEmployee() {
        Scanner scan = new Scanner(System.in);
        ClassEmployee employee = new ClassEmployee();
        
        System.out.println("Add an Employee");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        Date date = new Date();  
        String stringDate = formatter.format(date);
        employee.setDate(stringDate);
        employee.setStatus("Active");
        while (true) {
            System.out.print("Enter employee's ID : ");
            try {
                int id = Integer.parseInt(scan.nextLine());
                employee.setID(id);

            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                continue;
            }
            System.out.print("Enter employee's name: ");
            employee.setName(scan.nextLine());
            System.out.print("Enter employee's position: ");
            employee.setPosition(scan.nextLine());
            break;
        }
        
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", "ID", "NAME", "STATUS", "DATE STARTED", "POSITION");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", employee.getID(), employee.getName(), employee.getStatus(), employee.getDate(), employee.getPosition());
        System.out.println("+-----------------------------------------------------------------------------------------------+");
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
            int answer;
            System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("[1] Check Complete Employee List");
            System.out.println("[2] Check Active Employee List");
            System.out.println("[3] Check Inactive Employee List");
            System.out.println("[4] Add New Employee");
            System.out.println("[5] Fire An Employee");
            System.out.println("[6] Update Employee Details");
            System.out.println("[0] Exit");
            
            
            System.out.print("-> Input: ");
            String user = scan.nextLine();
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

                addEmployee();
            } else if (answer == 5) {

            } else if (answer == 6) {

            }

 
            
            
        }
        
        

        scan.close();
    }
}
