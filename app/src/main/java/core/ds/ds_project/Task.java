package core.ds.ds_project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task extends Job {

    private List<Interval> intervals = new ArrayList<Interval>();

    // If an interval last less than "minimumDuration", i'll be ignored
    private LocalDateTime minimumDuration = null;
}
