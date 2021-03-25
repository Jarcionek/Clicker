package uk.co.jpawlak.clicker;

import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.KeyPressAction;
import uk.co.jpawlak.clicker.actions.KeyReleaseAction;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;
import uk.co.jpawlak.clicker.actions.MouseButtonReleaseAction;
import uk.co.jpawlak.clicker.actions.SleepAction;
import uk.co.jpawlak.clicker.gui.MainWindow;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.KeyEvent;

/**
 * Selling cooked food one by one to Cronus to earn nanites.
 */
public class MainNoMansSky {

    private static final Actions actions = new Actions();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        holdE();
        tryToSkipDialog();
        press(KeyEvent.VK_2);
        tryToSkipDialog();

        new MainWindow(actions);
    }

    private static void press(int keyCode) {
        actions.add(new KeyPressAction(new KeyEvent(new JLabel(), 0, 0, 0, keyCode, '\0')));
        actions.add(new SleepAction(100));
        actions.add(new KeyReleaseAction(new KeyEvent(new JLabel(), 0, 0, 0, keyCode, '\0')));
        actions.add(new SleepAction(100));
    }

    private static void holdE() {
        actions.add(new KeyPressAction(new KeyEvent(new JLabel(), 0, 0, 0, KeyEvent.VK_E, '\0')));
        actions.add(new SleepAction(2000));
        actions.add(new KeyReleaseAction(new KeyEvent(new JLabel(), 0, 0, 0, KeyEvent.VK_E, '\0')));
        actions.add(new SleepAction(100));
    }

    private static void sleep(int time) {
        actions.add(new SleepAction(time));
    }

    private static void clickLmb() {
        actions.add(new MouseButtonPressAction(true, false, false));
        actions.add(new SleepAction(50));
        actions.add(new MouseButtonReleaseAction(true, false, false));
        actions.add(new SleepAction(100));
    }

    private static void tryToSkipDialog() {
        sleep(1000);
        clickLmb();
        sleep(1000);
        clickLmb();
        sleep(1000);
        clickLmb();
//        sleep(1000);
//        clickLmb();
//        sleep(1000);
//        clickLmb();
    }

}
