/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tech_company;


import Employee.Tester;
import Employee.Developer;
import Employee.Designer;
import Compensation.*;
import Department.*;
import Entity.*;
import InputUtilities.*;
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
    static InputUtilities input = new InputUtilities();
    static int ID = 0;//employee ID
    static List<Employee> employee = new ArrayList<>();
    private static String fileName;
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        fileName = "Applicants_Form.txt";//Name of the file
        loadFromFile(fileName);//Load our database stored in our .txt File
        mainMenu();//Ititiate the program
    }
    //Main menu with the list
    public static void mainMenu(){
        MenuOptions selectedMenu;
        do{
            selectedMenu = MenuOptions.select();
            switch (selectedMenu) {
                case SORT ->{
                    System.out.println("\nSorting employees by name aphabetically:");
                    mergeSort(employee, 0, employee.size()-1);
                    displayList(true);//Display only 20 employees
                }
                case SEARCH ->{
                    System.out.println("\n-- Search Employee --");
                    mergeSort(employee, 0, employee.size()-1);//We Sort the list before using the binary Search (It is a requirement).
                    binarySearch(employee, input.insertName("Enter the First Name: "), input.insertName("Enter the Surname: "),0, employee.size()-1);
                }
                case ADD ->{
                    int selectedAddition = AdditionOptions.select().getCode();
                    switch (selectedAddition) {
                            case 1 -> addEmployee();
                            case 2 -> generateEmployee();
                            case 3 -> displayList(false);//Display the whole list
                    }
                }
                case EXIT -> System.out.println("Exiting...");
            }
        }while(selectedMenu != MenuOptions.EXIT);//Finish the program only if the user enters the case EXIT
    }
    //Method to add employees
    private static void addEmployee() {
        System.out.println("\n-- Adding new Employee --");
        String name = input.insertName("Enter Employee First Name: ");
        String surname = input.insertName("Enter Employee Surname: ");
        int employeeChoice = EmployeeOptions.select().getCode();
        int compensationChoice = CompensationOptions.select().getCode();
        int departmentChoice = DepartmentOptions.select().getCode();
        int EntityChoice = EntityOptions.select().getCode();
        ID++;//will increase the employee ID
        createEmployee(name, surname, employeeChoice, compensationChoice, departmentChoice, EntityChoice, false);//call the method, sending the necessary data
    }
            
    //Method to generate a random employee
    private static void generateEmployee(){
        System.out.println("\n-- Generate Employees --");
        int numberEmployees = Math.min(50, input.insertNumber("How many employees want to generate: "));//Math.min implemented to avoid the system to colapse
        String[] names = {"Anna", "Jake", "Lily", "Alex", "Sam", "Jamie", "Taylor", "Chris", "Jordan", "Mark", "Sophie","Mia","Amy","Paul"};
        String[] surnames = {"Grey", "Stone", "Dane", "Smith", "Brown", "Lee", "Garcia", "Davis", "Miller", "Hawk", "Shaw", "Silva"};
        for (int i = 0; i < numberEmployees; i++) {
            Random randomEmployee = new Random();
            String name = names[randomEmployee.nextInt(names.length)];//generate a random name from the array name
            String surname = surnames[randomEmployee.nextInt(surnames.length)];//generate a random surname from the array surname
            int employeeChoice = EmployeeOptions.values()[randomEmployee.nextInt(EmployeeOptions.values().length)].getCode();//We get a random value from the enum of employeeOptions
            int compensationChoice = CompensationOptions.values()[randomEmployee.nextInt(CompensationOptions.values().length)].getCode();//We get a random value from the enum of CompensationOptions
            int departmentChoice = DepartmentOptions.values()[randomEmployee.nextInt(DepartmentOptions.values().length)].getCode();//We get a random value from the enum of DepartmentOptions
            int entityChoice = EntityOptions.values()[randomEmployee.nextInt(EntityOptions.values().length)].getCode();//We get a random value from the enum of EntityOptions
            ID++;//will increase the employee ID
            createEmployee(name, surname, employeeChoice, compensationChoice, departmentChoice, entityChoice, false);//call the method, sending the necessary data
        }
        System.out.println(numberEmployees + " employee(s) were generated\n");
    }
    
    //Method where generate employees, adding employees create a new employee.
    private static void createEmployee(String name, String surname, int employeeChoice, int compensationChoice, int departmentChoice, int EntityChoice, boolean loadFiles){
        Employee newEmployee = null;
        Department newDepartment = null;
        Compensation newCompensation = null;
        Entity newEntity = null;
        String employeeOption = EmployeeOptions.fromCode(employeeChoice).getDescription();//To get what type of employee is (Dev, Tester, Designer)
        String compensationOption = CompensationOptions.fromCode(compensationChoice).getDescription();//To get if the employee works (Full time, Part Time)
        String departmentOption = DepartmentOptions.fromCode(departmentChoice).getDescription();//To get the department name
        String entityOption = EntityOptions.fromCode(EntityChoice).getDescription();//To get the entity name
        switch(compensationChoice){//Choosing one compensation
                case 1 -> newCompensation = new FullTimeEmployee(compensationOption);
                case 2 -> newCompensation = new PartTimeEmployee(compensationOption);
                case 3 -> newCompensation = new ContractorEmployee(compensationOption);
        }
        switch(departmentChoice){//Choosing one department
                case 1 -> newDepartment = new CustomerService(departmentOption);
                case 2 ->  newDepartment = new TechnicalSupport(departmentOption);
                case 3 ->  newDepartment = new HumanResources(departmentOption);
        }
        switch(EntityChoice){//Choosing an entity
                case 1 -> newEntity = new EntityIreland(entityOption);
                case 2 -> newEntity = new EntitySpain(entityOption);
                case 3 -> newEntity = new EntityLondon(entityOption);
                case 4 -> newEntity = new EntityUSA(entityOption);
        }
        switch(employeeChoice){//Choosing the type of employee
                case 1 -> newEmployee = new Developer(ID, name, surname, employeeOption, newCompensation, newDepartment, newEntity);
                case 2 ->  newEmployee = new Tester(ID, name, surname, employeeOption, newCompensation, newDepartment, newEntity);
                case 3 ->  newEmployee = new Designer(ID, name, surname, employeeOption, newCompensation, newDepartment, newEntity);
        }
        employee.add(newEmployee);//adding into the list
        if (!loadFiles) {//To avoid printing the list AGAIN when the system load from the .txt file
            System.out.println("\n"+ID+": "+name + " " + surname + " has been added as " + newEmployee.getPosition()+ " with a "+ newCompensation.getTypeOfEmployee() + " contract to " + newDepartment + " located in "+ newEntity +" successfully!\n");
            writeToFile(newEmployee, fileName);//Save the new employee into the file .txt
        }
    }
    
    //Method to append users to the file .txt
    private static void writeToFile(Employee employee, String fileName){
        try(FileWriter writer = new FileWriter(fileName, true)){//true to append
            String name = employee.getName();
            String surname = employee.getSurname();
            String position = employee.getPosition();//we get the simple name from the child class
            //String manager = employee.getManagement().toString();
            String typeOfEmployee = employee.getCompensation().getTypeOfEmployee();
            String department = employee.getDepartment().toString();
            String entity = employee.getEntity().getName();
            String line = String.format("%s,%s,%s,%s,%s,%s,%s%n", ID, name, surname, position, typeOfEmployee, department, entity);
            writer.write(line);            
        }catch(IOException e){
            System.out.println("Error writing into the file "+fileName+" "+e.getMessage());
        }
    }
    
    //Load data from the .txt file into the system (List employee)
    public static void loadFromFile(String fileName) {
        try(Scanner fileSC = new Scanner(new java.io.File(fileName))){
            System.out.println("File "+fileName+" was read successfully\n");
            if (fileSC.hasNextLine()) {
                fileSC.nextLine();//with this line we skip the header of the document.
            }
            while(fileSC.hasNextLine()){
                String line = fileSC.nextLine(); // Read the next line
                String[] fields = line.split(","); // Split line into different parts
                if (fields.length == 7) {
                    ID = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    String surname = fields[2].trim();
                    String position = fields[3].trim().toUpperCase().replace(" ", "_");//To normalize the format to EmployeeOptions
                    String compensationStr = fields[4].trim().toUpperCase().replace(" ", "_");//To normalize the format to CompensationOptions
                    String departmentStr = fields[5].trim().toUpperCase().replace(" ", "_");//Tp normalize the format to DepartmentOptions
                    String entityStr = fields[6].trim().toUpperCase().replace(" ", "_");//To normalize the format to EntityOptions
                    try{
                        int employeeChoice = EmployeeOptions.fromDescription(position).getCode();
                        int compensationChoice = CompensationOptions.valueOf(compensationStr).getCode();
                        int departmentChoice = DepartmentOptions.valueOf(departmentStr).getCode();
                        int entityChoice = EntityOptions.valueOf(entityStr).getCode();
                        createEmployee(name, surname, employeeChoice, compensationChoice, departmentChoice, entityChoice, true);
                    }catch(IllegalArgumentException  e){
                        System.out.println("Invalid values: "+line);
                    }
                }
            }
            System.out.println("Employees were loaded from file "+fileName+" were loaded successfully");
        }catch(Exception e){
            System.out.println("Error reading the file: "+e.getMessage());
        }
    }
    
    //Method to display the list of employees
    public static void displayList(boolean max){
        int maxDisplay;
        if (max) {//if true the system will print only 20 employees
            maxDisplay = Math.min(20, employee.size());//To display only 20 employees, and check if the list is smaller than 20
        }else{
            maxDisplay = employee.size();
        }
        
        System.out.println("\nList of employees:");
        for (int i = 0; i < maxDisplay; i++) {
            System.out.println(employee.get(i));
        }
    }
    
    //Recursive method of Merge Sort
    public static void mergeSort(List<Employee> employeeList, int left, int right){
        if (left<right) {//Only if there is at least one element in the list
            int middle = left + (right - left)/2;//calculate the middle index
            mergeSort(employeeList, left, middle);//it will sort the left half recursively
            mergeSort(employeeList, middle+1, right);//it will sort the right half recursively
            merge(employeeList, left, middle, right);//then it will sort the two halves
        }
    }
    
    //Merge method
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
        //Here is where merge elements based on the name and surname
        while(i < leftSize && j < rightSize){
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
        //Copying any remain from the left lists
        while (i < leftSize) {
            employeeList.set(index, leftList.get(i));
            i++;
            index++;
        }//Copying any remain from the right lists
        while (j < rightSize) {
            employeeList.set(index, rightList.get(j));
            j++;
            index++;
        }
    }
    
    private static void binarySearch(List<Employee> list, String name, String surname, int left, int right){
        if (left > right) {//if the search inputs are not valid or the employee was not found
            System.out.println("\n-- Result of the search--");
            System.out.println("Employee not found.\n");
            return;
        }
        int middle = left + (right - left)/2; //calculate the middle index
        //Get the name and surname of the employee
        String employeeName = list.get(middle).getName();
        String employeeSurname = list.get(middle).getSurname().trim();
        //check if the employee name matches
        if ((employeeName.compareToIgnoreCase(name))==0) {//If the name matches
            if (employeeSurname.compareToIgnoreCase(surname.trim())==0) {//If the lastname matches
                //return list.get(middle);//The employee was found
                System.out.println("\n-- Result of the search--");
                System.out.println("Name: "+list.get(middle).getName()+" "+list.get(middle).getSurname());
                System.out.println("Position: "+list.get(middle).getPosition());
                System.out.println("Contract type: "+list.get(middle).getCompensation().getTypeOfEmployee());
                System.out.println("Salary: "+list.get(middle).getCompensation().getSalary());
                System.out.println("Department: "+list.get(middle).getDepartment().getName());
                System.out.println("Located in: "+list.get(middle).getEntity().getName()+"\n");
            }
        }else{//it will call recursively BinarySearch
            //If the middle name comes after the search name, the search will go to the Left
            if ((employeeName.compareToIgnoreCase(name))> 0) {
                binarySearch(list, name, surname, left, middle-1);
            }else{//If the middle name comes before the search nam, the search will go to the Right
                binarySearch(list, name, surname, middle+1, right);
            }
        }
    }
    
}
