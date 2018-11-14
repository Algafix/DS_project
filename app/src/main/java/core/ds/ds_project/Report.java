package core.ds.ds_project;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Superclass that will handle de different
 * kinds of report (ascii, html...) in its subclasses.
 */
public class Report {

    /**
     * Logger of the class Report.
     */
    private final Logger log = LoggerFactory.getLogger(Interval.class);
    /**
     * Title of the report.
     */
    private String title = "";

    /**
     * Dictionary with the row info.
     */
    private Map<String, ReportSection> reportSectionCollection =
            new HashMap<>();

    /**
     * Constructor of the Report class.
     * @param titleParam Name of the report.
     */
    public Report(final String titleParam) {
        title = titleParam;
    }

    /**
     * Constructor of the Report class.
     * @param titleParam Name of the report.
     * @param sectionsTitles List with the titles for the report.
     * @param sectionsDescriptions List with the description of the sections.
     * @param sectionsColumnNumbers List with each section column numbers.
     * @param sectionsHeaders List with the table headers of that section.
     */
    public Report(final String titleParam, final List<String> sectionsTitles,
                  final List<String> sectionsDescriptions,
                  final List<Integer> sectionsColumnNumbers,
                  final List<List<String>> sectionsHeaders) {
        title = titleParam;
        for (int i = 0; i < sectionsTitles.size(); i++) {

            String subtitle = sectionsTitles.get(i);
            ReportSection section = new ReportSection(subtitle,
                    sectionsDescriptions.get(i),
                    sectionsColumnNumbers.get(i),
                    sectionsHeaders.get(i));

            reportSectionCollection.put(subtitle, section);
        }
    }

    /**
     * Adds a given row into a report section.
     * @param sectionName Name of the section where the row will be added.
     * @param row Row to add.
     */
    public void addRowIntoSection(final String sectionName, final List row) {
        reportSectionCollection.computeIfPresent(sectionName, (k, v) -> {
            v.addRow(row);
            log.debug("Row correctly added into key" + k);
            return v;
        });

    }

    /**
     * Returns the title of the report.
     * @return Title of the report.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the dictionary with the sections of the report.
     * @return Dictionary with the sections.
     */
    public Map<String, ReportSection> getReportSectionCollection() {
        return reportSectionCollection;
    }

    /**
     * Returns the concrete section of the report.
     * @param subtitle Key of the section.
     * @return Section of the report.
     */
    public ReportSection getFromReportSectionCollection(final String subtitle) {
        return reportSectionCollection.get(subtitle);
    }
}
