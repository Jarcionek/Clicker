package uk.co.jpawlak.clicker.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Actions implements Iterable<Action> {

    private List<Action> actions = new ArrayList();

    public void add(Action action) {
        this.actions.add(action);
    }

    public void execute() {
        for (Action action : this.actions) {
            action.execute();
        }
    }

    public Iterator<Action> iterator() {
        return this.actions.iterator();
    }

    public Action[] asArray() {
        return (Action[]) this.actions.toArray(new Action[0]);
    }

    public void deleteActions(int[] selectedIndices) {
        Action[] array = asArray();
        for (int selectedIndice : selectedIndices) {
            array[selectedIndice] = null;
        }
        this.actions = new ArrayList(this.actions.size() - selectedIndices.length);
        for (Action action : array) {
            if (action != null) {
                this.actions.add(action);
            }
        }
    }

    public int[] moveUp(int[] selectedIndices) {
        Action[] array = (Action[]) concatenate(new Action[]{null}, asArray());
        for (int i = 0; i < selectedIndices.length; i++) {
            swap(array, selectedIndices[i] + 1, selectedIndices[i]);
            selectedIndices[i] -= 1;
        }
        this.actions = new ArrayList(this.actions.size());
        for (Action action : array) {
            if (action != null) {
                this.actions.add(action);
            }
        }
        return selectedIndices;
    }

    public int[] moveDown(int[] selectedIndices) {
        Action[] array = (Action[]) concatenate(asArray(), new Action[]{null});
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            swap(array, selectedIndices[i], selectedIndices[i] + 1);
            selectedIndices[i] += 1;
        }
        this.actions = new ArrayList(this.actions.size());
        for (Action action : array) {
            if (action != null) {
                this.actions.add(action);
            }
        }
        return selectedIndices;
    }

    public int[] copy(int[] selectedIndices) {
        Action[] array = asArray();
        for (int selectedIndice : selectedIndices) {
            this.actions.add(array[selectedIndice]);
        }
        int[] newIndices = new int[selectedIndices.length];
        for (int i = 0; i < newIndices.length; i++) {
            newIndices[i] = (array.length + i);
        }
        return newIndices;
    }

    private static void swap(Object[] array, int index, int index2) {
        Object t = array[index];
        array[index] = array[index2];
        array[index2] = t;
    }

    private static <T> T[] concatenate(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

}
