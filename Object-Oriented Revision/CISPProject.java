
package cisp.project;

import java.time.LocalDate;
import java.time.Month;


public class CISPProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Library l = new Library("C:\\Users\\Cristian\\Documents\\NetBeansProjects\\CISP Project\\src\\cisp\\project\\Info.txt");
       
       l.readData();
       
       StockedBook a = new StockedBook("The Making of: CISP 1020 Project", "The Team", "Chatt State", "None");
       StockedBook d = new StockedBook("Spaghetti Code 2: Electric Boogaloo", "Big Zuck", "Facebook", "13-656-3-31233");
       
       l.addBook(a);
       l.addBook(d);
       
       Student c = new Student("Praj Jecht", "A01001020");  
       if(-1 != l.getStudentIndex(c))
       {  c = l.getStudent(l.getStudentIndex(c));
       }else l.addStudent(c);
       c = l.getStudent(l.getStudentIndex(c));
       
    //  IssuedBook b = new IssuedBook(a, c, 14);
    //   c.issueBook(b);
    //   if(-1 != l.getBookIndex(b))((StockedBook)l.getBook(l.getBookIndex(b))).issueBook(b);
        l.issueBook(c, d);
        l.issueBook(c, a);
       Billing.setBorrowPeriod(14);
       Billing.setOverdueRate(.50);
       IssuedBook book = l.returnBook(c,c.getBook(0));
       book.setReturnDate(LocalDate.of(2022, Month.MARCH, 21));
       Billing.generateInvoice(c, book);
       
       System.out.println(c.getBooks());
       System.out.println(l);       
       


       l.writeData();
       
    }
    
}
