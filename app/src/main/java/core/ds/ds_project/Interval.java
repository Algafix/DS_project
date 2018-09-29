package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer{

    private LocalDateTime startTime = null;
    private LocalDateTime endtime = null;
    private LocalDateTime duration = null; // check java.time.Duration ;)

    @Override
    public void update(Observable obs, Object obj) {

    }
}
