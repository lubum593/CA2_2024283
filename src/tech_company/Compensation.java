/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public abstract class Compensation {
    protected String typeOfEmployee;
    protected int salary;
    
    public Compensation(String typeOfEmployee){
        this.typeOfEmployee = typeOfEmployee;
    }
    public int getSalary(){
        return salary;
    }
    public String getTypeOfEmployee(){
        return typeOfEmployee;
    }
    @Override
    public String toString() {
        return typeOfEmployee;
    }
}
