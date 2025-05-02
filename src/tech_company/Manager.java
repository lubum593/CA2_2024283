/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech_company;

/**
 *
 * @author Luis
 */
public class Manager {
    private String position;
    
    public Manager(String position){
        this.position = position;
    }
    
    public String getPosition(){
        return position;
    }
    
    @Override
    public String toString() {
        return position;
    }
}
