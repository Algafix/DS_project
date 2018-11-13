package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Not necessary all ready understandable.
 */
public class LimitTimeTaskDecorator extends TaskDecorator {

    /**
     * Max duration of an interval in milliseconds.
     */
    private long maxDuration;

    /**
     * Constructor of Limit Time Task Decorator.
     * @param basicTask Task that is wrapped by the current decorator.
     * @param maxDurationLim Max duration of an interval.
     */
    public LimitTimeTaskDecorator(final Task basicTask,
                                  final long maxDurationLim) {
        super(basicTask);
        this.maxDuration = maxDurationLim;
    }


    /**
     * Updates the duration of the object and, if the object is not the last,
     * call update on it parent.
     *
     * @param duration Increment of time.
     */
    @Override
    public void updateDuration(final Duration duration,
                               final LocalDateTime startTime,
                               final LocalDateTime endTime) {

        if (Duration.between(startTime, endTime).toMillis() >= maxDuration) {
            getTask().stopLastInterval();
        }

        super.updateDuration(duration, startTime, endTime);
    }
}
