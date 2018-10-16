package core.ds.ds_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    
    /**
     * Convierte un duration en un string para mostrar formato HH:mm:ss
     */
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;

    }

    public static String formatDateTime(LocalDateTime time) {

        if(time != null ){
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
            return time.format(formatter);
        }
        else{
            return "                       ";
        }
    }

    /**
     * Start an interval that will be stopped afther the given number of seconds. This function is
     * called from testForInterval()
     *
     * @param numero number in seconds.
     */
    public static void testInterval(int numero) {

        Interval interval = new Interval("Interval "+numero, null);
        System.out.println("Creado "+interval.getName());

        new java.util.Timer().schedule(new IntervalTimerTask(interval), numero*1000);
    }


    /**
     * Call this function to debug the creation of concurrent threads and its time
     */
    public static void testForInterval() {
        for(int i = 1; i<=10 ; i++) {
            testInterval(i);
        }
    }

    /**
     * Call this function to debug the creation of intervals nested in project tree and propagation
     * of duration.

    public static Project testNestedInterval() {
        final Project allFather = new Project("Projecte Pare", "Projecte Pare",null);

        Project projecte1 = allFather.addChild(new Project("Projecte1", "Projecte de test1", allFather));

        Project projecte11 = projecte1.addChild(new Project("Projecte11", "Projecte anidat 11", projecte1));
        Task tasca12 = projecte1.addChild(new Task("Tasca12", "Tasca anidada 12", projecte1));

        Task tasca111 = projecte11.addChild(new Task("Tasca111", "Tasca anidada 111", projecte11));
        Task tasca112 = projecte11.addChild(new Task("Tasca112", "Tasca anidada 112", projecte11));

        Interval interval1 = tasca12.addInterval("interval1");
        Interval interval2 = tasca111.addInterval("interval2");
        Interval interval3 = tasca111.addInterval("interval3");

        // Prints the tree with initial durations (intervals has null because haven't stopped yet
        allFather.printDebug("");

        // Stop the intervals
        final Timer timer = new Timer();
        timer.schedule(new IntervalTimerTask(interval1) , 2000);
        timer.schedule(new IntervalTimerTask(interval2), 2000);
        timer.schedule(new IntervalTimerTask(interval3), 10000);

        // Reprint the tree with updated durations
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                allFather.printDebug("");
                timer.cancel();
            }
        },11000);

        return allFather;

    }
    */
    /**
     * Test the Job and Interval Serialization

    public static void testSerializeSave() {

        final Project allFather = testNestedInterval();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                serializeProject(allFather, "allFather.ser");
            }
        },7000);
    }
    */

    /**
     * Serializes a project to a file with the name given by the parameters
     *
     * @param allFather the project expected to be received (dummy project) and the one we will serialize
     * @param filename the name of the file where the project is going to be saved
     */
    public static void serializeProject(Project allFather, String filename){
        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(allFather);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

    }

    /**
     * Deserializes a project from a file with the name given by the parameters
     *
     * @param filename the name of the file where the project is going to be loaded from
     */
    public static Project deserializeProject(String filename){
        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Project deserializedProject = (Project)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            return deserializedProject;
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
            return null;
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
            return null;
        }
    }


    public static void main(String [] args){


        AppClock.getInstance(2000);

        Test test = new Test();

        //test.testApenndA1();

        test.testApenndA2();

        //test.testLimitTimeDecorator();

        //test.testProgramatedtask();

        //testAppClockInterval();

        //testIntervalStop();

        //test.testForInterval();

        //test.testNestedInterval();
    }
}
