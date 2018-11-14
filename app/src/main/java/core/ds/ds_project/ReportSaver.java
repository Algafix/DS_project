package core.ds.ds_project;

import java.util.List;
/**
 * Not necessary understandable.
 */
public abstract class ReportSaver {
    /**
     * to save a report.
     * @param report .
     * @param subtitlesList .
     * @param filename .
     */
    public abstract void save(Report report,
                              List<String> subtitlesList,
                              String filename);
}
