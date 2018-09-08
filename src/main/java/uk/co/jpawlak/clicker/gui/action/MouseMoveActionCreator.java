package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.MouseMoveAction;

import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class MouseMoveActionCreator implements ActionCreator {

    @Override
    public Action create(Frame parentComponent) {
        JOptionPane.showMessageDialog(parentComponent, "Position the mouse pointer and press enter/space", parentComponent.getTitle(), PLAIN_MESSAGE);

        Point location = MouseInfo.getPointerInfo().getLocation();
        return new MouseMoveAction(location.x, location.y);
    }

}
