package core.ds.ds_project;

public class LimitTime extends TaskDecorator {

    ConcreteTask concreteTask;
    int maxDuration;

    public LimitTime(ConcreteTask concreteTask, int milisconds){
        this.concreteTask = concreteTask;
        this.maxDuration = milisconds;
    }

}
