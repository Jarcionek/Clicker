package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.function.BiConsumer;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

abstract class AbstractKeyAction implements Action {

    private final KeyEvent key;
    private final BiConsumer<Robot, Integer> robotAction;

    AbstractKeyAction(KeyEvent key, BiConsumer<Robot, Integer> robotAction) {
        this.key = key;
        this.robotAction = robotAction;
    }

    @Override
    public final void execute() {
        robotAction.accept(robot(), key.getKeyCode());
    }

    public KeyEvent getKey() {
        return key;
    }

}
