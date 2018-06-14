package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.function.BiConsumer;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

public abstract class AbstractMouseButtonAction implements Action {


    private final boolean left;
    private final boolean middle;
    private final boolean right;
    private final BiConsumer<Robot, Integer> robotAction;
    private final int buttonsMask;

    AbstractMouseButtonAction(boolean left, boolean middle, boolean right, BiConsumer<Robot, Integer> robotAction) {
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.robotAction = robotAction;
        this.buttonsMask = mouseButtonsMask(left, middle, right);
    }

    @Override
    public final void execute() {
        robotAction.accept(robot(), buttonsMask);
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isMiddle() {
        return middle;
    }

    public boolean isRight() {
        return right;
    }


    private static int mouseButtonsMask(boolean left, boolean middle, boolean right) {
        int buttons = 0;
        if (left) {
            buttons |= InputEvent.BUTTON1_DOWN_MASK;
        }
        if (middle) {
            buttons |= InputEvent.BUTTON2_DOWN_MASK;
        }
        if (right) {
            buttons |= InputEvent.BUTTON3_DOWN_MASK;
        }
        if (buttons == 0) {
            throw new IllegalArgumentException();
        }
        return buttons;
    }

}
