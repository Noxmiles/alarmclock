package laboratory.fra_uas.eu.hefnerklammenzianochs.alarmclockusingthecalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Depending on the parameter, the function return the next tab in order.
     * @param index integer specify which fragment is returned.
     * @return the clicked Fragment Tab
     */
    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0: // Fragment for Overview Tab
                return new Overview();
            case 1: // Fragment for Calendar Tab
                return new laboratory.fra_uas.eu.hefnerklammenzianochs.alarmclockusingthecalendar.Calendar();
            case 2: // Fragment for Clock Tab
                return new Clock();
            case 3: // Fragment for Settings Tab
                return new Settings();
        }
        return null;
    }

    /**
     *
     * @return Number of Tabs as int
     */
    @Override
    public int getCount() {
        return 4;
    }
}
