import java.util.Comparator;

public class Book{
    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private String issuedDate;
    private String returnDate;

    /** 
     * Constructs new instance of Book with only a title.
     * 
     * @author William Reinhardt
     * 
     * @param title the title of the book
     */
    public Book(String title) {
        this.title = title;
        this.author = "";
        this.publisher = "";
        this.ISBN = "";
        this.issuedDate = "";
        this.returnDate = "";
    }

    /**
     * Constructs new instance of Book with a title, author, publisher, and ISBN.
     * 
     * @author William Reinhardt
     * 
     * @param title the title of the book
     * @param author the author of the book
     * @param publisher the publisher of the book
     * @param ISBN the ISBN of the book
     */
    public Book(String title, String author, String publisher, String ISBN) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.issuedDate = "";
        this.returnDate = "";
    }

    /**
     * Constructs new instance of Book with a title, author, publisher, ISBN,
     * date issued, and return date.
     * 
     * @author William Reinhardt
     * 
     * @param title the title of the book
     * @param author the author of the book
     * @param publisher the publisher of the book
     * @param ISBN the ISBN of the book
     * @param issuedDate the date the book was issued
     * @param returnDate the date for the book to be returned
     */
    public Book(String title, String author, String publisher, String ISBN, String issuedDate, String returnDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
    }

    /**
     * Gets the title of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @return the title of this instance of Book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of an instance of Book
     *
     * @author William Reinhardt
     * 
     * @return the author of this instance of Book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the publisher of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @return the publisher of this instance of Book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Gets the ISBN of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @return the publisher of this instance of Book
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Gets the Issued date of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @return the issued date of this instance of Book
     */
    public String getIssuedDate() {
        return issuedDate;
    }

    /**
     * Gets the return date of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @return the return date of this instance of Book
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the title of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @param title the new title of this instance of Book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the author of an instance of Book
     * 
     * @author William Reinhardt
     *
     * @param author the new author of this instance of Book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the publisher of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @param publisher the new publisher of this instance of Book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Sets the ISBN of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @param ISBN the new ISBN of this instance of Book
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Sets the issued date of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @param issuedDate the new issued date of this instance of Book
     */
    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    /**
     * Sets the return date of an instance of Book
     * 
     * @author William Reinhardt
     * 
     * @param returnDate the new return date of this instance of Book
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Compares the value of this instance of Book and another object.
     * 
     * @author William Reinhardt
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
     * @author William Reinhardt
     * 
     * @return a String containing the Book data in text form
     */
    @Override
    public String toString(){
        String str = title + ":";
        if(!author.equals(""))
            str += "\n\tAuthor: " + author;
        if(!publisher.equals(""))
            str += "\n\tPublisher: " + publisher;
        if(!ISBN.equals(""))
            str += "\n\tISBN: " + ISBN;
        if(!issuedDate.equals(""))
            str += "\n\tIssuedDate: " + issuedDate;
        if(!returnDate.equals(""))
            str += "\n\tReturnDate: " + returnDate;
        return str + "\n";
    }
}

class CompareTitle implements Comparator<Book>{
    @Override
    public int compare(Book b1, Book b2){
        return b1.getTitle().compareTo(b2.getTitle());
    }
}

class CompareTitleDescending implements Comparator<Book>{
    @Override
    public int compare(Book b1, Book b2){
        return b2.getTitle().compareTo(b1.getTitle());
    }
}

class CompareAuthor implements Comparator<Book>{
    @Override
    public int compare(Book b1, Book b2){
        return b1.getAuthor().compareTo(b2.getAuthor());
    }
}

class CompareAuthorDescending implements Comparator<Book>{
    @Override
    public int compare(Book b1, Book b2){
        return b2.getAuthor().compareTo(b1.getAuthor());
    }
}

class ComparePublisher implements Comparator<Book>{
    @Override
    public int compare(Book b1, Book b2){
        return b1.getPublisher().compareTo(b2.getPublisher());
    }
}

class ComparePublisherDescending implements Comparator<Book>{
    @Override
    public int compare(Book b1, Book b2){
        return b2.getPublisher().compareTo(b1.getPublisher());
    }
}