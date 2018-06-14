package uk.co.jpawlak.clicker.actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyPressAction extends AbstractKeyAction {

    public KeyPressAction(KeyEvent key) {
        super(key, Robot::keyPress);
    }

}