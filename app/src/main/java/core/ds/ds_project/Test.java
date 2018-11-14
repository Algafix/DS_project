package core.ds.ds_project;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("CheckStyle")
public class Test {


    /**
     * This is te test of Append A1 of the project
     * The tree created is: . -> Project1 (P1): -> Project2 (P2): -> Task1 (T1)
     * -> Task2 (T2)
     * -> Task3 (T3)
     */
    public static void testAppendA1() {
        final Project allFather = new Project(".", "Projecte Pare");

        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1"));
        final BasicTask task3 = (BasicTask) project1.addChild(new BasicTask("T3", "Tasca 3"));

        final Project project2 = project1.addChild(new Project("P2", "Project 2"));
        final BasicTask task1 = (BasicTask) project2.addChild(new BasicTask("T1", "Tasca 1"));
        final BasicTask task2 = (BasicTask) project2.addChild(new BasicTask("T2", "Tasca 2"));

        allFather.acceptVisitor(new Printer());

        //final Interval interval1 = task3.addInterval("interval1");
        final Timer timerTask = new Timer();


        TimerTask Applicationwindow = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Nom   Temps inici                  Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+----------------------------+----------------------------+--------------------");
                System.out.println(project1.getName()+ "    " + Client.formatDateTime(project1.getStartTime()) + "      " + Client.formatDateTime(project1.getEndTime()) + "       " + Client.formatDuration(project1.getDuration()));
                System.out.println(task3.getName() + "    " + Client.formatDateTime(task3.getStartTime()) + "      " + Client.formatDateTime(task3.getEndTime()) + "       " + Client.formatDuration(task3.getDuration()));
                System.out.println(project2.getName() + "    " + Client.formatDateTime(project2.getStartTime()) + "      " + Client.formatDateTime(project2.getEndTime()) + "       " + Client.formatDuration(project2.getDuration()));
                System.out.println(task1.getName() + "    " + Client.formatDateTime(task1.getStartTime()) + "      " + Client.formatDateTime(task1.getEndTime()) + "       " + Client.formatDuration(task1.getDuration()));
                System.out.println(task2.getName() + "    " + Client.formatDateTime(task2.getStartTime()) + "      " + Client.formatDateTime(task2.getEndTime()) + "       " + Client.formatDuration(task2.getDuration()));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(" ");

            }
        };


        final TimerTask Tasktime1 = new TimerTask() {
            @Override
            public void run() {
                task3.stopLastInterval();


            }
        };

        final TimerTask Tasktime2 = new TimerTask() {
            @Override
            public void run() {
                task2.addInterval("interval1");

            }
        };

        final TimerTask Tasktime3 = new TimerTask() {
            @Override
            public void run() {
                task2.stopLastInterval();
                task3.addInterval("interval2");
            }
        };

        final TimerTask Tasktime4 = new TimerTask() {
            @Override
            public void run() {
                task3.stopLastInterval();
                Client.serializeProject(allFather, "allFather.ser");
                allFather.acceptVisitor(new Printer());
            }
        };

        task3.addInterval("interval1");
        timerTask.schedule(Tasktime1, 3070);
        timerTask.schedule(Tasktime2, 10000);
        timerTask.schedule(Tasktime3, 20070);
        timerTask.schedule(Tasktime4, 22000);


        Timer updateWindow = new Timer();

        updateWindow.scheduleAtFixedRate(Applicationwindow, 0, AppClock.getInstance().getCurrentRefreshTime());


    }

    /**
     * This is te test of Append A2 of the project
     * The tree created is: . -> Project1 (P1): -> Project2 (P2): -> Task1 (T1)
     * -> Task2 (T2)
     * -> Task3 (T3)
     */
    public static void testAppendA2() {
        Project allFather = new Project(".", "Projecte Pare");

        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1"));
        final BasicTask task3 = (BasicTask) project1.addChild(new BasicTask("T3", "Tasca 3"));

        final Project project2 = project1.addChild(new Project("P2", "Project 2"));
        final BasicTask task1 = (BasicTask) project2.addChild(new BasicTask("T1", "Tasca 1"));
        final BasicTask task2 = (BasicTask) project2.addChild(new BasicTask("T2", "Tasca 2"));

        allFather.acceptVisitor(new Printer());

        final Timer timerTask = new Timer();

        TimerTask Applicationwindow = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Nom   Temps inici                  Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+----------------------------+----------------------------+--------------------");
                System.out.println(project1.getName()+ "    " + Client.formatDateTime(project1.getStartTime()) + "      " + Client.formatDateTime(project1.getEndTime()) + "       " + Client.formatDuration(project1.getDuration()));
                System.out.println(task3.getName() + "    " + Client.formatDateTime(task3.getStartTime()) + "      " + Client.formatDateTime(task3.getEndTime()) + "       " + Client.formatDuration(task3.getDuration()));
                System.out.println(project2.getName() + "    " + Client.formatDateTime(project2.getStartTime()) + "      " + Client.formatDateTime(project2.getEndTime()) + "       " + Client.formatDuration(project2.getDuration()));
                System.out.println(task1.getName() + "    " + Client.formatDateTime(task1.getStartTime()) + "      " + Client.formatDateTime(task1.getEndTime()) + "       " + Client.formatDuration(task1.getDuration()));
                System.out.println(task2.getName() + "    " + Client.formatDateTime(task2.getStartTime()) + "      " + Client.formatDateTime(task2.getEndTime()) + "       " + Client.formatDuration(task2.getDuration()));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(" ");

            }
        };


        final TimerTask Tasktime1 = new TimerTask() {
            @Override
            public void run() {
                task2.addInterval("interval21");


            }
        };

        final TimerTask Tasktime2 = new TimerTask() {
            @Override
            public void run() {
                task3.stopLastInterval();

            }
        };

        final TimerTask Tasktime3 = new TimerTask() {
            @Override
            public void run() {
                task1.addInterval("interval11");


            }
        };

        final TimerTask Tasktime4 = new TimerTask() {
            @Override
            public void run() {
                task1.stopLastInterval();


            }
        };

        final TimerTask Tasktime5 = new TimerTask() {
            @Override
            public void run() {
                task2.stopLastInterval();

            }
        };

        final TimerTask Tasktime6 = new TimerTask() {
            @Override
            public void run() {
                task3.addInterval("interval32");

            }
        };

        final TimerTask Tasktime7 = new TimerTask() {
            @Override
            public void run() {
                task3.stopLastInterval();

            }
        };

        task3.addInterval("interval31");
        timerTask.schedule(Tasktime1, 4050);
        timerTask.schedule(Tasktime2, 6050);
        timerTask.schedule(Tasktime3, 8050);
        timerTask.schedule(Tasktime4, 12050);
        timerTask.schedule(Tasktime5, 14050);
        timerTask.schedule(Tasktime6, 18050);
        timerTask.schedule(Tasktime7, 20050);

        Timer updateWindow = new Timer();

        updateWindow.scheduleAtFixedRate(Applicationwindow, 0, AppClock.getInstance().getCurrentRefreshTime());


    }

    /**
     * Debug the creation and time of 1 interval
     */
    public static void testAppClockInterval() {
        final List<Interval> intervals = new ArrayList<>();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("New interval");
                        intervals.add(new Interval("", null));
                    }
                },
                500, 10000
        );
    }


    /**
     * Start an interval that will be stopped afther the given number of seconds. This function is
     * called from testForInterval()
     *
     * @param numero number in seconds.
     */
    public static void testInterval(int numero) {

        Interval interval = new Interval("Interval " + numero, null);
        System.out.println("Creado " + interval.getName());

        new java.util.Timer().schedule(new IntervalTimerTask(interval), numero * 1000);
    }


    /**
     * Call this function to debug the creation of concurrent threads and its time
     */
    public static void testForInterval() {
        for (int i = 1; i <= 10; i++) {
            testInterval(i);
        }
    }

    /**
     * Call this function to debug the creation of intervals nested in project tree and propagation
     * of duration.
     */
    public static void testNestedInterval() {
        final Project allFather = new Project("Projecte Pare", "Projecte Pare");

        Project projecte1 = allFather.addChild(new Project("Projecte1", "Projecte de test1"));

        Project projecte11 = projecte1.addChild(new Project("Projecte11", "Projecte anidat 11"));
        Task tasca12 = projecte1.addChild(new BasicTask("Tasca12", "Tasca anidada 12"));

        Task tasca111 = projecte11.addChild(new BasicTask("Tasca111", "Tasca anidada 111"));
        Task tasca112 = projecte11.addChild(new BasicTask("Tasca112", "Tasca anidada 112"));

        Interval interval1 = tasca12.addInterval("interval1");
        Interval interval2 = tasca111.addInterval("interval2");

        // Prints the tree with initial durations (intervals has null because haven't stopped yet
        allFather.acceptVisitor(new Printer());

        // Stop the intervals
        final Timer timer = new Timer();
        timer.schedule(new IntervalTimerTask(interval1), 2000);
        timer.schedule(new IntervalTimerTask(interval2), 3000);

        // Reprint the tree with updated durations
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
            }
        }, 5000);


    }


    /**
     * Test for the implementation of the testLimitTimeDecorator() functionality, an interval is created on T3 who's parent is P1,
     * immediately after creation is running and after 10 seconds stops seconds the interval start running.
     *
     */
    public static void testLimitTimeDecorator() {
        final Project allFather = new Project(".", "Projecte Pare");
        final LocalDateTime initTime = LocalDateTime.now();
        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1"));
        final LimitTimeTaskDecorator task3 = (LimitTimeTaskDecorator) project1.addChild
                (new LimitTimeTaskDecorator(new BasicTask("T3", "Tasca 3"), 10000));

        allFather.acceptVisitor(new Printer());
        task3.addInterval("Interval1");


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Interval que hauria de pararse al cap de  10s(quan sigui "+ Client.formatDateTime(initTime.plusSeconds(10)) +") comença la T3 que pertany a P1" );
                System.out.println("");
                System.out.println("Nom   Temps inici                  Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+----------------------------+----------------------------+--------------------");
                System.out.println(project1.getName() + "    " + Client.formatDateTime(project1.getStartTime()) + "      " + Client.formatDateTime(project1.getEndTime()) + "       " + Client.formatDuration(project1.getDuration()));
                System.out.println(task3.getName() + "    " + Client.formatDateTime(task3.getStartTime()) + "      " + Client.formatDateTime(task3.getEndTime()) + "       " + Client.formatDuration(task3.getDuration()));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(" ");
            }
        }, 1000, AppClock.getInstance().getCurrentRefreshTime());

    }

    /**
     * Test for the implementation of the ProgramatedTask() functionality, an interval is created on T3 who's parent is P1,
     * after  5 seconds the interval start running.
     *
     */
    public static void testProgramatedtask() {
        final Project allFather = new Project(".", "Projecte Pare");

        final LocalDateTime initTime = LocalDateTime.now();
        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1"));
        final ProgramatedTask task3 = (ProgramatedTask) project1.addChild
                (new ProgramatedTask(new BasicTask("T3", "Tasca 3"), initTime.plusSeconds(5), "interval1"));

        TimerTask Applicationwindow = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Interval que hauria de pararse al cap de  10s(quan sigui "+ Client.formatDateTime(initTime.plusSeconds(10)) +") comença la T3 que pertany a P1" );
                System.out.println("");
                System.out.println("Nom   Temps inici                  Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+----------------------------+----------------------------+--------------------");
                System.out.println(project1.getName() + "    " + Client.formatDateTime(project1.getStartTime()) + "      " + Client.formatDateTime(project1.getEndTime()) + "       " + Client.formatDuration(project1.getDuration()));
                System.out.println(task3.getName() + "    " + Client.formatDateTime(task3.getStartTime()) + "      " + Client.formatDateTime(task3.getEndTime()) + "       " + Client.formatDuration(task3.getDuration()));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(" ");
            }
        };

        Timer updateWindow = new Timer();

        updateWindow.scheduleAtFixedRate(Applicationwindow,0,AppClock.getInstance().getCurrentRefreshTime());
    }

    /**
     * Test for the implementation of the ProgrammatedTask() and LimitTime TasDecorator  functionality at the same time,
     * An interval is created on T3 who's parent is P1, after  at 4 seconds the task3 start running, at  6 seconds
     * the task3 start running, at 8 second stops thethe task2, at 10 seconds starts task1, at 12 seconds stop task3,
     * at 14 seconds stop task1
     *
     */
    public static void testProgrammatedTaskAndLimitTimeTaskDecorator() {
        final Project allFather = new Project(".", "Projecte Pare");

        final LocalDateTime initTime = LocalDateTime.now();
        //Preparation of programated Task and LimitTaskDecorator

        final ProgramatedTask task31 = new ProgramatedTask( new BasicTask("T3", "Tasca 3"), initTime.plusSeconds(2), "Interval1");
        final LimitTimeTaskDecorator task32  = new LimitTimeTaskDecorator(task31, 8000);
        final ProgramatedTask task21 = new ProgramatedTask( new BasicTask("T2", "Tasca 2"), initTime.plusSeconds(4), "Interval1");
        final LimitTimeTaskDecorator task22  = new LimitTimeTaskDecorator(task21, 1000);
        final ProgramatedTask task11 = new ProgramatedTask( new BasicTask("T1", "Tasca 1"), initTime.plusSeconds(8), "Interval1");
        final LimitTimeTaskDecorator task12  = new LimitTimeTaskDecorator(task11, 4000);


        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1"));
        final Task task3 = project1.addChild(task32);


        final Project project2 = allFather.addChild(new Project("P2", "Projecte 2"));
        final Task task2 = project2.addChild(task22);
        final Task task1 = project2.addChild(task12);

        allFather.acceptVisitor(new Printer());


        TimerTask Applicationwindow = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Als 2 segons (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(2)) +") comença la T3 que pertany a P1  i s'acabarà als 10 segons (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(10)) + ")");
                System.out.println("Als 4 segons (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(4)) +") comença la T2 que pertany a P2  i s'acabarà als 6 segons (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(6))+") està dins T3");
                System.out.println("Als 8 segons (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(8)) +") comença la T1 que pertany a P2  i s'acabarà als 12 segons (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(12))+") comença abans d'acabar T3 i acaba després");
                System.out.println(" ");
                System.out.println("Nom    Temps inici                     Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+------------------------------+------------------------------+--------------------");
                System.out.println(project1.getName() + "    " + Client.formatDateTime(project1.getStartTime()) + "      " + Client.formatDateTime(project1.getEndTime()) + "       " + Client.formatDuration(project1.getDuration()));
                System.out.println(project2.getName() + "    " + Client.formatDateTime(project2.getStartTime()) + "      " + Client.formatDateTime(project2.getEndTime()) + "       " + Client.formatDuration(project2.getDuration()));
                System.out.println(task3.getName() + "    " + Client.formatDateTime(task3.getStartTime()) + "      " + Client.formatDateTime(task3.getEndTime()) + "       " + Client.formatDuration(task3.getDuration()));
                System.out.println(task2.getName() + "    " + Client.formatDateTime(task2.getStartTime()) + "      " + Client.formatDateTime(task2.getEndTime()) + "       " + Client.formatDuration(task2.getDuration()));
                System.out.println(task1.getName() + "    " + Client.formatDateTime(task1.getStartTime()) + "      " + Client.formatDateTime(task1.getEndTime()) + "       " + Client.formatDuration(task1.getDuration()));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(" ");
            }
        };

        Timer updateWindow = new Timer();

        updateWindow.scheduleAtFixedRate(Applicationwindow,0,AppClock.getInstance().getCurrentRefreshTime());
    }

    public static void testDeserialize() {
        Project allFather = Client.deserializeProject("allFather.ser");
        allFather.acceptVisitor(new Printer());
    }



    /**
     * Test for the implementation of the ProgrammatedTask() and LimitTime TasDecorator  functionality at the same time,
     * An interval is created on T3 who's parent is P1, after  at 4 seconds the task3 start running, at  6 seconds
     * the task3 start running, at 8 second stops thethe task2, at 10 seconds starts task1, at 12 seconds stop task3,
     * at 14 seconds stop task1
     *
     */
    public static void testReportTreeGenerate() {
        final Project allFather = new Project(".", "Projecte Pare");

        final LocalDateTime initTime = LocalDateTime.now();
        //Preparation of programated Task and LimitTaskDecorator
        final LimitTimeTaskDecorator task41  = new LimitTimeTaskDecorator(new BasicTask("T4", "Tasca 4"), 9500);

        final ProgramatedTask task31 = new ProgramatedTask( new BasicTask("T3", "Tasca 3"), initTime.plusSeconds(10), "Interval1");
        final ProgramatedTask task32 = new ProgramatedTask(task31, initTime.plusSeconds(16), "Interval2");
        final LimitTimeTaskDecorator task33  = new LimitTimeTaskDecorator(task32, 3500);

        final ProgramatedTask task21 = new ProgramatedTask( new BasicTask("T2", "Tasca 2"), initTime.plusSeconds(4), "Interval1");
        final ProgramatedTask task22 = new ProgramatedTask(task21, initTime.plusSeconds(14), "Interval2");
        final LimitTimeTaskDecorator task23  = new LimitTimeTaskDecorator(task22, 5500);

        final LimitTimeTaskDecorator task11  = new LimitTimeTaskDecorator(new BasicTask("T1", "Tasca 1"), 3500);


        final Project project1 = allFather.addChild(new Project("P1", "Projecte 1"));
        final Task task1 = project1.addChild(task11);
        task1.addInterval("Interval 1");
        final Task task2 = project1.addChild(task23);

        final Project project12 = project1.addChild(new Project("P1.2", "Projecte 1.2"));
        final Task task4 = project12.addChild(task41);
        task4.addInterval("Interval 1");

        final Project project2 = allFather.addChild(new Project("P2", "Projecte 2"));
        final Task task3 = project2.addChild(task33);

        allFather.acceptVisitor(new Printer());


        TimerTask applicationWindow = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Al segon 0 (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(0)) +") comença la T1 que pertany a P1  i T4 que pertany a P1.2.");
                System.out.println("Al segon 4 (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(4)) +") acaba la T1 i comença la T2 que pertany a P1.");
                System.out.println("Al segon 10 (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(10)) +") acaba T4, s'atura temporalment T2 i comença la T3 que pertany a P2");
                System.out.println("Al segon 14 (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(14)) +") s'atura temporalment T3 i es reanuda T2.");
                System.out.println("Al segon 16 (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(16)) +") es reanuda T3.");
                System.out.println("Al segon 20 (quan sigui "+ Client.formatDateTime(initTime.plusSeconds(20)) +") acaba T3 i T2.");
                System.out.println(" ");
                System.out.println("Nom    Temps inici                     Temps final                  Durada (hh:mm:ss)");
                System.out.println("----+------------------------------+------------------------------+--------------------");
                System.out.println(project1.getName()+ "    " + Client.formatDateTime(project1.getStartTime()) + "      " + Client.formatDateTime(project1.getEndTime()) + "       " + Client.formatDuration(project1.getDuration()));
                System.out.println(task1.getName() + "    " + Client.formatDateTime(task1.getStartTime()) + "      " + Client.formatDateTime(task1.getEndTime()) + "       " + Client.formatDuration(task1.getDuration()));
                System.out.println(task2.getName() + "    " + Client.formatDateTime(task2.getStartTime()) + "      " + Client.formatDateTime(task2.getEndTime()) + "       " + Client.formatDuration(task2.getDuration()));
                System.out.println(project12.getName() + "    " + Client.formatDateTime(project12.getStartTime()) + "      " + Client.formatDateTime(project12.getEndTime()) + "       " + Client.formatDuration(project12.getDuration()));
                System.out.println(task4.getName() + "    " + Client.formatDateTime(task4.getStartTime()) + "      " + Client.formatDateTime(task4.getEndTime()) + "       " + Client.formatDuration(task4.getDuration()));
                System.out.println(project2.getName() + "    " + Client.formatDateTime(project2.getStartTime()) + "      " + Client.formatDateTime(project2.getEndTime()) + "       " + Client.formatDuration(project2.getDuration()));
                System.out.println(task3.getName() + "    " + Client.formatDateTime(task3.getStartTime()) + "      " + Client.formatDateTime(task3.getEndTime()) + "       " + Client.formatDuration(task3.getDuration()));
                System.out.println("-----------------------------------------------------------------------------------");

                System.out.println(" ");

            }
        };

        final TimerTask Tasktime = new TimerTask() {
            @Override
            public void run() {
                Client.serializeProject(allFather, "allFather2.ser");
            }
        };
        new Timer().schedule(Tasktime, 22000);


        Timer updateWindow = new Timer();

        updateWindow.scheduleAtFixedRate(applicationWindow, 0, AppClock.getInstance().getCurrentRefreshTime());
    }


    /**
     * Creates a BasicReport with ASCII
     */
    public static void testBasicReportASCII(Project allFather) {
        ReportSaver saver = new ReportSaverAsASCII();
        Visitor visitor = new BasicReportGeneratorVisitor(allFather.getStartTime().plusSeconds(4),
                allFather.getStartTime().plusSeconds(14), saver);
        //Visitor visitor = new Printer();
        allFather.acceptVisitor(visitor);
        ((ReportGeneratorVisitor) visitor).save();
        boolean finished = true;
    }

    /**
     * Creates a DetailedReport with ASCII
     */
    public static void testDetailedReportASCII(Project allFather) {
        ReportSaver saver = new ReportSaverAsASCII();
        Visitor visitor = new DetailedReportGeneratorVisitor(allFather.getStartTime().plusSeconds(4),
                allFather.getStartTime().plusSeconds(14), saver);
        //Visitor visitor = new Printer();
        allFather.acceptVisitor(visitor);
        ((ReportGeneratorVisitor) visitor).save();
        boolean finished = true;
    }

    /**
     * Creates a BasicReport with HTML
     */
    public static void testBasicReportHTML(Project allFather) {
        ReportSaver saver = new ReportSaverAsHTML();
        Visitor visitor = new BasicReportGeneratorVisitor(allFather.getStartTime().plusSeconds(4),
                allFather.getStartTime().plusSeconds(14), saver);
        //Visitor visitor = new Printer();
        allFather.acceptVisitor(visitor);
        ((ReportGeneratorVisitor) visitor).save();
        boolean finished = true;
    }

    /**
     * Creates a DetailedReport with HTML
     */
    public static void testDetailedReportHTML(Project allFather) {
        ReportSaver saver = new ReportSaverAsHTML();
        Visitor visitor = new DetailedReportGeneratorVisitor(allFather.getStartTime().plusSeconds(4),
                allFather.getStartTime().plusSeconds(14), saver);
        //Visitor visitor = new Printer();
        allFather.acceptVisitor(visitor);
        ((ReportGeneratorVisitor) visitor).save();
        boolean finished = true;
    }


}
