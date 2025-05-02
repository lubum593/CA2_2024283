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
    private String surname;
    private String gender;
    private String email;
    private String salary;
    private String position;
    private Department department;
    private Manager management;
    
    
    public Employee(String name, String surname, Manager management, Department department){
        this.name = name;
        this.surname = surname;
        this.management = management;
        this.department = department;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public Manager getManagement(){
        return management;
    }
    public Department getDepartment(){
        return department;
    }
    @Override
    public String toString() {
        return name + " " + surname + " - " + management + " - " + department;
    }
    
}
