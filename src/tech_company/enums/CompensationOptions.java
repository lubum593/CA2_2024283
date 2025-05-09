/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company.enums;

import InputUtilities.InputUtilities;

/**
 *
 * @author Luis
 */
public enum CompensationOptions {
    FULL_TIME(1, "Full Time"),
    PART_TIME(2, "Part Time"),
    CONTRACTOR(3, "Contractor");
    private final int code;
    private final String description;

    CompensationOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static CompensationOptions fromCode(int code) {
        for (CompensationOptions option : CompensationOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    public static CompensationOptions fromDescription(String description) {
        for (CompensationOptions option : CompensationOptions.values()) {
            if (option.getDescription().equalsIgnoreCase(description)) {
                return option;
            }
        }
        return null;
    }
    public static CompensationOptions select(){
        CompensationOptions selectedCompensation;
        InputUtilities input = new InputUtilities();
        System.out.println("-- Select a Position option --");
        for (CompensationOptions options : CompensationOptions.values()) {
                System.out.println(options);
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedCompensation = CompensationOptions.fromCode(option);
            if (selectedCompensation != null) {
                return selectedCompensation;
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
