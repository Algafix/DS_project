package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer{

    private LocalDateTime startTime = null;
    private LocalDateTime endtime = null;
    private Duration duration = null; // check java.time.Duration ;)
    private String name = null; //debugging purposes


    /**
     * Constructor that creates a new interval
     *
     */
    public Interval(String name){
        AppClock clock = AppClock.getInstance();
        this.name = name;
        this.startTime = clock.getTime();
        this.endtime = clock.getTime();
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
        this.endtime = (LocalDateTime) obj;
    }


    /**
     * Method called to stop an interval previously started and calculate it's duration.
     *
     * @return Object Duration with the time lasted by the interval.
     */
    public Duration stop(){
        AppClock.getInstance().deleteObserver(this);
        this.duration = Duration.between(this.startTime, this.endtime);
        return this.duration;
    }

    public Duration getDuration(){
        return this.duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
