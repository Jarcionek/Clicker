package uk.co.jpawlak.clicker.actions;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Optional;
import java.util.function.BiConsumer;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

abstract class AbstractMouseButtonAction implements Action {

    private final BiConsumer<Robot, Integer> robotAction;
    private final String actionDescription;

    private final int buttons;
    private final String buttonsDescription;

    AbstractMouseButtonAction(boolean left, boolean middle, boolean right, BiConsumer<Robot, Integer> robotAction, String actionDescription) {
        this.robotAction = robotAction;
        this.actionDescription = actionDescription;

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
        this.buttonsDescription = text;
    }

    @Override
    public final void execute() {
        robotAction.accept(robot(), buttons);
    }

    public final String toString() { //TODO this belongs to the UI
        return actionDescription + " buttons: " + this.buttonsDescription;
    }

    static Optional<boolean[]> buttonsFromPopup(Frame parentComponent, String actionWord) {
        JCheckBox left = new JCheckBox("Left");
        JCheckBox middle = new JCheckBox("Middle");
        JCheckBox right = new JCheckBox("Right");

        int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to " + actionWord + ":", left, middle, right), parentComponent.getTitle(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (choice == JOptionPane.YES_OPTION && (left.isSelected() || middle.isSelected() || right.isSelected())) {
            return Optional.of(new boolean[] {left.isSelected(), middle.isSelected(), right.isSelected()});
        }
        return Optional.empty();
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
