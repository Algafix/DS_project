package core.ds.ds_project;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ConcreteTask extends Task {

    private List<Interval> intervals = new ArrayList<Interval>();
    public Period maxDuration = null;

    /**
     * Constructor of the class, only calls the super constructor.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     * @param parent Which is the upper node of the tree.
     */
    public ConcreteTask(String name, String description, Project parent) {

        super(name,description,parent);
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    /**
     * Add's an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The interval created.
     */
    public Interval addInterval(String name) {

        Interval newInterval = new Interval(name, this);
        intervals.add(newInterval);
        return newInterval;
    }

    /**
     * Prints info about the Task and call print method of it's child.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    @Override
    public void printDebug(String tabs) {

        System.out.println(tabs + name+": ");

        tabs = tabs.concat("\t");

        for (Interval child : intervals) {
            child.printDebug(tabs);
        }

    }

}
