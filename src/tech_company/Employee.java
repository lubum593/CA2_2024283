/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public abstract class Employee {
    private int ID;
    private String name;
    private String surname;
    private String position;
    private Compensation compensation;
    private Department department;
    private Entity entity;
    
    
    public Employee(int ID, String name, String surname, String position, Compensation compensation, Department department, Entity entity){
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.compensation = compensation;
        this.department = department;
        this.entity = entity;
    }
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getPosition(){
        return position;
    }
    public Compensation getCompensation(){
        return compensation;
    }    
    public Department getDepartment(){
        return department;
    }
    public Entity getEntity(){
        return entity;
    }
    
    @Override
    public String toString() {
        return name + " " + surname + " - " + position + " - " + compensation + " - " + department + " - " + entity;
    }
}
