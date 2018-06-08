package uk.co.jpawlak.clicker.gui;

public enum Status {

    ACTIVE(0),
    INACTIVE(0),
    COUNTDOWN(3);

    private int time;

    Status(int time) {
        this.time = time;
    }

    public static Status countdown(int time) {
        COUNTDOWN.time = time;
        return COUNTDOWN;
    }

    public int getTime() {
        return this.time;
    }

    public Status decrement() { //TODO omg! mutable enum!
        this.time -= 1;
        return this;
    }

}
