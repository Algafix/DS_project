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
     * @param parent Which is the upper node of the tree.
     */
    Project(String name, String description, Project parent) {

        super(name,description,parent);
    }

    /**
     * Indicates if the list of jobs has any job.
     *
     * @return False if the list is empty, True otherwise.
     */
    public boolean hasChild() {
        return !this.children.isEmpty();
    }

    /**
     * Returns the children list.
     *
     * @return Children list.
     */
    public List<Job> getChildren() {
        return this.children;
    }

    /**
     * Add a child of the instance Project at the end of the list.
     *
     * @param child Instance of Project that will be added.
     * @return Instance of Project that has just been added.
     */
    public Project addChild(Project child) {
        this.children.add(child);
        return child;
    }

    /**
     * Add a child of the instance Task at the end of the list.
     *
     * @param child Instance of Task that will be added.
     * @return Instance of Task that has just been added.
     */
    public Task addChild(Task child) {
        this.children.add(child);
        return child;
    }

    @Override
    public void printDebug(String tabs) {

        System.out.println(tabs+this.name+": "+this.description);

        tabs = tabs.concat("\t");

        if (this.hasChild()) {
            for (Job child : children) {
                child.printDebug(tabs);
            }
        }
    }
}
