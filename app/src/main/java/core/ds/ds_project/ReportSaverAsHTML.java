package core.ds.ds_project;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import paginaweb.PaginaWeb;

public class ReportSaverAsHTML extends ReportSaver {
    private static final String EXTENSION = ".html";
    private static final int MAIN_HEADER = 1;
    private static final int SUBHEADER = 2;
    private static final boolean IS_SUBHEADER_CENTERED = false;
    private static final boolean IS_MAIN_HEADER_CENTERED = true;
    private static final boolean IS_PRIM = true;
    private static final boolean IS_COL = true;

    @Override
    //TODO Hay que cambiar lo de la tabla,
    // ella misma deberia saber si su primera col y fila se remarcan
    public void save(final Report report, final List<String> subtitlesList, final String filename) {
        PaginaWeb webPage = new PaginaWeb();
        webPage.afegeixLiniaSeparacio();
        webPage.afegeixHeader(report.getTitle(), MAIN_HEADER, IS_MAIN_HEADER_CENTERED);
        webPage.afegeixLiniaSeparacio();
        for (final String subtitle: subtitlesList) {
            ReportSection section = report.getFromReportSectionCollection(subtitle);
            webPage.afegeixHeader(section.getSubtitle(), SUBHEADER, IS_SUBHEADER_CENTERED);
            webPage.afegeixSaltDeLinia();
            webPage.afegeixTextNormal(section.getDescription());
            webPage.afegeixTaula(section.getTable().getTaula(), IS_PRIM, IS_COL);
            webPage.afegeixLiniaSeparacio();
        }
        saveFile(filename, webPage);
    }

    @Override
    protected void saveFile(final String filename, final StringBuilder sb) {
        try {
            PrintWriter writer = new PrintWriter(filename + EXTENSION, "UTF-8");
            writer.write(sb.toString());
            writer.close();

            System.out.print("Object has been serialized");

        } catch (IOException ex) {

            System.out.print("IOException when serializing the project" + ex.getMessage());
        }
    }

    private void saveFile(final String filename, final PaginaWeb pw) {

        try {
            // Creating a File object that represents the disk file.
            PrintStream o = new PrintStream(new File(filename + EXTENSION));
            PrintStream console = System.out;

            System.setOut(o);

            pw.escriuPagina();

            System.setOut(console);

        } catch (IOException ex) {

            System.out.print("IOException when serializing the project" + ex.getMessage());
        }
    }
}
