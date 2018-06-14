package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyReleaseAction extends AbstractKeyAction {

    public KeyReleaseAction(KeyEvent key) {
        super(key, Robot::keyRelease);
    }

}