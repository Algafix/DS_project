 package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer, Serializable {

    private LocalDateTime startTime = null;
    private LocalDateTime endtime = null;
    private Duration duration = Duration.ofSeconds(0);
    private BasicTask parent = null;
    private String name = null; //debugging purposes


    /**
     * Constructor that creates a new interval
     *
     */
    public Interval(String name, BasicTask parent){
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
        Duration partialDuration = Duration.between(endtime, (LocalDateTime) obj);
        partialDuration = roundToSeconds(partialDuration);
        duration = duration.plus(partialDuration);
        endtime = (LocalDateTime) obj;
        parent.updateDuration(partialDuration, startTime, endtime);
    }


    /**
     * Method called to stop an interval previously started and calculate it's duration.
     *
     * @return Object Duration with the time lasted by the interval.
     */
    public Duration stop(){
        AppClock.getInstance().deleteObserver(this);
        return duration;
    }

    /**
     * Prints info about the Interval.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    public void printDebug(String tabs) {

        System.out.println(tabs + name + ": " + Client.formatDuration(Duration.between(startTime, endtime)));

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

    /**
     * Corrects the precision error of the clock that adds or subs around 1ms.
     *
     * @param duration Duration object with a time to correct.
     * @return The same Duration object with the seconds fixed.
     */
    public static Duration roundToSeconds(Duration duration) {

        if(duration.getNano()>995000000) {
            duration = duration.plusSeconds(1);
        }

        duration = duration.minusNanos(duration.getNano());

        return duration;
    }

}
