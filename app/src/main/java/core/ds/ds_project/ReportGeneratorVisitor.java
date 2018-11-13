package core.ds.ds_project;

import java.time.LocalDateTime;

/**
 * Not necessary understandable if necessary explain the class.
 */
public abstract class ReportGeneratorVisitor extends Visitor {

    public ReportGeneratorVisitor(final LocalDateTime startReportDateParam,
                                  final  LocalDateTime endReportDateParam,
                                  final Report reportParam) {
        /*
      Explained variable report.
     */ /**
         * Explained variable report.
         */Report report = reportParam;
        LocalDateTime endReportDate = endReportDateParam;
        LocalDateTime startReportDate = startReportDateParam;
        initializeReport();
    }

    /**
     * Method that will generate and add the info for the report from a project
     * @param project Project where the method is called.
     */
    @Override
    public void visitProject(final Project project) {
        getInfoProject();
        handleInfoProject();
    }

    /**
     * Method that will generate and add the info for the report from a task
     * @param task Task where the method is called.
     */
    @Override
    public void visitTask(final Task task) {
        getInfoTask();
        handleInfoTask();
    }

    /**
     * Method that will generate and add the info for the report from a interval
     * @param interval Interval where the method is called.
     */
    @Override
    public void visitInterval(final Interval interval) {
        getInfoInterval();
        handleInfoInterval();
    }

    protected void initializeReport() {
        int a = 0;
    }

    protected void getInfoProject() { }

    protected void handleInfoProject() { }

    protected void getInfoTask() { }

    protected void handleInfoTask() { }

    protected void getInfoInterval() { }

    protected void handleInfoInterval() { }
}
