package uk.co.jpawlak.clicker.actions;

public class FakeAction implements Action {

    private final String name;

    public FakeAction(String name) {
        this.name = name;
    }

    @Override
    public void execute() {}

}
