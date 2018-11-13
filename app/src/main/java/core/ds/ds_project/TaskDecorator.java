package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Not necessary all ready understandable.
 */
public abstract class TaskDecorator extends Task {
    /**
     * Not necessary all ready understandable.
     */
    private Task task = null;

    /**
     * Not necessary all ready understandable.
     * @param task1 .
     */
    public TaskDecorator(final Task task1) {
        super(task1.getName(), task1.getDescription());
        this.task = task1;
        task.setHigherLayerDecorator(this);
    }
    /**
     * Not necessary all ready understandable.
     * @return the intervals of a task
     */
    public Collection<Interval> getIntervals() {
        return task.getIntervals();
    }

    /**
     * Add's an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The interval created.
     */
    @Override
    public Interval addInterval(final String name) {
        return task.addInterval(name);
    }
    /**
     * Not necessary all ready understandable.
     */
    @Override
    public Duration stopLastInterval() {
        return task.stopLastInterval();
    }
    /**
     * Not necessary all ready understandable.
     */
    @Override
    public void acceptVisitor(final Visitor visitor) {
        task.acceptVisitor(visitor);
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
        return task.getDurationInRange(fromDate, toDate);
    }

    /**
     * Obtain the internal class of the decorator.
     * @return Wrapped task.
     */
    public Task getTask() {

        return task;
    }


    /**
     * Get the duration of the internal class.
     * @return Duration of the internal class.
     */
    @Override
    public Duration getDuration() {
        return task.getDuration();
    }

    /**
     * Set the duration of the internal class.
     * @param durationParam Duration time for the task.
     */
    @Override
    public void setDuration(final Duration durationParam) {
        this.task.setDuration(durationParam);
    }

    /**
     * Get the endTime of the internal class.
     * @return DateTime object with the end time.
     */
    @Override
    public LocalDateTime getEndTime() {
        return task.getEndTime();
    }

    /**
     * Set the endTime of the internal class.
     * @param endTimeParam End time for the task.
     */
    @Override
    public void setEndTime(final LocalDateTime endTimeParam) {
        this.task.setEndTime(endTimeParam);
    }

    /**
     * Get the start time of the internal class.
     * @return DateTime object with the start time.
     */
    @Override
    public LocalDateTime getStartTime() {
        return task.getStartTime();
    }

    /**
     * Set the startTime of the internal class.
     * @param startTimeParam StartTime for the class.
     */
    @Override
    public void setStartTime(final LocalDateTime startTimeParam) {
        this.task.setStartTime(startTimeParam);
    }


}
