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
     * Whether the first row is a header row or not.
     */
    private boolean isFirstRowHeader;

    /**
     * Default value for isFirstRowHeader.
     */
    private static final boolean IS_FIRST_ROW_HEADER = true;

    /**
     * Whether the first row is a header row or not.
     */
    private boolean isFirstColumnHeader;

    /**
     * Default value for isFirstColumnHeader.
     */
    private static final boolean IS_FIRST_COLUMN_HEADER = false;

    /**
     * Constructor of the ReportSection.
     * @param subtitleParam Subtitle of the section.
     * @param descriptionParam Description of the section.
     * @param columnsNumber Columns of the info table.
     * @param tableHeaders Headers of the table.
     * @param isFirstRowColumnHeaderParam An optional
     *        array that contains the boolean values
     *        indicating whether the first row is a header and
     *        if the first column is a header. If only one is passed,
     *        it is supposed to be the row one. If none is passed, both
     *        will have the default values.
     */
    public ReportSection(final String subtitleParam,
                         final String descriptionParam,
                         final int columnsNumber,
                         final List tableHeaders,
                         final boolean... isFirstRowColumnHeaderParam) {
        subtitle = subtitleParam;
        description = descriptionParam;
        table = new Taula(0, columnsNumber);
        table.afegeixFila(tableHeaders);
        if (isFirstRowColumnHeaderParam.length > 0) {
            if (isFirstRowColumnHeaderParam.length == 1) {
                isFirstRowHeader = isFirstRowColumnHeaderParam[0];
                isFirstColumnHeader = IS_FIRST_COLUMN_HEADER;
            } else {
                isFirstRowHeader = isFirstRowColumnHeaderParam[0];
                isFirstColumnHeader = isFirstRowColumnHeaderParam[1];
            }
        } else {
            isFirstRowHeader = IS_FIRST_ROW_HEADER;
            isFirstColumnHeader = IS_FIRST_COLUMN_HEADER;
        }
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
     * Returns the info table of the section as an ArrayList.
     * @return ArrayList with the info of the section.
     */
    public ArrayList getTableAsArrayList() {
        return table.getTaula();
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
     * Print the info table.
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

    /**
     * Indicates if the first column is a header.
     * @return the boolean value.
     */
    public boolean isFirstColumnHeader() {
        return isFirstColumnHeader;
    }

    /**
     * Indicates if the first row is a header.
     * @return the boolean value.
     */
    public boolean isFirstRowHeader() {
        return isFirstRowHeader;
    }
}
