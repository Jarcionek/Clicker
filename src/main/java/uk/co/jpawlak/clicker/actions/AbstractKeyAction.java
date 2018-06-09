package uk.co.jpawlak.clicker.actions;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;
import java.util.function.BiConsumer;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

abstract class AbstractKeyAction implements Action {

    private final KeyEvent key;
    private final BiConsumer<Robot, Integer> robotAction;
    private final String description;

    AbstractKeyAction(KeyEvent key, BiConsumer<Robot, Integer> robotAction, String description) {
        this.key = key;
        this.robotAction = robotAction;
        this.description = description;
    }

    @Override
    public final void execute() {
        robotAction.accept(robot(), key.getKeyCode());
    }

    public final String toString() { //TODO this belongs to the UI
        return description + " key: " + KeyEvent.getKeyText(key.getKeyCode());
    }

    static Optional<KeyEvent> keyEventFromPopup(Frame parentComponent) { //TODO this code below is terrible...
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

        return Optional.ofNullable(chosenKey[0]);
    }

}
