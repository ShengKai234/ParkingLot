/**
 * TODO: Write a comment describing your class here.
 Vehicles have these objects to record the type, id, model, colour and when they entry.
 and need to get the entry time to calculate the fee.
 * @author TODO: Fill in your name, university email, and student number here.
 Name: Chenhsuan Wang
 University email: chenhsuanw@student.unimelb.edu.au
 Student number: 1279195
 *
 */
public class Bike extends Vehicle {

    int parkingFee = 2;
    int hitFee = 0;
    int overnightFee = 5;

    public Bike(String Type, String Id, String Model,String Colour,String TimeEntry, String DateEntry){
        super(Type, Id, Model, Colour, TimeEntry, DateEntry);
        super.parkingFee = this.parkingFee;
        super.hitFee = this.hitFee;
        super.overnightFee = this.overnightFee;
    }

    @Override
    public int getFee(String exitTimeStr) {
        String[] exitTime = exitTimeStr.split(":");
        String[] entryTime = this.TimeEntry.split(":");
        Integer exitMin = Integer.parseInt(exitTime[0]) * 60 + Integer.parseInt(exitTime[1]);
        Integer entryMin = Integer.parseInt(entryTime[0]) * 60 + Integer.parseInt(entryTime[1]);
        int hours = (int) Math.ceil((double) (exitMin - entryMin) / 60);
        return hours * this.parkingFee + this.hits * this.hitFee;
    }
}