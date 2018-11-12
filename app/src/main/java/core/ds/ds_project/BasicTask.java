package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Not necessary understandable.
 */
public class BasicTask extends Task {

    /**
     * Array of intervals.
     */
    private Collection<Interval> intervals = new ArrayList<Interval>();
    /**
     * The runningInterval.
     */
    private Interval runningInterval = null;


    /**
     * Constructor of the class, only calls the super constructor.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     */

    public BasicTask(final String name, final String description) {

        super(name, description);
    }

    /**
     * Get the intervals.
     *
     * @return all the intervals
     */
    public Collection<Interval> getIntervals() {
        return intervals;
    }

    /**
     * Add's an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The interval created.
     */
    public Interval addInterval(final String name) {

        if (runningInterval == null) {

            runningInterval = new Interval(name, this);
            intervals.add(runningInterval);
            return runningInterval;

        }
        return null;
    }

    /**
     * Stop the last (and presumed only because only one interval
     * should exist at once) interval running if there's an interval
     * running at all.
     * @return The duration of the last interval or null if there
     * isn't an interval running
     */
    @Override
    public Duration stopLastInterval() {
        if (runningInterval != null) {
            Duration lastDuration = runningInterval.stop();
            runningInterval = null;
            return lastDuration;
        }
        return null;
    }

    /**
     * Method that obtains the activity duration of the job
     * within a range defined by two Dates.
     *
     * @param fromDate Date that sets the beginning of the range.
     * @param toDate Date that sets the end of the range.
     * @return [Duration] Returns the duration in the specified range.
     */
    @Override
    public Duration getDurationInRange(final LocalDateTime fromDate,
                                       final LocalDateTime toDate) {
        Duration temp = Duration.ofSeconds(0);
        for (Interval interval : intervals) {
            temp.plus(interval.getDurationInRange(fromDate, toDate));
        }
        return temp;
    }

    /**
     * Accepts a visitor.
     */
    @Override
    public void acceptVisitor(final Visitor visitor) {
        visitor.visitTask(this);

        for (Interval interval : intervals) {
            interval.acceptVisitor(visitor);
        }
    }

}
