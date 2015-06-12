package laboratory.fra_uas.eu.hefnerklammenzianochs.alarmclockusingthecalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.*;
import java.util.Calendar;

public class Alarms {

    private static String ACTIVETAG = "Active";
    private static String ALARMTAG = "Alarm";

    public Alarm[] alarmsPerDayOfWeek = new Alarm[8]; // 0-ignor, 1-So, 2-Mo, ...

    public Alarms(Context context) {
        for(int i = 0; i < alarmsPerDayOfWeek.length; i++) {
            alarmsPerDayOfWeek[i] = new Alarm(i);
            loadAlarms(context);
        }
    }

    /**
     * List of alarm grouped by the same wake-up time
     * @return grouped Alarm List
     */
    public List<List<Alarm>> alarmList() {
        List<List<Alarm>> list = new ArrayList<List<Alarm>>();
        weekdayLoop: for(int day : Alarm.weekdays()) {
            Alarm alarm = alarmsPerDayOfWeek[day];
            if(!alarm.active) {
                continue;
            }
            String alarmString = Alarm.timeAsString(alarm.hours, alarm.minutes);
            // Already entry for Alarm Clock?
            for(List<Alarm> listPair : list) {
                Alarm firstAlarmList = listPair.get(0);
                if(alarmString.equals(Alarm.timeAsString(firstAlarmList.hours, firstAlarmList.minutes))) {
                    listPair.add(alarm);
                    continue weekdayLoop;
                }
            }
            // new entry
            List<Alarm> group = new ArrayList<Alarm>();
            group.add(alarm);
            list.add(group);
        }

        return list;
    }

    public boolean isActive(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(ACTIVETAG, false);
    }

    public void setActive(Context context, boolean active) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(ACTIVETAG, active);
        editor.commit();
    }

    public Alarm nextAlarm() {
        Alarm nextAlarm = null;
        //find minimum
        for(int i = 0; i < alarmsPerDayOfWeek.length; i++) {
            Alarm alarm = alarmsPerDayOfWeek[i];
            if(alarm.active) {
                if(nextAlarm == null || nextAlarm.calendar().after(alarm.calendar())) {
                    nextAlarm = alarm;
                }
            }
        }
        return nextAlarm;
    }

    public void storeAlarms(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
    }

    public void initDefaultAlarms() {
        for(int i = 0; i < alarmsPerDayOfWeek.length; i++) {
            Alarm alarm = alarmsPerDayOfWeek[i];
            if(i != Calendar.SATURDAY && i != Calendar.SUNDAY) {
                alarm.hours = 7;
                alarm.minutes = 0;
                alarm.active = true;
            } else {
                alarm.hours = 10;
                alarm.minutes = 0;
                alarm.active = false;
            }
        }
    }

    public void scheduleNextAlarm(Context context) {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiverActivity.class), 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Alarm alarm = nextAlarm();

        if(alarm == null || !isActive(context)) {
            return; // no activ alarm!
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.calendar().getTimeInMillis(), pendingIntent);
    }

    private void loadAlarms(Context context) {

    }

}
