package uk.co.jpawlak.clicker.gui;

import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberField extends JTextField {

    public NumberField(String text) {
        super(text);
        setPreferredSize(new Dimension(50, getPreferredSize().height));
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                try {
                    String text = NumberField.this.getText();
                    if ((text != null) && (!text.equals(""))) {
                        Integer.parseInt(text);
                    }
                } catch (NumberFormatException ex) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
                if (((c < '0') || (c > '9')) && (c != '\b') && (c != '')) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }

    public long getLong() {
        return getText().length() == 0 ? 0L : Long.parseLong(getText());
    }

    public void paste() {
    }

}
