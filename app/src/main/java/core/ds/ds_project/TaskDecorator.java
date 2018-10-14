package core.ds.ds_project;

import java.time.Period;

public abstract class TaskDecorator extends Task {

    public Period maxDuration = null;

    public TaskDecorator(String name, String description, Project parent) {

        super(name,description,parent);
    }

    public abstract void setLimitTime(ConcreteTask task, int miliseconds);

}
