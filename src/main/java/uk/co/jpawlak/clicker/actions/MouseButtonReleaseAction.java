package uk.co.jpawlak.clicker.actions;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

public class MouseButtonReleaseAction implements Action {

    private final int buttons;
    private final String text;

    public static MouseButtonReleaseAction release(boolean left, boolean middle, boolean right) {
        return new MouseButtonReleaseAction(left, middle, right);
    }

    private MouseButtonReleaseAction(boolean left, boolean middle, boolean right) {
        int buttons = 0;
        String text = "";
        if (left) {
            buttons |= 0x400;
            text = text + "L";
        }
        if (middle) {
            buttons |= 0x800;
            text = text + "M";
        }
        if (right) {
            buttons |= 0x1000;
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

        int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to release", left, middle, right), parentComponent.getTitle(), 2);

        if (choice == JOptionPane.YES_OPTION) {
            return new MouseButtonReleaseAction(left.isSelected(), middle.isSelected(), right.isSelected());
        } else {
            return null;
        }
    }

    private static JPanel mouseButtonPanel(String text, JCheckBox left, JCheckBox middle, JCheckBox right) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text), "North");

        JPanel boxes = new JPanel(new GridLayout());
        boxes.add(left);
        boxes.add(middle);
        boxes.add(right);
        panel.add(boxes, "Center");

        return panel;
    }

}
