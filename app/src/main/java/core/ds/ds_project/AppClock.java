package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class AppClock extends Observable {

    // Singleton unique instance
    private static volatile AppClock clockSoleInstance;

    // Declaration of the object that will be used to periodically update our time
    private Timer timer = new Timer();

    // java.time package is only available on API26 and above
    private LocalDateTime time = null;

    private static int defaultRefresh = 1000;

    private int currentRefreshTime;


    /**
     * The constructor starts a thread that will be checking and updating our time
     * every given refreshTime. If the user manage to access this constructor before creating an
     * object, an exemption is raised.
     *
     * @param refreshTime   In miliseconds.
     */
    private AppClock(int refreshTime) {

        //Prevent form the reflection api.
        if (clockSoleInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        else {
            currentRefreshTime = refreshTime;
            time = LocalDateTime.now();
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
     * Function used to obtain the instance of the singleton. It check's 2 times before creating
     * the objects, even check thread race condition.
     *
     * @param refreshTime The time will be updated at this rate, in ms. Optional parameter.
     * @return Instance of AppClock object.
     */
    public static AppClock getInstance(int... refreshTime) {
        //Double check locking pattern
        if (clockSoleInstance == null) { //Check for the first time
            synchronized (AppClock.class) {   //Check for the second time
                //if there is no instance available... create new one
                if (clockSoleInstance == null) clockSoleInstance = new AppClock(refreshTime.length > 0 ? refreshTime[0] : AppClock.defaultRefresh);
            }
        }
        return clockSoleInstance;
    }


    public LocalDateTime getTime(){
        return time;
    }

    public int getCurrentRefreshTime() {
        return currentRefreshTime;
    }
}
