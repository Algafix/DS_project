package core.ds.ds_project;

import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public abstract class Task extends Job{


    public Period maxDuration = null;
    private List<Interval> intervals = new ArrayList<Interval>();
    protected Task higherLayerDecorator = null;

    public Task(String name, String description, Project parent) {

        super(name,description,parent);
    }

    /**
     * Sets the higher layer decorator, that is the Decorator that wraps the current task
     */
    public void setHigherLayerDecorator(Task higherLayerDecorator){
        this.higherLayerDecorator = higherLayerDecorator;
    }

    public abstract List<Interval> getIntervals();

    /**
     * Adds an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The interval created.
     */
    public abstract Interval addInterval(String name);

    /**
     * Stops the last interval, which is presumed to be the only one running, considering there's an
     * interval running at all.
     *
     * @return Duration of the last Interval
     */
    public abstract Duration stopLastInterval();

    /**
     * Prints info about the Task and call print method of it's child.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    @Override
    public abstract void printDebug(String tabs);



}
