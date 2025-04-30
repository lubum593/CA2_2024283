/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public class Employee {
    private String name;
    private String management;
    private String department;
    
    public Employee(String name, String management, String department){
        this.name = name;
        this.management = management;
        this.department = department;
    }
    public String getName(){
        return name;
    }
    public String getManagement(){
        return management;
    }
    public String getDepartment(){
        return department;
    }
    @Override
    public String toString() {
        return name + " - " + management + " - " + department;
    }
    
}
