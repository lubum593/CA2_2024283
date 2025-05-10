/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company.enums;
import InputUtilities.*;
/**
 *
 * @author Luis
 */
public enum AdditionOptions {
    ADD_EMPLOYEE(1, "Add Employee"),
    GENERATE_EMPLOYEE(2, "Generate Employee"),
    PRINT_EMPLOYEES(3, "Print Complete List");
    private final int code;
    private final String description;

    AdditionOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    //Method to display and choose an option
    public static AdditionOptions select(){
        AdditionOptions selectedAddition;
        InputUtilities input = new InputUtilities();
        System.out.println("\n-- Select an option --");
        for (AdditionOptions options : AdditionOptions.values()) {
                System.out.println(options);//it will print each option of the enum
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedAddition = AdditionOptions.fromCode(option);
            if (selectedAddition != null) {
                return selectedAddition;//it will return the option selected
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    public static AdditionOptions fromCode(int code) {
        for (AdditionOptions option : AdditionOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
