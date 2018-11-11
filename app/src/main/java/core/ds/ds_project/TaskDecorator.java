package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.List;

public abstract class TaskDecorator extends Task {

    public Period maxDuration = null;
    protected Task task = null;

    public TaskDecorator(Task task) {
        super(task.name,task.description);
        this.task = task;
        task.setHigherLayerDecorator(this);
    }

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
    public Interval addInterval(String name) {
        return task.addInterval(name);
    }

    @Override
    public Duration stopLastInterval() {
        return task.stopLastInterval();
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        task.acceptVisitor(visitor);
    }


    /**
     * Method that obtains the activity duration of the job within a range defined by two Dates.
     *
     * @param fromDate Date that sets the beginning of the range.
     * @param toDate Date that sets the end of the range.
     * @return [Duration] Returns the duration in the specified range.
     */
    @Override
    public Duration getDurationInRange(final LocalDateTime fromDate, final LocalDateTime toDate) {
        return task.getDurationInRange(fromDate, toDate);
    }

    /**
     * Prints info about the Task and call print method of it's child.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    @Override
    public void printDebug(String tabs) {
        task.printDebug(tabs);
    }

}
