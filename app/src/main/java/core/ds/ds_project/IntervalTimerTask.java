package core.ds.ds_project;

import java.time.Duration;
import java.util.TimerTask;

/**
 * Class for debugging purposes.
 */
public class IntervalTimerTask extends TimerTask {

    Interval interval;

    IntervalTimerTask(Interval interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        System.out.println("Parado "+interval.getName()+" en: ");
    }
}
