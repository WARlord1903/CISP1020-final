/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cisp.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Comparator;

public class IssuedBook extends Book{
    
    Student borrower;
    private LocalDate returnDate;
    private LocalDate issuedDate;    
    
    public IssuedBook(Book b, Student s, int borrowingPeriod)
    {
        super(b.getTitle() ,b.getAuthor(), b.getPublisher(), b.getISBN());
        this.borrower = s;
        this.issuedDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(borrowingPeriod);
    }
    
    public IssuedBook(Book b, LocalDate issuedDate, LocalDate returnDate)
    {
        super(b.getTitle() ,b.getAuthor(), b.getPublisher(), b.getISBN());
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
    }
    
    public IssuedBook(Book b, Student s, LocalDate issuedDate, LocalDate returnDate)
    {
        super(b.getTitle() ,b.getAuthor(), b.getPublisher(), b.getISBN());
        this.borrower = s;
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
    }    
    
    public Student getBorrower()
    {
        return borrower;
    }
    
    public void setBorrower(Student s)
    {
        borrower = s;
    }    
    
    /**
     * Gets the date that the student should return their book by
     *
     * @return the return date
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date that the student should return their book by
     *
     * @param dueBy sets the student's new return date
     */
    public void setReturnDate(LocalDate dueBy) {
        returnDate = dueBy;
    }

    /**
     * Gets the date that the book was issued to the student
     *
     * @return the issued date
     */
    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    /**
     * Sets the date that the book was issued to the student
     *
     * @param issue sets the new issued date
     */
    public void setIssuedDate(LocalDate issue) {
        issuedDate = issue;
    }

    /**
     * Calculates the days between the issued date and return date
     *
     * @return the days between the issued date and return date
     */
    public long getDaysBetween() {
        long daysLeft = returnDate.getLong(ChronoField.EPOCH_DAY) - issuedDate.getLong(ChronoField.EPOCH_DAY);
        return daysLeft;
    }

    /**
     * Calculates the days between now and the return date
     *
     * @return the days between now and the return date
     */
    public long getDaysUntil() {
        long daysLeft = returnDate.getLong(ChronoField.EPOCH_DAY) - LocalDate.now().getLong(ChronoField.EPOCH_DAY);
        return daysLeft;
    }    
    
    public String fileFormat(){
        String str = "IssuedBook:\n\t" + super.fileFormat();
        str += "\n\tIssuedDate: " + issuedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        str += "\n\tReturnDate: " + returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return str + "\n\tend\n";
    }
    
    /**
     * Formats a string that contains the book title and author.
     * 
     * @return a string that contains the book title and author.
     */   
    public String toString(){
        return super.toString() + String.format("\nIssued: %s \nDue: %s\n", issuedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) );
    }
    
}

/**
 * Comparator to sort Students by issued date
 */
class BookCompareIssuedDate implements Comparator<IssuedBook>{
    private boolean descending;

    public BookCompareIssuedDate(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(IssuedBook s1, IssuedBook s2){
        if(descending)
            return s2.getIssuedDate().compareTo(s1.getIssuedDate());
        else
            return s1.getIssuedDate().compareTo(s2.getIssuedDate());
    }
}

/**
 * Comparator to sort Students by return date
 */
class BookCompareReturnDate implements Comparator<IssuedBook>{
    private boolean descending;

    public BookCompareReturnDate(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(IssuedBook s1, IssuedBook s2){
        if(descending)
            return s2.getReturnDate().compareTo(s1.getReturnDate());
        else
            return s1.getReturnDate().compareTo(s2.getReturnDate());
    }
}

/**
 * Comparator to sort Students by days remaining until return date
 */
class BookCompareDaysRemaining implements Comparator<IssuedBook>{
    private boolean descending;

    public BookCompareDaysRemaining(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(IssuedBook s1, IssuedBook s2){
        Long s1DaysUntil = s1.getDaysUntil();
        Long s2DaysUntil = s2.getDaysUntil();
        if(descending)
            return s2DaysUntil.compareTo(s1DaysUntil);
        else
            return s1DaysUntil.compareTo(s2DaysUntil);
    }
}