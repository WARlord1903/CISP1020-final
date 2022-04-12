
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Library {
    private ArrayList<Book> books;
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
    };

    /**
     * Constructs a new Library instance, which reads and writes to a file at
     * the given filepath.
     * 
     * @param filepath the path of the database file to be read from and written
     * to
     */
    public Library(String filepath) {
        this.books = new ArrayList<Book>();
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
     * Gets the index of the book that is equal to the book that is passed.
     * 
     * @param book the book whose index you want to search for
     * @return the index of the equivalent book, -1 otherwise
     */
    
    public int getBookIndex(Book book){
        for(int i = 0; i < books.size(); i++){
            if(book.equals(books.get(i)))
                return i;
        }
        return -1;
    }

    /**
     * Adds a Book to the library.
     * 
     * @param book the Book to be added to the library
     */
    public void addBook(Book book){
        int searchIndex = getBookIndex(book);
        if(searchIndex != -1){
            System.out.println("The book added already exists.");
            books.get(searchIndex).increment();
            return;
        }
        books.add(book);
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
     * Instantiates Book objects from text in the database file.
     */
    public void readBooks(){
        try{
            String type;
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                type = in.nextLine();
                switch(type){
                    case "Book:":
                        books.add(parseBook(in));
                        break;
                    case "Student:":
                        //add student
                        break;
                }
            }
            in.close();
        } catch(FileNotFoundException ex){
            System.out.println("ERROR: File not found.");
        }
    }
    
    /**
     * Internal implementation of Book instantiation from text.
     * 
     * @param in the Scanner object that scans the file.
     * @return a Book object instantiated from the text in the database file.
     */
    
    private Book parseBook(Scanner in){
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
            if(!temp.startsWith("\t\t"))
                break;
            Scanner line = new Scanner(temp);
            temp = line.next();
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
        return new Book(title, author, publisher, ISBN, quantity);
    }
    
    /**
     * To-do: implement method that parses students
     * 
     * @param in the Scanner object that scans the file.
     * @return a Student object instantiated from the text in the database file.
     */
    private Object parseStudent(Scanner in){
        return null;
    }
    
    /**
     * Writes each Book in the library to the database file.
     */
    public void writeBooks(){
        try{
            PrintWriter out = new PrintWriter(file);
            for(Book b : books){
                out.println(b);
            }
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
     * Sorts the library for books by a given attribute.
     * 
     * @param type the attribute of the book to sort by
     * @param descending whether or not the function sorts in ascending or
     * descending order
     */
    public void sortBooks(BookAttribute type, boolean descending){
        switch(type){
            case TITLE:
                if(!descending)
                    Collections.sort(books, new BookCompareTitle());
                else
                    Collections.sort(books, new BookCompareTitleDescending());
                break;
            case AUTHOR:
                if(!descending)
                    Collections.sort(books, new BookCompareAuthor());
                else
                    Collections.sort(books, new BookCompareAuthorDescending());
                break;
            case PUBLISHER:
                if(!descending)
                    Collections.sort(books, new BookComparePublisher());
                else
                    Collections.sort(books, new BookComparePublisherDescending());
                break;
            case ISBN:
                if(!descending)
                    Collections.sort(books, new BookCompareISBN());
                else
                    Collections.sort(books, new BookCompareISBNDescending());
                break;
            case QUANTITY:
                if(!descending)
                    Collections.sort(books, new BookCompareQuantity());
                else
                    Collections.sort(books, new BookCompareQuantityDescending());
                break;
            default:
                break;
        }
    }
    
    /**
     * Formats each Book in the library into a String
     *
     * @return the data of each Book in the library in text form.
     */
    @Override
    public String toString(){
        String str = "";
        for(Book b : books)
            str += b;
        return str;
    }
}