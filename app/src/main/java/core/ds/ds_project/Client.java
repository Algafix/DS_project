package core.ds.ds_project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {

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

    public static void testAppClock() {
        AppClock appClock = new AppClock(1000);
    }

    public static void testAppClockInterval() {
        final AppClock appClock = new AppClock(1000);

        final List<Interval> intervals = new ArrayList<Interval>();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("New interval");
                        intervals.add(new Interval(appClock));
                    }
                },
                500, 10000
        );
    }

    public static void main(String [] args){

        //testAppClock();

        //testAnidament();

        testAppClockInterval();

        //System.out.println("Hello world!");

    }
}
