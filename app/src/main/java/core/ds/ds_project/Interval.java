 package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

 /**
  * Not necessary all ready understandable.
  */

public class Interval implements Observer, Serializable {

    /**
    * The start time.
    */
    private LocalDateTime startTime = null;
    /**
    * The end time.
     */
    private LocalDateTime endTime = null;
    /**
    * the duration in seconds.
    */
    private Duration duration = Duration.ofSeconds(0);
    /**
    * Pointer to the parent of a task.
    */
    private BasicTask parent = null;
     /**
      * The name.
      */
    private String name = null; //debugging purposes
    /**
    * A variable that permits tolerance of the clock.
    */
    private static final int CLOCK_CORRECTOR = 995000000;


    /**
     * Constructor that creates a new interval.
     * @param nameInterval the name of the interval.
     * @param parentInterval the parent of the interval.
     */
    public Interval(final String nameInterval, final BasicTask parentInterval) {
        AppClock clock = AppClock.getInstance();
        this.name = nameInterval;
        this.startTime = clock.getTime();
        this.endTime = clock.getTime();
        this.parent = parentInterval;
        clock.addObserver(this);
    }


    /**
     * Method called when the Observable object is subscribed invoke.
     * "notifyObservers()"
     *
     * @param obs Parameter containing info about the observable object.
     * @param obj Parameter containing info about the update.
     */
    @Override
    public void update(final Observable obs, final Object obj) {
        Duration partialDuration =
                Duration.between(endTime, (LocalDateTime) obj);
        partialDuration = roundToSeconds(partialDuration);
        duration = duration.plus(partialDuration);
        endTime = (LocalDateTime) obj;
        parent.updateDuration(partialDuration, startTime, endTime);
    }


    /**
     * Method called to stop an interval previously started and
     * calculate it's duration.
     *
     * @return Object Duration with the time lasted by the interval.
     */
    public Duration stop() {
        AppClock.getInstance().deleteObserver(this);
        return duration;
    }

    /**
    * Accepts a visitor.
    *
    * @param visitor the accepted visitor.
    */
    public void acceptVisitor(final Visitor visitor) {
        visitor.visitInterval(this);
    }

    /**
     * Prints info about the Interval.
     *
     * @param tabs String of concatenated "\t" for visual purpose.
     */
    public void printDebug(final String tabs) {

        System.out.println(
                tabs + name + ": " + Client.formatDuration(duration));

    }

    /**
     * Corrects the precision error of the clock that adds or subs around 1ms.
     *
     * @param duration Duration object with a time to correct.
     * @return The same Duration object with the seconds fixed.
     */
    public static Duration roundToSeconds(@SuppressWarnings("CheckStyle") Duration duration) {

        if (duration.getNano() > CLOCK_CORRECTOR) {
            duration = duration.plusSeconds(1);
        }

        duration = duration.minusNanos(duration.getNano());

        return duration;
    }

    /**
     * Method that obtains the activity duration
     * of the job within a range defined by two Dates.
     *
     * @param fromDate Date that sets the beginning of the range.
     * @param toDate Date that sets the end of the range.
     * @return [Duration] Returns the duration in the specified range.
     */
    public Duration getDurationInRange(final LocalDateTime fromDate,
                                       final LocalDateTime toDate) {
        Duration temp = Duration.ofSeconds(0);
        LocalDateTime startTemp = startTime;
        LocalDateTime endTemp = endTime;
        if (!(fromDate.isAfter(endTime) || toDate.isBefore(startTime))
                && fromDate.isBefore(toDate)) {
            if (fromDate.isAfter(startTime)) {
                startTemp = fromDate;
            }
            if (toDate.isBefore(endTime)) {
                endTemp = toDate;
            }

            temp = Duration.between(startTemp, endTemp);
        }
        return temp;
    }
     /**
      * Get the duration.
      * @return the duration.
      */
    public Duration getDuration() {

        return duration;
    }
     /**
      * Set the name.
      * @param nameInterval the name of the interval
      */
    public void setName(final String nameInterval) {

        this.name = nameInterval;
    }
     /**
      * Get the name.
      * @return the name.
      */
    public String getName() {

        return name;
    }
     /**
      * Get the duration.
      * @return the duration.
      */
    public BasicTask getParent() {
        return parent;
    }
}
