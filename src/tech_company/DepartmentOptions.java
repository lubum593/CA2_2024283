/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

import static tech_company.Tech_Company.insertNumber;

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
        boolean flag = false;
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
        return selectedDepartment;
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
