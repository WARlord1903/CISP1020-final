package mainclassforproject;

import java.util.Scanner;

public class MainClassForProject {

    public static void main(String[] args) {

        boolean exit = false;
        do {
            System.out.println("1: Search for a book");
            System.out.println("2: Show Current Book Inventory");
            System.out.println("3: Add a Book");
            System.out.println("4: Issue a Book");
            System.out.println("5: Pay Fees");
            System.out.println("6: Exit");
            System.out.print("Enter a Menu Option: ");
            Scanner in = new Scanner(System.in);
            int reponse = in.nextInt();
            if (reponse < 1 || reponse > 6) {
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
                    System.out.println("fees can be displayed and invoices can be printed here");
                    break;
                case 6:
                    exit = true;
                    System.out.println("Have a nice day :)");
                    break;
            }

        } while (!exit);

    }
}
