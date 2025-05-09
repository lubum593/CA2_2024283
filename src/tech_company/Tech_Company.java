/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tech_company;


import InputUtilities.*;
import Subclasses.*;
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
    static InputUtilities input = new InputUtilities();
    static List<Employee> employee = new ArrayList<>();
    private static final String fileName = "Applicants_Form.txt";
    
    /**
     * @param args the command line arguments
     */
    public Tech_Company(){//Constructor
        this.employee = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        loadFromFile(fileName);//Load our database stored in our .txt File
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
                    binarySearch(employee, input.insertName("Enter the First Name: "), input.insertName("Enter the Surname: "),0, employee.size()-1);
                }
                case ADD ->{
                    int selectedAddition = AdditionOptions.select().getCode();
                    switch (selectedAddition) {
                            case 1 -> addEmployee();
                            case 2 -> generateEmployee(input.insertNumber());
                            case 3 -> displayList(false);//Display the whole list
                    }
                }
                case EXIT -> System.out.println("Exiting...");
            }
        }while(option!= MenuOptions.EXIT.getCode());
    }
    
    private static void addEmployee() {
        System.out.println("\n-- Adding new Employee --");
        String name = input.insertName("Enter Employee First Name: ");
        String surname = input.insertName("Enter Employee Surname: ");
        int employeeChoice = EmployeeOptions.select().getCode();
        int managerChoice = ManagerOptions.select().getCode();
        int departmentChoice = DepartmentOptions.select().getCode();
        createEmployee(name, surname, employeeChoice, managerChoice, departmentChoice, false);
    }
    
    private static void createEmployee(String name, String surname, int choiceEmployee, int managerChoice, int departmentChoice, boolean loadFiles){
        Employee newEmployee = null;
        Manager newManager = null;
        Department newDepartment = null;
        String managerOption = ManagerOptions.fromCode(managerChoice).getDescription();
        String departmentOption = DepartmentOptions.fromCode(departmentChoice).getDescription();
        switch(managerChoice){
                case 1 -> newManager = new TeamLeader(managerOption);
                case 2 -> newManager = new AssistantManager(managerOption);
                case 3 -> newManager = new SeniorManager(managerOption);
                case 4 -> newManager = new HeadManager(managerOption);
        }
        switch(departmentChoice){
                case 1 -> newDepartment = new CustomerService(departmentOption);
                case 2 ->  newDepartment = new TechnicalSupport(departmentOption);
                case 3 ->  newDepartment = new HumanResources(departmentOption);
        }
        switch(choiceEmployee){
                case 1 -> newEmployee = new Developer(name, surname, newManager, newDepartment);
                case 2 ->  newEmployee = new Tester(name, surname, newManager, newDepartment);
                case 3 ->  newEmployee = new Designer(name, surname, newManager, newDepartment);
        }
        employee.add(newEmployee);
        if (!loadFiles) {//To avoid printing the list when the system load from the .txt file
            System.out.println("\n"+name + " " + surname + " has been added as " + newEmployee.getClass().getSimpleName()+ " under the management of " + newManager + " to " + newDepartment + " successfully!\n");
            writeToFile(newEmployee, fileName);
        }        
    }
    
    private static void generateEmployee(int numberEmployees){
        System.out.println("\n-- Generate Employees --");
        String[] names = {"Anna", "Jake", "Lily", "Alex", "Sam", "Jamie", "Taylor", "Chris", "Jordan", "Mark", "Sophie","Mia","Amy","Paul"};
        String[] surnames = {"Grey", "Stone", "Dane", "Smith", "Brown", "Lee", "Garcia", "Davis", "Miller", "Hawk", "Shaw", "Silva"};
        for (int i = 0; i < numberEmployees; i++) {
            Random randomEmployee = new Random();
            String name = names[randomEmployee.nextInt(names.length)];
            String surname = surnames[randomEmployee.nextInt(surnames.length)];
            EmployeeOptions employeeOption = EmployeeOptions.values()[randomEmployee.nextInt(EmployeeOptions.values().length)];//We get a random value from the enum of employeeOptions
            ManagerOptions managerOption = ManagerOptions.values()[randomEmployee.nextInt(ManagerOptions.values().length)];//We get a random value from the enum of ManagerOptions
            DepartmentOptions departmentOption = DepartmentOptions.values()[randomEmployee.nextInt(DepartmentOptions.values().length)];//We get a random value from the enum of DepartmentOptions
            int employeeChoice = employeeOption.getCode();
            int managerChoice = managerOption.getCode();
            int departmentChoice = departmentOption.getCode();
            createEmployee(name, surname, employeeChoice, managerChoice, departmentChoice, false);
        }
        
    }
    
    private static void writeToFile(Employee employee, String fileName){
        try(FileWriter writer = new FileWriter(fileName, true)){//true to append
            String name = employee.getName();
            String surname = employee.getSurname();
            String manager = employee.getManagement().toString();
            String position = employee.getClass().getSimpleName();//we get the simple name from the child class
            String department = employee.getDepartment().toString();
            String line = String.format("%s,%s,%s,%s,%s%n", name, surname, position, manager, department);
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
                        int employeeChoice = EmployeeOptions.fromDescription(position).getCode();
                        int managerChoice = ManagerOptions.valueOf(managementStr).getCode();
                        int departmentChoice = DepartmentOptions.valueOf(departmentStr).getCode();
                        createEmployee(name, surname, employeeChoice, managerChoice, departmentChoice, true);
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
