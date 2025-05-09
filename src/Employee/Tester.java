/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee;

import tech_company.Compensation;
import tech_company.Department;
import tech_company.Employee;
import tech_company.Entity;

/**
 *
 * @author Luis
 */
public class Tester extends Employee{
    public Tester(int ID, String name, String surname, String position, Compensation compensation, Department department, Entity entity) {
        super(ID, name, surname, position, compensation, department, entity);
    }
}
