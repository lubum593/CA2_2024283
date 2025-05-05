/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tech_company;


import tech_company.enums.AdditionOptions;
import tech_company.enums.ManagementOptions;
import tech_company.enums.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author Luis
 */
public class Tech_Company {
    static Scanner sc = new Scanner(System.in);
    static List<Employee> employee = new ArrayList<>();
    private static final String fileName = "Applicants_Form.txt";
    /**
     * @param args the command line arguments
     */
    public Tech_Company(){//Constructor
        this.employee = new ArrayList<>();
        //this.fileName = fileName;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //String fileName = "Applicants_Form.txt";
        loadFromFile(fileName);
        mainMenu();
    }
        
    public static void mainMenu(){
        
        int option;
        do{
            System.out.println("Do You wish to SORT or SEARCH:");
            for (MenuOptions options : MenuOptions.values()) {
                System.out.println(options);
            }
            System.out.print("Enter your choice: ");
            while(!sc.hasNextInt()){
                System.out.println("Invalid input! please only enter numbers.");
                sc.next();
                System.out.println("Enter your choice: ");
            }
            option = sc.nextInt();
            MenuOptions selectedOption = MenuOptions.fromCode(option);
            
            if (selectedOption == null) {
                System.out.println("Invalid choice, try again.");
                continue;//This is going to allow the program to continue
            }
            switch (selectedOption) {
                case SORT ->{
                    System.out.println("\nSorting employees by name aphabetically:");;
                    mergeSort(employee, 0, employee.size()-1);
                    displayList(true);//Display only 20 employees
                }
                case SEARCH ->{
                    System.out.println("Searching");
                    System.out.println("Enter the name of the employee you are looking for: ");
                    mergeSort(employee, 0, employee.size()-1);//We Sort the list before using the binary Search.
                    binarySearch(employee, insertName("Enter the First Name: "), insertName("Enter the Surname: "),0, employee.size()-1);
                }
                case ADD ->{
                    int AddOption;
                    do{
                        for (AdditionOptions options : AdditionOptions.values()) {
                        System.out.println(options);
                        }
                        AddOption = insertNumber();
                        AdditionOptions selectedAdd = AdditionOptions.fromCode(AddOption);
                        if (selectedAdd == null) {
                            System.out.println("Invalid choice, try again.");
                            continue;
                        }
                        switch (selectedAdd) {
                            case ADD_EMPLOYEE -> addEmployee();
                            case GENERATE_EMPLOYEE -> generateEmployee(insertNumber());
                            case PRINT_EMPLOYEES -> displayList(false);//Display the whole list
                        }
                    }while(AddOption<1&&AddOption>3);    
                }
                case EXIT -> System.out.println("Exiting...");
            }
        }while(option!= MenuOptions.EXIT.getCode());
    }
    
    
    private static void addEmployee() {
        System.out.println();
        String name = insertName("Enter Employee First Name: ");
        String surname = insertName("Enter Employee Surname: ");
        Employee newEmployee = null;
            int choice;
            do{
                System.out.println("\n-- Choose Employee Type --");
                for (EmployeeOptions options : EmployeeOptions.values()) {
                    System.out.println(options);
                }
                System.out.print("Enter your choice: ");
                while(!sc.hasNextInt()){
                    System.out.println("Invalid input! please only enter numbers.");
                    sc.next();
                    System.out.print("Enter your choice: ");
                }
                choice = sc.nextInt();
                sc.nextLine();
            }while(choice>EmployeeOptions.values().length);
                        
            Manager Manager = new Manager(ManagementOptions.select().getDescription());
            Department department = new Department(DepartmentOptions.select().getDescription());
            switch(choice){
                case 1 -> newEmployee = new Developer(name, surname, Manager, department);
                case 2 ->  newEmployee = new Tester(name, surname, Manager, department);
                case 3 ->  newEmployee = new Designer(name, surname, Manager, department);
            }
        employee.add(newEmployee);
        System.out.println("\n"+name + " " + surname + " has been added as " + Manager + " to " + department + " successfully!\n");
        writeToFile(newEmployee, fileName);
    }
            
