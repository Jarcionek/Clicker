package uk.co.jpawlak.clicker.threads;

import uk.co.jpawlak.clicker.gui.Status;
import uk.co.jpawlak.clicker.gui.StatusLabel;

import javax.swing.SwingWorker;

public class StartingWorker extends SwingWorker {

    private static final int WAIT_TIME = 3;
    private final StatusLabel statusLabel;

    public StartingWorker(StatusLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    protected Object doInBackground() throws Exception {
        Status status = Status.countdown(WAIT_TIME);
        while ((Status.COUNTDOWN.getTime() >= 0) && (!isCancelled())) {
            this.statusLabel.setStatus(status);
            status.decrement();
            Thread.sleep(1000L);
        }
        this.statusLabel.setStatus(Status.ACTIVE);
        return null;
    }

}
