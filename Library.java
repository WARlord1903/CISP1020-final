
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author willi
 */
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
    };

    /**
     * Constructs a new Library instance, which reads and writes to a file at
     * the given filepath.
     * 
     * @author William Reinhardt
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
     * @author William Reinhardt
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
    
    public int getBookIndex(Book book){
        for(int i = 0; i < books.size(); i++){
            if(book.equals(books.get(i)))
                return i;
        }
        System.out.println("No equivalent books found.");
        return -1;
    }

    /**
     * Adds a Book to the library.
     * 
     * @author William Reinhardt
     * 
     * @param book the Book to be added to the library
     */
    public void addBook(Book book){
        books.add(book);
    }
    
    /**
     * Removes the Book at the given index
     * 
     * @author William Reinhardt
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
     * 
     * @author William Reinhardt
     */
    public void readBooks(){
        try{
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                books.add(parseBook(in));
            }
            in.close();
        } catch(FileNotFoundException ex){
            System.out.println("ERROR: File not found.");
        }
    }
    
    /**
     * Internal implementation of Book instantiation from text.
     * 
     * @author William Reinhardt
     * 
     * @param in the Scanner object that scans the file.
     * @return a Book object instantiated from the text in the database file.
     */
    
    private Book parseBook(Scanner in){
        String temp, title = "", author = "", publisher = "", ISBN = "", issuedDate = "", returnDate = "";
        title = in.nextLine();
        while(title.equals(""))
            title = in.nextLine();
        title = title.substring(0, title.length() - 1);
        while(in.hasNextLine()){
            //Note: Because the Scanner reads the next line in the file until it 
            //comes across a line that does not start with a tab, it is 
            //imperative that a single new-line character separates each book
            //entry in the file. This is done internally automatically, so it is
            //best not to manually edit the database file.
            temp = in.nextLine();
            if(!temp.startsWith("\t"))
                break;
            Scanner line = new Scanner(temp);
            temp = line.next();
            switch (temp) {
                case "Author:":
                    author = line.nextLine();
                    author = author.substring(1, author.length());
                    break;
                case "Publisher:":
                    publisher = line.nextLine();
                    publisher = publisher.substring(1, publisher.length());
                    break;
                case "ISBN:":
                    ISBN = line.nextLine();
                    ISBN = ISBN.substring(1, ISBN.length());                
                    break;
                case "IssuedDate:":
                    issuedDate = line.nextLine();
                    issuedDate = issuedDate.substring(1, issuedDate.length());  
                    break;
                case "ReturnDate:":
                    returnDate = line.nextLine();
                    returnDate = returnDate.substring(1, returnDate.length());
                    break;
                default:
                    break;
            }
        }
        return new Book(title, author, publisher, ISBN, issuedDate, returnDate);
    }
    
    /**
     * Writes each Book in the library to the database file.
     * 
     * @author William Reinhardt
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
     * @author William Reinhardt
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
     * Formats each Book in the library into a String
     * 
     * @author William Reinhardt
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