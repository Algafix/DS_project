package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clock of the project.
 */

public final class AppClock extends Observable {


    /**
     * Singleton unique instance.
     */
    private static volatile AppClock clockSoleInstance;

    /**
     * java.time package is only available on API26 and above.
     */
    private LocalDateTime time = null;


    /**
     * the refresh time time we are actually using.
     */
    private int currentRefreshTime;


    /**
     * The constructor starts a thread that will be checking and updating
     * our time every given refreshTime. If the user manage to access this
     * constructor before creating an
     * object, an exemption is raised.
     *
     * @param refreshTime   In milliseconds.
     */
    private AppClock(final int refreshTime) {

        //Prevent form the reflection api.
        if (clockSoleInstance != null) {

            throw new RuntimeException(
                    "Use getInstance() method to get the single instance.");

        } else {

            currentRefreshTime = refreshTime;
            time = LocalDateTime.now();
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    time = LocalDateTime.now();
                    setChanged();
                    notifyObservers(time);
                    //System.out.println(time);
                }
            }, 0, refreshTime);
        }
    }


    /**
     * Function used to obtain the instance of the singleton.
     * It check's 2 times before creating the objects,
     * even check thread race condition.
     *
     * @param refreshTime The time will be updated at this rate, in ms.
     * Optional parameter.
     * @return Instance of AppClock object.
     */
    public static AppClock getInstance(final int... refreshTime) {
        final int defaultRefresh = 1000;


         final Logger log = LoggerFactory.getLogger(Interval.class);


        //Double check locking pattern
        if (clockSoleInstance == null) { //Check for the first time
            synchronized (AppClock.class) {   //Check for the second time
                //if there is no instance available... create new one
                if (clockSoleInstance == null) {
                    int selectedRefreshTime = defaultRefresh;
                    if (refreshTime.length > 0) {
                        selectedRefreshTime = refreshTime[0];
                    }
                    clockSoleInstance = new AppClock(selectedRefreshTime);
                    log.info("Clock has started");
                }
            }
        }
        return clockSoleInstance;
    }

    /**
     * Get the current time.
     * @return the current time
     */
    public LocalDateTime getTime() {

        return time;
    }

    /**
     * Get the current refresh time.
     * @return the current refresh time
     */
    public int getCurrentRefreshTime() {
        return currentRefreshTime;
    }
}
