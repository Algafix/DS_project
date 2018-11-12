package core.ds.ds_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Client class implements two methods that change date format
 *  another method to deserialize projects and the main where the app and
 *  test are executed.
 *
 * @author Adrià Figuerola, Aleix Galan, Diego Fraile
 * @version 1.0
 */

public final class Client {


    /**
     * Constructor of class Client never called.
     */
    private Client() {
        //not called
    }

    /**
     * Convert object duration to string with format HH:mm:ss.
     *
     * @param duration .
     * @return the duration with format HH:mm:ss
     */
    public static String formatDuration(final Duration duration) {
        /**
         * The seconds of an hour.
         */
        final int secondsHour = 3600;
        /**
         * The minutes of an hour.
         */
        final int minutesHour = 60;

        if (duration == null) {
            return null;
        } else {
            long seconds = duration.getSeconds();
            long absSeconds = Math.abs(seconds);
            String positive = String.format(Locale.getDefault(),
                    "%02d:%02d:%02d",
                    absSeconds / secondsHour,
                    (absSeconds % secondsHour) / minutesHour,
                    absSeconds % minutesHour);

            return positive;
        }

    }
    /**
     * Convert object LocalDateTime to string with format dd-MMM-yyyy HH:mm:ss.
     *
     * @param time .
     * @return the duration with format HH:mm:ss
     */
    public static String formatDateTime(final LocalDateTime time) {

        if (time != null) {
            final DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
            return time.format(formatter);
        } else {
            return "                       ";
        }
    }

    /**
     * Serializes a project to a file with the name given by the parameters.
     *
     * @param allFather the project expected to be received (dummy project)
     * and the one we will serialize
     * @param filename the name of the file where the project wil be saved
     */
    public static void serializeProject(final Project allFather,
                                        final String filename) {

        try {

            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(allFather);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {

            System.out.println("IOException is caught");
        }

    }

    /**
     * Deserialize a project from a file with the name given by the parameters.
     *
     * @param filename the name of the file where the project will be loaded
     * @return the project deserialize
     */
    public static Project deserializeProject(final String filename) {

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Project deserializeProject = (Project) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialize ");
            return deserializeProject;
        } catch (IOException ex) {

            System.out.println("IOException is caught");
            return null;
        } catch (ClassNotFoundException ex) {

            System.out.println("ClassNotFoundException is caught");
            return null;
        }
    }

    /**
     * Executes test of the app .
     *
     * @param args arguments necessary to main
     */
    public static void main(final String[] args) {

        final int timeUpdate = 2000;

        AppClock.getInstance(timeUpdate);

        Test test = new Test();

        test.testAppendA1();

        //test.testDeserialize();

        //test.testAppendA2();

        //test.testProgrammatedtaskAndLimitTimeTaskDecarator();

        //test.testLimitTimeDecorator();

        //test.testProgramatedtask();

        //test.testNestedInterval();

        //test.testReportTreeGenerate();
    }
}
