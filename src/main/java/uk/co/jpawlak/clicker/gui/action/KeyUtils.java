package uk.co.jpawlak.clicker.gui.action;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

class KeyUtils {

    static String keyEventAsText(KeyEvent keyEvent) {
        return KeyEvent.getKeyText(keyEvent.getKeyCode());
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
