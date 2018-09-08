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
 * Used for finding seeds with nice geysers. Generate the world, reveal it, remove all, save it.
 */
public class MainOxygenNotIncluded {

    private static final Actions actions = new Actions();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // new game
        moveMouseAndClick(2295, 508);

        // survival
        moveMouseAndClick(1407, 705);

        // custom game
        moveMouseAndClick(1370, 556);

        // seed
        moveMouseAndClick(1360, 812);
        copyToClipboard();

        // sandbox
        moveMouseAndClick(1289, 856);

        // start game
        moveMouseAndClick(1288, 901);
        actions.add(new SleepAction(20_000));

        // colony name
        moveMouseAndClick(1786, 1042);
        pasteFromClipboard();

        // embark
        moveMouseAndClick(1957, 1080);
        actions.add(new SleepAction(10_000));

        // begin
        moveMouseAndClick(1276, 824);
        actions.add(new SleepAction(3_000));

        // pause game
        press(KeyEvent.VK_SPACE);

        // sandbox
        moveMouseAndClick(362, 93);

        // reveal
        moveMouseAndClick(2506, 1312);

        // brush size
        moveMouseAndClick(2444, 1199);
        press(KeyEvent.VK_2);
        press(KeyEvent.VK_0);
        press(KeyEvent.VK_0);
        press(KeyEvent.VK_ENTER);

        // use the brush
        moveMouseAndClick(1326, 736);
        actions.add(new SleepAction(5_000));

        // brush
        moveMouseAndClick(1980, 1301);

        // water (liquid)
        moveMouseAndClick(2217, 1020);

        // enter text...
        moveMouseAndClick(2185, 643);
        press(KeyEvent.VK_U);
        press(KeyEvent.VK_U);

        // vacuum (none)
        moveMouseAndClick(2189, 681);

        // use the brush
        moveMouseAndClick(1326, 736);
        actions.add(new SleepAction(30_000));

        // switch speed and unpause
        press(KeyEvent.VK_TAB);
        press(KeyEvent.VK_TAB);
        press(KeyEvent.VK_SPACE);

        // wait for items to fall
        actions.add(new SleepAction(7_000));

        // and pause again
        press(KeyEvent.VK_SPACE);

        // brush vacuum again
//        moveMouseAndClick(1980, 1301); // no need to select brush again, it's already selected
        moveMouseAndClick(1326, 736);
        actions.add(new SleepAction(5_000));

        // cancel brush
        press(KeyEvent.VK_ESCAPE);

        // open menu
        press(KeyEvent.VK_ESCAPE);

        // save
        moveMouseAndClick(1282, 633);

        // override
        moveMouseAndClick(1286, 734);
        actions.add(new SleepAction(3_000));

        // main menu
        moveMouseAndClick(1282, 812);

        // confirm
        moveMouseAndClick(1276, 748);
        actions.add(new SleepAction(5_000));

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

}
