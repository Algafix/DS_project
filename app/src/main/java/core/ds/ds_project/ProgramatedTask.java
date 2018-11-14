package core.ds.ds_project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;

/**
 * Task type that starts at fixed time.
 */
public class ProgramatedTask extends TaskDecorator implements Observer {

    /**
     * Name of the programed interval for debug purposes.
     */
    private final String nameInterval;

    /**
     * Date where the interval will be started.
     */
    private final  LocalDateTime programDate;

    /**
     * Constructor of the ProgramedTask kind of Task.
     * @param basicTask Task to be wrapped in the decorator.
     * @param programDateParam Date to start the interval.
     * @param nameIntervalParam Name of the launched interval.
     */
    public ProgramatedTask(final Task basicTask,
                           final LocalDateTime programDateParam,
                           final String nameIntervalParam) {
        super(basicTask);
        AppClock clock = AppClock.getInstance();
        clock.addObserver(this);
        this.nameInterval = nameIntervalParam;
        this.programDate = programDateParam;

    }

     /** Method called when the Observable object where
      * this object is subscribed invoke.
     * "notifyObservers()"
     *
     * @param obs Parameter containing info about the observable object.
     * @param obj Parameter containing info about the update.
      */
    @Override
    public void update(final Observable obs, final Object obj) {

        long seconds  = ChronoUnit.SECONDS.between((LocalDateTime) obj,
                                                    programDate);

        final  int constant = 1000;

        float refreshTimeSec1 = AppClock.getInstance().getCurrentRefreshTime();
        float refreshTimeSec2 = refreshTimeSec1 / constant;

        if (seconds < (refreshTimeSec2 / 2) && seconds
                > -(refreshTimeSec2 / 2)) {
            getTask().addInterval(nameInterval);
        }

    }



}


