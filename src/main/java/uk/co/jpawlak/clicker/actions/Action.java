package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;

public interface Action {

    Robot robot = SafeRobot.robotOrNull(); //TODO move to SafeRobot and make it singleton

    void execute();

}
