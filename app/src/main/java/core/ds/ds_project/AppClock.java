package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class AppClock extends Observable {

    // Declaration of the object that will be used to periodically update our time
    private Timer timer = new Timer();

    // java.time package is only available on API26 and above
    private LocalDateTime time = null;

    /**
     * The constructor starts a thread that will be checking and updating our time
     * every given refreshTime.
     *
     * @param refreshTime   In miliseconds.
     */
    AppClock(int refreshTime) {

        timer.schedule(new TimerTask() {
            public void run() {
                time = LocalDateTime.now();
                System.out.println(time);
            }
        },0,refreshTime);

    }

}
