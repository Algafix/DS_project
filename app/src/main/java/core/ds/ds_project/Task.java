package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public abstract class Task extends Job{

    /** The Task that wraps the current one. If null, means is the higher layer and the object that
     *  interactuates with other.
     */
    protected Task higherLayerDecorator = null;

    public Task(String name, String description) {

        super(name,description);
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


    /**
     * Updates the duration of the object and, if the object is not the last, call update on it's
     * parent.
     *
     * @param duration Increment of time.
     */
    @Override
    public void updateDuration(Duration duration, LocalDateTime startTime, LocalDateTime endTime) {

        if(higherLayerDecorator == null) {
            super.updateDuration(duration, startTime, endTime);
        }
        else {
            higherLayerDecorator.updateDuration(duration, startTime, endTime);
        }
    }


}
