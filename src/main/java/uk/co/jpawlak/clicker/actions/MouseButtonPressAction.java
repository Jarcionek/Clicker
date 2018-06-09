package uk.co.jpawlak.clicker.actions;

import java.awt.Frame;
import java.awt.Robot;

public class MouseButtonPressAction extends AbstractMouseButtonAction {

    public MouseButtonPressAction(boolean left, boolean middle, boolean right) {
        super(left, middle, right, Robot::mousePress, "Press");
    }

    public static MouseButtonPressAction showPopup(Frame parentComponent) {
        return buttonsFromPopup(parentComponent, "press").map(b -> new MouseButtonPressAction(b[0], b[1], b[2])).orElse(null);
    }

}
