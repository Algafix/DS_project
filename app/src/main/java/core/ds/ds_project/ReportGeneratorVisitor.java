package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Abstract structure of all visitors to generate a report.
 */
public abstract class ReportGeneratorVisitor extends Visitor {
    /**
     *The report saver, with the implementation to save as a file.
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
     * The report generator visitor.
     * @param startReportDateParam Starting date of the report.
     * @param endReportDateParam Ending date of the report.
     * @param saverParam Saver object that will define how
     *                  the report is stored.
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
     * To save a report.
     */
    public abstract void save();
    /**
     * To save from a subclass.
     * @param subtitles The titles that represent each section,
     *                  in the order to be saved.
     */
    protected void save(final List<String> subtitles) {
        saver.save(report, subtitles, getFileName());
    }

    /**
     * A counter.
     */
    private static int counter = 0;
    /**
     * To get the file name without extension.
     * @return the number of reports.
     */
    private String getFileName() {
        counter++;
        return  "Report" + counter;
    }

    /**
     * Method that will generate and add the info for the report from a project.
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
     * Check if a project is visitable.
     * @param project Project to be checked.
     * @return false: default value of the method if not overriden.
     */
    protected boolean isVisitableProject(final Project project) {
        return false;
    }
    /**
     * To get info of a project.
     * @param project Project to be read.
     */
    protected void getInfoProject(final Project project) { }

    /**
     * To handle infoProject.
     * @param project Project to be handled.
     */
    protected void handleInfoProject(final Project project) { }

    /**
     /**
     * Check if a task is visitable.
     * @param task Task to be checked.
     * @return false: default value of the method if not overriden.
     */
    protected boolean isVisitableTask(final Task task) {
        return false;
    }
    /**
     * To get info of a a task.
     * @param task Task to be read.
     */
    protected void getInfoTask(final Task task) { }

    /**
     * To handle info of a task.
     * @param task Task to be handled.
     */
    protected void handleInfoTask(final Task task) { }

    /**
     /**
     * Check if a interval is visitable.
     * @param interval Interval to be checked.
     * @return false: default value of the method if not overriden.
     */
    protected boolean isVisitableInterval(final Interval interval) {
        return false;
    }
    /**
     * To get info of an interval.
     * @param interval Interval to be read.
     */
    protected void getInfoInterval(final Interval interval) { }

    /**
     * To handle an interval.
     * @param interval Interval to be handled.
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
     * @param reportParam Set the report from a external source.
     */
    public void setReport(final Report reportParam) {
        this.report = reportParam;
    }
}
