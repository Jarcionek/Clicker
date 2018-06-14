package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;

public class MouseButtonPressAction extends AbstractMouseButtonAction {

    public MouseButtonPressAction(boolean left, boolean middle, boolean right) {
        super(left, middle, right, Robot::mousePress);
    }

}
