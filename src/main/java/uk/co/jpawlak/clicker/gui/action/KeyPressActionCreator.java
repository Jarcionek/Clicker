package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.KeyPressAction;

import java.awt.Frame;

import static uk.co.jpawlak.clicker.gui.action.KeyUtils.keyEventFromPopup;

public class KeyPressActionCreator implements ActionCreator {

    @Override
    public Action create(Frame parentComponent) {
        return keyEventFromPopup(parentComponent).map(KeyPressAction::new).orElse(null);
    }

}
