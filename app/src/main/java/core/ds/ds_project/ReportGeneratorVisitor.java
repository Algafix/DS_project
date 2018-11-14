package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Not necessary understandable if necessary explain the class.
 */
public abstract class ReportGeneratorVisitor extends Visitor {

    private ReportSaver saver;
    private Report report;
    private LocalDateTime endReportDate;
    private LocalDateTime startReportDate;

    public ReportGeneratorVisitor(final LocalDateTime startReportDateParam,
                                  final  LocalDateTime endReportDateParam,
                                  final ReportSaver saverParam) {
        endReportDate = endReportDateParam;
        startReportDate = startReportDateParam;
        saver = saverParam;
        initializeReport();
    }

    public abstract void save();

    protected void save(final List<String> subtitles) {
        saver.save(report, subtitles, getFileName());
    }

    //TODO
    private String getFileName() {
        //return "Report" + startReportDate.toString() + endReportDate.toString();
        return  "Report";
    }

    /**
     * Method that will generate and add the info for the report from a project
     * @param project Project where the method is called.
     */
    @Override
    public void visitProject(final Project project) {
        if (isVisitableProject(project)) {
            getInfoProject(project);
            handleInfoProject(project);
        }
    }

    /**
     * Method that will generate and add the info for the report from a task
     * @param task Task where the method is called.
     */
    @Override
    public void visitTask(final Task task) {
        if (isVisitableTask(task)) {
            getInfoTask(task);
            handleInfoTask(task);
        }
    }

    /**
     * Method that will generate and add the info for the report from a interval
     * @param interval Interval where the method is called.
     */
    @Override
    public void visitInterval(final Interval interval) {
        if (isVisitableInterval(interval)) {
            getInfoInterval(interval);
            handleInfoInterval(interval);
        }
    }

    protected void initializeReport() { };

    protected boolean isVisitableProject(final Project project) {
        return false;
    }
    protected void getInfoProject(final Project project) { };
    protected void handleInfoProject(final Project project) { };
    protected boolean isVisitableTask(final Task task) {
        return false;
    }
    protected void getInfoTask(final Task task) { };
    protected void handleInfoTask(final Task task) { };
    protected boolean isVisitableInterval(final Interval interval) {
        return false;
    }
    protected void getInfoInterval(final Interval interval) { };
    protected void handleInfoInterval(final Interval interval) { };

    public LocalDateTime getEndReportDate() {
        return endReportDate;
    }
    public LocalDateTime getStartReportDate() {
        return startReportDate;
    }
    public Report getReport() {
        return report;
    }
    public void setReport(final Report reportParam) {
        this.report = reportParam;
    }
}
