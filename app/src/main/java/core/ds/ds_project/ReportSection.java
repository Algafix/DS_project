package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles an entire report section,
 * each section has a table, a title and a description.
 */
public class ReportSection {

    /**
     * Title of the subsection (aka subtitle).
     */
    private String subtitle = "";

    /**
     * Description of the subsection.
     */
    private String description = "";

    /**
     * Table with the info of the subsection.
     */
    private Taula table = null;

    /**
     * Constructor of the ReportSection.
     * @param subtitleParam Subtitle of the section.
     * @param descriptionParam Description of the section.
     * @param columnsNumber Columns of the info table.
     * @param tableHeaders Headers of the table.
     */
    public ReportSection(final String subtitleParam,
                         final String descriptionParam,
                         final int columnsNumber,
                         final List tableHeaders) {
        subtitle = subtitleParam;
        description = descriptionParam;
        table = new Taula(0, columnsNumber);
        table.afegeixFila(tableHeaders);
    }

    /**
     * Returns the description of the section.
     * @return Description of the section.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the title of the section.
     * @return Title of the section.
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Returns the info table of the section.
     * @return Table with the info of the section.
     */
    public Taula getTable() {
        return table;
    }

    /**
     * Set the description of the section.
     * @param descriptionParam Description of the section.
     */
    public void setDescription(final String descriptionParam) {
        description = descriptionParam;
    }

    /**
     * Set the title of the section.
     * @param subtitleParam Title of the section.
     */
    public void setSubtitle(final String subtitleParam) {
        subtitle = subtitleParam;
    }

    /**
     * Set the info table of the section.
     * @param tableParam Table with the info.
     */
    public void setTable(final Taula tableParam) {
        table = tableParam;
    }

    /**
     * Add a new row at the end of the info table.
     * @param stringsList List with same items as columns.
     */
    public void addRow(final List stringsList) {
        table.afegeixFila((ArrayList) stringsList);
    }

    /**
     * Print the info talble.
     */
    public void printTable() {
        table.imprimeix();
    }

    /**
     * Converts the info table to string.
     * @return Table as string.
     */
    public String tableToString() {
        return table.toString();
    }
}
