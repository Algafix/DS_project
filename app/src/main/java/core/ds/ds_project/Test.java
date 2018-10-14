package core.ds.ds_project;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Test {


    /**
     * This is te test of Append A1 of the project
     * The tree created is: . -> Project1 (P1): -> Project2 (P2): -> Task1 (T1)
     *                                                            -> Task2 (T2)
     *                        -> Task3 (T3)
     */

    public static void testApenndA1 (){
        Project allFather = new Project(".", "Projecte Pare",null);

        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1", allFather));
        final BasicTask task3 = project1.addChild(new BasicTask("T3", "Tasca 3", project1));

        final Project project2 = project1.addChild(new Project( "P2", "Project 2", project1));
        final BasicTask task1 = project2.addChild(new BasicTask("T1", "Tasca 1", project1));
        final BasicTask task2 = project2.addChild(new BasicTask("T2", "Tasca 2", project1));

        allFather.printDebug("");

        AppClock.getInstance(2000);

        //final Interval interval1 = task3.addInterval("interval1");
        final Timer timerTask = new Timer();




        TimerTask Applicationwindow = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Nom   Temps inici                  Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+----------------------------+----------------------------+--------------------");
                System.out.println(project1.name +"    "+ Client.formatDateTime(project1.startTime)+"      "+Client.formatDateTime(project1.endTime)+"       "+ Client.formatDuration(project1.duration) );
                System.out.println(task3.name +"    "+ Client.formatDateTime(task3.startTime) +"      "+Client.formatDateTime(task3.endTime)+"       "+ Client.formatDuration(task3.duration));
                System.out.println(project2.name +"    "+ Client.formatDateTime(project2.startTime) +"      "+Client.formatDateTime(project2.endTime)+"       "+ Client.formatDuration(project2.duration));
                System.out.println(task1.name +"    "+ Client.formatDateTime(task1.startTime) +"      "+Client.formatDateTime(task1.endTime)+"       "+ Client.formatDuration(task1.duration));
                System.out.println(task2.name +"    "+ Client.formatDateTime(task2.startTime) +"      "+Client.formatDateTime(task2.endTime)+"       "+ Client.formatDuration(task2.duration));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(" ");

            }
        };

        final TimerTask Tasktime0 = new TimerTask(){
            @Override
            public void run() {
                task3.addInterval("interval1");


            }
        };


        final TimerTask Tasktime1 = new TimerTask(){
            @Override
            public void run() {
                task2.addInterval("interval1");


            }
        };

        final TimerTask Tasktime2 = new TimerTask(){
            @Override
            public void run() {
                task3.stopLastInterval();

            }
        };

        final TimerTask Tasktime3 = new TimerTask(){
            @Override
            public void run() {
                task1.addInterval("interval1");


            }
        };

        final TimerTask Tasktime4 = new TimerTask(){
            @Override
            public void run() {
                task1.stopLastInterval();


            }
        };

        final TimerTask Tasktime5 = new TimerTask(){
            @Override
            public void run() {
                task2.stopLastInterval();

            }
        };

        final TimerTask Tasktime6 = new TimerTask(){
            @Override
            public void run() {
                task3.addInterval("interval2");

            }
        };

        final TimerTask Tasktime7 = new TimerTask(){
            @Override
            public void run() {
                task3.stopLastInterval();

            }
        };

        timerTask.schedule(Tasktime0,0);
        timerTask.schedule(Tasktime1,4000);
        timerTask.schedule(Tasktime2,6000);
        timerTask.schedule(Tasktime3,8000);
        timerTask.schedule(Tasktime5,10000);
        timerTask.schedule(Tasktime4,14000);
        timerTask.schedule(Tasktime6,16000);
        timerTask.schedule(Tasktime7,20000);

        Timer updateWindow = new Timer();

        updateWindow.scheduleAtFixedRate(Applicationwindow, 0, 2000);



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
                            System.out.println("Stop interval1: " + Client.formatDuration(intervals.get(0).stop()));
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
    public static void testNestedInterval() {
        final Project allFather = new Project("Projecte Pare", "Projecte Pare",null);

        Project projecte1 = allFather.addChild(new Project("Projecte1", "Projecte de test1", allFather));

        Project projecte11 = projecte1.addChild(new Project("Projecte11", "Projecte anidat 11", projecte1));
        Task tasca12 = projecte1.addChild(new BasicTask("Tasca12", "Tasca anidada 12", projecte1));

        Task tasca111 = projecte11.addChild(new BasicTask("Tasca111", "Tasca anidada 111", projecte11));
        Task tasca112 = projecte11.addChild(new BasicTask("Tasca112", "Tasca anidada 112", projecte11));

        Interval interval1 = tasca12.addInterval("interval1");
        Interval interval2 = tasca111.addInterval("interval2");
        Interval interval3 = tasca111.addInterval("interval3");

        // Prints the tree with initial durations (intervals has null because haven't stopped yet
        allFather.printDebug("");

        // Stop the intervals
        final Timer timer = new Timer();
        timer.schedule(new IntervalTimerTask(interval1) , 2000);
        timer.schedule(new IntervalTimerTask(interval2), 2000);
        timer.schedule(new IntervalTimerTask(interval3), 3000);

        // Reprint the tree with updated durations
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                allFather.printDebug("");
                timer.cancel();
            }
        },5000);


    }

    public static void testDecorator(){
        Project allFather = new Project(".", "Projecte Pare",null);



        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1", allFather));
        //final ConcreteTask task3 = allFather.addChild(new ConcreteTask("T3", "Tasca 3", allFather));
        //task3 = new LimitTimeTaskDecorator(task3, 1000);

        final BasicTask task3 = allFather.addChild(new BasicTask("T3", "Tasca 3", allFather));
        //task3 = new LimitTime(task3, 1000);



    }


}
