package LibraryManager;

import LibraryManager.Library.StudentAttribute;
import java.util.Scanner;
import java.util.ArrayList;

public class LibraryManager {

    public static void main(String[] args) {
        Library lib = new Library("C:\\Users\\Cristian\\Documents\\NetBeansProjects\\CISP Project\\src\\LibraryManager\\Info.txt");
        lib.readData();
        
        boolean exit = false;
        do {
            System.out.println("1: Search for a Book");
            System.out.println("2: Show Current Book Inventory");
            System.out.println("3: Add a Book");
            System.out.println("4: Search for a Student");
            System.out.println("5: Show Current Student List");
            System.out.println("6: Pay Fees");
            System.out.println("0: Exit");
            Scanner in = new Scanner(System.in);
            int reponse = InputUtils.inputInt("Enter a menu option: ", 0, 6);
            switch (reponse) {
                case 1:
                    System.out.println("Select an Attribute to search for:");
                    System.out.println("1. Book Title");
                    System.out.println("2. Book Author");
                    System.out.println("3. Book Publisher");
                    System.out.println("4. Book ISBN");
                    System.out.println("0. Go Back");
                    
                    int booksearch = InputUtils.inputInt("Select an Attribute to Search for: ", 0, 4);
                    Scanner in2 = new Scanner(System.in);
                    
                    String term;
                    ArrayList<Book> matches = new ArrayList<>();
                    
                    switch (booksearch) {
                        case 1:
                            System.out.print("What is the Title of the book: ");
                            term = in2.nextLine();
                            matches = lib.searchBooks(term, Library.BookAttribute.TITLE);
                            break;
                            
                        case 2:
                            System.out.print("Who is the Author of the book: ");
                            
                            term = in2.nextLine();
                            matches = lib.searchBooks(term, Library.BookAttribute.AUTHOR);
                            break;
                            
                        case 3:
                            System.out.print("Who is the Publisher of the book: ");
                            term = in2.nextLine();
                            matches = lib.searchBooks(term, Library.BookAttribute.PUBLISHER);
                            break;
                            
                        case 4:
                            System.out.print("What is the book's ISBN: ");
                            term = in2.nextLine();
                            matches = lib.searchBooks(term, Library.BookAttribute.ISBN);
                            break;
                            
                        case 0:
                            break;
                            
                    }
                    if(!matches.isEmpty()){
                        int matchNumber;
                        for(int i = 0; i < matches.size(); i++)
                        {
                            matchNumber = i + 1;
                            System.out.println(matchNumber +": " + matches.get(i) );
                        }
                        int bookIndex = InputUtils.inputInt("\nPlease enter the number of the book \n to view options (Enter 0 to go back):", 0, matches.size());
                        if(bookIndex > 0)
                        {
                            bookManager(lib, (StockedBook)matches.get(bookIndex - 1));
                        }
                        else
                        {
                            break;
                        }
                    }
                    else if(booksearch != 5)
                    {
                        System.out.println("No matches found.");
                    }
                    
                    break;
                case 2:
                    if(lib.getSize() > 0)
                    {
                        boolean notDone = true;
                        while(notDone)
                        {
                        System.out.println("\nHow would you like to sort the books?" );
                        System.out.println("1. Book Title");
                        System.out.println("2. Book Author");
                        System.out.println("3. Book Publisher");
                        System.out.println("4. Book ISBN");
                        int resp = InputUtils.inputInt("\n(Enter 0 to go back):\n", 0, 4);
                        switch (resp){
                            case 0:
                                notDone = false;
                                break;
                            case 1:
                                lib.sortBooks(Library.BookAttribute.TITLE, false);
                            case 2:
                                lib.sortBooks(Library.BookAttribute.AUTHOR, false);
                            case 3:
                                lib.sortBooks(Library.BookAttribute.PUBLISHER, false);
                            case 4:
                                lib.sortBooks(Library.BookAttribute.ISBN, false);
                        }
                    if(resp != 0) {    
                        for(int i = 0; i < lib.getSize(); i++)
                        {
                            System.out.println((i + 1) +": " + lib.getBook(i) );
                        }
                        int bookIndex = InputUtils.inputInt("\nPlease enter the number of the book \n to view options (Enter 0 to go back):\n", 0, lib.getSize());
                        if(bookIndex > 0)
                        {
                            bookManager(lib, (StockedBook)lib.getBook(bookIndex - 1));
                        }
                        else
                        {
                            break;
                        }
                        }    
                        }
                    }
                    else
                    {
                        System.out.println("No books found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the title of the book: ");
                    String bookTitle = in.nextLine();
                    System.out.println("Enter the name of the author: ");
                    String bookAuthor = in.nextLine();
                    System.out.println("Enter the publisher of the book: ");
                    String pub = in.nextLine();
                    System.out.println("Enter the ISBN: ");
                    String isbn = in.next();
                    System.out.println("Enter the quantity: ");
                    int quant = InputUtils.inputInt("");
                    StockedBook a = new StockedBook(bookTitle, bookAuthor, pub, isbn, quant);
                    lib.addBook(a);
                    lib.writeData();
                    break;
                case 4:
                    System.out.println("Select an Attribute to search for:");
                    System.out.println("1. Student Name");
                    System.out.println("2. Student ANumber");
                    System.out.println("0. Go Back");
                    
                    int selection = InputUtils.inputInt("Select an Attribute to Search for: ", 0, 2);
                    Scanner in3 = new Scanner(System.in);
                    
                    String term2;
                    ArrayList<Student> matches2 = new ArrayList<>();
                    
                    switch (selection) {
                        case 1:
                            System.out.print("What is the Name of the student: ");
                            term2 = in3.nextLine();
                            matches2 = lib.searchStudents(term2, Library.StudentAttribute.NAME);
                            break;
                            
                        case 2:
                            System.out.print("What is the ANumber of the student: ");
                            term2 = in3.nextLine();
                            matches2 = lib.searchStudents(term2, Library.StudentAttribute.ANUMBER);
                            break;
                        case 0:
                            break;                          
                    }
                    if(!matches2.isEmpty()){
                        int matchNumber;
                        for(int i = 0; i < matches2.size(); i++)
                        {
                            matchNumber = i + 1;
                            System.out.println(matchNumber +": " + matches2.get(i) );
                        }
                        int studentIndex = InputUtils.inputInt("\nPlease enter the number of the student \n to view options (Enter 0 to go back):", 0, matches2.size());
                        if(studentIndex > 0)
                        {
                            studentManager(lib, matches2.get(studentIndex - 1));
                        }
                        else
                        {
                            break;
                        }
                    }
                    else if(selection != 5)
                    {
                        System.out.println("No matches found.");
                    }
                    
                    break;
                case 5:
                   if(lib.getNumberStudents()> 0)
                    {
                        boolean notDone = true;
                        while(notDone)
                        {
                        System.out.println("\nHow would you like to sort the students?" );
                        System.out.println("1. Book Name");
                        System.out.println("2. Book ANumber");
                        int resp = InputUtils.inputInt("\n(Enter 0 to go back):\n", 0, 2);
                        switch (resp){
                            case 0:
                                notDone = false;
                                break;
                            case 1:
                                lib.sortStudents(Library.StudentAttribute.NAME, false);
                            case 2:
                                lib.sortStudents(Library.StudentAttribute.ANUMBER, false);
                        }
                    if(resp != 0) {    
                        for(int i = 0; i < lib.getNumberStudents(); i++)
                        {
                            System.out.println((i + 1) +": " + lib.getStudent(i) );
                        }
                        int studentIndex = InputUtils.inputInt("\nPlease enter the number of the student \n to view options (Enter 0 to go back):\n", 0, lib.getNumberStudents());
                        if(studentIndex > 0)
                        {
                            studentManager(lib, lib.getStudent(studentIndex - 1));
                        }
                        else
                        {
                            break;
                        }
                        }    
                        }
                    }
                    else
                    {
                        System.out.println("No students found.");
                    }
                    break;
                case 6:
                    System.out.println("Please enter Student ID");
                    in = new Scanner(System.in);
                    String id = in.nextLine();
                    ArrayList<Student> students = lib.searchStudents(id, StudentAttribute.ANUMBER);
                    if(!students.isEmpty())
                    {
                        Student returner = students.get(0);
                        ArrayList<IssuedBook> returnedBooks = new ArrayList<>();
                        
                        boolean notDone = true;
                        while(notDone)
                        {
                            System.out.println(returner.showBooks());
                            int respon = InputUtils.inputInt("Enter a book to return(0 to finish): ", 0, returner.numBooks());
                            if(respon > 0)
                            {
                                IssuedBook book = returner.getBook(respon - 1);
                                returnedBooks.add(lib.returnBook(returner, book));
                                lib.removeStudent(lib.getStudentIndex(returner));
                                lib.addStudent(returner);
                            }
                            else
                            {
                                notDone = false;
                            }
                        }

                        if(!returnedBooks.isEmpty()){
                        String invoice = Billing.generateInvoice(returner,returnedBooks);
                        System.out.println("\n" + invoice);
                        }
                    }
                    else
                    {
                        System.out.println("Please verify ID and try again.");
                    }
                    
                    break;
                case 0:
                    exit = true;
                    System.out.println("Have a nice day :)");
                    break;
            }
        } while (!exit);

    }
    
    public static void bookManager(Library lib, StockedBook b)
    {
        Scanner in;
        boolean notDone = true;
        while(notDone){
            
        System.out.println("\nTitle: " + b.getTitle());
        System.out.println("Author: " + b.getAuthor());
        System.out.println("Publisher: " + b.getPublisher());
        System.out.println("ISBN: " + b.getISBN());
        System.out.println(String.format("(Quantity : %d, Available: %d)\n", b.getQuantity(), b.numberAvailable()));
    
        System.out.println("1. View Books Issued");
        System.out.println("2: Edit Book Information");
        System.out.println("3: Issue this Book");
        System.out.println("4: Remove this Book");
        
            int reponse = InputUtils.inputInt("Enter a menu option (0 to go back): ", 0, 4);
            switch (reponse) {
                case 0:
                    notDone = false;
                    break;
                case 1:
                    System.out.println("\n" + b.showIssuedBooks()+ "\n");
                    break;
                case 2:
                    System.out.println("Needs to be done still.....");
                    break;
                case 3:
                    System.out.println("Please enter Student ID");
                    in = new Scanner(System.in);
                    String id = in.nextLine();
                    ArrayList<Student> students = lib.searchStudents(id, StudentAttribute.ANUMBER);
                    if(!students.isEmpty())
                    {
                        issueBook(lib,students.get(0), b);
                    }
                    else
                    {
                        System.out.println("No ID exist. Creating new account, please enter Name: ");
                        in = new Scanner(System.in);
                        String name = in.nextLine();
                        Student newStudent = new Student(name, id);
                        lib.addStudent(newStudent);
                        String returnDate = issueBook(lib,newStudent,b);
                        System.out.println(returnDate);
                    }  
                    break;
                case 4:
                    for(int i = lib.getNumberStudents() - 1; i >= 0 ; i--)
                    {
                        Student s = lib.getStudent(i);
                        lib.removeStudent(i);
                        s.returnBook(s.getBookIndex(b));
                        lib.addStudent(s);
                    }
                    break;      
            }
        }
    }
    
    public static void studentManager(Library lib, Student stu)
    {
        Scanner in;
        boolean notDone = true;
        while(notDone){
            
        System.out.println("\nName: " + stu.getStudentName());
        System.out.println("ANumber: " + stu.getStudentANumber() + "\n");
    
        System.out.println("1. View Books Issued");
        System.out.println("2: Edit Student Name");
        System.out.println("3: Remove this Student");
        
            int reponse = InputUtils.inputInt("Enter a menu option (0 to go back): ", 0, 3);
            switch (reponse) {
                case 0:
                    notDone = false;
                    break;
                case 1:
                    System.out.println("\n" + stu.showBooks() + "\n");
                    break;
                case 2:
                    System.out.println("Needs to be done still.....");
                    break;
                case 3:
                    for(int j = stu.numBooks() - 1; j >= 0 ; j--)
                    {
                        lib.returnBook(stu, stu.getBook(j));
                    } 
                    lib.removeStudent(lib.getStudentIndex(stu));
                    System.out.println(stu + "\nwas removed successfully.\n");
                    notDone = false;
                    break;      
            }
        }        
    }
    
    public static String issueBook(Library lib, Student stu, StockedBook b)
    {
        lib.issueBook(stu, b);
        return "Book is due back on: \n\t" + stu.getBook(stu.getBookIndex(b)).getReturnDate().toString();
    }
}