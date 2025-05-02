/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public class Department {
    private String name;
    
    public Department(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
    
}
