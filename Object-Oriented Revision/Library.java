package LibraryManager;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class that handles all operations related to manipulating Books and 
 * Students in the Library Management System.
 */
public class Library {
    private ArrayList<StockedBook> books;
    private ArrayList<Student> students;
    private File file;

    
    /**
     * An enum that is used to determine what Book attribute the search and sort
     * functions use (title, author, publisher, or ISBN).
     */
    public enum BookAttribute{

        /**
         * Search/Sort by Book title
         */
        TITLE,

        /**
         * Search/Sort by Book author
         */
        AUTHOR,

        /**
         * Search/Sort by Book publisher
         */
        PUBLISHER,

        /**
         * Search/Sort by Book ISBN
         */
        ISBN,
        
        /**
         * Sort by quantity
         */
        QUANTITY
    }

    /**
     * An enum that is used to determine what Book attribute the search and sort
     * functions use (Name, A Number, Issued/Return Date, and held Book 
     * attributes).
     */
    public enum StudentAttribute{

        /**
         * Search/sort by Student name
         */
        NAME,

        /**
         * Search/sort by Student A Number
         */
        ANUMBER,

        /**
         * Search/Sort by date book was issued
         */
        ISSUEDDATE,

        /**
         * Search/Sort by date book is to be returned
         */
        RETURNDATE,

        /**
         * Sort by days from now until the return date
         */
        DAYSUNTIL,

        /**
         * Search/sort by the title of the held Book
         */
        BOOKTITLE,

        /**
         * Search/sort by the author of the held Book
         */
        BOOKAUTHOR,

        /**
         * Search/sort by the publisher of the held Book
         */
        BOOKPUBLISHER,

        /**
         * Search/sort by the ISBN of the held Book
         */
        BOOKISBN
    }
    
    /**
     * Constructs a new Library instance, which reads and writes to a file at
     * the given filepath.
     * 
     * @param filepath the path of the database file to be read from and written
     * to
     */
    public Library(String filepath) {
        this.books = new ArrayList<StockedBook>();
        this.students = new ArrayList<Student>();
        this.file = new File(filepath);
        if(!file.exists())
            try{
                System.out.println("Could not find database listed, creating new file...");
                file.createNewFile();
            } catch(IOException ex){
                System.out.println("ERROR: Could not create new file.");
                System.exit(0);
            }
    }
    
    /**
     * Retrieves the Book at the given index.
     * 
     * @param index the index of the Book 
     * @return the Book at the given index
     */
    public Book getBook(int index){
        if(index >= books.size()){
            System.out.println("ERROR: Index passed exceeds length of book list.");
            System.exit(0);
        }
        return books.get(index);
    }
    
    /**
     * Retrieves the Student at the given index.
     * 
     * @param index the index of the Student
     * @return the Student at the given index
     */
    public Student getStudent(int index){
        if(index >= students.size()){
            System.out.println("ERROR: Index passed exceeds length of student list.");
            System.exit(0);
        }
        return students.get(index);        
    }
    
    /**
     * Gets the index of the Book that is equal to the Book that is passed.
     * 
     * @param book the Book whose index is to be searched for
     * @return the index of the equivalent Book, -1 otherwise
     */
    public int getBookIndex(Book book){
        for(int i = 0; i < books.size(); i++){
            if(book.equals(books.get(i)))
                return i;
        }
        return -1;
    }
    
    /**
     * Gets the index of the Student that is equal to the Student that is 
     * passed.
     * 
     * @param student the Student whose index is to be searched for
     * @return the index of the equivalent Student, -1 otherwise
     */
    public int getStudentIndex(Student student){
        for(int i = 0; i < students.size(); i++){
            if(student.equals(students.get(i)))
                return i;
        }
        return -1;
    }

    /**
     * Adds a Book to the library records.
     * 
     * @param book the Book to be added to the library records
     */
    public void addBook(StockedBook book){
        int searchIndex = getBookIndex(book);
        if(searchIndex != -1){
            books.get(searchIndex).increaseBy(book.getQuantity());
            return;
        }
        books.add(book);
    }
    
    /**
     * Adds a Student to the library records.
     * 
     * @param student the Student to be added to the library records
     */
    public void addStudent(Student student){
        int searchIndex = getStudentIndex(student);
        if(searchIndex != -1)
            return;
        students.add(student);
    }
    
    /**
     * Removes the Book at the given index
     * 
     * @param index the index of the Book to be removed
     */
    public void removeBook(int index){
        if(index >= books.size()){
            System.out.println("ERROR: Index passed exceeds length of book list.");
            System.exit(0);
        }
        books.remove(index);        
    }
    
    /**
     * Removes the Student at the given index
     * 
     * @param index the index of the Student to be removed
     */
    public void removeStudent(int index){
        if(index >= students.size()){
            System.out.println("ERROR: Index passed exceeds length of student list.");
            System.exit(0);
        }
        students.remove(index);
    }

