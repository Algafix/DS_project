package core.ds.ds_project;

import java.util.List;
/**
 * Class to save the Report, following the strategy pattern.
 */
public abstract class ReportSaver {
    /**
     * To save a report.
     * @param report The report to be saved
     * @param subtitlesList List of string keys (subtitles) in the order we want the sections
     * @param filename The filename without extension.
     */
    public abstract void save(Report report,
                              List<String> subtitlesList,
                              String filename);
}
