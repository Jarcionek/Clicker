package uk.co.jpawlak.clicker.gui.action;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
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
        JLabel label = new JLabel("Press a key");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JOptionPane pane = new JOptionPane(label, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});

        JDialog dialog = pane.createDialog(parentComponent, parentComponent.getTitle());

        KeyEvent[] chosenKey = {null};

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                chosenKey[0] = e;
                dialog.setVisible(false);
            }
        };
        dialog.addKeyListener(keyListener);

        dialog.setVisible(true);
        dialog.dispose();

        return Optional.ofNullable(chosenKey[0]);
    }

}
