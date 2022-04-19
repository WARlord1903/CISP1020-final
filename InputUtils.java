package LibraryManager;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for input validation.
 */
public class InputUtils {

    /**
     * Ensures that the user inputs an integer.
     * 
     * @param msg a message to be displayed in each iteration of the loop
     * @return the integer entered by the user
     */
    public static int inputInt(String msg){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print(msg);
            if(!in.hasNextInt())
                System.out.println("ERROR: Invalid input. Please enter an integer.");
            else
                return in.nextInt();
            in.next();
        }
    }
    
    /**
     * Ensures that the user inputs an integer of a minimum value.
     * 
     * @param msg a message to be displayed in each iteration of the loop
     * @param min the minimum value for the input to be accepted
     * @return the integer entered by the user
     */
    public static int inputInt(String msg, int min){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print(msg);
            if(!in.hasNextInt())
                System.out.println("ERROR: Invalid input. Please enter an integer.");
            else{
                int i = in.nextInt();
                if(i < min){
                    System.out.printf("ERROR: Please enter an integer of at least %d\n", min);
                    continue;
                }
                else
                    return i;
            }
            in.next();
        }
    }
    
    /**
     * Ensures that the user inputs an integer of a minimum and maximum value.
     *
     * @param msg a message to be displayed in each iteration of the loop
     * @param min the minimum value for the input to be accepted
     * @param max the maximum value for the input to be accepted
     * @return the integer entered by the user
     */
    public static int inputInt(String msg, int min, int max){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print(msg);
            if(!in.hasNextInt())
                System.out.println("ERROR: Invalid input. Please enter an integer.");
            else{
                int i = in.nextInt();
                if(i < min || i > max){
                    System.out.printf("ERROR: Please enter an integer between %d and %d\n", min, max);
                    continue;
                }
                else
                    return i;
            }
            in.next();
        }
    }
    
    /**
     * Ensures that the user inputs a string that can be parsed into a LocalDate
     * object.
     * 
     * @param msg a message to be displayed in each iteration of the loop.
     * @return the LocalDate parsed from the user input
     */
    public static LocalDate inputDate(String msg){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print(msg);
            String dateStr = in.next();
            try{
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch(DateTimeException ex){
                System.out.println("ERROR: Please enter a date of the format \"MM/dd/yyyy\".");
                continue;
            }
        }
    }
}
