/**
 * TODO: Write a comment describing your class here.
 create a class about parkinglot and parkinlot engine
 create functions to display different words in different commands.
 * @author TODO: Fill in your name, university email, and student number here.
 * 
 Name: Chenhsuan Wang
 University email: chenhsuanw@student.unimelb.edu.au
 Student number: 1279195
 */
import java.util.Scanner;
import java.util.regex.*;

public class ParkingLotEngine {
    
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotEngine engine = new ParkingLotEngine();
        // Runs the main game loop.
        engine.startProgram(args);
        engine.displayWelcomeText();
        Scanner scanner = new Scanner(System.in);
        //TODO: Implementation here
        int emptyLots = 47;
        // System.out.println("Empty Lots: "+"[None]"+" | Occupied: "+"[None]");
        // System.out.println("Please enter a command to continue.");
        // System.out.println("Type 'help' to learn how to get started.");
        // System.out.print("> ");
        while (true) {
            engine.displayMenuText(parkingLot);
            String input = scanner.nextLine();
            if (input.equals("help")){
                engine.displayHelpText();
            } else if (input.equals("commands")){
                engine.displayCommandsText();
            } else if (input.equals("menu")){
                engine.displayMenuText(parkingLot);
            } else if (input.equals("parkinglot")){
                parkingLot.displayParkingLotMenu();
            } else if (input.equals("init")){
                // TODO should be remove
                System.out.print("> ");
                parkingLot.initParkingLot(scanner);
                System.out.println("");
                ParkingLot.displayParkingLotMenu();
                // System.out.println("> Empty Lots: " + parkingLot.totalLots + " | Occupied: 0");
                // System.out.println("Please enter a command to continue.");
                // System.out.println("Type 'help' to learn how to get started.");

            } else if (input.equals("view")){
                // TODO should be remove
                int length = parkingLot.getLength();
                int width = parkingLot.getWidth();
                parkingLot.displayLot(length, width);
            } else if (input.equals("checkin")){
                engine.checkin(scanner, parkingLot);
            }
            else if (input.equals("exit")){
                engine.displayExitText();
                break;
            }
            else if(input.equals("")) {
                // System.out.println("Type 'commands' to list all the available commands");
                // System.out.println("Type 'menu' to return to the main menu");
                // System.out.print("> ");
                engine.displayMenuText(parkingLot);
            }
            else {
                System.out.println("Command not found!\n");
                System.out.println("Type 'commands' to list all the available commands");
                System.out.println("Type 'menu' to return to the main menu");
                System.out.println("> ");
            }
        }

    }
	
	
    /*
     *  Start with the main menu here.
    */
    private void startProgram(String[] args) {

        // Print out the title text.
    }
	
    /*
     *  Displays the welcome text.
    */
    private void displayWelcomeText() {

        String titleText =
                " _     _  _______  ___      _______  _______  __   __  _______ \n"+
                        "| | _ | ||       ||   |    |       ||       ||  |_|  ||       |\n"+
                        "| || || ||    ___||   |    |      _||   _   ||       ||    ___|\n"+
                        "|       ||   |___ |   |    |     |  |  | |  ||       ||   |___ \n"+
                        "|       ||    ___||   |___ |     |  |  |_|  || ||_|| ||    ___|\n"+
                        "|   _   ||   |___ |       ||     |_ |       || |\\/|| ||   |___ \n"+
                        "|__| |__||_______||_______||_______||_______||_|   |_||_______|\n" +
                        "_________________________ TO JAVA PARKING _____________________";

        System.out.println(titleText);
        System.out.println();

    }

    private void displayHelpText() {
        System.out.println();
        System.out.println("Type 'commands' to list all the available commands");
        System.out.println("Type 'menu' to return to the main menu");
        System.out.print("> ");
    }

    private void displayCommandsText(){
        System.out.println();
        System.out.println("help: shows you list of commands that you can use."
        + "\nparkinglot: initialise the space for parking lot or view the layout of parking lot." 
        + "\ncheckin: add your car details while entering the parking lot."
        + "\npark: park your car to one of the empty spot."
        + "\ncheckout: view the parking fee while exiting the parking lot."
        + "\nexit: To exit the program.");

        System.out.println("\nType 'commands' to list all the available commands" + 
        "\nType 'menu' to return to the main menu");
    }
    private void displayMenuText(ParkingLot parkingLot){
        System.out.println();
        if (parkingLot.totalLots == null || parkingLot.occupiedLots == null) {
            System.out.println("Empty Lots: "+"[None]"+" | Occupied: "+"[None]");
        } else {
            int emptyLots = parkingLot.totalLots - parkingLot.occupiedLots;
            System.out.println("> Empty Lots: " + emptyLots + " | Occupied: " + parkingLot.occupiedLots);
        }
        System.out.println("Please enter a command to continue.");
        System.out.println("Type 'help' to learn how to get started.");
        System.out.print("> ");
    }




    private void displayExitText(){
        System.out.println("> Good bye from the Java Parking Lot! See you next time!");
    }

    private void displayNotFoundText(){
        System.out.println("Command not found!");
        System.out.println("Type 'commands' to list all the available commands");
        System.out.println("Type 'menu' to return to the main menu");
        System.out.print("> ");
    }

    private void displayParkingLotMenu(){
        System.out.println("Type 'init' to initialise the parking space");
        System.out.println("Type 'view' to view the layout of the parking space");
        System.out.println("Type 'menu' to return to the main menu");
        System.out.print("> ");
    }

    private void checkin(Scanner scanner, ParkingLot parkingLot) {
        if (parkingLot.totalLots == null || parkingLot.occupiedLots == null) {
            System.out.print("The parking lot hasn't been initialised. Please set up a space for the parking lot. Taking you back to main menu.");
            return;
        }

        // space check
        if (!validSpace(parkingLot)) return;

        System.out.println("Please enter the vehicle details");
        // key in vehicle type
        String vehicleType = "";
        boolean isTypeValid = false;
        while (!isTypeValid) {
            System.out.print("Vehicle Type: ");
            vehicleType = scanner.nextLine();
            if (vehicleType.toLowerCase().equals("car") || vehicleType.toLowerCase().equals("bike")) {
                isTypeValid = true;
            } else {
                System.out.println("Invalid detail, please enter detail again!");
            }
        }

        // valid Vehicle Type is not full (one car, one bike)
        if (!validCheckInVehicleType(parkingLot, vehicleType)) return;

        // key in regn id
        String regnId = "";
        boolean isRegnIdValid = false;
        while (!isRegnIdValid) {
            System.out.print("Regn Id: ");
            regnId = scanner.nextLine();
            if (regnId.length() == 6) {
                isRegnIdValid = true;
            } else {
                System.out.println("Invalid detail, please enter detail again!");
            }
        }
        System.out.print("Vehicle Model: ");
        String Model = scanner.nextLine();
        System.out.print("Vehicle Colour: ");
        String Colour = scanner.nextLine();

        // key in time
        String pattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        String time = "";
        boolean isTimeValid = false;
        while (!isTimeValid) {
            System.out.print("Time of entry: ");
            time = scanner.nextLine();
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(time);
            if (matcher.matches()) {
                isTimeValid = true;
            } else {
                System.out.println("Incorrect time format, please enter time in HH:mm format again!");
            }
        }

        if (vehicleType.toLowerCase().equals("car")){
            Car car = new Car(vehicleType, regnId, Model, Colour, time);
            parkingLot.cars.add(car);
        } else if (vehicleType.toLowerCase().equals("bike")){
            Bike bike = new Bike(vehicleType, regnId, Model, Colour, time);
            parkingLot.bikes.add(bike);
        }
        parkingLot.occupiedLots++;
        displayMenuText(parkingLot);
    }

    private boolean validCheckInVehicleType(ParkingLot parkingLot, String vehicleType) {
        if (vehicleType.toLowerCase().equals("car") && parkingLot.cars.size() == 1) {
            System.out.print("The parking already has a car parked. Please come back later. Taking you back to main menu.");
            return false;
        } else if (vehicleType.toLowerCase().equals("bike") && parkingLot.bikes.size() == 1) {
            System.out.print("The parking already has a bike parked. Please come back later. Taking you back to main menu.");
            return false;
        }
        return true;
    }

    private boolean validSpace(ParkingLot parkingLot) {
        if (parkingLot.cars.size() == 1 && parkingLot.bikes.size() == 1) {
            System.out.print("The parking already has a car and a bike parked. Please come back later. Taking you back to main menu.");
            return false;
        }
        return true;
    }

    private checkout
}
