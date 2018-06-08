package uk.co.jpawlak.clicker.actions;

import java.awt.AWTException;
import java.awt.Robot;

class RobotSingleton {

    private static final Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static Robot robot() {
        return robot;
    }

}
