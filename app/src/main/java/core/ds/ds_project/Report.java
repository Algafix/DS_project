package core.ds.ds_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Not necessary understandable if necessary explain the class.
 */
public class Report {

    private String title = "";
    private Map<String, ReportSection> reportSectionCollection = new HashMap<>();

    public Report(final String titleParam) {
        title = titleParam;
    }

    public Report(final String titleParam, final List<String> sectionsTitles,
                  final List<String> sectionsDescriptions,
                  final List<Integer> sectionsColumnNumbers,
                  final List<List<String>> sectionsHeaders) {
        title = titleParam;
        for (int i = 0; i < sectionsTitles.size(); i++) {
            String subtitle = sectionsTitles.get(i);
            ReportSection section = new ReportSection(subtitle, sectionsDescriptions.get(i),
                    sectionsColumnNumbers.get(i), sectionsHeaders.get(i));

            reportSectionCollection.put(subtitle, section);
        }
    }

    public void addRowIntoSection(final String sectionName, final List row) {
        reportSectionCollection.computeIfPresent(sectionName, (k, v) -> {
            v.addRow(row);
            return v;
        });
    }

    public String getTitle() {
        return title;
    }

    public Map<String, ReportSection> getReportSectionCollection() {
        return reportSectionCollection;
    }

    public ReportSection getFromReportSectionCollection(final String subtitle) {
        return reportSectionCollection.get(subtitle);
    }
}
