package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Visitor that obtains and adapt the
 * information needed for a report between 2 dates.
 */
public class BasicReportGeneratorVisitor
        extends ReportGeneratorVisitor {

    /**
     * Total duration in the interval.
     */
    private Duration calculatedDuration;

    /**
     * Start time of the report.
     */
    private LocalDateTime determinedStartTime;

    /**
     * End time of the report.
     */
    private LocalDateTime determinedEndTime;

    /**
     * Title of the report, in this class is Basic Report.
     */
    public static final String TITLE = "Informe Breu";

    /**
     * List with the names of each section.
     */
    public static final List<String> SECTIONS_TITLES =
            Collections.unmodifiableList(
                    Arrays.asList("Període", "Projectes Arrel"));

    /**
     * List with the description of each section of the report.
     */
    public static final List<String> SECTIONS_DESCRIPTIONS =
            Collections.unmodifiableList(Arrays.asList("", ""));

    /**
     * List with the columns of the tables of each section.
     */
    public static final List<Integer> SECTIONS_COLUMNS_NUMBERS =
            Collections.unmodifiableList(Arrays.asList(2, 4));

    /**
     * Headers of the tables of each section.
     */
    public static final List<List<String>> SECTIONS_HEADERS =
            Collections.unmodifiableList(
            new ArrayList<List<String>>() {{
                add(Collections.unmodifiableList(
                        Arrays.asList("", "Data")));
                add(Collections.unmodifiableList(
                        Arrays.asList("Projecte", "Temps d'Inici",
                                "Temps Final", "Temps Total")));
            }});

    /**
     * Index of the first section of the report (info about the report).
     */
    public static final int PERIOD_INDEX = 0;

    /**
     * Index of the second section of the report (root projects).
     */
    public static final int PROOT_INDEX = 1;

    /**
     * Contructor of the BasicReportGeneratorVisitor class,
     * it will initialize the values that will define the
     * interval of the report and how it's stored.
     * @param startReportDateParam Starting date of the report.
     * @param endReportDateParam Ending date of the report.
     * @param saverParam Saver object that will define how
     *                  the report is stored.
     */
    public BasicReportGeneratorVisitor(final LocalDateTime startReportDateParam,
                                       final LocalDateTime endReportDateParam,
                                       final ReportSaver saverParam) {
        super(startReportDateParam, endReportDateParam, saverParam);
    }

    /**
     * Method that calls the superclass to save
     * the non-changing parts of the report.
     */
    @Override
    public void save() {
        super.save(SECTIONS_TITLES);
    }

    /**
     * Generates a report object with the information
     * obtained via visitor pattern.
     */
    @Override
    protected void initializeReport() {
        Report report = new Report(TITLE, SECTIONS_TITLES,
                SECTIONS_DESCRIPTIONS, SECTIONS_COLUMNS_NUMBERS,
                SECTIONS_HEADERS);
        this.setReport(report);

        List<String> newRow = new ArrayList<String>() {{
            add("Desde");
            add(Client.formatDateTime(getStartReportDate()));
        }};
        getReport().addRowIntoSection(
                SECTIONS_TITLES.get(PERIOD_INDEX), newRow);

        newRow = new ArrayList<String>() {{
            add("Fins a");
            add(Client.formatDateTime(getEndReportDate()));
        }};
        getReport().addRowIntoSection(
                SECTIONS_TITLES.get(PERIOD_INDEX), newRow);

        newRow = new ArrayList<String>() {{
            add("Data de generació de l'informe");
            add(Client.formatDateTime(LocalDateTime.now()));
        }};
        getReport().addRowIntoSection(
                SECTIONS_TITLES.get(PERIOD_INDEX), newRow);

    }

    /**
     * Check if a project should be visited in this kind of Report.
     * In this visitor we are only interested in root projects.
     * @param project Project to be checked.
     * @return True if we should visit this project.
     */
    @Override
    protected boolean isVisitableProject(final Project project) {
        return (
        project.isRoot() && !project.getStartTime().isAfter(getEndReportDate())
        && !project.getEndTime().isBefore(getStartReportDate())
        );
    }

    /**
     * Obtain the necessary information of a given project
     * and store-it.
     * @param project Project to be read.
     */
    @Override
    protected void getInfoProject(final Project project) {

        calculatedDuration = project.getDurationInRange(getStartReportDate(),
                getEndReportDate());

        if (getStartReportDate().isAfter(project.getStartTime())) {
            determinedStartTime = getStartReportDate();
        } else {
            determinedStartTime = project.getStartTime();
        }
        if (getEndReportDate().isBefore(project.getEndTime())) {
            determinedEndTime = getEndReportDate();
        } else {
            determinedEndTime = project.getEndTime();
        }
    }

    /**
     * Transforms the dates and times of a project to a readable form.
     * @param project Project to be modified.
     */
    @Override
    protected void handleInfoProject(final Project project) {
        List<String> newRow = new ArrayList<String>() {{
            add(project.getName());
            add(Client.formatDateTime(determinedStartTime));
            add(Client.formatDateTime(determinedEndTime));
            add(Client.formatDuration(calculatedDuration));
        }};
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PROOT_INDEX), newRow);
    }

}
