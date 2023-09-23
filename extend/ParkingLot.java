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
import java.awt.event.KeyEvent;

public class ParkingLot {
    static Scanner scanner = new Scanner(System.in);
    static int length;
    static int width;
    static Integer totalLots;
    static Integer occupiedLots;
    static List<Car> cars = new ArrayList<>();
    static List<Bike> bikes = new ArrayList<>();
    static char[][] map;

    public static void displayParkingLotMenu() {
        displayParkingLotMenuText();
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("init")) {
                initParkingLot(scanner);
            } else if (input.equals("view")) {
                displayLot();
                System.out.println("Press any key to return to parkinglot menu");
            } else if (input.equals("menu")) {
                // exit
                break;
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
    public Vehicle getVehicle(String vehicleType) {
        if (vehicleType.toLowerCase().equals("car")) {
            return cars.get(0);
        } else if (vehicleType.toLowerCase().equals("bike")) {
            return bikes.get(0);
        }
        return null;
    }
    public void removeVehicle(Vehicle vehicle) {
        map[vehicle.x][vehicle.y] = '.';
        if (vehicle.Type.toLowerCase().equals("car")) {
            vehicle = cars.get(0);
            cars.remove(0);
        } else if (vehicle.Type.toLowerCase().equals("bike")) {
            vehicle = bikes.get(0);
            bikes.remove(0);
        }
        occupiedLots--;
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

        // calculate total lots amount
        // for origin version ->
        // totalLots = (length - 2) * (width - 2) - 1; 
        // for extend version ->
        totalLots = (length - 2 - (length-2) /2) * (width - 4) - (length - 2 - (length-2) /2)*2;
        occupiedLots = 0;

        // create map
        map = new char[width][length];
        for(int i = 0; i < width; i++) {
            
            for(int j = 0; j < length; j++) {
                if ((i == 2 || i == width - 3) && (j % 2 == 1 && j != 0 && j != length - 1)) {
                    // pillar
                    map[i][j] = 'P';
                }else if ((i == 1 && j == 0) || (i == width - 2 && j == length - 1)) {
                    // entry point
                    map[i][j] = 'D';
                } else if (j == 0 || j == length - 1) {
                    // left and right wall
                    map[i][j] = '|';
                } else if (i == 0 || i == width - 1) {
                    // top and button wall
                    map[i][j] = '-';
                } else if (i == 1 || i == width - 2 || (j % 2 == 0 && j != 0 && j != length - 1)) {
                    map[i][j] = '~';
                } else {
                    map[i][j] = '.';
                }
            }
        }

        System.out.println("Parking Lot Space is setup. Here is the layout -");
        displayLot();
        System.out.println("Press any key to return to parkinglot menu");
        scanner.nextLine();
    }

    public static void displayLot() {
        // diplay
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        return;
    }

    private static void displayParkingLotMenuText(){
        System.out.println("Type 'init' to initialise the parking space");
        System.out.println("Type 'view' to view the layout of the parking space");
        System.out.println("Type 'menu' to return to the main menu");
        System.out.print("> ");
    }

    public static boolean initPark() {
        System.out.println("To park a vehicle provide the details.");
        if (cars.size() == 0 && bikes.size() == 0) {
            System.out.println("No vehicle checked in the parking lot, taking you back to main menu");
            return false;
        }

        boolean isTypeValid = false;
        while(!isTypeValid) {
            System.out.print("> Vehicle Type: ");
            String vehicleType = scanner.nextLine();
            if ((vehicleType.toLowerCase().equals("car") && cars.size() == 0) ||
                (vehicleType.toLowerCase().equals("bike") && bikes.size() == 0)) {
                System.out.println("The vehicle mentioned is not parked in the parking lot.");
            } else {
                isTypeValid = true;
                Vehicle vehicle = null;
                if (vehicleType.toLowerCase().equals("car")) {
                    vehicle = cars.get(0);
                } else if (vehicleType.toLowerCase().equals("bike")) {
                    vehicle = bikes.get(0);
                }
                if (vehicle.x != 1 && vehicle.y != 0) {
                    
                }
                park(vehicle);
            }
        }
        return isTypeValid;
    }

    public static void park(Vehicle vehicle) {
        displayLot();
        if (!(vehicle.x == 1 && vehicle.y == 0)) {
            map[vehicle.x][vehicle.y] = '.';
        }
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("q")) {
                // exit
                if (vehicle.x == 1 && vehicle.y == 0) {
                    return;
                } else {
                    map[vehicle.x][vehicle.y] = vehicle.Type.toUpperCase().charAt(0);
                }
                return;
            } else if (input.equals("w")) {
                // up, -1, 0
                System.out.println("up");
                move(vehicle, -1, 0);
            } else if (input.equals("s")) {
                // down, 1, 0
                System.out.println("down");
                move(vehicle, 1, 0);
            } else if (input.equals("a")) {
                // left, 0, -1
                System.out.println("left");
                move(vehicle, 0, -1);
            } else if (input.equals("d")) {
                // right, 0, 1
                System.out.println("right");
                move(vehicle, 0, 1);
            } else {
                continue;
            }
        }
    }

    private static void move(Vehicle vehicle, int moveX, int moveY) {
        int nextX = vehicle.x + moveX;
        int nextY = vehicle.y + moveY;
        if (nextX < 0 || nextX >= width || nextY < 0 || nextY >= length || !(map[nextX][nextY] == '.' || map[nextX][nextY] == '~')) {
            vehicle.hits += 1;
            if (map[nextX][nextY] == 'P') {
                System.out.println("You have hit the pillar, there will be a damage fee!");
            } else if (map[nextX][nextY] == 'C' || map[nextX][nextY] == 'B') {
                System.out.println("You have hit a vehicle, there will be a damage fee!");
            } else if (map[nextX][nextY] == 'D') {
                System.out.println("You cannot exit the parking lot without checkout.");
            } else {
                System.out.println("You have hit the wall, there will be a damage fee!");
            }
        } else {
            vehicle.x += moveX;
            vehicle.y += moveY;
        }

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < length; j++) {
                if ((map[vehicle.x][vehicle.y] == '.' || map[vehicle.x][vehicle.y] == '~') && vehicle.x == i && vehicle.y == j) {
                    System.out.print(vehicle.Type.toUpperCase().charAt(0));
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
    }
}

