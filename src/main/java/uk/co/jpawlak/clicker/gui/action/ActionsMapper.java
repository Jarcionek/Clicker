package uk.co.jpawlak.clicker.gui.action;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.KeyPressAction;
import uk.co.jpawlak.clicker.actions.KeyReleaseAction;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;
import uk.co.jpawlak.clicker.actions.MouseButtonReleaseAction;
import uk.co.jpawlak.clicker.actions.MouseMoveAction;
import uk.co.jpawlak.clicker.actions.SleepAction;

import static uk.co.jpawlak.clicker.gui.action.KeyUtils.keyEventAsText;
import static uk.co.jpawlak.clicker.gui.action.MouseButtonUtils.mouseButtonsAsText;

public class ActionsMapper {

    public String[] convert(Actions actions) {
        return actions.stream().map(ActionsMapper::convert).toArray(String[]::new);
    }

    public static String convert(Action action) {
        if (action instanceof SleepAction) {
            return "Sleep: " + ((SleepAction) action).getTime() + "ms";
        } else if (action instanceof MouseButtonPressAction) {
            return "Press buttons: " + mouseButtonsAsText((MouseButtonPressAction) action);
        } else if (action instanceof MouseButtonReleaseAction) {
            return "Release buttons: " + mouseButtonsAsText((MouseButtonReleaseAction) action);
        } else if (action instanceof MouseMoveAction) {
            return "Move mouse to: (" + ((MouseMoveAction) action).getX() + "," + ((MouseMoveAction) action).getY() + ")";
        } else if (action instanceof KeyPressAction) {
            return "Press key: " + keyEventAsText(((KeyPressAction) action).getKey());
        } else if (action instanceof KeyReleaseAction) {
            return "Release key: " + keyEventAsText(((KeyReleaseAction) action).getKey());
        } else {
            throw new UnsupportedOperationException("should never happen");
        }
    }

}
