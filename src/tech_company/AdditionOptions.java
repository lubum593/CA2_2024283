/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public enum AdditionOptions {
    ADD_EMPLOYEE(1, "Add Employee"),
    GENERATE_EMPLOYEE(2, "Generate Employee"),
    PRINT_EMPLOYEES(3, "Print");
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
