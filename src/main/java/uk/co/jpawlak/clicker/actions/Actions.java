package uk.co.jpawlak.clicker.actions;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Actions {

    private List<Action> actions = new ArrayList<>();

    public void add(Action action) {
        this.actions.add(action);
    }

    public void execute() throws InterruptedException {
        for (Action action : this.actions) {
            action.execute();
        }
    }

    public Action[] asArray() {
        return this.actions.toArray(new Action[0]);
    }

    public void deleteActions(int[] selectedIndices) {
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            actions.remove(selectedIndices[i]);
        }
    }

    public int[] moveUp(int[] selectedIndices) {
        List<Action> selectedActions = stream(selectedIndices).mapToObj(actions::get).collect(toList());

        actions.add(0, null);
        for (int selectedIndex : selectedIndices) {
            swap(actions, selectedIndex + 1, selectedIndex);
        }
        actions.remove(null);

        return selectedActions.stream().mapToInt(actions::indexOf).toArray();
    }

    public int[] moveDown(int[] selectedIndices) {
        Action[] array = concatenate(asArray(), new Action[]{null});
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            swap(array, selectedIndices[i], selectedIndices[i] + 1);
            selectedIndices[i] += 1;
        }
        this.actions = new ArrayList<>(this.actions.size());
        for (Action action : array) {
            if (action != null) {
                this.actions.add(action);
            }
        }
        return selectedIndices;
    }

    public int[] copy(int[] selectedIndices) {
        Action[] array = asArray();
        for (int selectedIndex : selectedIndices) {
            this.actions.add(array[selectedIndex]);
        }
        int[] newIndices = new int[selectedIndices.length];
        for (int i = 0; i < newIndices.length; i++) {
            newIndices[i] = (array.length + i);
        }
        return newIndices;
    }

    private static <T> void swap(List<T> list, int index, int index2) {
        T t = list.get(index);
        list.set(index, list.get(index2));
        list.set(index2, t);
    }

    private static void swap(Object[] array, int index, int index2) {
        Object t = array[index];
        array[index] = array[index2];
        array[index2] = t;
    }

    private static <T> T[] concatenate(T[] first, T[] second) {
        T[] result = copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

}
