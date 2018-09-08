package uk.co.jpawlak.clicker.actions;

import static uk.co.jpawlak.clicker.actions.RobotSingleton.robot;

public class MouseMoveAction implements Action {

    private final int x;
    private final int y;

    public MouseMoveAction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void execute() {
        robot().mouseMove(x, y);
    }

}
