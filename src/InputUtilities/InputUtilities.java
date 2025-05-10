package InputUtilities;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luis
 */
public class InputUtilities {
    Scanner sc = new Scanner(System.in);
    
    public String insertName(String prompt){
        String name="";
        System.out.print(prompt);
        do{
            name = sc.nextLine();
            if (!name.matches("[a-zA-Z]+")||(name.length()<3)) {
                System.out.println("Name format incorrect, please try again.");
                System.out.print(prompt);
            }
        }while(!name.matches("[a-zA-Z]+")||(name.length()<3));
        return name;
    }
    
    public int insertNumber(String prompt){
        System.out.print(prompt);
        String number = sc.nextLine();
        while(!number.matches("[0-9]+")){
            if (!number.matches("")) {
                System.out.println("You must enter valid numbers only");
                System.out.print(prompt);
            }
            number = sc.nextLine();
        }
       return (Integer.parseInt(number));
    }
}
