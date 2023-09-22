 /**
 * TODO: Write a comment describing your class here.
 First, to create a menu to make user know what commands can use, and get length and width because it's need to use in many functions,
 Create a function which Implement the parkingot map calculate
 initParkingLot to init the parking lot and determine the size .
 * @author TODO: Fill in your name, university email, and student number here.
 Name: Chenhsuan Wang
 University email: chenhsuanw@student.unimelb.edu.au
 Student number: 1279195
 *
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class ParkingLot {
    static Scanner scanner = new Scanner(System.in);
    static int length;
    static int width;
    static Integer totalLots;
    static Integer occupiedLots;
    static List<Car> cars = new ArrayList<>();
    static List<Bike> bikes = new ArrayList<>();

    public static void displayParkingLotMenu() {
        displayParkingLotMenuText();
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("init")) {
                initParkingLot(scanner);
            } else if (input.equals("view")) {
                displayLot(length, width);
            } else if (input.equals("menu")) {
                return;
            } else {
                if (!input.equals("")) System.out.println("Command not found!");
                displayParkingLotMenuText();
            }
        }
    }

    public int getLength(){
        return length;
    }
    public int getWidth(){
        return width;
    }
    public static void displayLot(int length, int width) {
        // calculate total lots amount
        totalLots = (length - 2) * (width - 2) - 1;
        occupiedLots = 0;

        // prints parking lot view
        System.out.print("|");
        for(int i =0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int i = 1; i < width-1; i++) {
            if (i< 2) {
                System.out.print("D");
            } else {
                System.out.print("|");
            }
    
            for (int j = 0; j < length-2; j++) {
                if (j == Math.ceil(length/2)-2 && i == Math.ceil(width/2)-1) {
                    System.out.print("P");
                } else {
                    System.out.print(".");
                }
            }

            if (i == width-2){
                System.out.print("D");
            } else {
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.print("|");
        for (int i =0; i < length - 2; i++){
            System.out.print("-");
        }
        System.out.println("|");
        System.out.println("Press any key to return to parkinglot menu");
        scanner.nextLine();
    }

    public static void initParkingLot(Scanner scanner){
        // if occupiedLots != 0, return error
        if (occupiedLots != null && occupiedLots > 0) {
            System.out.println("init There are vehicles in the Parking Lot, you cannot change the space of the parking lot at the moment.");
            displayParkingLotMenuText();
            return;
        }
        System.out.println("Please enter the length of the parking lot.");
        System.out.print("> ");
        int newLength = scanner.nextInt();
        while(newLength < 5){
            System.out.println("The length of the parking lot cannot be less than 5. Please re-enter.");
            System.out.print("> ");
            newLength = scanner.nextInt(); 
        }
        System.out.println("Please enter the width of the parking lot.");
        System.out.print("> ");
        int newWidth = scanner.nextInt();
        while (newWidth < 5){
            System.out.println("The width of the parking lot cannot be less than 5. Please re-enter.");
            System.out.print("> ");
            newWidth = scanner.nextInt();
        }
        
        length = newLength;
        width = newWidth;

        System.out.println("Parking Lot Space is setup. Here is the layout -");
        displayLot(length, width);
    }

    private static void displayParkingLotMenuText(){
        System.out.println("Type 'init' to initialise the parking space");
        System.out.println("Type 'view' to view the layout of the parking space");
        System.out.println("Type 'menu' to return to the main menu");
        System.out.print("> ");
    }
}

