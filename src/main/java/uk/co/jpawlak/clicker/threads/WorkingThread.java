package uk.co.jpawlak.clicker.threads;

import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.gui.TimeModule;

import java.util.LinkedList;
import java.util.List;

public class WorkingThread extends Thread {

    private final TimeModule timeModule;
    private final long original;
    private final List<ThreadListener> listeners;
    private final Actions actions;

    public static WorkingThread workingThread(TimeModule timeModule, Actions actions) {
        return new WorkingThread(timeModule, actions);
    }

    private WorkingThread(TimeModule timeModule, Actions actions) {
        this.timeModule = timeModule;
        this.actions = actions;
        this.original = timeModule.getMillis();
        this.listeners = new LinkedList<>();
    }

    public void run() {
        long end = now() + this.timeModule.getMillis();
        while ((now() < end) && (!isInterrupted())) {
            System.out.println("Starting a new run");
            this.timeModule.setMillis(end - now());
            try {
                this.actions.execute();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException thrown: " + e.getMessage());
                break;
            }
            System.out.println("Finished a run");
        }
        System.out.println("FINISHED!!!");
        System.out.println("now() = " + now());
        System.out.println("end = " + end);
        System.out.println("isInterrupted() = " + isInterrupted());
        this.timeModule.setMillis(this.original);
        for (ThreadListener listener : this.listeners) {
            listener.finished();
        }
    }

    public void addThreadListener(ThreadListener threadListener) {
        this.listeners.add(threadListener);
    }

    private static long now() {
        return System.currentTimeMillis();
    }

}
