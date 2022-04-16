package LibraryManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Static class that holds and processes the billing information.
 */
public class Billing {
    private static String libraryName = "Greater Colleges of Technology Library";
    private static String libraryAddress = "123 Address Street";
    private static String libraryNumber = "123-456-7890";
    private static double overdueRate = .25;
    private static int issuePeriod = 14;

    /**
     * Gets the name of the library
     * 
     * @return the name of the library
     */
    public static String getLibraryName() {
        return libraryName;
    }
    
    /**
     * Sets the name of the library.
     * 
     * @param libraryName String with the library name
     */
    public static void setLibraryName(String libraryName) {
        Billing.libraryName = libraryName;
    }
    
    /**
     * Gets the address of the library
     * 
     * @return the address of the library
     */
    public static String getLibraryAddress() {
        return libraryAddress;
    }
    
    /**
     * Sets the address of the library.
     * 
     * @param libraryAddress String with the library a
     */
    public static void setLibraryAddress(String libraryAddress) {
        Billing.libraryAddress = libraryAddress;
    }
    
    /**
     * Gets the number of the library
     * 
     * @return the number of the library
     */
    public static String getLibraryNumber() {
        return libraryNumber;
    }
    
    /**
     * Sets the phone number of the library.
     * 
     * @param libraryNumber String with the library phone number
     */
    public static void setLibraryNumber(String libraryNumber) {
        Billing.libraryNumber = libraryNumber;
    }  
    
    /*
    *Gets the dollar amount per day the library charges for a book is overdue.
    *
    *@return dollar amount per day a book is overdue
    */ 
    public static double getOverdueRate() {
        return overdueRate;
    }
    
    /*
    Sets the dollar amount per day the library charges for a book is overdue.
    
    @param dollar amount per day a book is overdue
    */ 
    public static void setOverdueRate(double odr) {
        overdueRate = odr;
    }

    /**
     * Gets the standard amount of time between when a book is issued and when it is due.
     * 
     * @return total number of days the book can be issued for
     */
    public static int getIssuePeriod() {
        return issuePeriod;
    }

    /**
     * Sets the standard amount of time between when a book is issued and when it is due.
     * @param days int containing total number of days the book can be issued for
     */
    public static void setIssuePeriod(int days) {
        issuePeriod = days;
    }
    
    /**
     * Determines if there is a charge for returning the book and how much it is.
     * 
     * @param b an Issued Book
     * @return the currency amount that is due for the book
     */
    public static double calculateCharge(IssuedBook b)
    {
        if((b.getDaysUntil())>= 0)
        {
            return 0;
        }
        return overdueRate * Math.abs(b.getDaysUntil());
    }
    
    /**
     * Creates the bill for returning a book to the library. 
     * 
     * @param s the student returning the book
     * @param b the book that is being returned
     * @return a printable invoice
     */
    public static String generateInvoice(Student s, IssuedBook b)
    {
        String str = libraryName + "\n" + libraryAddress + "\n" + libraryNumber + "\n\n";
        str += s.toString() + "\n" + "Returning: " + "\n" + b.toString() + "\n";
        str += "Amount due: $" + new DecimalFormat("0.00").format(calculateCharge(b));
        return str;
    }
    
    /**
     * Creates the bill for returning a book to the library. 
     * 
     * @param s the student returning the book
     * @param books a list of the books that are being returned
     * @return a printable invoice
     */
    public static String generateInvoice(Student s, ArrayList<IssuedBook> books)
    {
        String str = libraryName + "\n" + libraryAddress + "\n" + libraryNumber + "\n\n";
        str += s.toString() + "\n" + "Returning: ";
        double total = 0;
        double charge = 0;
        for(IssuedBook b: books)
        {
            charge = calculateCharge(b);
            str += "\n" + b.toString();
            str += "$" + new DecimalFormat("0.00").format(charge);
            total += charge;
        }
        str += "\n\n" + "Amount due: $" + new DecimalFormat("0.00").format(total) + "\n";
        return str;
    }
    
}
