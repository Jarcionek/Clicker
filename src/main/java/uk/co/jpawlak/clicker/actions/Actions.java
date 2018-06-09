package uk.co.jpawlak.clicker.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Actions {

    private final List<Action> actions = new ArrayList<>();

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
        validate(selectedIndices);

        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            actions.remove(selectedIndices[i]);
        }
    }

    public int[] moveUp(int[] selectedIndices) {
        validate(selectedIndices);

        List<Action> selectedActions = stream(selectedIndices).mapToObj(actions::get).collect(toList());

        actions.add(0, null);
        for (int selectedIndex : selectedIndices) {
            swap(actions, selectedIndex);
        }
        actions.remove(null);

        return selectedActions.stream().mapToInt(actions::indexOf).toArray();
    }

    public int[] moveDown(int[] selectedIndices) {
        validate(selectedIndices);

        List<Action> selectedActions = stream(selectedIndices).mapToObj(actions::get).collect(toList());

        actions.add(null);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            swap(actions, selectedIndices[i]);
        }
        actions.remove(null);

        return selectedActions.stream().mapToInt(actions::indexOf).toArray();
    }

    public int[] copy(int[] selectedIndices) {
        validate(selectedIndices);

        for (int selectedIndex : selectedIndices) {
            actions.add(actions.get(selectedIndex));
        }

        return IntStream.range(actions.size() - selectedIndices.length, actions.size()).toArray();
    }


    private static void validate(int[] selectedIndices) {
        for (int i = 1; i < selectedIndices.length; i++) {
            if (selectedIndices[i] <= selectedIndices[i - 1]) {
                throw new IllegalArgumentException("Array expected to be sorted, with no repetitions, was " + Arrays.toString(selectedIndices));
            }
        }
    }

    private static <T> void swap(List<T> list, int index) {
        T t = list.get(index);
        list.set(index, list.get(index + 1));
        list.set(index + 1, t);
    }

}
