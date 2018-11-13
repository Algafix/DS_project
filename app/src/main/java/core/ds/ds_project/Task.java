package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.Collection;


/**
 * Not necessary all ready understandable.
 */
public abstract class Task extends Job {

    /** The Task that wraps the current one.
     * If null, means is the higher layer and the object that
     *  interactuates with other.
     */
    private Task higherLayerDecorator = null;

    /**
     * Not necessary all ready understandable.
     * @param name .
     * @param description .
     */
    public Task(final String name, final String description) {

        super(name, description);
    }

    /**
     * Sets the higher layer decorator, that is the
     * Decorator that wraps the current task.
     * @param higherLayerDecorator1 .
     */
    public void setHigherLayerDecorator(final Task higherLayerDecorator1) {
        this.higherLayerDecorator = higherLayerDecorator1;
    }
    /**
     * Not necessary all ready understandable.
     * @return  the intervals.
     */
    public abstract Collection<Interval> getIntervals();

    /**
     * Adds an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The interval created.
     */
    public abstract Interval addInterval(String name);

    /**
     * Stops the last interval, which is presumed to be the only one running,
     * considering there's an
     * interval running at all.
     *
     * @return Duration of the last Interval
     */
    public abstract Duration stopLastInterval();

    /**
     * Updates the duration of the object and,
     * if the object is not the last, call update on it's
     * parent.
     *
     * @param duration Increment of time.
     */
    @Override
    public void updateDuration(final Duration duration,
                               final LocalDateTime startTime,
                               final LocalDateTime endTime) {

        if (higherLayerDecorator == null) {
            super.updateDuration(duration, startTime, endTime);
        } else {
            higherLayerDecorator.updateDuration(duration, startTime, endTime);
        }
    }


}
