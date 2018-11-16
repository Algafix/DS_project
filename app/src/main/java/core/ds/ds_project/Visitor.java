package core.ds.ds_project;

/**
 * Visitor abstract class.
 */
abstract class Visitor {
    /**
     * Visit a project.
     * @param project Project to be visited.
     */
    public abstract void visitProject(Project project);

    /**
     * Visit a task.
     * @param task Task to be visited.
     */
    public abstract void visitTask(Task task);
    /**
     * Visit a interval.
     * @param interval Interval to be visited.
     */
    public abstract void visitInterval(Interval interval);
}
