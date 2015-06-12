package laboratory.fra_uas.eu.hefnerklammenzianochs.alarmclockusingthecalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Calendar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View calendar = inflater.inflate(R.layout.calendar_layout, container, false);
        return calendar;
    }
}
