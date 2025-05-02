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
public enum ManagementOptions {
    TEAM_LEADER(1, "Team Leader"),
    ASSISTANT_MANAGER(2, "Assistant Manager"),
    SENIOR_MANAGER(3, "Senior Manager"),
    HEAD_MANAGER(4, "Head Manager");
    private final int code;
    private final String description;

    ManagementOptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static ManagementOptions fromCode(int code) {
        for (ManagementOptions option : ManagementOptions.values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
    
    public static ManagementOptions select(){
        boolean flag = false;
        int option = -1;
        ManagementOptions selectedManagement;
        System.out.println("Select Manager Type:");
        for (ManagementOptions options : ManagementOptions.values()) {
                System.out.println(options);
        }
        do{
            option = insertNumber();
            selectedManagement = ManagementOptions.fromCode(option);
            if (selectedManagement == null) {
                System.out.println("Invalid choice, try again.");
            }else{
                flag = true;
            }
        }while(!flag);
        return selectedManagement;
    }
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
