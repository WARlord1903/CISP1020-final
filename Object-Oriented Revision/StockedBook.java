/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cisp.project;

import java.util.ArrayList;
import java.util.Comparator;
/**
 *
 * @author Cristian
 */
public class StockedBook extends Book{
    private int quantity;
    private ArrayList<IssuedBook> issuedBooks;

    /** 
     * Constructs new instance of Book with only a title.
     * 
     * @param title the title of the book
     */    
    public StockedBook(String title){
    super(title);
    this.quantity = 1;
    this.issuedBooks = new ArrayList<IssuedBook>();
    }

    /** 
     * Constructs new instance of Book with only a title and a quantity.
     * 
     * @param title the title of the book
     * @param quantity number of books the library owns
     */
    public StockedBook(String title, int quantity){
    super(title);
    this.quantity = quantity;
    this.issuedBooks = new ArrayList<IssuedBook>();
    }
    
    /**
     * Constructs new instance of Book with a title, author, publisher, and ISBN.
     * 
     * @param title the title of the book
     * @param author the author of the book
     * @param publisher the publisher of the book
     * @param ISBN the ISBN of the book
     */
    public StockedBook(String title, String author, String publisher, String ISBN) {
        super(title, author, publisher, ISBN);
        this.quantity = 1;
        this.issuedBooks = new ArrayList<IssuedBook>();
    }    
   
    /**
     * Constructs new instance of Book with a title, author, publisher, ISBN, and quantity.
     * 
     * @param title the title of the book
     * @param author the author of the book
     * @param publisher the publisher of the book
     * @param ISBN the ISBN of the book
     * @param quantity number of books the library owns
     */
    public StockedBook(String title, String author, String publisher, String ISBN, int quantity) {
        super(title, author, publisher, ISBN);
        this.quantity = quantity;
        this.issuedBooks = new ArrayList<IssuedBook>();
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
     * Sets the quantity of an instance of Book
     * 
     * @param quantity the new quantity of this instance of Book
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void increaseBy(int quantity)
    {
        this.quantity += quantity;
    }
    
    public void decreaseBy(int quantity)
    {
        this.quantity -= quantity;
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
     * Tells whether or not the selected book is in stock.
     * 
     * @return whether or not the Book is in stock (quantity is greater than 0)
     */
    public boolean inStock(){
        return quantity - issuedBooks.size() > 0;
    }
    
    /**
     * Shows the number of books available.
     * 
     * @return the number of books available
     */
    public int numberAvailable(){
        return quantity - issuedBooks.size();
    }    

    public void issueBook(IssuedBook b)
    {
        issuedBooks.add(b);
    }
    
    public void removeBook(Book b)
    {
        for(Book i:issuedBooks)
        {
            if(i.equals(b))
            {
                issuedBooks.remove(i);
                break;
            }                  
        }    
    }
    
    public String displayIssuedBooks()
    {
        String str = "";
        
        for(IssuedBook i:issuedBooks)
        {
            str += i.getBorrower().toString() + "\n";
            str += i.toString();
            str += "Days Until Due:" + i.getDaysUntil();
        }
        return str;
    }
    
    /**
     * Formats the book into a string that can be easily read from/written to 
     * a text database.
     * 
     * @return a String containing the Book data in text form
     */    
    public String fileFormat(){
        return "StockedBook:\n\t" + super.fileFormat() + "\n\t\tQuantity: "  + quantity + "\n\tend\n";
    }
    
    /**
     * Formats a string that contains the book title and author.
     * 
     * @return a string that contains the book title and author.
     */   
    public String toString(){
        return super.toString() + String.format(" (Quantity : %d, Available: %d)\n", quantity, numberAvailable());
    }
}

/**
 * Comparator to sort books by quantity
 */
class BookCompareQuantity implements Comparator<StockedBook>{
    private boolean descending;
    BookCompareQuantity(boolean descending){
        this.descending = descending;
    }
    
    @Override
    public int compare(StockedBook b1, StockedBook b2){
        if(descending)
            return b2.getQuantity() - b1.getQuantity();
        else
            return b1.getQuantity() - b2.getQuantity();
    }
}