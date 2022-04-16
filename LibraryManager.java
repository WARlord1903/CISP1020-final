package LibraryManager;

import java.util.Scanner;

public class LibraryManager {

    public static void main(String[] args) {

        boolean exit = false;
        do {
            System.out.println("1: Search for a Book");
            System.out.println("2: Show Current Book Inventory");
            System.out.println("3: Add a Book");
            System.out.println("4: Issue a Book");
            System.out.println("5: Edit or Delete Books");
            System.out.println("6: Pay Fees");
            System.out.println("7: Exit");
            System.out.print("Enter a Menu Option: ");
            Scanner in = new Scanner(System.in);
            int reponse = in.nextInt();
            if (reponse < 1 || reponse > 7) {
                System.out.println("Enter the correct menu option below");
            }
            switch (reponse) {
                case 1:
                    System.out.println("user can search for a book here");
                    break;
                case 2:
                    System.out.println("a list of all books in text database can be displayed here");
                    break;
                case 3:
                    System.out.println("user can add a book here");
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

