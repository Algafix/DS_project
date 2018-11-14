package core.ds.ds_project;

import java.util.List;

public abstract class ReportSaver {
    public abstract void save(Report report, List<String> subtitlesList, String filename);
    protected abstract void saveFile(String filename, StringBuilder sb);
}
