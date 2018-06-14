package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;
import uk.co.jpawlak.clicker.actions.MouseButtonReleaseAction;
import uk.co.jpawlak.clicker.actions.SleepAction;

import static uk.co.jpawlak.clicker.gui.action.MouseButtonUtils.mouseButtonsAsText;

public class ActionsMapper {

    public String[] convert(Actions actions) {
        return actions.stream().map(this::convert).toArray(String[]::new);
    }

    private String convert(Action action) {
        if (action instanceof SleepAction) {
            return "Sleep: " + ((SleepAction) action).getTime() + "ms";
        } else if (action instanceof MouseButtonPressAction) {
            return "Press buttons: " + mouseButtonsAsText((MouseButtonPressAction) action);
        } else if (action instanceof MouseButtonReleaseAction) {
            return "Release buttons: " + mouseButtonsAsText((MouseButtonReleaseAction) action);
        }
        return action.toString(); //TODO temporary until all UI code removed from Actions
//        throw new UnsupportedOperationException("should never happen");
    }

}
