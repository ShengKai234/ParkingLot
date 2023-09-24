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
public class Truck extends Vehicle {

    int parkingFee = 10;
    int hitFee = 50;
    int overnightFee = 20;

    public Truck(String Type, String Id, String Model,String Colour,String TimeEntry, String DateEntry){
        super(Type, Id, Model, Colour, TimeEntry, DateEntry);
        super.parkingFee = this.parkingFee;
        super.hitFee = this.hitFee;
        super.overnightFee = this.overnightFee;
    }
}