package core.ds.ds_project;

/**
 * Class that implements the visitor pattern and will print all
 * the nodes of the tree.
 */
public class Printer extends Visitor {

    /**
     * Method that will print a project's name and it's duration.
     * @param project Project where the method is called.
     */
    @Override
    public void visitProject(final Project project) {

        System.out.println(project.getName() + ": "
                + Client.formatDuration(project.getDuration()));
    }

    /**
     * Method that will print a task's name and it's duration.
     * @param task Task where the method is called.
     */
    @Override
    public void visitTask(final Task task) {

        System.out.println(task.getName() + ": "
                + Client.formatDuration(task.getDuration()));
    }

    /**
     * Method that will print an interval's name and it's duration.
     * @param interval Interval where the method is called.
     */
    @Override
    public void visitInterval(final Interval interval) {

        System.out.println(interval.getName() + ": "
                + Client.formatDuration(interval.getDuration()));
    }
}
