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
public enum MenuOptions {
    SORT(1, "SORT"),
    SEARCH(2, "SEARCH"),
    ADD(3, "ADD RECORDS"),
    EXIT(4, "EXIT");
    private final int code;
    private final String description;

    MenuOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static MenuOptions fromCode(int code) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    public static MenuOptions select(){
       MenuOptions selectedMenu;
       InputUtilities input = new InputUtilities();
        System.out.println("\n-- Do You wish to SORT, SEARCH or ADD Employees --");
        for (MenuOptions options : MenuOptions.values()) {
                System.out.println(options);
        }
        while(true){
            int option = input.insertNumber("Enter your choice: ");
            selectedMenu = MenuOptions.fromCode(option);
            if (selectedMenu != null) {
                return selectedMenu;
            }
            System.out.println("Invalid choice, try again.");
        }
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
    
}
