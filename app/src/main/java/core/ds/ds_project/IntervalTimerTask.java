package core.ds.ds_project;

import java.time.Duration;
import java.util.TimerTask;

public class IntervalTimerTask extends TimerTask {

    Interval interval;

    IntervalTimerTask(Interval interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        Duration duration = interval.stop();
        System.out.println("Parado "+interval.getName()+" en: " + Client.humanReadableFormatDuration(duration));
    }
}
