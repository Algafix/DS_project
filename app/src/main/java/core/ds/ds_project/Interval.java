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

    @Override
    public void update(Observable obs, Object obj) {
        this.endtime = (LocalDateTime) obj;
    }

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
