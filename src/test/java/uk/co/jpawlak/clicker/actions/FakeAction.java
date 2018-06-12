package uk.co.jpawlak.clicker.actions;

public class FakeAction implements Action {

    @SuppressWarnings("FieldCanBeLocal") // used by shazamcrest
    private final String name;

    private FakeAction(String name) {
        this.name = name;
    }

    @Override
    public void execute() {}

    @Override
    public String toString() {
        return name;
    }

    static Actions actionsWithElements(int... elementsNames) {
        Actions actions = new Actions();
        for (int elementName : elementsNames) {
            actions.add(new FakeAction(String.valueOf(elementName)));
        }
        return actions;
    }

    static int[] indices(int... indices) {
        return indices;
    }

}
