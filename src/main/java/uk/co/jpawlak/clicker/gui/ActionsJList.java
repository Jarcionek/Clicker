package uk.co.jpawlak.clicker.gui;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.Actions;

import javax.swing.JList;

public class ActionsJList extends JList<Action> {

    private final Actions actions;

    ActionsJList(Actions actions) {
        super(actions.asArray());
        this.actions = actions;
    }

    public Actions getActions() {
        return actions;
    }

    void addAction(Action action) {
        if (action != null) {
            this.actions.add(action);
            int[] selectedIndices = getSelectedIndices();
            setListData(actions.asArray());
            setSelectedIndices(selectedIndices);
        }
    }

    void deleteActions() {
        actions.deleteActions(getSelectedIndices());
        setListData(actions.asArray());
        setSelectedIndices(new int[] {});
    }

    void moveUp() {
        if (getSelectedIndices().length > 0) {
            int[] newIndices = actions.moveUp(getSelectedIndices());
            setListData(actions.asArray());
            setSelectedIndices(newIndices);
        }
    }

    void moveDown() {
        if (getSelectedIndices().length > 0) {
            int[] newIndices = actions.moveDown(getSelectedIndices());
            setListData(actions.asArray());
            setSelectedIndices(newIndices);
        }
    }

    void copy() {
        if (getSelectedIndices().length > 0) {
            int[] newIndices = actions.copy(getSelectedIndices());
            setListData(actions.asArray());
            setSelectedIndices(newIndices);
        }
    }

}
