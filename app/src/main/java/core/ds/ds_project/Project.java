package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class Project extends Job {

    private List<Job> children = new ArrayList<Job>();


    /**
     * Constructor of the class, only calls the super constructor.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     */
    Project(String name, String description) {

        super(name,description);
    }


    /**
     * Indicates if the list of jobs has any job.
     *
     * @return False if the list is empty, True otherwise.
     */
    public boolean hasChild() {
        return !children.isEmpty();
    }


    /**
     * Returns the children list.
     *
     * @return Children list.
     */
    public List<Job> getChildren() {
        return children;
    }


    /**
     * Add a child of the instance Project at the end of the list.
     *
     * @param child Instance of Project that will be added.
     * @return Instance of Project that has just been added.
     */
    public Project addChild(Project child) {
        child.setParent(this);
        children.add(child);
        return child;
    }

    /**
     * Add a child of the instance Task at the end of the list.
     *
     * @param child Instance of Task that will be added.
     * @return Instance of Task that has just been added.
     */
    public Task addChild(Task child) {
        child.setParent(this);
        children.add(child);
        return child;
    }


    /**
     * Print info about the Project and call print method of it's child.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    @Override
    public void printDebug(String tabs) {

        System.out.println(tabs + name +": " + Client.formatDuration(duration));

        tabs = tabs.concat("\t");

        for (Job child : children) {
            child.printDebug(tabs);
        }

    }
}
