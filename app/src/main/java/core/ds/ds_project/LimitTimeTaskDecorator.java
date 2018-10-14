package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;

public class LimitTimeTaskDecorator extends TaskDecorator {

    /**
     * Max duration of an interval in miliseconds
     */
    long maxDuration;

    public LimitTimeTaskDecorator(Task basicTask, long maxDuration){
        super(basicTask);
        this.maxDuration = maxDuration;
    }

    public void setLimitTime(long maxDuration){

    }

    /**
     * Updates the duration of the object and, if the object is not the last, call update on it's
     * parent.
     *
     * @param duration Increment of time.
     */
    @Override
    public void updateDuration(Duration duration, LocalDateTime startTime, LocalDateTime endTime) {

        if (Duration.between(startTime,endTime).toMillis() >= maxDuration){
            task.stopLastInterval();
        }

        super.updateDuration(duration, startTime, endTime);
    }
}