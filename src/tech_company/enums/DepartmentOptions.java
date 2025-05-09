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
public enum DepartmentOptions {
    CUSTOMER_SERVICE(1, "Customer Service"),
    TECHNICAL_SUPPORT(2, "Technical Support"),
    HUMAN_RESOURCES(3, "Human Resources");
    private final int code;
    private final String description;

    DepartmentOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static DepartmentOptions fromCode(int code) {
        for (DepartmentOptions option : DepartmentOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    public static DepartmentOptions select(){
        DepartmentOptions selectedDepartment;
        InputUtilities input = new InputUtilities();
        System.out.println("\n-- Select a Department option --");
        for (DepartmentOptions options : DepartmentOptions.values()) {
                System.out.println(options);
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedDepartment = DepartmentOptions.fromCode(option);
            if (selectedDepartment != null) {
                return selectedDepartment;
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
