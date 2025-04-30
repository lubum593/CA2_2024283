/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public enum DepartmentOptions {
    Customer_Service(1, "Customer Service"),
    Technical_Support(2, "Technical Support"),
    HR(3, "Human Resources");
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
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
