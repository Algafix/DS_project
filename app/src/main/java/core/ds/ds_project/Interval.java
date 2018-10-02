package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer{

    private LocalDateTime startTime = null;
    private LocalDateTime endtime = null;
    private LocalDateTime duration = null; // check java.time.Duration ;)

    /**
     * Constructor that creates a new interval
     *
     * @param clock Observable clock which the interval subscribes
     */
    public Interval(AppClock clock){
        this.startTime = clock.getTime();
        this.endtime = clock.getTime();
        clock.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object obj) {
        this.endtime = (LocalDateTime) obj;
        System.out.println(this.endtime);
    }
}
