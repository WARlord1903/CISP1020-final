package LibraryManager;

import java.util.Scanner;

public class LibraryManager {

    public static void main(String[] args) {

        boolean exit = false;
        do {
            Library lib = new Library("Info.txt");
            lib.readData();
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
                    System.out.println("user can search for a book here");
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

