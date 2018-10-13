package core.ds.ds_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Client {


    /**
     * Test the nested creation of Jobs
     */
    public static void testAnidament() {

        Project allFather = new Project("Projecte Pare", "Projecte Pare",null);

        Project projecte1 = allFather.addChild(new Project("Projecte1", "Projecte de test1", allFather));
        Project projecte2 = allFather.addChild(new Project("Projecte2", "Projecte de test2", allFather));

        Project projecte11 = projecte1.addChild(new Project("Projecte11", "Projecte anidat 11", projecte1));
        projecte1.addChild(new Task("Tasca12", "Tasca anidada 12", projecte1));

        projecte2.addChild(new Task("Tasca21", "Tasca anidada 21", projecte2));

        projecte11.addChild(new Task("Tasca111", "Tasca anidada 111", projecte11));
        projecte11.addChild(new Task("Tasca112", "Tasca anidada 112", projecte11));

        allFather.printDebug("");
    }


    /**
     * Debug the creation and time of 1 interval
     */
    public static void testAppClockInterval() {
        final List<Interval> intervals = new ArrayList<Interval>();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("New interval");
                        intervals.add(new Interval("",null));
                    }
                },
                500, 10000
        );
    }


    /**
     * Convierte un duration en un string para mostrar.
     */
    public static String formatDuration(Duration duration) {
        if (duration == null) {
            return null;
        }else {
            return duration.toString()
                    .substring(2)
                    .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                    .toLowerCase();
        }
    }


    /**
     * Peligro mortal cosa de Diego
     */
    public static void testIntervalStop(){
        final AppClock appClock = AppClock.getInstance();
        final Interval interval1 = new Interval("",null);

        final List<Interval> intervals = new ArrayList<Interval>();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("New interval");
                        intervals.add(new Interval("",null));
                    }
                },
                1000, 1000
        );

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if(!intervals.isEmpty()) {
                            System.out.println("Stop interval1: " + formatDuration(intervals.get(0).stop()));
                            intervals.remove(0);
                        }
                    }
                },
                2000, 2000
        );

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
     */
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

    /**
     * Test the Job and Interval Serialization
     */
    public static void testSerializeSave() {

        final Project allFather = testNestedInterval();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                serializeProject(allFather, "allFather.ser");
            }
        },7000);
    }

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

        AppClock.getInstance(1000);

        //testAnidament();

        //testAppClockInterval();

        //testIntervalStop();

        //testForInterval();

        // testNestedInterval();

        // testSerializeSave();

         deserializeProject("allFather.ser").printDebug("");
    }
}
