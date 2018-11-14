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
public class DetailedReportGeneratorVisitor
        extends ReportGeneratorVisitor {

    private Duration calculatedDuration;
    private LocalDateTime determinedStartTime;
    private LocalDateTime determinedEndTime;

    public static final String TITLE = "Informe Detallat";

    public static final List<String> SECTIONS_TITLES =
            Collections.unmodifiableList(Arrays.asList("Període", "Projectes Arrel", "Subprojectes",
                    "Tasques", "Intervals"));
    public static final List<String> SECTIONS_DESCRIPTIONS =
            Collections.unmodifiableList(Arrays.asList("", "", "S'inclouen en la següent taula només els subprojectes que tinguin alguna tasca amb algun interval dins del període.",
                    "S'inclouen en la següent taula la durada de totes les tasques al període i s'especifica el projecte al qual pertanyen.",
                    "S'inclouen en la següent taula la data d'inici, final i durada de tots els intervals que es troben entre les dates especificades, afegint també la tasca i projecte al qual pertanyen. "));
    public static final List<Integer> SECTIONS_COLUMNS_NUMBERS =
            Collections.unmodifiableList(Arrays.asList(2, 4, 5, 5, 6));

    public static final List<List<String>> SECTIONS_HEADERS = Collections.unmodifiableList(
            new ArrayList<List<String>>() {{
                add(Collections.unmodifiableList(Arrays.asList("", "Data")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte", "Data d'Inici",
                        "Data Final", "Data Total")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte Pare", "Projecte",
                        "Data d'Inici", "Data Final", "Data Total")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte Pare", "Tasca",
                        "Data d'Inici", "Data Final", "Data Total")));
                add(Collections.unmodifiableList(Arrays.asList("Projecte Pare", "Tasca",
                        "Interval", "Data d'Inici", "Data Final", "Data Total")));
            }});

    public static final int PERIOD_INDEX = 0;
    public static final int PROOT_INDEX = 1;
    public static final int SPROJ_INDEX = 2;
    public static final int TASK_INDEX = 3;
    public static final int INTVL_INDEX = 4;

    public DetailedReportGeneratorVisitor(
                                        final LocalDateTime startReportDateParam,
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
        return !project.isDummie() && !project.getStartTime().isAfter(getEndReportDate())
                && !project.getEndTime().isBefore(getStartReportDate());
    }

    private void getInfoJob(final Job job) {
        calculatedDuration = job.getDurationInRange(getStartReportDate(), getEndReportDate());

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

    @Override
    protected void getInfoProject(final Project project) {
        getInfoJob(project);
    }

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

    @Override
    protected boolean isVisitableTask(final Task task) {
        return !task.getStartTime().isAfter(getEndReportDate())
                && !task.getEndTime().isBefore(getStartReportDate());
    }

    @Override
    protected void getInfoTask(final Task task) {
        getInfoJob(task);
    }

    @Override
    protected void handleInfoTask(final Task task) {
        List<String> newRow = new ArrayList<String>() {{
            add(task.getName());
            add(task.getName());
            add(Client.formatDateTime(determinedStartTime));
            add(Client.formatDateTime(determinedEndTime));
            add(Client.formatDuration(calculatedDuration));
        }};

        addRowIntoTASK(newRow);
    }


    @Override
    protected boolean isVisitableInterval(final Interval interval) {
        return !interval.getStartTime().isAfter(getEndReportDate())
                && !interval.getEndTime().isBefore(getStartReportDate());
    }

    @Override
    protected void getInfoInterval(final Interval interval) {
        calculatedDuration = interval.getDurationInRange(getStartReportDate(), getEndReportDate());

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

    @Override
    protected void handleInfoInterval(final Interval interval) {
        Task task = interval.getParent();
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



    private void addRowIntoPROOT(List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(PROOT_INDEX), newRow);
    }

    private void addRowIntoSPROJ(List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(SPROJ_INDEX), newRow);
    }

    private void addRowIntoTASK(List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(TASK_INDEX), newRow);
    }

    private void addRowIntoINTVL(List<String> newRow) {
        getReport().addRowIntoSection(SECTIONS_TITLES.get(INTVL_INDEX), newRow);
    }

}
