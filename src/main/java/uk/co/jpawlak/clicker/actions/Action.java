package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;

public abstract interface Action {

    public static final Robot robot = SafeRobot.robotOrNull();

    public abstract void execute();

}
