package core.ds.ds_project;

import java.time.LocalDateTime;

public abstract class Job {

    private Project parent = null;
    private String name = null;
    private String description = null;

    // Im not certain that this attribute should exist, the duration can be
    // calculated recursively, but saving it will provide a faster access.
    private LocalDateTime duration = null;

}
