package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.SleepAction;

import javax.swing.JOptionPane;
import java.awt.Frame;

import static java.lang.Long.parseLong;

public class SleepActionCreator implements ActionCreator {

    @Override
    public Action create(Frame parentComponent) {
        String input;
        do {
            input = JOptionPane.showInputDialog(parentComponent, "Enter wait time (milliseconds)", parentComponent.getTitle(), JOptionPane.PLAIN_MESSAGE);
        } while ((input != null) && (!isLong(input)));

        return input == null ? null : new SleepAction(parseLong(input));
    }

    private static boolean isLong(String value) {
        try {
            parseLong(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
