package uk.co.jpawlak.clicker.gui.action;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

class KeyUtils {

    static String keyEventAsText(KeyEvent keyEvent) {
        return KeyEvent.getKeyText(keyEvent.getKeyCode());
    }

    static Optional<KeyEvent> keyEventFromPopup(Frame parentComponent) {
        JLabel label = new JLabel("Press a key");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JOptionPane pane = new JOptionPane(label, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});

        JDialog dialog = pane.createDialog(parentComponent, parentComponent.getTitle());

        AtomicReference<KeyEvent> chosenKey = new AtomicReference<>();

        dialog.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                chosenKey.set(e);
                dialog.setVisible(false);
            }
        });

        dialog.setVisible(true);
        dialog.dispose();

        return Optional.ofNullable(chosenKey.get());
    }

}
