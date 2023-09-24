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
public class Car extends Vehicle {

    int parkingFee = 4;
    int hitFee = 20;
    int overnightFee = 10;

    public Car(String Type, String Id, String Model,String Colour,String TimeEntry, String DateEntry){
        super(Type, Id, Model, Colour, TimeEntry, DateEntry);
        super.parkingFee = this.parkingFee;
        super.hitFee = this.hitFee;
        super.overnightFee = this.overnightFee;
    }
}