package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;


public class ProgramatedTask extends TaskDecorator implements Observer {

    String nameInterval;
    LocalDateTime ProgramDate;

    public ProgramatedTask(Task basicTask, LocalDateTime ProgramDate, String nameInterval) {
        super(basicTask);
        AppClock clock = AppClock.getInstance();
        clock.addObserver(this);
        this.nameInterval = nameInterval;
        this.ProgramDate = ProgramDate;

    }

     /** Method called when the Observable object where this object is subscribed invoke
     * "notifyObservers()"
     *
     * @param obs Parameter containing info about the observable object.
     * @param obj Parameter containing info about the update.
      */
    @Override
    public void update(Observable obs, Object obj) {

        long seconds  = ChronoUnit.SECONDS.between((LocalDateTime)obj, ProgramDate);

        float refreshTimeSeconds = AppClock.getInstance().getCurrentRefreshTime()/1000;

        if(seconds < (refreshTimeSeconds/2) && seconds > -(refreshTimeSeconds/2)){
            task.addInterval(nameInterval);
        }

    }



}


