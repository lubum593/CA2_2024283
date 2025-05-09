/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compensation;

import tech_company.Compensation;
import tech_company.enums.*;

/**
 *
 * @author Luis
 */
public class PartTimeEmployee extends Compensation{
    
    public PartTimeEmployee(String typeOfEmployee) {
        super(typeOfEmployee);
        this.salary = 3000;
        //this.typeOfEmployee = CompensationOptions.fromCode(2).getDescription();
    }
}
