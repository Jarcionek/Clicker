package uk.co.jpawlak.clicker.actions;

import java.awt.AWTException;
import java.awt.Robot;

class SafeRobot extends Robot {

    public static SafeRobot robotOrNull() {
        try {
            return new SafeRobot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SafeRobot() throws AWTException {
    }

}
