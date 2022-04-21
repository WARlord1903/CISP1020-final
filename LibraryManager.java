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
            System.out.println("4: Pay Fees");
            System.out.println("5: Exit");
            Scanner in = new Scanner(System.in);
            int reponse = InputUtils.inputInt("Enter a menu option: ", 1, 5);
            switch (reponse) {
                case 1:
                    System.out.println("Select an Attribute to search for:");
                    System.out.println("1. Book Title");
                    System.out.println("2. Book Author");
                    System.out.println("3. Book Publisher");
                    System.out.println("4. Book ISBN");
                    System.out.println("5. Go Back");
                    
                    int booksearch = InputUtils.inputInt("Select an Attribute to Search for: ", 1, 5);
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
                            
                        case 5:
                            break;
                            
                    }
                    if(!matches.isEmpty()){
                        int matchNumber;
                        for(int i = 0; i < matches.size(); i++)
                        {
                            matchNumber = i + 1;
                            System.out.println(matchNumber +": " + matches.get(i) );
                        }
                        int bookIndex = InputUtils.inputInt("\nPlease enter the number of the book to view options (Enter 0 to go back):", 0, matches.size());
                        if(bookIndex > 0)
                        {
                            bookManagement(lib, (StockedBook)matches.get(bookIndex - 1));
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
                    System.out.println(lib.toString());
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

                        
                        String invoice = Billing.generateInvoice(returner,returnedBooks);
                        System.out.println("\n" + invoice);
                    }
                    else
                    {
                        System.out.println("Please verify ID and try again.");
                    }
                    
                    break;
                case 5:
                    exit = true;
                    System.out.println("Have a nice day :)");
                    break;
            }
        } while (!exit);

    }
    
    public static void bookManagement(Library lib, StockedBook b)
    {
        Scanner in;
        boolean notDone = true;
        while(notDone){
    
        System.out.println("1: View Book Information");
        System.out.println("2. View Books Issued");
        System.out.println("3: Edit Book Information");
        System.out.println("4: Issue This Book");
        System.out.println("5: Remove this Book");
        System.out.println("6: Go Back");
            int reponse = InputUtils.inputInt("Enter a menu option: ", 1, 6);
            switch (reponse) {
                case 1:
                    System.out.println("\nTitle: " + b.getTitle());
                    System.out.println("Author: " + b.getAuthor());
                    System.out.println("Publisher: " + b.getPublisher());
                    System.out.println("ISBN: " + b.getISBN());
                    System.out.println(String.format("(Quantity : %d, Available: %d)\n", b.getQuantity(), b.numberAvailable()));
                    break;
                case 2:
                    System.out.println("\n" + b.showIssuedBooks());
                    break;
                case 3:
                    System.out.println("Needs to be done still.....");
                    break;
                case 4:
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
                case 5:
                    for(int i = lib.getNumberStudents() - 1; i >= 0 ; i--)
                    {
                        Student s = lib.getStudent(i);
                        lib.removeStudent(i);
                        s.returnBook(s.getBookIndex(b));
                        lib.addStudent(s);
                    }
                    break;
                case 6:
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