 package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer, Serializable {

    private LocalDateTime startTime = null;
    private LocalDateTime endtime = null;
    private Duration duration = null;
    private Task parent = null;
    private String name = null; //debugging purposes


    /**
     * Constructor that creates a new interval
     *
     */
    public Interval(String name, Task parent){
        AppClock clock = AppClock.getInstance();
        this.name = name;
        this.startTime = clock.getTime();
        this.endtime = clock.getTime();
        this.parent = parent;
        clock.addObserver(this);
    }


    /**
     * Method called when the Observable object where this object is subscribed invoke
     * "notifyObservers()"
     *
     * @param obs Parameter containing info about the observable object.
     * @param obj Parameter containing info about the update.
     */
    @Override
    public void update(Observable obs, Object obj) {
        endtime = (LocalDateTime) obj;
        // duration = Duration.between(startTime, endtime);
    }


    /**
     * Method called to stop an interval previously started and calculate it's duration.
     *
     * @return Object Duration with the time lasted by the interval.
     */
    public Duration stop(){
        AppClock.getInstance().deleteObserver(this);
        duration = Duration.between(startTime, endtime);
        parent.updateDuration(duration);
        return duration;
    }

    /**
     * Prints info about the Interval.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    public void printDebug(String tabs) {

        System.out.println(tabs + name + ": " + Client.formatDuration(duration));

    }

    public Duration getDuration(){
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
