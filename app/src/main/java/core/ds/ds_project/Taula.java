package core.ds.ds_project;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Class that handle a Table for the reports.
 */
public class Taula {

    public static final int COL_SEPARATION = 3;

    /**
     * Number of rows.
     */
    private int nfiles;

    /**
     * Get number of rows of the table.
     * @return Number of rows.
     */
    public int getNfiles() {
        return nfiles;
    }

    /**
     * Set the number of rows of the table.
     * @param nfilesParam Number of rows.
     */
    protected void setNfiles(final int nfilesParam) {
        nfiles = nfilesParam;
    }

    /**
     * Number of columns.
     */
    private int ncolumnes;

    /**
     * Get the number of columns of the table.
     * @return Number of columns.
     */
    public int getNcolumnes() {
        return ncolumnes;
    }

    /**
     * Set the number of columns of the table.
     * @param ncolumnesParam Number of columns.
     */
    protected void setNcolumnes(final int ncolumnesParam) {
        ncolumnes = ncolumnesParam;
    }

    /**
     * Array that represents a table.
     */
    private ArrayList<List<String>> taula = null;

    /**
     * Get the object table.
     * @return Table object.
     */
    public ArrayList getTaula() {
        return taula;
    }

    /**
     * Set an object table.
     * @param taulaParam ArrayList object that represents a table.
     */
    public void setTaula(final ArrayList taulaParam) {
        taula = taulaParam;
    }

    /**
     * Constructor of the Taula class. For each row adds
     * an array with the values for each column.
     * @param nfilesParam Number of rows of the table.
     * @param ncolumnesParam Number of columns of the table.
     */
    public Taula(final int nfilesParam, final int ncolumnesParam) {
        setNfiles(nfilesParam);
        setNcolumnes(ncolumnesParam);
        ArrayList t = new ArrayList();
        for (int i = 0; i < nfiles; i++) {
            ArrayList fila = new ArrayList();
            for (int j = 0; j < ncolumnes; j++) {
                // fila.add(new String());
                fila.add(null);
            }
            t.add(fila);
        }
        setTaula(t);
    }

    /**
     * Adds a row at the end of the table.
     */
    public void afegeixFila() {
        int ncolumnesFila = getNcolumnes();
        ArrayList fila = new ArrayList();
        for (int j = 0; j < ncolumnesFila; j++) {
            // fila.add(new String());
            fila.add(null);
        }
        getTaula().add(fila);
        setNfiles(getNfiles() + 1);
    }

    /**
     * Adds a given arraylist as a row in the table.
     * @param llistaStrings Array that represents a row.
     */
    public void afegeixFila(final List<String> llistaStrings) {
        getTaula().add(llistaStrings);
        setNfiles(getNfiles() + 1);
    }

    /**
     * Change the value of a cell in the table.
     * @param fila Row of the cell.
     * @param columna Column of the cell.
     * @param str String for the value of the cell.
     */
    public void setPosicio(final int fila,
                           final int columna,
                           final String str) {
        // numerem de 1 ... n i no de 0 ... n-1
        ((ArrayList) getTaula().get(fila - 1)).set(columna - 1, str);
    }

    /**
     * Get the value of a cell.
     * @param fila Row of the cell.
     * @param columna Column of the cell.
     * @return Value of the cell.
     */
    public String getPosicio(final int fila, final int columna) {
        return (String) ((ArrayList) getTaula().get(fila - 1)).get(columna - 1);
    }

    /**
     * Prints the table object.
     */
    public void imprimeix() {
        int maxLength = getMaxLength();
        for (final List<String> row: taula) {
            for (int i = 0; i < ncolumnes; i++) {
                String padding = IntStream
                        .range(0, maxLength - row.get(i).length() + COL_SEPARATION)
                        .mapToObj(j -> " ")
                        .collect(Collectors.joining(""));
                System.out.print(row.get(i) + padding);
            }
            System.out.print("\n");
        }
    }

    private int getMaxLength() {
        int maxLength = 0;
        for (final List<String> row: taula) {
            for (int i = 0; i < ncolumnes; i++) {
                if (maxLength < row.get(i).length()) {
                    maxLength = row.get(i).length();
                }
            }
        }
        return maxLength;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int maxLength = getMaxLength();
        for (final List<String> row: taula) {
            for (int i = 0; i < ncolumnes; i++) {
                String padding = IntStream
                        .range(0, maxLength - row.get(i).length() + COL_SEPARATION)
                        .mapToObj(j -> " ")
                        .collect(Collectors.joining(""));
                sb.append(row.get(i) + padding);
            }
            sb.append('\n');
        }
        return sb.toString();
    }


}
