package uk.co.jpawlak.clicker;

import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;
import uk.co.jpawlak.clicker.actions.MouseButtonReleaseAction;
import uk.co.jpawlak.clicker.gui.MainWindow;

public class Main {

    public static void main(String[] args) {
        Actions actions = new Actions();
        actions.add(new MouseButtonPressAction(true, false, false));
        actions.add(new MouseButtonReleaseAction(true, false, false));

        new MainWindow(actions);
    }

}
