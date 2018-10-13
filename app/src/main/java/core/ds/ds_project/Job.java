package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;


public abstract class Job implements Serializable {

    protected Project parent;
    protected String name;
    protected String description;
    protected Duration duration = null;


    /**
     * Constructor of the superclass that automatically initializes the common parameters.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     * @param parent Which is the upper node of the tree.
     */
    public Job(String name, String description, Project parent){
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.duration = Duration.ofSeconds(0);
    }

    /**
     * Updates the duration of the object and, if the object is not the last, call update on it's
     * parent.
     *
     * @param duration Increment of time.
     */
    public void updateDuration(Duration duration) {
        synchronized (this.duration) {
            this.duration = this.duration.plus(duration);
        }
        if(parent != null) {
            parent.updateDuration(duration);
        }
    }


    /**
     * This function will print recursively the information of every child in the tree.
     * In every level, the tabulation will increase in 1 tab (4 spaces).
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    abstract void printDebug(String tabs);

}
