/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

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
    
    @Override
    public String toString() {
        return code + ". " + description;
    }
}
