package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.KeyReleaseAction;

import java.awt.Frame;

import static uk.co.jpawlak.clicker.gui.action.KeyUtils.keyEventFromPopup;

public class KeyReleaseActionCreator implements ActionCreator {

    @Override
    public Action create(Frame parentComponent) {
        return keyEventFromPopup(parentComponent).map(KeyReleaseAction::new).orElse(null);
    }
}
