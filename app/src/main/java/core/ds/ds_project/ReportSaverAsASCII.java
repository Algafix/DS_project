package core.ds.ds_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that will save a report in ascii mode.
 */
public class ReportSaverAsASCII extends ReportSaver {

    /**
     * Extension of the file, to be referenced later.
     */
    public static final String EXTENSION = ".txt";

    /**
     * Logger of the class ReportSaverAsASCII.
     */
    private final Logger log =
            LoggerFactory.getLogger(ReportSaverAsASCII.class);

    /**
     * Length of the dashed line used as a separator.
     */
    private static final int DASH_LENGTH = 125;

    /**
     * Method that save a report object in a file in a proper way.
     * @param report Report to be saved (can be simple or detailed).
     * @param subtitlesList List with the titles of the sections.
     * @param filename Name of the file (the extension is a constant).
     */
    @Override
    public void save(final Report report,
                     final List<String> subtitlesList,
                     final String filename) {

        StringBuilder sb = new StringBuilder();
        String lineSeparator = IntStream
                .range(0, DASH_LENGTH)
                .mapToObj(j -> "-")
                .collect(Collectors.joining("")) + '\n';
        sb.append(report.getTitle() + '\n');
        sb.append(lineSeparator);

        for (final String subtitle: subtitlesList) {
            ReportSection section =
                    report.getFromReportSectionCollection(subtitle);
            sb.append(section.getSubtitle() + '\n');
            sb.append(section.getDescription() + '\n');
            sb.append(section.tableToString() + '\n');
            sb.append(lineSeparator);
        }
        saveFile(filename, sb);
    }

    /**
     * Method that handles the file managing.
     * @param filename Name of the file to be filled.
     * @param sb String with the text to be writted.
     */
    protected void saveFile(final String filename, final StringBuilder sb) {
        try {
            PrintWriter writer = new PrintWriter(filename + EXTENSION, "UTF-8");
            writer.write(sb.toString());
            writer.close();

            log.info("The report has been saved");

        } catch (IOException ex) {

            log.error("IOException when saving the report", ex);
        }
    }
}
