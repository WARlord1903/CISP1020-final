/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryMangager;
import java.util.Scanner;
/**
 *
 * @author Vince
 */
public class LibraryManagerTester {
    public static void main(String[] args)
    {
        Scanner in3 = new Scanner(System.in);
        Library lib = new Library("C:\\Users\\Vince\\OneDrive\\Documents\\NetBeansProjects\\LibraryManager\\src\\LibraryMangager\\Info.txt");
        lib.readData();
        System.out.println("Please Enter the Student's first and last name: ");
        String name = in3.nextLine();
        System.out.println("Please enter the student's A Number: ");
        String aNumber = in3.nextLine();
        Student b = new Student(name, aNumber);
        lib.addStudent(b);
        lib.writeData();
        
        
    }
}
