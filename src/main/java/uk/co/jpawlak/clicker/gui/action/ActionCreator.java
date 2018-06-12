package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;

import java.awt.Frame;

public interface ActionCreator {

    Action create(Frame parentComponent);

}
