package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task extends Job {

    private List<Interval> intervals = new ArrayList<Interval>();

    // If an interval last less than "minimumDuration", i'll be ignored
    private LocalDateTime minimumDuration = null;


    /**
     * Constructor of the class, only calls the super constructor.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     * @param parent Which is the upper node of the tree.
     */
    public Task(String name, String description, Project parent) {

        super(name,description,parent);
    }

    public List<Interval> getIntervals() {
        return this.intervals;
    }

    public Interval addInterval(String name) {

        Interval newInterval = new Interval(name);
        this.intervals.add(newInterval);
        return newInterval;
    }

    /**
     * Prints info about the Task
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    @Override
    public void printDebug(String tabs) {
        System.out.println(tabs + this.name+": "+this.description);
    }
}
