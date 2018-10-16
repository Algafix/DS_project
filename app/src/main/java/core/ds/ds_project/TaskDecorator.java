package core.ds.ds_project;

import java.time.Duration;
import java.time.Period;
import java.util.List;

public abstract class TaskDecorator extends Task {

    public Period maxDuration = null;
    protected Task task = null;

    public TaskDecorator(Task task) {
        super(task.name,task.description);
        this.task = task;
        task.setHigherLayerDecorator(this);
    }

    public List<Interval> getIntervals() {
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
