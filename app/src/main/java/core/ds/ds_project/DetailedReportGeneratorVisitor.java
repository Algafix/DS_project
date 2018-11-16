package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Visitor that creates a detailed Report. It contains the structure
 * and all the metadata that builds a detailed Report.
 */
public class DetailedReportGeneratorVisitor
        extends ReportGeneratorVisitor {

    /**
     * The duration calculated in the visit.
     */
    private Duration calculatedDuration;
    /**
     * The start time determined in the visit.
     */
    private LocalDateTime determinedStartTime;
    /**
     * The Endtime determined in the visit.
     */
    private LocalDateTime determinedEndTime;
    /**
     * The title of the inform.
     */
    public static final String TITLE = "Informe Detallat";
    /**
     * The subtitles of the inform.
     */
    public static final List<String> SECTIONS_TITLES =
            Collections.unmodifiableList(Arrays.asList("Període",
                    "Projectes Arrel", "Subprojectes",
                    "Tasques", "Intervals"));
    /**
     * The descriptions of the inform.
     */
    public static final List<String> SECTIONS_DESCRIPTIONS =
            Collections.unmodifiableList(Arrays.asList("", "",
                    "S'inclouen en la següent taula només els subprojectes"
                    + "que tinguin alguna tasca amb algun interval dins del"
                    + "període.",
                    "S'inclouen en la següent taula la durada de totes les"
                    + "tasques al període i s'especifica el projecte al qual"
                    + "pertanyen.",
                    "S'inclouen en la següent taula la data d'inici, final i"
                    + "durada de tots els intervals que es troben entre les"
                    + "dates especificades, afegint també la tasca i projecte"
                    + "al qual pertanyen. "));
    /**
     * The number of columns each section has.
     */
    public static final List<Integer> SECTIONS_COLUMNS_NUMBERS =
            Collections.unmodifiableList(Arrays.asList(2, 4, 5, 5, 6));
    /**
     * The headers of the table each section has.
     */
    public static final List<List<String>> SECTIONS_HEADERS =
            Collections.unmodifiableList(
            new ArrayList<List<String>>() {{
                add(Collections.unmodifiableList(Arrays.asList("",
                                                               "Data")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte",
                                                               "Data d'Inici",
                                                               "Data Final",
                                                               "Data Total")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte Pare",
                                                               "Projecte",
                                                               "Data d'Inici",
                                                               "Data Final",
                                                               "Data Total")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte Pare",
                                                   "Tasca", "Data d'Inici",
                                                 "Data Final", "Data Total")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte Pare",
                        "Tasca", "Interval", "Data d'Inici", "Data Final",
                                                                "Data Total")));
            }});
    /**
     * The index to period section.
     */
    public static final int PERIOD_INDEX = 0;
    /**
     * The index to root project section.
     */
    public static final int PROOT_INDEX = 1;
    /**
     * The index to subproject section.
     */
    public static final int SPROJ_INDEX = 2;
    /**
     * The index of the task section.
     */
    public static final int TASK_INDEX = 3;
    /**
     * The index of the interval section.
     */
    public static final int INTVL_INDEX = 4;
    /**
     * TheDetailed Generator Visitor.
     * @param startReportDateParam Start date boundary of the range to visit.
     * @param endReportDateParam End date boundary of the range to visit.
     * @param saverParam An object with the implementation to save the
     *                   report as a file.
     */
    public DetailedReportGeneratorVisitor(
                        final LocalDateTime startReportDateParam,
                        final LocalDateTime endReportDateParam,
                        final ReportSaver saverParam) {
        super(startReportDateParam, endReportDateParam, saverParam);
    }
    /**
     * Saving the section titles.
     */
    @Override
    public void save() {
        super.save(SECTIONS_TITLES);
    }
    /**
     * Initialize reports with all the metadata and builds the period table.
     */
    @Override
    protected void initializeReport() {
        Report report = new Report(TITLE,
                SECTIONS_TITLES, SECTIONS_DESCRIPTIONS,
                SECTIONS_COLUMNS_NUMBERS, SECTIONS_HEADERS);
        this.setReport(report);

        List<String> newRow = new ArrayList<String>() {{
            add("Desde");
            add(Client.formatDateTime(getStartReportDate()));
        }};

        getReport().addRowIntoSection(SECTIONS_TITLES.get(PERIOD_INDEX),
                newRow);

        newRow = new ArrayList<String>() {{
            add("Fins a");
            add(Client.formatDateTime(getEndReportDate()));
        }};
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PERIOD_INDEX),
                newRow);

        newRow = new ArrayList<String>() {{
            add("Data de generació de l'informe");
            add(Client.formatDateTime(LocalDateTime.now()));
        }};
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PERIOD_INDEX),
                newRow);

    }
    /**
     * Check if a project should be visited. As long as is not the dummie
     * if it is between the 2 dates, is a visitable project.
     */
    @Override
    protected boolean isVisitableProject(final Project project) {
        return !project.isDummie()
                && !project.getStartTime().isAfter(getEndReportDate())
                && !project.getEndTime().isBefore(getStartReportDate());
    }
    /**
     * To get info of concrete object Job.
     * @param job .
     */
    private void getInfoJob(final Job job) {
        calculatedDuration = job.getDurationInRange(getStartReportDate(),
                getEndReportDate());

        if (getStartReportDate().isAfter(job.getStartTime())) {
            determinedStartTime = getStartReportDate();
        } else {
            determinedStartTime = job.getStartTime();
        }
        if (getEndReportDate().isBefore(job.getEndTime())) {
            determinedEndTime = getEndReportDate();
        } else {
            determinedEndTime = job.getEndTime();
        }
    }
    /**
     * To get info of a project.
     */
    @Override
    protected void getInfoProject(final Project project) {
        getInfoJob(project);
    }
    /**
     * Adds a new row in the correspondent section with the info of a Project.
     */
    @Override
    protected void handleInfoProject(final Project project) {
        List<String> newRow = new ArrayList<String>() {{
            add(project.getName());
            add(Client.formatDateTime(determinedStartTime));
            add(Client.formatDateTime(determinedEndTime));
            add(Client.formatDuration(calculatedDuration));
        }};

        if (project.isRoot()) {
            addRowIntoPROOT(newRow);
        } else {
            newRow.add(0, project.getParentName());
            addRowIntoSPROJ(newRow);
        }
    }
    /**
     * Checks if a task should be visited.
     */
    @Override
    protected boolean isVisitableTask(final Task task) {
        return !task.getStartTime().isAfter(getEndReportDate())
                && !task.getEndTime().isBefore(getStartReportDate());
    }
    /**
     * To get info of a a task.
     */
    @Override
    protected void getInfoTask(final Task task) {
        getInfoJob(task);
    }
    /**
     * To handle info of a task.
     */
    @Override
    protected void handleInfoTask(final Task task) {
        List<String> newRow = new ArrayList<String>() {{
            add(task.getParentName());
            add(task.getName());
            add(Client.formatDateTime(determinedStartTime));
            add(Client.formatDateTime(determinedEndTime));
            add(Client.formatDuration(calculatedDuration));
        }};

        addRowIntoTASK(newRow);
    }
    /**
     * Check if a interval should be visited.
     */
    @Override
    protected boolean isVisitableInterval(final Interval interval) {
        return !interval.getStartTime().isAfter(getEndReportDate())
                && !interval.getEndTime().isBefore(getStartReportDate());
    }
    /**
     * To get info of an interval.
     * @param interval .
     */
    @Override
    protected void getInfoInterval(final Interval interval) {
        calculatedDuration = interval.getDurationInRange(getStartReportDate(),
                getEndReportDate());

        if (getStartReportDate().isAfter(interval.getStartTime())) {
            determinedStartTime = getStartReportDate();
        } else {
            determinedStartTime = interval.getStartTime();
        }
        if (getEndReportDate().isBefore(interval.getEndTime())) {
            determinedEndTime = getEndReportDate();
        } else {
            determinedEndTime = interval.getEndTime();
        }
    }
    /**
     * To handle an interval.
     */
    @Override
    protected void handleInfoInterval(final Interval interval) {
        final Task task = interval.getParent();
        List<String> newRow = new ArrayList<String>() {{
            add(task.getParentName());
            add(task.getName());
            add(interval.getName());
            add(Client.formatDateTime(determinedStartTime));
            add(Client.formatDateTime(determinedEndTime));
            add(Client.formatDuration(calculatedDuration));
        }};
        addRowIntoINTVL(newRow);
    }
    /**
     * To add the intro proot row.
     * @param newRow .
     */
    private void addRowIntoPROOT(final List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PROOT_INDEX), newRow);
    }
    /**
     * To add the intro sproj row.
     * @param newRow .
     */
    private void addRowIntoSPROJ(final List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(SPROJ_INDEX), newRow);
    }
    /**
     * To add the intro into task.
     * @param newRow .
     */
    private void addRowIntoTASK(final List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(TASK_INDEX), newRow);
    }
    /**
     * To add the intro interval.
     * @param newRow .
     */
    private void addRowIntoINTVL(final List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(INTVL_INDEX), newRow);
    }

}
