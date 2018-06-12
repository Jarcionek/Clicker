package uk.co.jpawlak.clicker.gui;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.gui.action.ActionsMapper;

import javax.swing.JList;

public class ActionsJList extends JList<String> {

    private final ActionsMapper actionMapper = new ActionsMapper();

    private final Actions actions;

    ActionsJList(Actions actions) {
        this.actions = actions;
        setListData(actionMapper.convert(actions));
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
        setListData(actionMapper.convert(actions));
        setSelectedIndices(selectedIndices);
    }

    void deleteActions() {
        actions.deleteActions(getSelectedIndices());
        setListData(actionMapper.convert(actions));
        setSelectedIndices(new int[]{});
    }

    void moveUp() {
        int[] newIndices = actions.moveUp(getSelectedIndices());
        setListData(actionMapper.convert(actions));
        setSelectedIndices(newIndices);
    }

    void moveDown() {
        int[] newIndices = actions.moveDown(getSelectedIndices());
        setListData(actionMapper.convert(actions));
        setSelectedIndices(newIndices);
    }

    void copy() {
        int[] newIndices = actions.copy(getSelectedIndices());
        setListData(actionMapper.convert(actions));
        setSelectedIndices(newIndices);
    }

}
