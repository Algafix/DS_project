package core.ds.ds_project;

/**
 * Not necessary all ready understandable.
 */
public class Printer extends Visitor {
    /**
     * Constructor of Limit Time Task Decorator.
     * @param basicTask Task that is wrapped by the current decorator.
     */
    @Override
    public void visitProject(Project project){

        System.out.println(project.getName()+": "+Client.formatDuration(project.getDuration()));
    }

    @Override
    public void visitTask(Task task) {

        System.out.println(task.getName()+": "+Client.formatDuration(task.getDuration()));
    }

    @Override
    public void visitInterval(Interval interval) {

        System.out.println(interval.getName()+": "+Client.formatDuration(interval.getDuration()));
    }
}
