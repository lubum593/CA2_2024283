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
public enum EntityOptions {
    IRELAND(1, "Ireland"),
    SPAIN(2, "Spain"),
    ENGLAND(3, "England"),
    UNITED_STATES(4, "United States");
    private final int code;
    private final String description;
    
    EntityOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static EntityOptions fromCode(int code) {
        for (EntityOptions option : EntityOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    public static EntityOptions fromDescription(String description) {
        for (EntityOptions option : EntityOptions.values()) {
            if (option.getDescription().equalsIgnoreCase(description)) {
                return option;
            }
        }
        return null;
    }
    public static EntityOptions select(){
        EntityOptions selectedEntities;
        InputUtilities input = new InputUtilities();
        System.out.println("-- Select a Position option --");
        for (EntityOptions options : EntityOptions.values()) {
                System.out.println(options);
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedEntities = EntityOptions.fromCode(option);
            if (selectedEntities != null) {
                return selectedEntities;
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
