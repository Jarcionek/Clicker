package uk.co.jpawlak.clicker.actions;

import java.awt.Frame;
import java.awt.Robot;

public class MouseButtonReleaseAction extends AbstractMouseButtonAction {

    public MouseButtonReleaseAction(boolean left, boolean middle, boolean right) {
        super(left, middle, right, Robot::mouseRelease, "Release");
    }

    public static MouseButtonReleaseAction showPopup(Frame parentComponent) {
        return buttonsFromPopup(parentComponent, "release").map(b -> new MouseButtonReleaseAction(b[0], b[1], b[2])).orElse(null);
    }

}
