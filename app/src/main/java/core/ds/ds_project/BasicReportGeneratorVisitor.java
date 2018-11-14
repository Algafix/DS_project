package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Not necessary understandable if necessary explain the class.
 */
public class BasicReportGeneratorVisitor
        extends ReportGeneratorVisitor {

    private Duration calculatedDuration;
    private LocalDateTime determinedStartTime;
    private LocalDateTime determinedEndTime;

    public static final String TITLE = "Informe Breu";

    public static final List<String> SECTIONS_TITLES =
            Collections.unmodifiableList(Arrays.asList("Període", "Projectes Arrel"));
    public static final List<String> SECTIONS_DESCRIPTIONS =
            Collections.unmodifiableList(Arrays.asList("", ""));
    public static final List<Integer> SECTIONS_COLUMNS_NUMBERS =
            Collections.unmodifiableList(Arrays.asList(2, 4));

    public static final List<List<String>> SECTIONS_HEADERS = Collections.unmodifiableList(
            new ArrayList<List<String>>() {{
                add(Collections.unmodifiableList(Arrays.asList("", "Data")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte", "Temps d'Inici",
                                                                "Temps Final", "Temps Total")));
            }});

    public static final int PERIOD_INDEX = 0;
    public static final int PROOT_INDEX = 1;

    public BasicReportGeneratorVisitor(final LocalDateTime startReportDateParam,
                                       final LocalDateTime endReportDateParam,
                                       final ReportSaver saverParam) {
        super(startReportDateParam, endReportDateParam, saverParam);
    }

    @Override
    public void save() {
        super.save(SECTIONS_TITLES);
    }

    @Override
    protected void initializeReport() {
        Report report = new Report(TITLE, SECTIONS_TITLES, SECTIONS_DESCRIPTIONS,
                SECTIONS_COLUMNS_NUMBERS, SECTIONS_HEADERS);
        this.setReport(report);

        List<String> newRow = new ArrayList<String>() {{
            add("Desde");
            add(Client.formatDateTime(getStartReportDate()));
        }};
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PERIOD_INDEX), newRow);

        newRow = new ArrayList<String>() {{
            add("Fins a");
            add(Client.formatDateTime(getEndReportDate()));
        }};
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PERIOD_INDEX), newRow);

        newRow = new ArrayList<String>() {{
            add("Data de generació de l'informe");
            add(Client.formatDateTime(LocalDateTime.now()));
        }};
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PERIOD_INDEX), newRow);

    }

    @Override
    protected boolean isVisitableProject(final Project project) {
        return project.isRoot() && !project.getStartTime().isAfter(getEndReportDate())
                && !project.getEndTime().isBefore(getStartReportDate());
    }

    @Override
    protected void getInfoProject(final Project project) {

        calculatedDuration = project.getDurationInRange(getStartReportDate(), getEndReportDate());

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
