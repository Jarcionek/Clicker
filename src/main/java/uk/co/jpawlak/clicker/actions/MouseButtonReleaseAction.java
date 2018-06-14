package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;

public class MouseButtonReleaseAction extends AbstractMouseButtonAction {

    public MouseButtonReleaseAction(boolean left, boolean middle, boolean right) {
        super(left, middle, right, Robot::mouseRelease);
    }

}