    private static void generateEmployee(int numberEmployees){
        String[] names = {"Anna", "Jake", "Lily", "Alex", "Sam", "Jamie", "Taylor", "Chris", "Jordan", "Mark", "Sophie","Mia","Amy","Paul"};
        String[] surnames = {"Grey", "Stone", "Dane", "Smith", "Brown", "Lee", "Garcia", "Davis", "Miller", "Hawk", "Shaw", "Silva"};
        Employee newEmployee = null;
        for (int i = 0; i < numberEmployees; i++) {
            Random randomEmployee = new Random();
            String name = names[randomEmployee.nextInt(names.length)];
            String surname = surnames[randomEmployee.nextInt(surnames.length)];
            EmployeeOptions employeeOption = EmployeeOptions.values()[randomEmployee.nextInt(EmployeeOptions.values().length)];//We get a random value from the enum of employeeOptions
            int EmployeeOption = employeeOption.getCode();
            ManagementOptions managerOption = ManagementOptions.values()[randomEmployee.nextInt(ManagementOptions.values().length)];//We get a random value from the enum of ManagerOptions
            Manager manager = new Manager(managerOption.getDescription());
            DepartmentOptions departmentOption = DepartmentOptions.values()[randomEmployee.nextInt(DepartmentOptions.values().length)];//We get a random value from the enum of DepartmentOptions
            Department department = new Department(departmentOption.getDescription());
            switch(EmployeeOption){
                case 1 -> newEmployee = new Developer(name, surname, manager, department);
                case 2 -> newEmployee = new Tester(name, surname, manager, department);
                case 3 -> newEmployee = new Designer(name, surname, manager, department);
            }
            employee.add(newEmployee);
            writeToFile(newEmployee, fileName);
            System.out.print("\n"+name + " " + surname + " " + employeeOption.getDescription() +" has been added as " + manager + " to " + department + " successfully!\n");
        }
        
    }
    
    private static void writeToFile(Employee employee, String fileName){
        try(FileWriter writer = new FileWriter(fileName, true)){//true to append
            String name = employee.getName();
            String surname = employee.getSurname();
            String manager = employee.getManagement().toString();
            String role = employee.getClass().getSimpleName();//we get the simple name from the child class
            String department = employee.getDepartment().toString();
            String line = String.format("%s,%s,%s,%s,%s%n", name, surname, role, manager, department);
            writer.write(line);            
        }catch(IOException e){
            System.out.println("Error writing into the file "+fileName+" "+e.getMessage());
        }
    }
    
    public static void loadFromFile(String fileName) {
        try(Scanner fileSC = new Scanner(new java.io.File(fileName))){
            System.out.println("File "+fileName+" was read successfully\n");
            if (fileSC.hasNextLine()) {
                fileSC.nextLine();//with this line we skip the header of the document.
            }
            while(fileSC.hasNextLine()){
                String line = fileSC.nextLine(); // Read the next line
                String[] fields = line.split(","); // Split line into different parts
                if (fields.length == 5) {
                    String name = fields[0].trim();
                    String surname = fields[1].trim();
                    String position = fields[2].trim().toUpperCase().replace(" ", "_");//To normalize the format to EmployeeOptions
                    String managementStr = fields[3].trim().toUpperCase().replace(" ", "_");//To normalize the format to ManagmentOptions
                    String departmentStr = fields[4].trim().toUpperCase().replace(" ", "_");//Tp normalize the format to DepartmentOptions
                    try{
                        Manager manager = new Manager(ManagementOptions.valueOf(managementStr).getDescription());
                        Department department = new Department(DepartmentOptions.valueOf(departmentStr).getDescription());
                        EmployeeOptions option = EmployeeOptions.fromDescription(position);
                        Employee newEmployee = null;
                        switch(option.getCode()){
                            case 1: 
                                newEmployee = new Developer(name, surname, manager, department);
                                break;
                            case 2: 
                                newEmployee = new Tester(name, surname, manager, department);
                                break;
                            case 3: 
                                newEmployee = new Designer(name, surname, manager, department);
                                break; 
                        }
                        employee.add(newEmployee);
                    }catch(IllegalArgumentException  e){
                        System.out.println("Invalid values: "+line);
                    }
                }
            }
            System.out.println("Employees were loaded from file "+fileName+" were loaded successfully\n");
        }catch(Exception e){
            System.out.println("Error reading the file: "+e.getMessage());
        }
        
        
        
    }
        
