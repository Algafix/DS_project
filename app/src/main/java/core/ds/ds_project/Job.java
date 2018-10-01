package core.ds.ds_project;

import java.time.LocalDateTime;

public abstract class Job {

    protected Project parent;
    protected String name;
    protected String description;

    // Im not certain that this attribute should exist, the duration can be
    // calculated recursively, but saving it will provide a faster access.
    protected LocalDateTime duration = null;

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
    }


    /**
     * This function will print recursively the information of every child in the tree.
     * In every level, the tabulation will increase in 1 tab (4 spaces).
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    abstract void printDebug(String tabs);

}
