package cisp.project;

import java.text.DecimalFormat;

public class Billing {
    private static String libraryName = "Library";
    private static String libraryAddress = "123 Address Street";
    private static String libraryNumber = "123-456-7890";
    private static double overdueRate = .25;
    private static int borrowPeriod = 14;

    public static String getLibraryName() {
        return libraryName;
    }

    public static void setLibraryName(String libraryName) {
        Billing.libraryName = libraryName;
    }

    public static String getLibraryAddress() {
        return libraryAddress;
    }

    public static void setLibraryAddress(String libraryAddress) {
        Billing.libraryAddress = libraryAddress;
    }

    public static String getLibraryNumber() {
        return libraryNumber;
    }

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

    public static int getBorrowPeriod() {
        return borrowPeriod;
    }

    public static void setBorrowPeriod(int BorrowPeriod) {
        borrowPeriod = BorrowPeriod;
    }
    
    public static double calculateCharge(IssuedBook b)
    {
        if((b.getDaysUntil())>= 0)
        {
            return 0;
        }
        return overdueRate * Math.abs(b.getDaysUntil());
    }
    
    public static void generateInvoice(Student s, IssuedBook b)
    {
        String str = libraryName + "\n" + libraryAddress + "\n" + libraryNumber + "\n\n";
        str += s.toString() + "\n" + "Returning: " + "\n" + b.toString() + "\n";
        str += "Amount due: $" + new DecimalFormat("0.00").format(calculateCharge(b));
        System.out.println(str);
    }
    
    
}
