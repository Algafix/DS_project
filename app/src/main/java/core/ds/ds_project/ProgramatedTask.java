package core.ds.ds_project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;

/**
 * Not necessary all ready understandable.
 */
public class ProgramatedTask extends TaskDecorator implements Observer {
    /**
     * Not necessary all ready understandable.
     */
    private final String nameInterval;
    /**
     * Not necessary all ready understandable.
     */
    private final  LocalDateTime programDate;

    /**
     * Not necessary all ready understandable.
     * @param basicTask .
     * @param programDateParam .
     * @param nameIntervalParam .
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
            task.addInterval(nameInterval);
        }

    }



}


