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
public enum EmployeeOptions {
    DEVELOPER(1, "Developer"),
    TESTER(2, "Tester"),
    DESIGNER(3, "Designer");
    private final int code;
    private final String description;
    
    EmployeeOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static EmployeeOptions fromCode(int code) {
        for (EmployeeOptions option : EmployeeOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    public static EmployeeOptions fromDescription(String description) {
        for (EmployeeOptions option : EmployeeOptions.values()) {
            if (option.getDescription().equalsIgnoreCase(description)) {
                return option;
            }
        }
        return null;
    }
    
    public static EmployeeOptions select(){
        EmployeeOptions selectedEmployee;
        InputUtilities input = new InputUtilities();
        System.out.println("-- Select a Position option --");
        for (EmployeeOptions options : EmployeeOptions.values()) {
                System.out.println(options);
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedEmployee = EmployeeOptions.fromCode(option);
            if (selectedEmployee != null) {
                return selectedEmployee;
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
