package ar.gob.demo.helpers;

import android.app.Activity;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import ar.gob.demo.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AnalyticsHelper {

    String TIMER_FORMAT = "dd:MM:yyyy HH:mm:ss a";
    Calendar calendar = Calendar.getInstance();
    GoogleAnalytics googleAnalytics;
    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public AnalyticsHelper() {
        this.googleAnalytics = GoogleAnalytics.getInstance(AppApplication.getInstance());
    }

    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? googleAnalytics.newTracker(R.xml.global_tracker)
                    : (trackerId == TrackerName.GLOBAL_TRACKER) ? googleAnalytics.newTracker(R.xml.global_tracker)
                    : googleAnalytics.newTracker(R.xml.global_tracker);
            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }

    public void activityStart(Activity activity) {
        googleAnalytics.reportActivityStart(activity);
        registerTiming();
    }

    public void activityStop(Activity activity) {
        googleAnalytics.reportActivityStop(activity);
        registerTiming();
    }

    public void sendEvent(String category, String action) {
        getTracker(TrackerName.APP_TRACKER).send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).build());
    }

    public void sendEvent(String category, String label, String action) {
        getTracker(TrackerName.APP_TRACKER).send(new HitBuilders.EventBuilder().setCategory(category).setLabel(label).setAction(action).build());
    }

    public void sendEvent(String category, String label, String action, Long value) {
        getTracker(TrackerName.APP_TRACKER).send(new HitBuilders.EventBuilder().setCategory(category).setLabel(label).setAction(action).setValue(value).build());
    }

    public void sentScreenName(String screenName) {
        getTracker(TrackerName.APP_TRACKER).setScreenName(screenName);
    }

    public void registerTiming() {
        getTracker(TrackerName.APP_TRACKER).send(new HitBuilders.TimingBuilder().setLabel(new SimpleDateFormat(TIMER_FORMAT).format(calendar.getTime())).build());
    }

    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER // Tracker used by all the apps from a company. eg: roll-up tracking
    }

}
