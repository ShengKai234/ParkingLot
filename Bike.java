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
// import java.util.DateTime;
public class Bike {
    String Type;
    String Id;
    String Model;
    String Colour;
    String TimeEntry;

    public Bike(String Type, String Id, String Model,String Colour,String TimeEntry){
        this.Type = Type;
        this.Id = Id;
        this.Model = Model;
        this.Colour = Colour;
        this.TimeEntry = TimeEntry;
    }

    public String getEntryTime(){
        return TimeEntry;
    }
}