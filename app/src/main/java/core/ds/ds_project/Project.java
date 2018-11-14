package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Project objects are nodes of the Job tree
 * that can be subdivided in other projects or tasks.
 * In other words, the project contains different,
 * more specific, jobs.
 */
public class Project extends Job {

    /**
     * Collection of jobs that conform this project.
     */
    private Collection<Job> children = new ArrayList<>();


    /**
     * Constructor of the class, only calls the super constructor.
     *
     * @param name Name of the job.
     * @param description What will be the job about.
     */
    Project(final String name, final String description) {

        super(name, description);
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
     * Whether this project is the dummie or not.
     *
     * @return True if the project is the Dummie, False otherwise.
     */
    public boolean isDummie() {
        return getParent() == null;
    }

    /**
     * Whether this project is one of the root projects or not.
     *
     * @return True if the project is Root, False otherwise.
     */
    public boolean isRoot() {
        if (this.isDummie()) {
            return false;
        } else {
            return getParent().isDummie();
        }
    }


    /**
     * Returns the children list.
     *
     * @return Children list.
     */
    public Collection<Job> getChildren() {
        return children;
    }


    /**
     * Add a child of the instance Project at the end of the list.
     *
     * @param child Instance of Project that will be added.
     * @return Instance of Project that has just been added.
     */
    public Project addChild(final Project child) {
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
    public Task addChild(final Task child) {
        child.setParent(this);
        children.add(child);
        return child;
    }

    /**
     * Method that obtains the activity duration of the
     * job within a range defined by two Dates.
     *
     * @param fromDate Date that sets the beginning of the range.
     * @param toDate Date that sets the end of the range.
     * @return [Duration] Returns the duration in the specified range.
     */
    @Override
    public Duration getDurationInRange(final LocalDateTime fromDate,
                                       final LocalDateTime toDate) {
        Duration temp = Duration.ofSeconds(0);
        if (!(fromDate.isAfter(getEndTime()) || toDate.isBefore(getStartTime()))
                && fromDate.isBefore(toDate)) {
            for (Job job : children) {
                temp = temp.plus(job.getDurationInRange(fromDate, toDate));
            }
        }
        return temp;
    }

    /**
     * Accept a visitor object.
     */
    @Override
    public void acceptVisitor(final Visitor visitor) {
        visitor.visitProject(this);
        for (final Job job : children) {
            job.acceptVisitor(visitor);
        }
    }
}
