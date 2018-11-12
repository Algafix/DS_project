package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;


public abstract class Job implements Serializable {

    protected Project parent = null;
    protected String name;
    protected String description;
    protected Duration duration = null;
    protected LocalDateTime startTime = null;
    protected LocalDateTime endTime = null;


    /**
     * Constructor of the superclass that automatically initializes the common parameters.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     */
    public Job(String name, String description){
        this.name = name;
        this.description = description;
        this.duration = Duration.ofSeconds(0);
    }

    /**
     * Method that implements the visitor pattern, allows the visitor to travel along the three.
     *
     * @param visitor Object that will do certain function.
     */
    public abstract void acceptVisitor(Visitor visitor);

    /**
     * Updates the duration of the object and, if the object is not the last, call update on it's
     * parent.
     *
     * @param duration Increment of time.
     */
    public void updateDuration(Duration duration, LocalDateTime startTime, LocalDateTime endTime) {

        if(this.startTime == null) {
            this.startTime = startTime;
        }

        synchronized (this.duration) {
            this.duration = this.duration.plus(duration);
            this.endTime = endTime;
        }

        if(parent != null) {
            parent.updateDuration(duration, startTime, endTime);
        }
    }

    public void setParent(Project parent) {
        this.parent = parent;
    }

    public String getName(){
        return name;
    }

    public Project getParent(){
        return parent;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Method that obtains the activity duration of the job within a range defined by two Dates.
     *
     * @param fromDate Date that sets the beginning of the range.
     * @param toDate Date that sets the end of the range.
     * @return [Duration] Returns the duration in the specified range.
     */
    public abstract Duration getDurationInRange(LocalDateTime fromDate, LocalDateTime toDate);

}
