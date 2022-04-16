package LibraryManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Comparator;

/**
 * Contains the information of a book that has been issued to a student.
 */

public class IssuedBook extends Book{
    
    Student recipient;
    private LocalDate returnDate;
    private LocalDate issuedDate;   
    
    /**
     * Constructs a new IssuedBook using a book that is stocked, a student that is being issued the book,
     * and the issuingPeriod to set the return date.
     * 
     * @param b The book that is being issued to the student.
     * @param s The Student the book is being issued to.
     * @param issuingPeriod the amount of time the book is being issued to the student for.
     */
    public IssuedBook(Book b, Student s, int issuingPeriod)
    {
        super(b.getTitle() ,b.getAuthor(), b.getPublisher(), b.getISBN());
        this.recipient = s;
        this.issuedDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(issuingPeriod);
    }
    
    /**
     * Constructs a new IssuedBook using a book that is stocked with specified issue and return dates.
     * 
     * @param b The book that is being issued to the student.
     * @param issuedDate The date the book is being/was issued;
     * @param returnDate The date the book is/was supposed to return by;
     */ 
    public IssuedBook(Book b, LocalDate issuedDate, LocalDate returnDate)
    {
        super(b.getTitle() ,b.getAuthor(), b.getPublisher(), b.getISBN());
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
    }
    
    /**
     * Constructs a new IssuedBook using a book that is stocked, a student that is being issued the book, 
     * and specified issue and return dates.
     * 
     * @param b The book that is being issued to the student.
     * @param s The Student the book is being issued to.
     * @param issuedDate The date the book is being/was issued;
     * @param returnDate The date the book is/was supposed to return by;
     */
    public IssuedBook(Book b, Student s, LocalDate issuedDate, LocalDate returnDate)
    {
        super(b.getTitle() ,b.getAuthor(), b.getPublisher(), b.getISBN());
        this.recipient = s;
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
    }    
    
    /**
     * Retrieves the student who is issued the book.
     *
     * @return the student who is issued the book.
     */
    public Student getRecipient()
    {
        return recipient;
    }
    /**
     * Sets the student who is issued the book.
     * 
     * @param s the student who is issued the book.
     */
    public void setRecipient(Student s)
    {
        recipient = s;
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
    
    /**
     * Formats the book into a string that can be easily read from/written to 
     * a text database.
     * 
     * @return a String containing the Book data in text form
     */   
    public String fileFormat(){
        String str = "IssuedBook:\n\t" + super.fileFormat();
        str += "\n\tIssuedDate: " + issuedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        str += "\n\tReturnDate: " + returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return str + "\n\tend\n";
    }
    
    /**
     * Formats a string that contains the book title, author, and relevant dates.
     * 
     * @return a string that contains the book title, author, and relevant dates.
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
