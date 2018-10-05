package core.ds.ds_project;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
                        intervals.add(new Interval(""));
                    }
                },
                500, 10000
        );
    }


    /**
     * Convierte un duration en un string para mostrar.
     */
    public static String humanReadableFormatDuration(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }


    /**
     * Peligro mortal cosa de Diego
     */
    public static void testIntervalStop(){
        final AppClock appClock = AppClock.getInstance();
        final Interval interval1 = new Interval("");

        final List<Interval> intervals = new ArrayList<Interval>();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("New interval");
                        intervals.add(new Interval(""));
                    }
                },
                1000, 1000
        );

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if(!intervals.isEmpty()) {
                            System.out.println("Stop interval1: " + humanReadableFormatDuration(intervals.get(0).stop()));
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

        final Interval interval = new Interval("Interval "+numero);
        System.out.println("Creado "+interval.getName());

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("Parado "+interval.getName()+" en: " + humanReadableFormatDuration(interval.stop()));
                    }
                },
                numero*1000
        );
    }


    /**
     * Call this function to debug the creation of concurrent threads and its time
     */
    public static void testForInterval() {
        for(int i = 1; i<=10 ; i++) {
            testInterval(i);
        }
    }


    public static void main(String [] args){

        AppClock.getInstance(1000);

        //testAnidament();

        //testAppClockInterval();

        //testIntervalStop();

        //testForInterval();

    }
}
