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

    public static String tryAgain(String sentence) {
        String ans = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(sentence);
            ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y") || ans.equals("N")) {
                break;
            }
        }
        return ans;
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


    public static void addEmployee(FileReader employeeFile) {
        Scanner scan = new Scanner(System.in);
        ClassEmployee employee = new ClassEmployee();
        ArrayList<ClassEmployee> arrayEmployees = getClassArray(employeeFile);
        ArrayList<Integer> arrayID = new ArrayList<Integer>(); // this will be used to check if current employee ID is already present
        for (int i = 0; i < arrayEmployees.size(); i++) {
            arrayID.add(arrayEmployees.get(i).getID());
        }

        System.out.println("Add an Employee");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        Date date = new Date();  
        String stringDate = formatter.format(date);
        employee.setDate(stringDate);
        employee.setStatus("Active");
        while (true) {
            System.out.print("Enter employee's ID (Type -1 to go back to main menu): ");
            try {
                int id = Integer.parseInt(scan.nextLine());
                if (id == -1) {
                    return;
                } else if (id <= 0) {
                    System.out.println("Invalid Input.");
                    continue;
                }
                employee.setID(id);
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input.");
                continue;
            }

            if (arrayID.contains(employee.getID())) {
                System.out.println("Employee ID already exist");
                continue;
            }

            while (employee.getName().equals("")) {
                System.out.print("Enter employee's name: ");
                employee.setName(scan.nextLine());
            }

            while (employee.getPosition().equals("")) {
                System.out.print("Enter employee's position: ");
                employee.setPosition(scan.nextLine());
            }
            
            System.out.println("+-----------------------------------------------------------------------------------------------+");
            System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", "ID", "NAME", "STATUS", "DATE STARTED", "POSITION");
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.format("|%6s|%30s|%10s|%15s|%30s|\n", employee.getID(), employee.getName(), employee.getStatus(), employee.getDate(), employee.getPosition());
            System.out.println("+-----------------------------------------------------------------------------------------------+");

            // String ans = "";
            String ans = tryAgain("Are the details correct? [Y/N]? "); // to check if the details of the new employee are correct
        
            if (ans.equals("Y")) {
                break;
            } else {
                String temp = tryAgain("Do you still want to add a new member [Y/N]? ");
                if (temp.equals("N")) {
                    return;
                }
            }
        }

        // appending new employee to the file
        try {
            FileWriter fw = new FileWriter("./src/employee.txt", true); // append the new data
            fw.write(employee.getID() + "\n"); 
            fw.write(employee.getName() + "\n"); 
            fw.write(employee.getStatus() + "\n"); 
            fw.write(employee.getDate() + "\n"); 
            fw.write(employee.getPosition() + "\n"); 
            fw.close();
        } catch(IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        
    }

    public static ArrayList<ClassEmployee> getClassArray(FileReader employeeFile) {
        Scanner sc = new Scanner(employeeFile);
        
        ArrayList<ClassEmployee> arrayEmployees = new ArrayList<ClassEmployee>(); // store employee IDs
        int temp = 1;
        String x;
        ClassEmployee employee = new ClassEmployee();
        while (sc.hasNextLine()) {
            // to store data from file depending on the file
            if (temp == 1) {
                x = sc.nextLine();
                employee.setID(Integer.parseInt(x));
            } else if (temp == 2) {
                employee.setName(sc.nextLine());
            } else if (temp == 3) {
                employee.setStatus(sc.nextLine());
            } else if (temp == 4) {
                employee.setDate(sc.nextLine());
            } else if (temp == 5) {
                employee.setPosition(sc.nextLine());
            }
            if (temp == 5) {
                arrayEmployees.add(employee);
                temp = 0;
                employee = new ClassEmployee();
            }
            temp++;
        }

        sc.close();
        return arrayEmployees;
    }

    public static void fireEmployee(FileReader employeeFile) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<ClassEmployee> arrayEmployees = getClassArray(employeeFile);
        ArrayList<Integer> arrayID = new ArrayList<Integer>(); // this will be used to check if current employee ID is already present
        int id;
        for (int i = 0; i < arrayEmployees.size(); i++) {
            if (arrayEmployees.get(i).getStatus().equals("Active")) {
                arrayID.add(arrayEmployees.get(i).getID());
            }
        }
        while (true) {
            System.out.print("Enter employee's ID (Type -1 to go back to main menu): ");
            try {
                id = Integer.parseInt(scan.nextLine());
                if (id == -1) {
                    return;
                } 
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                continue;
            }

            if (!arrayID.contains(id)) {
                System.out.println("Employee ID does not exist");
                continue;
            } 
            break;
        }



        // This is to remove data from file before overwriting
        PrintWriter writer;
        try {
            writer = new PrintWriter("./src/employee.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        
        FileWriter fw = null;
        try {
            fw = new FileWriter("./src/employee.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        } 


        for (int i = 0; i < arrayEmployees.size(); i++) {
            if (arrayEmployees.get(i).getID() == id) {
                System.out.println(arrayEmployees.get(i).getName() + ", you are fired!");
                arrayEmployees.get(i).setStatus("Inactive");
            }

            try {
                fw.write(arrayEmployees.get(i).getID() + "\n"); 
                fw.write(arrayEmployees.get(i).getName() + "\n"); 
                fw.write(arrayEmployees.get(i).getStatus() + "\n"); 
                fw.write(arrayEmployees.get(i).getDate() + "\n"); 
                fw.write(arrayEmployees.get(i).getPosition() + "\n"); 
            } catch(IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }

        fw.close();
    }

    public static void updatePosition(FileReader employeeFile) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<ClassEmployee> arrayEmployees = getClassArray(employeeFile);
        ArrayList<Integer> arrayID = new ArrayList<Integer>(); // this will be used to check if current employee ID is already present
        String newPosition = "";
        int index = 0;
        int id;
        for (int i = 0; i < arrayEmployees.size(); i++) {
            if (arrayEmployees.get(i).getStatus().equals("Active")) {
                arrayID.add(arrayEmployees.get(i).getID());
            }
        }
        while (true) {
            System.out.print("Enter employee's ID (Type -1 to go back to main menu): ");
            try {
                id = Integer.parseInt(scan.nextLine());
                if (id == -1) {
                    return;
                } 
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                continue;
            }

            if (!arrayID.contains(id)) {
                System.out.println("Employee ID does not exist");
                continue;
            }  else {
                for (int i = 0; i < arrayEmployees.size(); i++) {
                    if (arrayEmployees.get(i).getID() == id) {
                        index = i;
                    }
                }
            }

            System.out.print("Enter employee's new position: ");
            newPosition = scan.nextLine();
            if (newPosition.isEmpty()) {
                continue;
            } else {
                System.out.print("Employee ID: ");
                System.out.println(id);
                System.out.println("Employee's Old Position: " + arrayEmployees.get(index).getPosition());
                System.out.println("Employee's New Position: " + newPosition);
                String ans = tryAgain("Are the details correct? [Y/N]? "); 
                if (ans.equals("N")) {
                    continue;
                }
            }
            break;
        }



        // This is to remove data from file before overwriting
        PrintWriter writer;
        try {
            writer = new PrintWriter("./src/employee.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        
        FileWriter fw = null;
        try {
            fw = new FileWriter("./src/employee.txt", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        } 


        for (int i = 0; i < arrayEmployees.size(); i++) {
            if (arrayEmployees.get(i).getID() == id) {
                System.out.println(arrayEmployees.get(i).getName() + ", position is updates!");
                arrayEmployees.get(i).setPosition(newPosition);
            }

            try {
                fw.write(arrayEmployees.get(i).getID() + "\n"); 
                fw.write(arrayEmployees.get(i).getName() + "\n"); 
                fw.write(arrayEmployees.get(i).getStatus() + "\n"); 
                fw.write(arrayEmployees.get(i).getDate() + "\n"); 
                fw.write(arrayEmployees.get(i).getPosition() + "\n"); 
            } catch(IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        }

        fw.close();
    }

    public static void pressEnterToContinue(){ 
        Scanner scan = new Scanner(System.in);
        System.out.println("Press Enter key to continue...");
        try {
            String temp = scan.nextLine();
        }  catch(Exception e) {

        } 
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        while (true) {
            int answer;
            FileReader employeeFile = null;
            try {
                employeeFile = new FileReader("./src/employee.txt"); // open file
            } catch (FileNotFoundException e) {
                System.out.println("File does not exist.");
                System.exit(0);
            }
            System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("[1] Check Complete Employee List");
            System.out.println("[2] Check Active Employee List");
            System.out.println("[3] Check Inactive Employee List");
            System.out.println("[4] Add New Employee");
            System.out.println("[5] Fire An Employee");
            System.out.println("[6] Update Employee Position");
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
                addEmployee(employeeFile);
            } else if (answer == 5) {
                System.out.println("### Fire an Employee ###");
                checkEmployeeList(employeeFile, "Active");
                try {
                    employeeFile = new FileReader("./src/employee.txt"); // open file
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist.");
                    System.exit(0);
                }
                fireEmployee(employeeFile);
            } else if (answer == 6) {
                System.out.println("### Update Employee Position ###");
                checkEmployeeList(employeeFile, "Active");
                try {
                    employeeFile = new FileReader("./src/employee.txt"); // open file
                } catch (FileNotFoundException e) {
                    System.out.println("File does not exist.");
                    System.exit(0);
                }
                updatePosition(employeeFile);
            }
            pressEnterToContinue();
        }
        

        scan.close();
    }
}
