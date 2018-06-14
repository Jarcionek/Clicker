package uk.co.jpawlak.clicker.actions;

public class SleepAction implements Action {

    private final long time;

    public SleepAction(long time) {
        this.time = time;
    }

    public void execute() throws InterruptedException {
        Thread.sleep(this.time);
    }

    public long getTime() {
        return time;
    }

}
