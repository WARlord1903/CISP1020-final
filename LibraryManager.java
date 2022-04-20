package LibraryManager;

import java.util.Scanner;
import java.util.ArrayList;

public class LibraryManager {

    public static void main(String[] args) {
        Library lib = new Library("C:\\Users\\Vince\\OneDrive\\Documents\\NetBeansProjects\\LibraryManager\\src\\LibraryMangager\\Info.txt");
        lib.readData();
            Student b = new Student("John Johnson", "A00339493");
            lib.addStudent(b);
            lib.writeData();
        boolean exit = false;
        do {
            //Library lib = new Library("Info.txt");
            //lib.readData();
            System.out.println("1: Search for a Book");
            System.out.println("2: Show Current Book Inventory");
            System.out.println("3: Add a Book");
            System.out.println("4: Issue a Book");
            System.out.println("5: Edit or Delete Book");
            System.out.println("6: Pay Fees");
            System.out.println("7: Exit");
            Scanner in = new Scanner(System.in);
            int reponse = InputUtils.inputInt("Enter a menu option: ", 1, 7);
            switch (reponse) {
                case 1:
                    System.out.println("Select an Attribute to search for:");
                    System.out.println("1. Book Title");
                    System.out.println("2. Book Author");
                    System.out.println("3. Book Publisher");
                    System.out.println("4. Book ISBN");
                    //System.out.println("5. Exit");
                    
                    int booksearch = InputUtils.inputInt("Select an Attribute to Search for: ", 1, 4);
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
                            
                        
                        
                    }
                    System.out.println(matches);

                    
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
                    System.out.println("user can issue a book here");
                    break;
                case 5:
                    System.out.println("user can edit or delete books here");
//                    Scanner in3 = new Scanner(System.in);
//                    
//                    System.out.print("What is the title of the book you would like to delete: ");
//                    term = in3.nextLine();
//                    matches = lib.searchBooks(term, Library.BookAttribute.TITLE);
//                    int r = Book
//                    lib.removeBook(r);
                    break;
                case 6:
                    System.out.println("fees or invoices can be displayed here");
                    break;
                case 7:
                    exit = true;
                    System.out.println("Have a nice day :)");
                    break;
            }
        } while (!exit);

    }
}

