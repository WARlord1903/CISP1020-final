package LibraryManager;

import java.util.Comparator;
import java.util.ArrayList;
/**
 * A class that holds student information and book records.
 */
public class Student {

    private ArrayList<IssuedBook> bookList;
    private String studentName;
    private String aNumber;


    /**
     * Constructs new instance of Student with student name, student's ANumber, and list of books checked out.
     *
     * @param studentName the name of the student
     * @param aNumber the student's ANumber
     */
    public Student(String studentName, String aNumber) {
        this.studentName = studentName;
        this.aNumber = aNumber;
        this.bookList = new ArrayList<IssuedBook>();
    }

    /**
     * Constructs new instance of Student with student name, student's ANumber, and list of books checked out.
     *
     * @param studentName the name of the student
     * @param aNumber the student's ANumber
     * @param b the first book a student is checking out
     */
    public Student(String studentName, String aNumber, IssuedBook b) {
        this.studentName = studentName;
        this.aNumber = aNumber;
        this.bookList = new ArrayList<IssuedBook>();
        bookList.add(b);
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
     * Gives the student a book they have been Issued.
     * 
     * @param b the IssuedBook they are receiving
     */
    public void receiveBook(IssuedBook b)
    {            
        bookList.add(b);           
    }
    
    /**
     * Removes the book the student is returning.
     * 
     * @param b the book a student is returning.
     */
    public void returnBook(IssuedBook b)
    {
        for(IssuedBook i:bookList)
        {
            if(i.equals(b))
            {
                bookList.remove(i);
                break;
            }            
        }
    }
    
    /**
     * Removes the book the student is returning.
     * 
     * @param index the index of the book a student is returning.
     */    
    public void returnBook(int index)
    {
        if(index >= bookList.size()){
            System.out.println("ERROR: Index passed exceeds length of book list.");
            System.exit(0);
        }
        bookList.remove(index);
    }
    
    /**
     * Finds the index of a selected book.
     * 
     * @param b the book you want to find the index for.
     * @return the index of the book provided.
     */
    public int getBookIndex(Book b)
    {
        for(int i = 0; i < bookList.size(); i++){
            if(b.equals(bookList.get(i)))
                return i;
        }
        return -1;
    }
    
    /**
     * Retrieves the book at the give index.
     * 
     * @param index the index of the book
     * @return the book at the selected index
     */
    public IssuedBook getBook(int index)
    {
        if(index >= bookList.size()){
            System.out.println("ERROR: Index passed exceeds length of book list.");
            System.exit(0);
        }
        return bookList.get(index);
    }
    
    /**
     * Returns whether a student has a selected book.
     * 
     * @param b a book the student may have
     * @return if the student has the selected book
     */
    public boolean hasBook(Book b)
    {
        for(Book i:bookList)
        {
            if(i.equals(b))
            {
               return true;
            }             
        }
        return false;
    }
    
    /**
     * Compiles the list of books a student has been issued.
     * 
     * @return a String of the books a student has been issued
     */
    public String showBooks()
    {
        String str = "";
        for(IssuedBook i:bookList)
        {
            str += i.toString();
        }
        return str;
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
                && aNumber.equals(student.aNumber);
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
        for(IssuedBook i: bookList){
        str += "\n\t" + i.fileFormat();
        }
        return str + "\n\tend\n";
    }
    
    
    /**
     * Formats a String that contains the Student name and A Number.
     * 
     * @return a String that contains the Student name and A Number.
     */
    public String toString(){
        return studentName + " (" + aNumber + ")";
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

