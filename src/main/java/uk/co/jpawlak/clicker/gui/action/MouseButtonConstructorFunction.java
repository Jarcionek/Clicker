package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.AbstractMouseButtonAction;

@FunctionalInterface
public interface MouseButtonConstructorFunction {

    AbstractMouseButtonAction invoke(boolean left, boolean middle, boolean right);

}
