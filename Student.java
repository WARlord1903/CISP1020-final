
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Student {

    private Book aBook;
    private String studentName;
    private String aNumber;
    private LocalDate returnDate;
    private LocalDate issuedDate;

    /**
     * Constructs new instance of Student with book object, student name, &
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
    }

    /**
     * Constructs new instance of Student with book object, student name,
     * student's ANumber, return date & issued date
     *
     * @param aBook the book object being assigned to the student
     * @param studentName the name of the student
     * @param aNumber the student's ANumber
     * @param issue the date that the student is being issued the book
     * @param dueBy the date that the student should return the book by
     */
    public Student(Book aBook, String studentName, String aNumber, String issue, String dueBy) {
        this.aBook = aBook;
        this.studentName = studentName;
        this.aNumber = aNumber;
        try {
            issuedDate = LocalDate.parse(issue, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Incorrect date format entered. Enter as follows: dd-MM-yyyy");
        }
        try {
            returnDate = LocalDate.parse(dueBy, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Incorrect date format entered. Enter as follows: dd-MM-yyyy");
        }
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
    public void setReturnDate(String dueBy) {
        try {
            returnDate = LocalDate.parse(dueBy, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Incorrect date format entered. Enter as follows: dd-MM-yyyy");
        }
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
    public void setIssuedDate(String issue) {
        try {
            issuedDate = LocalDate.parse(issue, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ex) {
            System.out.println("Incorrect date format entered. Enter as follows: dd-MM-yyyy");
        }
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
        str += "\n\tIssuedDate: " + issuedDate;
        str += "\n\tReturnDate: " + returnDate;
        return str + "\n";
    }

}
