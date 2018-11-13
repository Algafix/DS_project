package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

public class ReportSection {
    private String subtitle = "";
    private String description = "";
    private Taula table = null;

    public ReportSection(final String subtitleParam,
                         final String descriptionParam,
                         final int columnsNumber,
                         final List tableHeaders) {
        subtitle = subtitleParam;
        description = descriptionParam;
        table = new Taula(0, columnsNumber);
        table.afegeixFila((ArrayList) tableHeaders);
    }

    public String getDescription() {
        return description;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Taula getTable() {
        return table;
    }

    public void setDescription(final String descriptionParam) {
        description = descriptionParam;
    }

    public void setSubtitle(final String subtitleParam) {
        subtitle = subtitleParam;
    }

    public void setTable(final Taula tableParam) {
        table = tableParam;
    }

    public void addRow(final List stringsList) {
        table.afegeixFila((ArrayList) stringsList);
    }

}
