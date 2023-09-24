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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

public class ParkingLotEngine {
    
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotEngine engine = new ParkingLotEngine();
        // Runs the main game loop.
        engine.startProgram(args);
        if (args.length == 2) {
            if (Integer.parseInt(args[0]) < 7 || Integer.parseInt(args[1]) < 7) {
                System.out.println("ParkingLot size cannot be less than 7. Goodbye!");
                return;
            }
            parkingLot.initParkingLot(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        engine.displayWelcomeText();

        //TODO: Implementation here
        engine.displayMenuText(parkingLot);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("help")){
                engine.displayHelpText();
            } else if (input.equals("commands")){
                engine.displayCommandsText();
            } else if (input.equals("menu")){
                engine.displayMenuText(parkingLot);
            } else if (input.equals("parkinglot")){
                parkingLot.displayParkingLotMenu(scanner);
                engine.displayMenuText(parkingLot);
            } else if (input.equals("init")){
                // TODO should be remove
                System.out.print("> ");
                parkingLot.initParkingLot(scanner);
                ParkingLot.displayParkingLotMenu(scanner);
            } else if (input.equals("checkin")) {
                engine.checkin(scanner, parkingLot);
            } else if (input.equals("park")) {
                parkingLot.initPark(scanner);
                engine.displayMenuText(parkingLot);
            } else if (input.equals("parkingfeelog")) {
                parkingLot.displayParkingFeeLog();
                engine.displayMenuText(parkingLot);
            } else if (input.equals("checkout")) {
                engine.checkout(scanner, parkingLot);
                engine.displayMenuText(parkingLot);
            } else if (input.equals("exit")){
                engine.displayExitText();
                break;
            } else if(input.equals("")) {
                engine.displayMenuText(parkingLot);
            }
            else {
                System.out.println("Command not found!");
                System.out.println("Type 'commands' to list all the available commands");
                System.out.println("Type 'menu' to return to the main menu");
                System.out.print("> ");
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
        + "\nparkingfeelog: view the transaction log for parking lot."
        + "\nexit: To exit the program.");

        System.out.println("\nType 'commands' to list all the available commands" + 
        "\nType 'menu' to return to the main menu");
        System.out.println("> ");
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
            if (vehicleType.toLowerCase().equals("car") || vehicleType.toLowerCase().equals("bike")
                || vehicleType.toLowerCase().equals("truck") || vehicleType.toLowerCase().equals("motorbike")) {
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

        // keyin entry date
        String datePattern = "^(19\\d\\d|[0-9][0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        String dateString = "";
        boolean isDateValid = false;
        while (!isDateValid) {
            System.out.print("Date of entry: ");
            dateString = scanner.nextLine();
            Pattern regexPattern = Pattern.compile(datePattern);
            Matcher matcher = regexPattern.matcher(dateString);
            if (matcher.matches()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
                    Date date = sdf.parse(dateString);

                    // check range
                    Date minDate = sdf.parse("1970-01-01");
                    Date maxDate = sdf.parse("2099-12-31");
                    
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        isDateValid = true;
                    } else {
                        System.out.println("Incorrect date format, please enter date in yyyy-MM-dd format again between 1970-01-01 and 2099-12-31!");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Incorrect date format, please enter date in yyyy-MM-dd format again!");
            }
        }

        // keyin entry time
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


        Vehicle vehicle = null;
        if (vehicleType.toLowerCase().equals("car")){
            vehicle = new Car(vehicleType, regnId, Model, Colour, time, dateString);
            parkingLot.vehicles.get("car").add(vehicle);
        } else if (vehicleType.toLowerCase().equals("bike")){
            vehicle = new Bike(vehicleType, regnId, Model, Colour, time, dateString);
            parkingLot.vehicles.get("bike").add(vehicle);
        } else if (vehicleType.toLowerCase().equals("truck")){
            vehicle = new Truck(vehicleType, regnId, Model, Colour, time, dateString);
            parkingLot.vehicles.get("truck").add(vehicle);
        } else if (vehicleType.toLowerCase().equals("motorbike")){
            vehicle = new Motorbike(vehicleType, regnId, Model, Colour, time, dateString);
            parkingLot.vehicles.get("motorbike").add(vehicle);
        }
        parkingLot.idTypeMap.put(regnId, vehicle.Type);
        parkingLot.occupiedLots++;
        displayMenuText(parkingLot);
    }

    private boolean validCheckInVehicleType(ParkingLot parkingLot, String vehicleType) {
        if (vehicleType.toLowerCase().equals("car") && parkingLot.vehicles.get("car").size() == 1) {
            System.out.print("The parking already has a car parked. Please come back later. Taking you back to main menu.");
            return false;
        } else if (vehicleType.toLowerCase().equals("bike") && parkingLot.vehicles.get("bike").size() == 1) {
            System.out.print("The parking already has a bike parked. Please come back later. Taking you back to main menu.");
            return false;
        }
        return true;
    }

    private boolean validSpace(ParkingLot parkingLot) {
        if (parkingLot.vehicles.get("car").size() == 1 && parkingLot.vehicles.get("bike").size() == 1) {
            System.out.print("The parking already has a car and a bike parked. Please come back later. Taking you back to main menu.");
            return false;
        }
        return true;
    }

    private void checkout(Scanner scanner, ParkingLot parkingLot) {
        if (parkingLot.totalLots == null || parkingLot.occupiedLots == null) {
            System.out.print("Invalid command! The parking is empty. Taking you back to main menu.");
            return;
        }

        System.out.println("Please enter the vehicle details");

        Vehicle vehicle = null;
        String regnId = "";
        boolean isCheckOutValid = false;
        while(!isCheckOutValid) {
            System.out.print("Regn Id:  ");
            regnId = scanner.nextLine();
            if (!parkingLot.idTypeMap.containsKey(regnId)) {
                System.out.println("The selected vehicle type is not present in the parking lot. Taking you back to main menu");
            } else {
                String type = parkingLot.idTypeMap.get(regnId);
                List<Vehicle> vehiclelist = parkingLot.vehicles.get(type);
                for (Vehicle v: vehiclelist) {
                    if (v.Id.equals(regnId)) {
                        if ((v.x == 1 && v.y == 0) || (v.x == parkingLot.width - 2 && v.y == parkingLot.length - 1)) {
                            vehicle = v;
                            isCheckOutValid = true;
                        } else {
                            System.out.println("The selected vehicle type is not at the checkout door. Please proceed to checkout door. Taking you back to main menu.");
                            return;
                        }
                        
                    }
                }
                if (!isCheckOutValid) System.out.println("The selected vehicle type is not present in the parking lot. Taking you back to main menu");
            }
        }

        boolean isExitBiggerThenEnrty = false;
        String exitDateString = "";
        String exitTime = "";
        while (!isExitBiggerThenEnrty) {
            // keyin entry date
            String datePattern = "^(19\\d\\d|[0-9][0-9][0-9][0-9])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
            boolean isDateValid = false;
            while (!isDateValid) {
                System.out.print("Date of entry: ");
                exitDateString = scanner.nextLine();
                Pattern regexPattern = Pattern.compile(datePattern);
                Matcher matcher = regexPattern.matcher(exitDateString);
                if (matcher.matches()) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                        Date date = sdf.parse(exitDateString);

                        // check range
                        Date minDate = sdf.parse("1970-01-01");
                        Date maxDate = sdf.parse("2099-12-31");
                        
                        if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                            isDateValid = true;
                        } else {
                            System.out.println("Incorrect date format, please enter date in yyyy-MM-dd format again between 1970-01-01 and 2099-12-31!");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Incorrect date format, please enter date in yyyy-MM-dd format again!");
                }
            }
            
            // keyin exit time
            String pattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
            boolean isTimeValid = false;
            while (!isTimeValid) {
                System.out.print("Time of exit: ");
                exitTime = scanner.nextLine();
                Pattern regexPattern = Pattern.compile(pattern);
                Matcher matcher = regexPattern.matcher(exitTime);
                if (matcher.matches()) {
                    isTimeValid = true;
                } else {
                    System.out.println("Incorrect time format, please enter time in HH:mm format again!");
                }
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date entryDate = sdf.parse(vehicle.DateEntry + " " + vehicle.TimeEntry);
                Date exitDate = sdf.parse(exitDateString + " " + exitTime);
                if (exitDate.compareTo(entryDate) > 0) {
                    isExitBiggerThenEnrty = true;
                } else {
                    System.out.println("Checkout datetime cannot be less than checkin datetime for the vehicle. Please re-enter.");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Please verify your details.");
        System.out.println("Total number of hours: " + vehicle.getHours(exitDateString, exitTime));
        // Total number of hits
        System.out.println("Total number of hits: " + vehicle.hits);
        System.out.println("Vehicle Typr: " + vehicle.Type);
        System.out.println("Regn Id: " + vehicle.Id);

        int totalFee = vehicle.getFee(exitDateString, exitTime);
        System.out.println("Total Parking fee: $" + totalFee);

        // remove
        System.out.println("Type Y to accept the fee or menu to return to main menu");
        System.out.print("> ");
        String answer = scanner.nextLine();
        if (answer.equals("N")) {
            while (!answer.equals("Y")) {
                System.out.print("You cannot checkout your vehicle. Please accept the fee by pressing Y or type menu to return to main menu and park your vehicle.");
                System.out.print("> ");
                answer = scanner.nextLine();
            }
        }
        parkingLot.removeVehicle(vehicle, exitDateString, exitTime);
        System.out.print("Thank you for visiting Java Parking Lot. See you next time!");
    }

    // private int getFee(String type, int hours, int hits) {
    //     if (type.toLowerCase().equals("car")) {
    //         return hours * 4 + hits * 20;
    //     } else if (type.toLowerCase().equals("bike")) {
    //         return hours * 2 + hits * 10;
    //     }
    //     return 0;
    // }
}
