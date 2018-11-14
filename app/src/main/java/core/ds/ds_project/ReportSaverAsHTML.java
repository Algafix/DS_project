package core.ds.ds_project;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import paginaweb.PaginaWeb;
/**
 * Not necessary understandable.
 */
public class ReportSaverAsHTML extends ReportSaver {
    /**
     * Initialize the extension.
     */
    private static final String EXTENSION = ".html";
    /**
     * Initialize the main header.
     */
    private static final int MAIN_HEADER = 1;
    /**
     * Initialize the sub header.
     */
    private static final int SUBHEADER = 2;
    /**
     * Initialize the sub header centered.
     */
    private static final boolean IS_SUBHEADER_CENTERED = false;
    /**
     * Initialize the extension the sub header centered.
     */
    private static final boolean IS_MAIN_HEADER_CENTERED = true;
    /**
     * To save all the table  .
     */
    @Override
    public void save(final Report report,
                     final List<String> subtitlesList,
                     final String filename) {
        PaginaWeb webPage = new PaginaWeb();
        webPage.afegeixLiniaSeparacio();
        webPage.afegeixHeader(report.getTitle(), MAIN_HEADER,
                IS_MAIN_HEADER_CENTERED);
        webPage.afegeixLiniaSeparacio();
        for (final String subtitle: subtitlesList) {
            ReportSection section = (
                    report.getFromReportSectionCollection(subtitle));
            webPage.afegeixHeader(section.getSubtitle(), SUBHEADER,
                    IS_SUBHEADER_CENTERED);
            webPage.afegeixTextNormal(section.getDescription());
            webPage.afegeixSaltDeLinia();
            webPage.afegeixTaula(section.getTableAsArrayList(),
                    section.isFirstRowHeader(),
                    section.isFirstColumnHeader());
            webPage.afegeixLiniaSeparacio();
        }
        saveFile(filename, webPage);
    }
    /**
     * To save the file.
     */
    @Override
    protected void saveFile(final String filename, final StringBuilder sb) {
        try {
            PrintWriter writer = new PrintWriter(filename + EXTENSION, "UTF-8");
            writer.write(sb.toString());
            writer.close();

            System.out.print("Object has been serialized");

        } catch (IOException ex) {

            System.out.print("IOException when serializing the project"
                    + ex.getMessage());
        }
    }
    /**
     * To save a file.
     * @param filename .
     * @param pw  web page.
     */
    private void saveFile(final String filename, final PaginaWeb pw) {

        try {
            // Creating a File object that represents the disk file.
            PrintStream o = new PrintStream(new File(filename + EXTENSION));
            PrintStream console = System.out;

            System.setOut(o);

            pw.escriuPagina();

            System.setOut(console);

        } catch (IOException ex) {

            System.out.print("IOException when serializing the project"
                    + ex.getMessage());
        }
    }
}
