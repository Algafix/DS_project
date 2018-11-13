package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Not necessary all ready understandable.
 */

public abstract class Job implements Serializable {

    /**
     * Pointer to the parent of a task.
     */
    private Project parent = null;
    /**
     * The name.
     */
    protected String name;
    /**
     * The description..
     */
    protected String description;
    /**
     * The duration.
     */
    protected Duration duration = null;
    /**
     * The start time.
     */
    protected LocalDateTime startTime = null;
    /**
     * The end time.
     */
    protected LocalDateTime endTime = null;


    /**
     * Constructor of the superclass that automatically
     * initializes the common parameters.
     *
     * @param jobName Name of the job.
     * @param jobDescription What will be the job about.
     */
    public Job(final String jobName, final String jobDescription) {
        this.name = jobName;
        this.description = jobDescription;
        this.duration = Duration.ofSeconds(0);
    }

    /**
     * Method that implements the visitor pattern,
     * allows the visitor to travel along the three.
     *
     * @param visitor Object that will do certain function.
     */
    public abstract void acceptVisitor(Visitor visitor);

    /**
     * Updates the duration of the object and, if the object is not the last,
     * call update on it's
     * parent.
     *
     * @param durationUpdate  Update of duration
     * @param startTimeUpdate  Update of starTime.
     * @param endTimeUpdate  Update of endTime.
     */
    public void updateDuration(final Duration durationUpdate,
                               final LocalDateTime startTimeUpdate,
                               final LocalDateTime endTimeUpdate) {

        if (this.startTime == null) {
            this.startTime = startTimeUpdate;
        }

        synchronized (this.duration) {
            this.duration = this.duration.plus(durationUpdate);
            this.endTime = endTimeUpdate;
        }

        if (parent != null) {
            parent.updateDuration(durationUpdate,
                                  startTimeUpdate, endTimeUpdate);
        }
    }
    /**
     * Set the name.
     * @param jobParent  set the parent
     */
    public void setParent(final Project jobParent) {

        this.parent = jobParent;
    }
    /**
     * Get the name.
     * @return the name.
     */
    public String getName() {

        return name;
    }
    /**
     * Get the parent.
     * @return the parent.
     */
    public Project getParent() {

        return parent;
    }

    /**
     * Get the duration.
     * @return the duration.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Get the endTime.
     * @return endTime.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Get the start time.
     * @return the start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }
    /**
     * Get the description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that obtains the activity duration of the job
     * within a range defined by two Dates.
     *
     * @param fromDate Date that sets the beginning of the range.
     * @param toDate Date that sets the end of the range.
     * @return [Duration] Returns the duration in the specified range.
     */
    public abstract Duration getDurationInRange(LocalDateTime fromDate,
                                                LocalDateTime toDate);

}
