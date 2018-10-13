package core.ds.ds_project;


import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    
    /**
     * Convierte un duration en un string para mostrar formato HH:mm:ss
     */
    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    public static void main(String [] args){

        Test test = new Test();

        test.testApenndA1();

        //testAppClockInterval();

        //testIntervalStop();

        //test.testForInterval();

        //test.testNestedInterval();
    }
}
