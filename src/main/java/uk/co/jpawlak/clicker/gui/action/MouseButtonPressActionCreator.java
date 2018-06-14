package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;

import java.awt.Frame;

import static uk.co.jpawlak.clicker.gui.action.MouseButtonUtils.createActionFromPopup;

public class MouseButtonPressActionCreator implements ActionCreator {

    @Override
    public Action create(Frame parentComponent) {
        return createActionFromPopup(parentComponent, "press", MouseButtonPressAction::new);
    }

}
