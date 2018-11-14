 package core.ds.ds_project;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /**
  * The intervals are actually the only class of the
  * tree that calculate the time. Then, propagates
  * the time to the parent class and so on.
  */
public class Interval implements Observer, Serializable {

    /**
    * Logger of the class Interval.
    */
    private final Logger log = LoggerFactory.getLogger(Interval.class);
    /**
     * Static logger of the static methods of class Interval.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Interval.class);
    /**
    * The start time of the interval.
    */
    private LocalDateTime startTime = null;
    /**
    * The end time of the interval.
     */
    private LocalDateTime endTime = null;
    /**
    * The duration of the interval in seconds.
    */
    private Duration duration = Duration.ofSeconds(0);
    /**
    * Pointer to the parent of a task.
    */
    private BasicTask parent = null;
    /**
     * The name of the interval, its not necessary but helps in the debugging.
     */
    private String name = null;
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

        log.info("Started interval " + name + " for "
                + parent.getName() + " at "
                + Client.formatDateTime(startTime));

        invariant();
    }

     /**
      * Invariant of the Interval class.
      */
     private void invariant() {
         assert (startTime != null) : "Null startTime";
         assert (endTime != null) : "Null endTime";
         assert (parent != null) : "Null parent";
         assert (duration != null) : "Null duration";
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

        /**
         * Class that will store variables checked at the postcondition.
         */
        class DataCopy {
            private Duration duration;
            /**
             * Saves the data at the current state.
             * @param durationCpy Duration of the interval at the start.
             */
            DataCopy(final Duration durationCpy) {
                duration = durationCpy;
            }
            /**
             * Check if the duration has augmented
             * @param durationCheck Current duration
             * @return True if the new duration is greather than the old.
             */
            public boolean bigger(final Duration durationCheck) {
                return (durationCheck.compareTo(duration) > 0);
            }
        }

        DataCopy copy = new DataCopy(duration);

        // Preconditions and invariant
        invariant();
        assert (copy != null) : "Datacopy not created, memory issue?";

        Duration partialDuration =
                Duration.between(endTime, (LocalDateTime) obj);
        partialDuration = roundToSeconds(partialDuration);
        duration = duration.plus(partialDuration);
        endTime = (LocalDateTime) obj;
        parent.updateDuration(partialDuration, startTime, endTime);

        // Posconditions and invariant
        assert copy.bigger(duration) : "Duration hasn't augmented";
        invariant();
    }


    /**
     * Method called to stop an interval previously started and
     * calculate it's duration.
     *
     * @return Object Duration with the time lasted by the interval.
     */
    public Duration stop() {
        // Preconditions and invariant
        invariant();

        AppClock.getInstance().deleteObserver(this);

        log.info("Interval " + name + " for "
                + parent.getName() + "stopped. Lasted for "
                + Client.formatDuration(duration));

        // Postconditions and invariant
        invariant();

        return duration;
    }

    /**
    * Accepts a visitor.
    *
    * @param visitor the accepted visitor.
    */
    public void acceptVisitor(final Visitor visitor) {
        invariant();

        try {
            visitor.visitInterval(this);
        } catch (Exception e) {
            log.error("Null visitor", e);
        }

        invariant();
    }

    /**
     * Corrects the precision error of the clock that adds or subs around 1ms.
     *
     * @param durationParam Duration object with a time to correct.
     * @return The same Duration object with the seconds fixed.
     */
    public static Duration roundToSeconds(final Duration durationParam) {

        try {
            if (durationParam == null) {
                throw new IllegalArgumentException("durationParam is null");
            } else {
                Duration duration = durationParam;

                if (duration.getNano() > CLOCK_CORRECTOR) {
                    duration = duration.plusSeconds(1);
                }

                duration = duration.minusNanos(duration.getNano());

                return duration;
            }
        } catch (IllegalArgumentException e) {
            LOG.error("Parameter null", e);
            return null;
        } catch (Exception e) {
            LOG.error("Error in roundToSeconds with not null params", e);
            return null;
        }
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
        // Preconditions and invariant
        invariant();

        try {
            if (fromDate == null || toDate == null) {
                throw new IllegalArgumentException("Some parameter is null");
            } else {
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
                    temp = roundToSeconds(temp);
                }
                invariant();
                return temp;
            }
        } catch (IllegalArgumentException e) {
            log.error("Null argument", e);
            return null;
        } catch (Exception e) {
            log.error("Error in getDurationInRange with not null params", e);
            return null;
        }
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

     /**
      * Get the startTime.
      * @return the startTime.
      */
     public LocalDateTime getStartTime() {
         return startTime;
     }

     /**
      * Get the endTime.
      * @return the endTime.
      */
     public LocalDateTime getEndTime() {
         return endTime;
     }
 }
