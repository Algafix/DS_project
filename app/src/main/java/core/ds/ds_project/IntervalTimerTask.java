package core.ds.ds_project;

import java.util.TimerTask;

/**
 * Class for debugging purposes.
 */
public class IntervalTimerTask extends TimerTask {
    /**
     * An interval.
     */
    private Interval interval;
    /**
     * Set an interval.
     * @param theInterval the interval.
     */
    IntervalTimerTask(final Interval theInterval) {

        this.interval = theInterval;
    }
    /**
     * Run an interval.
     */
    @Override
    public void run() {
        interval.stop();
        String firstVar = interval.getName();
        String finalVar = Client.formatDuration(interval.getDuration());
        System.out.println("Parado " + firstVar  + " en: " + finalVar);
    }
}
