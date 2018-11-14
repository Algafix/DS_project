package core.ds.ds_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import paginaweb.PaginaWeb;
/**
 * ReportSaver with the implementation to save the report as a HTML file.
 */
public class ReportSaverAsHTML extends ReportSaver {
    /**
     * Initialize the extension.
     */
    private static final String EXTENSION = ".html";
    
    /**
     * Logger of the class ReportSaverAsASCII.
     */
    private final Logger log =
            LoggerFactory.getLogger(ReportSaverAsHTML.class);
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
     * To save all the table.
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
     * To save a file.
     * @param filename String with the name of the file
     * @param pw A web page object to be saved.
     */
    private void saveFile(final String filename, final PaginaWeb pw) {

        try {
            // Creating a File object that represents the disk file.
            PrintStream o = new PrintStream(new File(filename + EXTENSION));
            PrintStream console = System.out;

            System.setOut(o);

            pw.escriuPagina();

            System.setOut(console);

            log.info("The report has been saved");

        } catch (IOException ex) {

            log.error("IOException when saving the report", ex);
        }
    }
}
