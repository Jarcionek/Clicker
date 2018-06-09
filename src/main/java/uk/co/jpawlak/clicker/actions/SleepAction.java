package uk.co.jpawlak.clicker.actions;

import javax.swing.JOptionPane;
import java.awt.Frame;

public class SleepAction implements Action {

    private final long time;

    public static SleepAction sleep(long time) {
        return new SleepAction(time);
    }

    private SleepAction(long time) {
        this.time = time;
    }

    public void execute() throws InterruptedException {
        Thread.sleep(this.time);
    }

    public String toString() {
        return "Sleep: " + this.time + "ms";
    }

    public static SleepAction showPopup(Frame parentComponent) {
        String input;
        do {
            input = JOptionPane.showInputDialog(parentComponent, "Enter wait time (milliseconds)", parentComponent.getTitle(), -1);
        } while ((input != null) && (!isLong(input)));
        return input == null ? null : new SleepAction(Long.parseLong(input));
    }

    private static boolean isLong(String value) {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
