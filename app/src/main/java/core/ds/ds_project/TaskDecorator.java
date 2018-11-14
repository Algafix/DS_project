package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Class to apply the Decorator pattern, it will have
 * an attribute of the class Task to wrap and apply
 * different decorators. The logic of the task class
 * (duration, startTime, intervals, etc.) will be
 * stored in the last wrapped task.
 */
public abstract class TaskDecorator extends Task {
    /**
     * Task object to wrap.
     */
    private Task task = null;

    /**
     * Constructor of the TaskDecorator.
     * @param taskParam Task that will be wrapped with this decorator.
     */
    public TaskDecorator(final Task taskParam) {
        super(taskParam.getName(), taskParam.getDescription());
        this.task = taskParam;
        task.setHigherLayerDecorator(this);
    }

    /**
     * Get the intervals of the wrapped class.
     * @return Interval collection of the wrapped task.
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
     * Stop the running interval of the wrapped task.
     */
    @Override
    public Duration stopLastInterval() {
        return task.stopLastInterval();
    }

    /**
     * Accept a visitor object.
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

    /**
     * Set the parent to every sublayer.
     * @param jobParent set the parent
     */
    @Override
    public void setParent(final Project jobParent) {
        super.setParent(jobParent);
        task.setParent(jobParent);
    }


}
