
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Comparator;

public class Student {

    private Book aBook;
    private String studentName;
    private String aNumber;
    private LocalDate returnDate;
    private LocalDate issuedDate;

    /**
     * Constructs new instance of Student with book object, student name, and
     * student's ANumber.
     *
     * @param aBook the book object being assigned to the student
     * @param studentName the name of the student
     * @param aNumber the student's ANumber
     */
    public Student(Book aBook, String studentName, String aNumber) {
        this.aBook = aBook;
        this.studentName = studentName;
        this.aNumber = aNumber;
        this.issuedDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(7);
    }

    /**
     * Constructs new instance of Student with book object, student name,
     * student's ANumber, return date and issued date
     *
     * @param aBook the book object being assigned to the student
     * @param studentName the name of the student
     * @param aNumber the student's ANumber
     * @param issue the date that the student is being issued the book
     * @param dueBy the date that the student should return the book by
     */
    public Student(Book aBook, String studentName, String aNumber, LocalDate issue, LocalDate dueBy) {
        this.aBook = aBook;
        this.studentName = studentName;
        this.aNumber = aNumber;
        this.issuedDate = issue;
        this.returnDate = dueBy;
    }

    /**
     * Gets the book object info
     *
     * @return the book's title and author
     */
    public Book getBookInfo() {
        return aBook;
    }

    /**
     * Sets the book object
     *
     * @param aBook the new book object being assigned to the student
     */
    public void setaBook(Book aBook) {
        this.aBook = aBook;
    }

    /**
     * Gets the student's name
     *
     * @return the student's name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the student's name
     *
     * @param studentName the new name of the student
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Gets the student's ANumber
     *
     * @return the student's ANumber
     */
    public String getStudentANumber() {
        return aNumber;
    }

    /**
     * Sets the student's ANumber
     *
     * @param aNumber sets the student's new ANumber
     */
    public void setStudentANumber(String aNumber) {
        this.aNumber = aNumber;
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
     * Compares the value of this instance of Student and another object.
     *
     * @param o the object to compare to
     * @return whether or not this instance of Student is equal to the passed
     * object
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return studentName.equals(student.studentName)
                && aNumber.equals(student.aNumber)
                && returnDate.equals(student.returnDate)
                && issuedDate.equals(student.issuedDate)
                && aBook.equals(student.aBook);
    }

    /**
     * Formats the book object and student into a string that can be easily read
     * from/written to a text database.
     *
     * @return a string containing the Book object data and student data in text form
     */
    public String fileFormat() {
        String str = "Student:";
        str += "\n\tName: " + studentName;
        str += "\n\tA#: " + aNumber;
        str += "\n\t" + aBook.studentFormat();
        str += "\n\tIssuedDate: " + issuedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        str += "\n\tReturnDate: " + returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return str + "\n\tend\n";
    }
    
    
    /**
     * Formats a String that contains the Student name and A Number, Book title,
     * and how many days until the Book is due to be returned.
     * 
     * @return a String that contains the Student name and A Number, Book title,
     * and how many days until the Book is due to be returned.
     */
    public String toString(){
        long daysLeft = this.getDaysUntil();
        if(daysLeft < 0)
            return(String.format("Student Name: %s\nA#: %s\nWas supposed to return %s %d days ago (%s).\n----------------------------------\n", studentName, aNumber, aBook.getTitle(), Math.abs(daysLeft), returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
        else if(daysLeft > 0)
            return(String.format("Student Name: %s\nA#: %s\n%s due in %d days (%s).\n----------------------------------\n", studentName, aNumber, aBook.getTitle(), Math.abs(daysLeft), returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
        else
            return(String.format("Student Name: %s\nA#: %s\n%s due today (%s).\n----------------------------------\n", studentName, aNumber, aBook.getTitle(), returnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
    }
}

/**
 * Comparator to sort Students by name
 */
class StudentCompareName implements Comparator<Student>{
    private boolean descending;

    public StudentCompareName(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getStudentName().compareTo(s1.getStudentName());
        else
            return s1.getStudentName().compareTo(s2.getStudentName());
    }
}

/**
 * Comparator to sort Students by A Number
 */
class StudentCompareANumber implements Comparator<Student>{
    private boolean descending;

    public StudentCompareANumber(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getStudentANumber().compareTo(s1.getStudentANumber());
        else
            return s1.getStudentANumber().compareTo(s2.getStudentANumber());
    }
}

/**
 * Comparator to sort Students by issued date
 */
class StudentCompareIssuedDate implements Comparator<Student>{
    private boolean descending;

    public StudentCompareIssuedDate(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getIssuedDate().compareTo(s1.getIssuedDate());
        else
            return s1.getIssuedDate().compareTo(s2.getIssuedDate());
    }
}

/**
 * Comparator to sort Students by return date
 */
class StudentCompareReturnDate implements Comparator<Student>{
    private boolean descending;

    public StudentCompareReturnDate(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getReturnDate().compareTo(s1.getReturnDate());
        else
            return s1.getReturnDate().compareTo(s2.getReturnDate());
    }
}

/**
 * Comparator to sort Students by days remaining until return date
 */
class StudentCompareDaysRemaining implements Comparator<Student>{
    private boolean descending;

    public StudentCompareDaysRemaining(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Student s1, Student s2){
        Long s1DaysUntil = s1.getDaysUntil();
        Long s2DaysUntil = s2.getDaysUntil();
        if(descending)
            return s2DaysUntil.compareTo(s1DaysUntil);
        else
            return s1DaysUntil.compareTo(s2DaysUntil);
    }
}

/**
 * Comparator to sort books by Title
 */
class StudentCompareBookTitle implements Comparator<Student>{
    private boolean descending;
    StudentCompareBookTitle(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getBookInfo().getTitle().compareTo(s1.getBookInfo().getTitle());
        else
            return s1.getBookInfo().getTitle().compareTo(s2.getBookInfo().getTitle());
    }
}


/**
 * Comparator to sort books by Author
 */
class StudentCompareBookAuthor implements Comparator<Student>{
    private boolean descending;
    StudentCompareBookAuthor(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getBookInfo().getAuthor().compareTo(s1.getBookInfo().getAuthor());
        else
            return s1.getBookInfo().getAuthor().compareTo(s2.getBookInfo().getAuthor());
    }
}

/**
 * Comparator to sort books by Publisher
 */
class StudentCompareBookPublisher implements Comparator<Student>{
    private boolean descending;
    StudentCompareBookPublisher(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getBookInfo().getPublisher().compareTo(s1.getBookInfo().getPublisher());
        else
            return s1.getBookInfo().getPublisher().compareTo(s2.getBookInfo().getPublisher());
    }
}

/**
 * Comparator to sort books by ISBN
 */
class StudentCompareBookISBN implements Comparator<Student>{
    private boolean descending;
    StudentCompareBookISBN(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Student s1, Student s2){
        if(descending)
            return s2.getBookInfo().getISBN().compareTo(s1.getBookInfo().getISBN());
        else
            return s1.getBookInfo().getISBN().compareTo(s2.getBookInfo().getISBN());
    }
}