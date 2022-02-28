
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Woy {

    public static final Locale A_LOCALE = new Locale("sv", "SE");

    public static void main(String[] args) throws Exception {
        final String dateString = "2021-01-11";
        cal(dateString);
        instant(dateString);
    }

    private static void cal(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date date = formatter.parse(dateString);
        Calendar cl = Calendar.getInstance(A_LOCALE);
        cl.setTime(date);

        System.out.println("week " + cl.get(Calendar.WEEK_OF_YEAR));
    }

    public static int instant(LocalDate date){
        return date.get(WeekFields.of(A_LOCALE).weekOfYear());
    }

    public static int instant(String dateString){
        LocalDate date = LocalDate.parse(dateString);

        return date.get(WeekFields.of(A_LOCALE).weekOfYear());
    }
}

