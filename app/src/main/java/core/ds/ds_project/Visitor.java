package core.ds.ds_project;

/**
 * Not necessary all ready understandable.
 */
abstract class Visitor {
    /**
     * Not necessary all ready understandable.
     * @param project .
     */
    public abstract void visitProject(Project project);

    /**
     * Not necessary all ready understandable.
     * @param task .
     */
    public abstract void visitTask(Task task);
    /**
     * Not necessary all ready understandable.
     * @param interval .
     */
    public abstract void visitInterval(Interval interval);
}
