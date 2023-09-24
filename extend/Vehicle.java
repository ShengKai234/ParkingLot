import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Vehicle {
    String Type;
    String Id;
    String Model;
    String Colour;
    String TimeEntry;
    String DateEntry;
    int hits;
    int x;
    int y;
    int parkingFee;
    int hitFee;
    int overnightFee;

    public Vehicle(String Type, String Id, String Model, String Colour, String TimeEntry, String DateEntry) {
        this.Type = Type;
        this.Id = Id;
        this.Model = Model;
        this.Colour = Colour;
        this.TimeEntry = TimeEntry;
        this.DateEntry = DateEntry;
        this.hits = 0;
        this.x = 1;
        this.y = 0;
    }

    public int getHours(String exitDateStr, String exitTimeStr) {
        // String[] exitTime = exitTimeStr.split(":");
        // String[] entryTime = this.TimeEntry.split(":");
        // Integer exitMin = Integer.parseInt(exitTime[0]) * 60 + Integer.parseInt(exitTime[1]);
        // Integer entryMin = Integer.parseInt(entryTime[0]) * 60 + Integer.parseInt(entryTime[1]);
        // int hours = (int) Math.ceil((double) (exitMin - entryMin) / 60);
        // return hours;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date entryDate = sdf.parse(this.DateEntry + " " + this.TimeEntry);
            Date exitDate = sdf.parse(exitDateStr + " " + exitTimeStr);
            long millisecondsDiff = exitDate.getTime() - entryDate.getTime();
            int hoursDiff = (int) Math.ceil((double)millisecondsDiff / (60 * 60 * 1000));
            return hoursDiff;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getFee(String exitDateStr, String exitTimeStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date entryDate = sdf.parse(this.DateEntry + " " + this.TimeEntry);
            Date exitDate = sdf.parse(exitDateStr + " " + exitTimeStr);
            long millisecondsDiff = exitDate.getTime() - entryDate.getTime();
            int hoursDiff = (int) Math.ceil((double)millisecondsDiff / (60 * 60 * 1000));
            int overnightDays = hoursDiff / 24;
            int remaindHours = hoursDiff - overnightDays * 24;
            return this.overnightFee * overnightDays + this.parkingFee * remaindHours + this.hitFee * this.hits;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    };
}