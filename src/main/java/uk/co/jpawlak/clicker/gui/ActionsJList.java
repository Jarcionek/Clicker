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
        if (action == null) {
            return;
        }
        actions.add(action);
        int[] selectedIndices = getSelectedIndices();
        setListData(actions.asArray());
        setSelectedIndices(selectedIndices);
    }

    void deleteActions() {
        actions.deleteActions(getSelectedIndices());
        setListData(actions.asArray());
        setSelectedIndices(new int[]{});
    }

    void moveUp() {
        int[] newIndices = actions.moveUp(getSelectedIndices());
        setListData(actions.asArray());
        setSelectedIndices(newIndices);
    }

    void moveDown() {
        int[] newIndices = actions.moveDown(getSelectedIndices());
        setListData(actions.asArray());
        setSelectedIndices(newIndices);
    }

    void copy() {
        int[] newIndices = actions.copy(getSelectedIndices());
        setListData(actions.asArray());
        setSelectedIndices(newIndices);
    }

}
