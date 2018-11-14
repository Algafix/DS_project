package core.ds.ds_project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReportSaverAsASCII extends ReportSaver {

    public static String EXTENSION = ".txt";
    public static int DASH_LENGTH = 125;

    @Override
    public void save(final Report report, final List<String> subtitlesList, final String filename) {
        StringBuilder sb = new StringBuilder();
        String lineSeparator = IntStream
                .range(0, DASH_LENGTH)
                .mapToObj(j -> "-")
                .collect(Collectors.joining("")) + '\n';
        sb.append(report.getTitle() + '\n');
        sb.append(lineSeparator);
        for (final String subtitle: subtitlesList) {
            ReportSection section = report.getFromReportSectionCollection(subtitle);
            sb.append(section.getSubtitle() + '\n');
            sb.append(section.getDescription()+ '\n');
            sb.append(section.tableToString()+ '\n');
            sb.append(lineSeparator);
        }
        saveFile(filename, sb);
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
}
