import java.util.Comparator;

public class Book{
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private int quantity;

    /** 
     * Constructs new instance of Book with only a title.
     * 
     * @param title the title of the book
     * @param quantity the quantity of the book available
     */
    public Book(String title, int quantity) {
        this.title = title;
        this.author = "";
        this.publisher = "";
        this.ISBN = "";
        this.quantity = quantity;
    }

    /**
     * Constructs new instance of Book with a title, author, publisher, and ISBN.
     * 
     * @param title the title of the book
     * @param author the author of the book
     * @param publisher the publisher of the book
     * @param ISBN the ISBN of the book
     * @param quantity the quantity of the book available
     */
    public Book(String title, String author, String publisher, String ISBN, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    /**
     * Gets the title of an instance of Book
     * 
     * @return the title of this instance of Book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of an instance of Book
     * 
     * @return the author of this instance of Book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the publisher of an instance of Book
     * 
     * @return the publisher of this instance of Book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Gets the ISBN of an instance of Book
     * 
     * @return the publisher of this instance of Book
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Gets the quantity of an instance of Book
     * 
     * @return the quantity of this instance of Book
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the title of an instance of Book
     * 
     * @param title the new title of this instance of Book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the author of an instance of Book
     *
     * @param author the new author of this instance of Book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the publisher of an instance of Book
     * 
     * @param publisher the new publisher of this instance of Book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Sets the ISBN of an instance of Book
     * 
     * @param ISBN the new ISBN of this instance of Book
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Sets the quantity of an instance of Book
     * 
     * @param quantity the new quantity of this instance of Book
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increments the quantity of an instance of Book
     */
    public void increment(){
        this.quantity++;
    }
    
    /**
     * Decrements the quantity of an instance of Book
     */
    public void decrement(){
        this.quantity--;
    }
    
    /**
     * Compares the value of this instance of Book and another object.
     * 
     * @param o the object to compare to
     * @return whether or not this instance of Book is equal to the passed 
     * object
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Book))
            return false;
        Book book = (Book) o;
        return title.equals(book.title) &&
               author.equals(book.author) &&
               publisher.equals(book.publisher) &&
               ISBN.equals(book.ISBN);
    }
    
    /**
     * Formats the book into a string that can be easily read from/written to 
     * a text database.
     * 
     * @return a String containing the Book data in text form
     */
    public String fileFormat(){
        String str = "Book:\n\t" + title + ":";
        if(!author.equals(""))
            str += "\n\t\tAuthor: " + author;
        if(!publisher.equals(""))
            str += "\n\t\tPublisher: " + publisher;
        if(!ISBN.equals(""))
            str += "\n\t\tISBN: " + ISBN;
        return str + "\n\t\tQuantity: "  + quantity + "\n";
    }
    
    public String studentFormat(){
        String str = "BookTitle: " + title;
        if(!author.equals(""))
            str += "\n\tBookAuthor: " + author;
        if(!publisher.equals(""))
            str += "\n\tBookPublisher: " + publisher;
        if(!ISBN.equals(""))
            str += "\n\tBookISBN: " + ISBN;
        return str;
    }
    
    @Override
    public String toString(){
        return String.format("%s by %s\n", title, author);
    }
}

/**
 * Comparator to sort books by titles
 */
class BookCompareTitle implements Comparator<Book>{
    private boolean descending;
    BookCompareTitle(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Book b1, Book b2){
        if(descending)
            return b2.getTitle().compareTo(b1.getTitle());
        else
            return b1.getTitle().compareTo(b2.getTitle());
    }
}

/**
 * Comparator to sort books by authors
 */
class BookCompareAuthor implements Comparator<Book>{
    private boolean descending;
    BookCompareAuthor(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Book b1, Book b2){
        if(descending)
            return b2.getAuthor().compareTo(b1.getAuthor());
        else
            return b1.getAuthor().compareTo(b2.getAuthor());
    }
}

/**
 * Comparator to sort books by publishers
 */
class BookComparePublisher implements Comparator<Book>{
    private boolean descending;
    BookComparePublisher(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Book b1, Book b2){
        if(descending)
            return b2.getPublisher().compareTo(b1.getPublisher());
        else
            return b1.getPublisher().compareTo(b2.getPublisher());
    }
}

/**
 * Comparator to sort books by ISBN
 */
class BookCompareISBN implements Comparator<Book>{
    private boolean descending;
    BookCompareISBN(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Book b1, Book b2){
        if(descending)
            return b2.getISBN().compareTo(b1.getISBN());
        else
            return b1.getISBN().compareTo(b2.getISBN());
    }
}

/**
 * Comparator to sort books by quantity
 */
class BookCompareQuantity implements Comparator<Book>{
    private boolean descending;
    BookCompareQuantity(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(Book b1, Book b2){
        if(descending)
            return b2.getQuantity() - b1.getQuantity();
        else
            return b1.getQuantity() - b2.getQuantity();
    }
}