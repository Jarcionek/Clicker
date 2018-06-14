package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;

import java.awt.Frame;

import static uk.co.jpawlak.clicker.gui.action.MouseButtonUtils.buttonsFromPopup;

public class MouseButtonPressActionCreator implements ActionCreator {

    @Override
    public Action create(Frame parentComponent) {
        return buttonsFromPopup(parentComponent, "press").map(b -> new MouseButtonPressAction(b[0], b[1], b[2])).orElse(null);
    }

}
