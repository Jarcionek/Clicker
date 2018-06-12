package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.SleepAction;

public class ActionsMapper {

    public String[] convert(Actions actions) {
        return actions.stream().map(this::convert).toArray(String[]::new);
    }

    private String convert(Action action) {
        if (action instanceof SleepAction) {
            return "Sleep: " + ((SleepAction) action).getTime() + "ms";
        }
        return action.toString(); //TODO temporary until all UI code removed from Actions
//        throw new UnsupportedOperationException("should never happen");
    }

}
