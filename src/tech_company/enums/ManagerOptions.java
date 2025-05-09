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
public enum ManagerOptions {
    TEAM_LEADER(1, "Team Leader"),
    ASSISTANT_MANAGER(2, "Assistant Manager"),
    SENIOR_MANAGER(3, "Senior Manager"),
    HEAD_MANAGER(4, "Head Manager");
    private final int code;
    private final String description;

    ManagerOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static ManagerOptions fromCode(int code) {
        
        for (ManagerOptions option : ManagerOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    
    public static ManagerOptions select(){
        ManagerOptions selectedManagement;
        InputUtilities input = new InputUtilities();
        System.out.println("\n-- Select Manager Type --");
        for (ManagerOptions options : ManagerOptions.values()) {
                System.out.println(options);
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedManagement = ManagerOptions.fromCode(option);
            if (selectedManagement != null) {
                return selectedManagement;
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
