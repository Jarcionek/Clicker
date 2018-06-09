package uk.co.jpawlak.clicker.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

public class KeyPressAction implements Action {

    private final KeyEvent key;

    public KeyPressAction(KeyEvent key) {
        this.key = key;
    }

    public void execute() {
        robot().keyPress(this.key.getKeyCode());
    }

    public String toString() {
        return "Press key: " + KeyEvent.getKeyText(key.getKeyCode());
    }

    public static KeyPressAction showPopup(Frame parentComponent) { //TODO this code below is terrible...
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
        dialog.dispose();

        if (chosenKey[0] != null) {
            return new KeyPressAction(chosenKey[0]);
        }
        return null;
    }

}