package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class AppClock extends Observable {

    private static volatile AppClock clockSoleInstance;

    // Declaration of the object that will be used to periodically update our time
    private Timer timer = new Timer();

    // java.time package is only available on API26 and above
    private LocalDateTime time = null;

    private static int defaultRefresh = 1000;

    /**
     * The constructor starts a thread that will be checking and updating our time
     * every given refreshTime.
     *
     * @param refreshTime   In miliseconds.
     */
    private AppClock(int refreshTime) {

        //Prevent form the reflection api.
        if (clockSoleInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        else {
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

    public static AppClock getInstance(int... refreshTime) {
        //Double check locking pattern
        if (clockSoleInstance == null) { //Check for the first time

            synchronized (AppClock.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (clockSoleInstance == null) clockSoleInstance = new AppClock(refreshTime.length > 0 ? refreshTime[0] : AppClock.defaultRefresh);
            }
        }
        return clockSoleInstance;
    }

    public LocalDateTime getTime(){
        return time;
    }

}
