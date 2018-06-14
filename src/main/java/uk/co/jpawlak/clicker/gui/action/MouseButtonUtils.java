package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.AbstractMouseButtonAction;
import uk.co.jpawlak.clicker.actions.Action;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

class MouseButtonUtils {

    static String mouseButtonsAsText(AbstractMouseButtonAction action) {
        String text = "";
        if (action.isLeft()) {
            text = text + "L";
        }
        if (action.isMiddle()) {
            text = text + "M";
        }
        if (action.isRight()) {
            text = text + "R";
        }
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return text;
    }

    static Action createActionFromPopup(Frame parentComponent, String actionWord, MouseButtonConstructorFunction constructor) {
        JCheckBox left = new JCheckBox("Left");
        JCheckBox middle = new JCheckBox("Middle");
        JCheckBox right = new JCheckBox("Right");

        int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to " + actionWord + ":", left, middle, right), parentComponent.getTitle(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.YES_OPTION && (left.isSelected() || middle.isSelected() || right.isSelected())) {
            return constructor.invoke(left.isSelected(), middle.isSelected(), right.isSelected());
        }
        return null;
    }

    private static JPanel mouseButtonPanel(String text, JCheckBox left, JCheckBox middle, JCheckBox right) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text), BorderLayout.NORTH);

        JPanel boxes = new JPanel(new GridLayout());
        boxes.add(left);
        boxes.add(middle);
        boxes.add(right);
        panel.add(boxes, BorderLayout.CENTER);

        return panel;
    }

}
