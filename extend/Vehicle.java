public class Vehicle {
    String Type;
    String Id;
    String Model;
    String Colour;
    String TimeEntry;
    int hits;
    int x;
    int y;

    public Vehicle(String Type, String Id, String Model,String Colour,String TimeEntry) {
        this.Type = Type;
        this.Id = Id;
        this.Model = Model;
        this.Colour = Colour;
        this.TimeEntry = TimeEntry;
        this.hits = 0;
        this.x = 1;
        this.y = 0;
    }

    public int getHours(String exitTimeStr) {
        String[] exitTime = exitTimeStr.split(":");
        String[] entryTime = this.TimeEntry.split(":");
        Integer exitMin = Integer.parseInt(exitTime[0]) * 60 + Integer.parseInt(exitTime[1]);
        Integer entryMin = Integer.parseInt(entryTime[0]) * 60 + Integer.parseInt(entryTime[1]);
        int hours = (int) Math.ceil((double) (exitMin - entryMin) / 60);
        return hours;
    }

    public int getFee(String exitTimeStr) {
        String[] exitTime = exitTimeStr.split(":");
        String[] entryTime = this.TimeEntry.split(":");
        Integer exitMin = Integer.parseInt(exitTime[0]) * 60 + Integer.parseInt(exitTime[1]);
        Integer entryMin = Integer.parseInt(entryTime[0]) * 60 + Integer.parseInt(entryTime[1]);
        int hours = (int) Math.ceil((double) (exitMin - entryMin) / 60);
        return hours * 0 + this.hits * 0;
    };
}