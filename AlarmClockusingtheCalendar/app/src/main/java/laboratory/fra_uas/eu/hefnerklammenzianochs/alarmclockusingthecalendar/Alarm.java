package laboratory.fra_uas.eu.hefnerklammenzianochs.alarmclockusingthecalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The Alarm with hours, minutes and weekdays
 */
public class Alarm {

    public int hours;
    public int minutes;
    public boolean active;
    final public int dayOfWeek;

    /**
     * Default constructor with one parameter for setting the alarm day
     * @param dayOfWeek
     */
    public Alarm(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        hours = 0;
        minutes = 0;
        active = false;
    }

    public java.util.Calendar calendar() {
        Calendar calendar = Calendar.getInstance(); // Date of today
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        if(calendar.after(Calendar.getInstance())) { // Check if the date is later than now
            return calendar;
        }
        calendar.add(Calendar.WEEK_OF_MONTH, 1); // calendar is in the past and add one week
        return calendar;
    }

    @Override
    public String toString() {
        return weekdayShortString(dayOfWeek) + " " + timeAsString(hours, minutes);
    }

    /**
     * Sort list of weekdays, beginning with the first day of the week (Mo for Germany and So US)
     * @return
     */
    public static List<Integer> weekdays() {
        List<Integer> weekdays = new ArrayList<Integer>(7);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.getFirstDayOfWeek();
        do {
            weekdays.add(day);
            day++;
            if(day > Calendar.SATURDAY) {
                day = Calendar.SUNDAY;
            }
        } while (day != calendar.getFirstDayOfWeek());
        return weekdays;
    }

    public static String weekdayShortString(int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        return sdf.format(calendar.getTime());
    }

    public static String weekdayLongString(int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(calendar.getTime());
    }

    public static String timeAsString(int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        DateFormat sdf = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT);
        return sdf.format(calendar.getTime());
    }

}
