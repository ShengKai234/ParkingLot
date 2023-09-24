import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
class test {
    public static void main(String[] args) {
        System.out.println("test....");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date entryDate = sdf.parse("2020-10-10 19:00");
            Date exitDate = sdf.parse("2020-10-12 19:01");
            long millisecondsDiff = exitDate.getTime() - entryDate.getTime();
            int hoursDiff = (int) Math.ceil(millisecondsDiff / (60 * 60 * 1000));
            int overnightDays = hoursDiff / 24;
            int remaindHours = hoursDiff - overnightDays * 24;
            System.out.println("mmm: " + Math.ceil((double)millisecondsDiff / (60 * 60 * 1000)));
            System.out.println("hoursDiff: " + hoursDiff);
            System.out.println("overnightDays: " + overnightDays);
            System.out.println("remaindHours: " + remaindHours);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}