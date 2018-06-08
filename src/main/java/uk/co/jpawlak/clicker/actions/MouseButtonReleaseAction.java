package uk.co.jpawlak.clicker.actions;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.InputEvent;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

public class MouseButtonReleaseAction implements Action { //TODO extract duplications to super class

    private final int buttons;
    private final String text;

    public static MouseButtonReleaseAction release(boolean left, boolean middle, boolean right) {
        return new MouseButtonReleaseAction(left, middle, right);
    }

    private MouseButtonReleaseAction(boolean left, boolean middle, boolean right) {
        int buttons = 0;
        String text = "";
        if (left) {
            buttons |= InputEvent.BUTTON1_DOWN_MASK;
            text = text + "L";
        }
        if (middle) {
            buttons |= InputEvent.BUTTON2_DOWN_MASK;
            text = text + "M";
        }
        if (right) {
            buttons |= InputEvent.BUTTON3_DOWN_MASK;
            text = text + "R";
        }
        if (buttons == 0) {
            throw new IllegalArgumentException();
        }
        this.buttons = buttons;
        this.text = text;
    }

    public void execute() {
        robot().mouseRelease(this.buttons);
    }

    public String toString() {
        return "Release buttons: " + this.text;
    }

    public static MouseButtonReleaseAction showPopup(Frame parentComponent) {
        JCheckBox left = new JCheckBox("Left");
        JCheckBox middle = new JCheckBox("Middle");
        JCheckBox right = new JCheckBox("Right");

        int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to release", left, middle, right), parentComponent.getTitle(), JOptionPane.OK_CANCEL_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                return new MouseButtonReleaseAction(left.isSelected(), middle.isSelected(), right.isSelected());
            } catch (IllegalArgumentException e) {
                // none of the buttons selected
            }
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
