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
public class ContractorEmployee extends Compensation{
    
    public ContractorEmployee(String typeOfEmployee) {
        super(typeOfEmployee);
        this.salary = 5000;//set a salary for Contractors
    }    
}
