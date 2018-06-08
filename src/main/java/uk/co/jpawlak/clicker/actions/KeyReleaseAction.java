package uk.co.jpawlak.clicker.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

public class KeyReleaseAction implements Action { //TODO copy-paste of KeyPressAction

    private final KeyEvent key;

    public KeyReleaseAction(KeyEvent key) {
        this.key = key;
    }

    public void execute() {
        robot().keyRelease(this.key.getKeyCode());
    }

    public String toString() {
        return "Release key: " + KeyEvent.getKeyText(key.getKeyCode());
    }

    public static KeyReleaseAction showPopup(Frame parentComponent) { //TODO this code below is terrible...
        JTextField textField = new JTextField();

        JOptionPane pane = new JOptionPane(textField, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});

        JDialog dialog = pane.createDialog(parentComponent, parentComponent.getTitle());

        KeyEvent[] chosenKey = {null};

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                chosenKey[0] = e;
                dialog.setVisible(false);
            }
        };
        textField.addKeyListener(keyListener);

        dialog.setVisible(true);

        if (chosenKey[0] != null) {
            return new KeyReleaseAction(chosenKey[0]);
        }
        return null;
    }

}