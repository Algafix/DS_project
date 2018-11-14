package core.ds.ds_project;

import java.time.Duration;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;


/**
 * Task objects are always leafs of the Job tree,
 * they can't be subdivided in other Job objects.
 * The task objects are the only objects of the tree
 * that can be timed (intervals will be used for this
 * purpose). Task can be normal tasks, limited time tasks,
 * programated tasks or any combination.
 */
public abstract class Task extends Job {

    /** The Task that wraps the current one.
     * If null, means is the higher layer and the object that
     * interactuates with other.
     */
    private Task higherLayerDecorator = null;

    /**
     * Logger of the class BasicTask.
     */
    private final Logger log = LoggerFactory.getLogger(Task.class);

    /**
     * Constructor of the task class.
     * @param name Name of the task.
     * @param description What is this task about.
     */
    public Task(final String name, final String description) {

        super(name, description);
        invariant();
    }

    /**
     * Invariant of the Task class.
     */
    private void invariant() {
        assert (this.getDescription() != null) : "Illegal null description";
        assert (this.getName() != null) : "Illegal null name";
    }

    /**
     * Sets the higher layer decorator, that is the
     * Decorator that wraps the current task.
     * @param higherLayerDecoratorParam Parameter for setting.
     */
    public void setHigherLayerDecorator(final Task higherLayerDecoratorParam) {
        this.higherLayerDecorator = higherLayerDecoratorParam;
    }

    /**
     * Returns the collection of intervals.
     * @return  Collection of intervals.
     */
    public abstract Collection<Interval> getIntervals();

    /**
     * Adds an interval to the interval array passing itself as a parent.
     *
     * @param name Name of the interval for debug purposes.
     * @return The intervot necessary all ready understandable.al created.
     */
    public abstract Interval addInterval(String name);

    /**
     * Stops the last interval, which is presumed to be the only one running,
     * considering there's an
     * interval running at all.
     *
     * @return Duration of the last Interval
     */
    public abstract Duration stopLastInterval();

    /**
     * Updates the duration of the object and,
     * if the object is not the last, call update on it's
     * parent.
     *
     * @param duration Increment of time.
     */
    @Override
    public void updateDuration(final Duration duration,
                               final LocalDateTime startTime,
                               final LocalDateTime endTime) {
        //Preconditions and invariant
        invariant();

        try {
            if (duration == null || startTime == null || endTime == null) {
                throw new IllegalArgumentException("Null argument in"
                        + " updateDuration");
            } else {
                if (higherLayerDecorator == null) {
                    if (this.getParent() != null) {
                        this.getParent().updateDuration(duration,
                                startTime, endTime);
                    }
                } else {
                    higherLayerDecorator.updateDuration(duration,
                            startTime, endTime);
                }
            }
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument at call", e);
        }

        //Postcondition and invariant
        invariant();
    }
}