    /**
     * Gives a student a book and updates the records to reflect the transaction.
     * 
     * @param s the student receiving the book
     * @param b the book the student is being issued
     */
    public void issueBook(Student s, StockedBook b)
    {
        IssuedBook i = new IssuedBook(b, s, Billing.getIssuePeriod());
        s.receiveBook(i);
        ((StockedBook)getBook(getBookIndex(b))).issueBook(i);
    }
    
    /**
     * Returns a book back to the library's stock.
     * 
     * It is important that the return argument is passed to Billing because
     * once the book is removed there is no reference to it anywhere else.
     * 
     * @param s the student returning the book
     * @param b the book the student is returning
     * @return the book that was returned
     */
    public IssuedBook returnBook(Student s, IssuedBook b)
    {
        s.returnBook(b);
        ((StockedBook)getBook(getBookIndex(b))).returnBook(b);
        return b;
    }
    
    /**
     * Instantiates Book and Student objects from text in the database file.
     */
    public void readData(){
        try{
            String type;
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                type = in.nextLine();
                switch(type){
                    case "StockedBook:":
                        this.addBook(parseStockedBook(in));
                        break;
                    case "Student:":
                        students.add(parseStudent(in));
                        break;
                }
            }
            in.close();
        } catch(FileNotFoundException ex){
            System.out.println("ERROR: File not found.");
        }
    }
    
    /**
     * Internal implementation of StockedBook instantiation from text.
     * 
     * @param in the Scanner object that scans the file.
     * @return a StockedBook object instantiated from the text in the database file.
     */
    private StockedBook parseStockedBook(Scanner in){
        String temp, title = "", author = "", publisher = "", ISBN = "";
        int quantity = 0;
        title = in.nextLine().substring(1);
        while(title.equals(""))
            title = in.nextLine().substring(1);
        title = title.substring(0, title.length() - 1);
        while(in.hasNextLine()){
            //Note: Because the Scanner reads the next line in the file until it 
            //comes across a line that does not start with a tab, it is 
            //imperative that a single new-line character separates each book
            //entry in the file. This is done internally automatically, so it is
            //best not to manually edit the database file.
            temp = in.nextLine();
            Scanner line = new Scanner(temp);
            temp = line.next();
            if(temp.equals("end"))
                break;
            switch (temp) {
                case "Author:":
                    author = line.nextLine().substring(1);
                    break;
                case "Publisher:":
                    publisher = line.nextLine().substring(1);
                    break;
                case "ISBN:":
                    ISBN = line.nextLine().substring(1);                
                    break;
                case "Quantity:":
                    quantity = Integer.parseInt(line.nextLine().substring(1));
                    break;
                default:
                    break;
            }
        }
        return new StockedBook(title, author, publisher, ISBN, quantity);
    }

    /**
     * Internal implementation of Student instantiation from text.
     * 
     * @param in the Scanner object that scans the file.
     * @return a Student object instantiated from the text in the database file.
     */
    public Student parseStudent(Scanner in)
    {
        String temp, name = "", aNumber = "";
        ArrayList<IssuedBook> books = new ArrayList<IssuedBook>();
        while(in.hasNextLine()){
            temp = in.nextLine();
            if(temp.equals(""))
                continue;
            Scanner line = new Scanner(temp);
            temp = line.next();
            if(temp.equals("end"))
                break;
            switch(temp){
                case "Name:":
                    name = line.nextLine().substring(1);
                    break;
                case "A#:":
                    aNumber = line.nextLine().substring(1);
                    break;
                case "IssuedBook:":
                    books.add(parseIssuedBook(in));
                    
                    break;
            }
        }
        
        Student newStudent = new Student(name, aNumber);
        for(IssuedBook i:books)
        {
            i.setRecipient(newStudent);
            newStudent.receiveBook(i);
            
            //the if statement is used to prevent testing issues in case there 
            //is a book that was issued to the student but not in the library
            if(-1 != getBookIndex(i))
            {
            ((StockedBook)getBook(getBookIndex(i))).issueBook(i);
            }
        }
        
        return newStudent;        
    }
    
    /**
     * Internal implementation of IssuedBook instantiation from text.
     * 
     * @param in the Scanner object that scans the file.
     * @return a IssuedBook object instantiated from the text in the database file.
     */
    private IssuedBook parseIssuedBook(Scanner in)
    {
        String temp, title = "", author = "", publisher = "", ISBN = "";
        LocalDate issuedDate = null, returnDate = null;
        title = in.nextLine().substring(1);
        while(title.equals(""))
        title = in.nextLine().substring(1);
        title = title.substring(0, title.length() - 1);
        while(in.hasNextLine()){
            temp = in.nextLine();
            if(temp.equals(""))
                continue;
            Scanner line = new Scanner(temp);
            temp = line.next();
            if(temp.equals("end"))
                break;
            switch(temp){
                case "Author:":
                    author = line.nextLine().substring(1);
                    break;
                case "Publisher:":
                    publisher = line.nextLine().substring(1);
                    break;
                case "ISBN:":
                    ISBN = line.nextLine().substring(1);                
                    break;
                case "IssuedDate:":
                    issuedDate = LocalDate.parse(line.nextLine().substring(1), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    break;
                case "ReturnDate:":
                    returnDate = LocalDate.parse(line.nextLine().substring(1), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    break;
            }
        }
        return new IssuedBook(new Book(title, author, publisher, ISBN), issuedDate, returnDate);
    }
    
    /**
     * Writes each Book and Student in the library to the database file.
     */
    public void writeData(){
        try{
            PrintWriter out = new PrintWriter(file);
            for(Book b : books)
                out.println(b.fileFormat());
            for(Student s : students)
                out.println(s.fileFormat());
            out.close();
        } catch (FileNotFoundException ex){
            System.out.println("ERROR: File not found.");
            System.exit(0);
        }
    }
    
    /**
     * Searches the library for books of a given attribute matching a given 
     * string.
     *
     * @param term the term to search for
     * @param type the attribute of the Book to search for
     * @return a list of all Books that match the criteria
     */
    public ArrayList<Book> searchBooks(String term, BookAttribute type){
        ArrayList<Book> matches = new ArrayList<>();
        if(type != BookAttribute.TITLE && type != BookAttribute.AUTHOR && type != BookAttribute.PUBLISHER && type != BookAttribute.ISBN)
            System.out.println("Invalid search attribute entered. Please choose Title, Author, Publisher, or ISBN.");
        for(Book b : books){
            switch(type){
                case TITLE:
                    if(b.getTitle().toLowerCase().contains(term.toLowerCase()))
                        matches.add(b);
                    break;
                case AUTHOR:
                    if(b.getAuthor().toLowerCase().contains(term.toLowerCase()))
                        matches.add(b);
                    break;
                case PUBLISHER:
                    if(b.getPublisher().toLowerCase().contains(term.toLowerCase()))
                        matches.add(b);
                    break;
                case ISBN:
                    if(b.getISBN().toLowerCase().contains(term.toLowerCase()))
                        matches.add(b);
                    break;
                default:
                    break;
            }
        }
        return matches;
    }
    
    /**
     * Searches the library for students of a given attribute matching a given
     * string.
     * 
     * @param term the term to search for
     * @param type the attribute of the Student to search for
     * @return a list of all Students that match the criteria
     */
    public ArrayList<Student> searchStudents(String term, StudentAttribute type){
        ArrayList<Student> matches = new ArrayList<>();
        for(Student s : students){
            switch(type){
                case NAME:
                    if(s.getStudentName().toLowerCase().contains(term.toLowerCase()))
                        matches.add(s);
                    break;
                case ANUMBER:
                    if(s.getStudentANumber().toLowerCase().contains(term.toLowerCase()))
                        matches.add(s);
                    break;
                default:
                    break;
            }
        }
        return matches;
    }
    
    /**
     * Sorts the library for books by a given attribute.
     * 
     * @param type the attribute of the book to sort by
     * @param descending whether or not the method sorts in ascending or
     * descending order
     */
    public void sortBooks(BookAttribute type, boolean descending){
        switch(type){
            case TITLE:
                Collections.sort(books, new BookCompareTitle(descending));
                break;
            case AUTHOR:
                Collections.sort(books, new BookCompareAuthor(descending));
                break;
            case PUBLISHER:
                Collections.sort(books, new BookComparePublisher(descending));
                break;
            case ISBN:
                Collections.sort(books, new BookCompareISBN(descending));
                break;
            case QUANTITY:
                Collections.sort(books, new BookCompareQuantity(descending));
                break;
            default:
                break;
        }
    }
    
    /**
     * Sorts the library for students by a given attribute.
     * 
     * @param type the attribute of the student to sort by
     * @param descending whether or not the method sorts in ascending or
     * descending order
     */    
    public void sortStudents(StudentAttribute type, boolean descending){
        switch(type){
            case NAME:
                Collections.sort(students, new StudentCompareName(descending));
                break;
            case ANUMBER:
                Collections.sort(students, new StudentCompareANumber(descending));
                break;
            default:
                break;
        }
    }
    
    /**
     * Formats each Book and Student in the library records into a String
     *
     * @return the data of each Book in the library in text form.
     */
    @Override
    public String toString(){
        String str = "";
        for(Book b : books)
            str += b;
        str += "----------------------------------\n";
        for(Student s : students)
            str += s;
        return str;
    }
}