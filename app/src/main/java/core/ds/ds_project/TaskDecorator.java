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
     * Get the name.
     * @return the task.
     */
    public Task getTask() {

        return task;
    }


    /**
     * Get the duration.
     * @return the duration.
     */
    @Override
    public Duration getDuration() {
        return task.getDuration();
    }

    /**
     * Set the duration.
     * @return duration.
     */
    @Override
    public void setDuration(final Duration durationParam) {
        this.task.setDuration(durationParam);
    }

    /**
     * Get the endTime.
     * @return endTime.
     */
    @Override
    public LocalDateTime getEndTime() {
        return task.getEndTime();
    }

    /**
     * Set the endTime.
     * @return endTime.
     */
    @Override
    public void setEndTime(final LocalDateTime endTimeParam) {
        this.task.setEndTime(endTimeParam);
    }

    /**
     * Get the start time.
     * @return the start time.
     */
    @Override
    public LocalDateTime getStartTime() {
        return task.getStartTime();
    }
    /**
     * Set the startTime.
     * @param startTimeParam.
     */
    @Override
    public void setStartTime(final LocalDateTime startTimeParam) {
        this.task.setStartTime(startTimeParam);
    }


}
