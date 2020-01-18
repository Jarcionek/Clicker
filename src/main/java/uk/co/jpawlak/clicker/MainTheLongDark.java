package uk.co.jpawlak.clicker;

import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.KeyPressAction;
import uk.co.jpawlak.clicker.actions.KeyReleaseAction;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;
import uk.co.jpawlak.clicker.actions.MouseButtonReleaseAction;
import uk.co.jpawlak.clicker.actions.MouseMoveAction;
import uk.co.jpawlak.clicker.actions.SleepAction;
import uk.co.jpawlak.clicker.gui.MainWindow;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.KeyEvent;

/**
 * Grind lighting a fire to obtain a treat for lighting a fire 1000 (?) times.
 */
public class MainTheLongDark {

    private static final Actions actions = new Actions();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // load game
        press(KeyEvent.VK_ESCAPE);
        moveMouseAndClick(275, 880); // load game
        moveMouseAndClick(340, 1000); // select slot - second slot, named "fire feat cheat"
        moveMouseAndClick(2160, 1355); // load
        actions.add(new SleepAction(7000)); // wait for loading to finish
        // game loaded

        // light the fire
        clickLmb();
        moveMouseAndClick(870, 960);
        moveMouseAndClick(440, 980);
        moveMouseAndClick(2120, 1350);
        actions.add(new SleepAction(5000));
        // fire lit

        // save the game
        press(KeyEvent.VK_ESCAPE);
        moveMouseAndClick(280, 840); // save game
        moveMouseAndClick(245, 950); // select slot - first slot, named "temp"
        moveMouseAndClick(2160, 1355); // overwrite
        moveMouseAndClick(1150, 800); // confirm
        actions.add(new SleepAction(200));
        clickLmb(); // confirm (same save name)
        actions.add(new SleepAction(7000)); // wait for saving to finish
        // game saved

        new MainWindow(actions);
    }

    private static void moveMouseAndClick(int x, int y) {
        moveMouseTo(x, y);
        clickLmb();
    }

    private static void copyToClipboard() {
        doClipboardStuff(KeyEvent.VK_C);
    }

    private static void pasteFromClipboard() {
        doClipboardStuff(KeyEvent.VK_V);
    }

    private static void doClipboardStuff(int keyCode) {
        actions.add(new KeyPressAction(new KeyEvent(new JLabel(), 0, 0, 0, KeyEvent.VK_CONTROL, '\0')));
        actions.add(new SleepAction(100));
        actions.add(new KeyPressAction(new KeyEvent(new JLabel(), 0, 0, 0, keyCode, '\0')));
        actions.add(new SleepAction(100));
        actions.add(new KeyReleaseAction(new KeyEvent(new JLabel(), 0, 0, 0, keyCode, '\0')));
        actions.add(new SleepAction(100));
        actions.add(new KeyReleaseAction(new KeyEvent(new JLabel(), 0, 0, 0, KeyEvent.VK_CONTROL, '\0')));
        actions.add(new SleepAction(100));
    }

    private static void press(int keyCode) {
        actions.add(new KeyPressAction(new KeyEvent(new JLabel(), 0, 0, 0, keyCode, '\0')));
        actions.add(new SleepAction(100));
        actions.add(new KeyReleaseAction(new KeyEvent(new JLabel(), 0, 0, 0, keyCode, '\0')));
        actions.add(new SleepAction(1000));
    }

    private static void moveMouseTo(int x, int y) {
        actions.add(new MouseMoveAction(x, y));
        actions.add(new SleepAction(100));
    }

    private static void clickLmb() {
        actions.add(new MouseButtonPressAction(true, false, false));
        actions.add(new SleepAction(50));
        actions.add(new MouseButtonReleaseAction(true, false, false));
        actions.add(new SleepAction(1000));
    }

    private static void clicRmb() {
        actions.add(new MouseButtonPressAction(false, false, true));
        actions.add(new SleepAction(50));
        actions.add(new MouseButtonReleaseAction(false, false, true));
        actions.add(new SleepAction(1000));
    }

}
