
package LibraryManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;


public class CISPProject {
    public static void main(String[] args) {
        
       //Setting the Library up from the file
       Library l = new Library("C:\\Users\\Cristian\\Documents\\NetBeansProjects\\CISP Project\\src\\LibraryManager\\Info.txt");
       l.readData();
       
       //Initializing Billing values
       Billing.setIssuePeriod(14);
       Billing.setOverdueRate(.50);
       
       //Creating new books and adding them to the library
       StockedBook a = new StockedBook("The Making of: CISP 1020 Project", "The Team", "Chatt State", "None");
       StockedBook d = new StockedBook("Spaghetti Code 2: Electric Boogaloo", "Big Zuck", "Facebook", "13-656-3-31233", 2);
       l.addBook(a);
       l.addBook(d);
       
       //Creating a student and finding them in the library database
       Student c = new Student("Praj Jecht", "A01001020");  
       if(-1 != l.getStudentIndex(c))
       {  
           c = l.getStudent(l.getStudentIndex(c));
       }
       else 
       {
           l.addStudent(c);
       }
       c = l.getStudent(l.getStudentIndex(c));
       
       //Giving the student some books
       l.issueBook(c, d);
       l.issueBook(c, a);
        
       //Setting up those books to be returned and returning them
       ArrayList<IssuedBook> books = new ArrayList<IssuedBook>(); 
       books.add(l.returnBook(c, c.getBook(0)));
       books.add(l.returnBook(c, c.getBook(0)));
       
       //Changing the due date of a book to see a fake charge
       books.get(0).setReturnDate(LocalDate.of(2022, Month.MARCH, 21));
       
       //Printing an invoice for the returned books
       System.out.println(Billing.generateInvoice(c, books));
     
       //Saving the data changes to the file
       l.writeData();
       
    }
    
}
