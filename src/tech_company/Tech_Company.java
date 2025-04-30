/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tech_company;

import java.util.*;
import java.util.Scanner;

/**
 *
 * @author Luis
 */
public class Tech_Company {
    static Scanner sc = new Scanner(System.in);
    static List<Employee> employee = new ArrayList<>();
    private final String fileName;
    /**
     * @param args the command line arguments
     */
    public Tech_Company(String fileName){//Constructor
        this.employee = new ArrayList<>();
        this.fileName = fileName;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        mainMenu();
    }
        
    public static void mainMenu(){
        String fileName = "Applicants_Form.txt";
        int option;
        do{
            System.out.println("Do You wish to SORT or SEARCH:");
            for (MenuOptions options : MenuOptions.values()) {
                System.out.println(options);
            }
            System.out.println("Enter your choice:");
            while(!sc.hasNextInt()){
                System.out.println("Invalid input! please only enter numbers.");
                sc.next();
                System.out.println("Enter your choice:");
            }
            option = sc.nextInt();
            MenuOptions selectedOption = MenuOptions.fromCode(option);
            
            if (selectedOption == null) {
                System.out.println("Invalid choice, try again.");
                continue;//This is going to allow the program to continue
            }
            switch (selectedOption) {
                case SORT ->{
                    System.out.println("Sorting");
                }
                case SEARCH ->{
                    System.out.println("Searching");
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
                            case GENERATE_EMPLOYEE -> generateEmployee();
                            case PRINT_EMPLOYEES -> System.out.println("The elements are: " + employee.toString()+"\n");
                        }
                    }while(AddOption<1&&AddOption>3);    
                }
                case EXIT -> System.out.println("Exiting...");
            }
            
        }while(option!= MenuOptions.EXIT.getCode());
    }
    
    
    private static void addEmployee() {
        System.out.println("Enter Employee Name: ");
        String name = insertName();
        ManagementOptions selectedManagement;
        String management = null;
        String department = null;
        boolean flag = false;
        for (ManagementOptions options : ManagementOptions.values()) {
                System.out.println(options);
        }
        do{
            int option = insertNumber();
            selectedManagement = ManagementOptions.fromCode(option);
            if (selectedManagement == null) {
                System.out.println("Invalid choice, try again.");
            }else{
                flag = true;
            }
        }while(!flag);
        management = selectedManagement.getDescription();
        DepartmentOptions selectedDepartment;
        flag = false;
        for (DepartmentOptions options : DepartmentOptions.values()) {
                System.out.println(options);
        }
        do{
            int option = insertNumber();
            selectedDepartment = DepartmentOptions.fromCode(option);
            if (selectedDepartment == null) {
                System.out.println("Invalid choice, try again.");
            }else{
                flag = true;
            }
        }while(!flag);
        department = selectedDepartment.getDescription();
        employee.add(new Employee(name, management, department));
        
        System.out.println("\n"+name + " has been added as " + management + " to " + department + " successfully!\n");
    }
            
    private static void generateEmployee(){
    
    }
    
    
    
    
    
    public static String insertName(){
        String name;
        System.out.println("Only text please.");
        do{
            name = sc.nextLine();
        }while(!name.matches("[a-zA-Z !.,@?\"]+"));
        return name;
    }
    
    public static int insertNumber(){
        String number = sc.nextLine();
        System.out.println("Enter your choice: ");
        while(!number.matches("[0-9]+")){
            if (!number.matches("")) {
                System.out.println("You must enter numbers only");
            }
            number = sc.nextLine();
        }
       return (Integer.parseInt(number));
    }
    
}
