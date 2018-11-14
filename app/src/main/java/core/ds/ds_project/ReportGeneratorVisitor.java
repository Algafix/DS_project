package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Not necessary understandable if necessary explain the class.
 */
public abstract class ReportGeneratorVisitor extends Visitor {
    /**
     *The report saver.
     */
    private ReportSaver saver;
    /**
     *The report.
     */
    private Report report;
    /**
     * The end report date.
     */
    private LocalDateTime endReportDate;
    /**
     * The start report date.
     */
    private LocalDateTime startReportDate;
    /**
     * The rport generator visitor.
     * @param startReportDateParam .
     * @param endReportDateParam .
     * @param saverParam .
     */
    public ReportGeneratorVisitor(final LocalDateTime startReportDateParam,
                                  final  LocalDateTime endReportDateParam,
                                  final ReportSaver saverParam) {
        endReportDate = endReportDateParam;
        startReportDate = startReportDateParam;
        saver = saverParam;
        initializeReport();
    }
    /**
     * The save.
     */
    public abstract void save();
    /**
     * The save .
     * @param subtitles .
     */
    protected void save(final List<String> subtitles) {
        saver.save(report, subtitles, getFileName());
    }

    /**
     * A counter.
     */
    private static int counter = 0;
    /**
     * To get the file name.
     * @return the number of reports.
     */
    private String getFileName() {
        counter++;
        return  "Report" + counter;
    }

    /**
     * Method that will generate and add the info for the report from a .
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
     * Method that will generate and add the info for the report from a task.
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
     * Method that will generate and add the info for
     * the report from a interval.
     * @param interval Interval where the method is called.
     */
    @Override
    public void visitInterval(final Interval interval) {
        if (isVisitableInterval(interval)) {
            getInfoInterval(interval);
            handleInfoInterval(interval);
        }
    }
    /**
     * To initialize a report.
     */
    protected void initializeReport() { }

    /**
     * For pruning the structure tree of projects, tasks,...
     * @param project .
     * @return false .
     */
    protected boolean isVisitableProject(final Project project) {
        return false;
    }
    /**
     * To get info of a project.
     * @param project .
     */
    protected void getInfoProject(final Project project) { }

    /**
     * To handle infoProject.
     * @param project .
     */
    protected void handleInfoProject(final Project project) { }

    /**
     * To know if a task is visited.
     * @param task .
     * @return false.
     */
    protected boolean isVisitableTask(final Task task) {
        return false;
    }
    /**
     * To get info of a a task.
     * @param task .
     */
    protected void getInfoTask(final Task task) { }

    /**
     * To handle info of a task.
     * @param task .
     */
    protected void handleInfoTask(final Task task) { }

    /**
     * To know if a interval is visited.
     * @param interval .
     * @return false .
     */
    protected boolean isVisitableInterval(final Interval interval) {
        return false;
    }
    /**
     * To get info of an interval.
     * @param interval .
     */
    protected void getInfoInterval(final Interval interval) { }

    /**
     * To handle an interval.
     * @param interval .
     */
    protected void handleInfoInterval(final Interval interval) { }

    /**
     * To get the end report date.
     * @return end date report.
     */
    public LocalDateTime getEndReportDate() {
        return endReportDate;
    }
    /**
     * To get the start report date.
     * @return start date report.
     */
    public LocalDateTime getStartReportDate() {
        return startReportDate;
    }
    /**
     * To get the report.
     * @return the report.
     */
    public Report getReport() {
        return report;
    }
    /**
     * To set the report.
     * @param reportParam .
     */
    public void setReport(final Report reportParam) {
        this.report = reportParam;
    }
}
