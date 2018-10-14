package core.ds.ds_project;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public abstract class Task extends Job{


    public Period maxDuration = null;
    private List<Interval> intervals = new ArrayList<Interval>();

    public Task(String name, String description, Project parent) {

        super(name,description,parent);
    }

    public abstract List<Interval> getIntervals();

    /**
     * Add's an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The interval created.
     */
    public abstract Interval addInterval(String name);

    /**
     * Prints info about the Task and call print method of it's child.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    @Override
    public abstract void printDebug(String tabs);



}