    public static String insertName(String prompt){
        String name="";
        System.out.print(prompt);
        do{
            name = sc.nextLine();
            if (!name.matches("[a-zA-Z]+")||(name.length()<3)) {
                System.out.println("Name format incorrect, please try again.");
                System.out.print(prompt);
            }
        }while(!name.matches("[a-zA-Z]+")||(name.length()<3));
        return name;
    }
    
    public static int insertNumber(){
        System.out.print("Enter your choice: ");
        String number = sc.nextLine();
        while(!number.matches("[0-9]+")){
            if (!number.matches("")) {
                System.out.println("You must enter numbers only");
            }
            number = sc.nextLine();
        }
       return (Integer.parseInt(number));
    }
    
    public static void displayList(boolean max){
        int maxDisplay;
        if (max) {
            maxDisplay = Math.min(20, employee.size());//To display only 20 employees
        }else{
            maxDisplay = employee.size();
        }
        
        System.out.println("\nList of employees:");
        for (int i = 0; i < maxDisplay; i++) {
            System.out.println(employee.get(i));
        }
        System.out.println("\n");
    }
    
    
    public static void mergeSort(List<Employee> employeeList, int left, int right){
        if (left<right) {//Only if there is at least one element in the list
            int middle = left + (right - left)/2;
            mergeSort(employeeList, left, middle);//it will sort the left half recursively
            mergeSort(employeeList, middle+1, right);//it will sort the right half recursively
            merge(employeeList, left, middle, right);
        }
    }
    
    public static void merge(List<Employee> employeeList, int left, int middle, int right){
        //creating two variables to get the sizes of each sublist
        int leftSize = middle - left+1;
        int rightSize = right - middle;
        //Creating temporary lists for each half
        List<Employee> leftList = new ArrayList<>();
        List<Employee> rightList = new ArrayList<>();
        // Copy into the temporary lists
        for (int i = 0; i < leftSize; ++i)
            leftList.add(employeeList.get(left + i));
        for (int j = 0; j < rightSize; ++j)
            rightList.add(employeeList.get(middle + 1 + j));
        int i = 0, j=0;
        int index = left;
        while(i < leftSize && j < rightSize){//Here is where we are going to compare the list
            String nameLeft = leftList.get(i).getName();
            String nameRight = rightList.get(j).getName();
            if (nameLeft.compareToIgnoreCase(nameRight)<0) {
                employeeList.set(index, leftList.get(i));
                i++;                        
            }else if (nameRight.compareToIgnoreCase(nameLeft)<0){
                employeeList.set(index,rightList.get(j));
                j++;
            }else if (nameLeft.compareToIgnoreCase(nameRight)==0) {//if they have the same name, it will check the last name to sort it
                String surnameLeft = leftList.get(i).getSurname();
                String surnameRight = rightList.get(j).getSurname();
                if (surnameLeft.compareToIgnoreCase(surnameRight)<0) {
                    employeeList.set(index, leftList.get(i));
                    i++;                        
                }else{
                    employeeList.set(index,rightList.get(j));
                    j++;
                }
            }
            index++;
        }
        //Copying any remain from both lists
        while (i < leftSize) {
            employeeList.set(index, leftList.get(i));
            i++;
            index++;
        }
        while (j < rightSize) {
            employeeList.set(index, rightList.get(j));
            j++;
            index++;
        }
    }
    
    private static void binarySearch(List<Employee> list, String name, String surname, int left, int right){
        if (left > right) {
            System.out.println("Employee not found.\n");
            return;
        }
        int middle = left + (right - left)/2;
        String employeeName = list.get(middle).getName();
        String employeeSurname = list.get(middle).getSurname().trim();
        //Employee result = new Employee();
        if ((employeeName.compareToIgnoreCase(name))==0) {//If the name matches
            if (employeeSurname.compareToIgnoreCase(surname.trim())==0) {//If the lastname matches
                //return list.get(middle);//The employee was found
                System.out.println("Result found: \n");
                System.out.println("Name: "+list.get(middle).getName()+" "+list.get(middle).getSurname()+"\n");
                System.out.println("Manager Type: "+list.get(middle).getManagement()+"\n");
                System.out.println("Department: "+list.get(middle).getDepartment()+"\n");
            }
        }else{
            if ((employeeName.compareToIgnoreCase(name))> 0) {
                binarySearch(list, name, surname, left, middle-1);
            }else{
                binarySearch(list, name, surname, middle+1, right);
            }
        }
    }
    
}
