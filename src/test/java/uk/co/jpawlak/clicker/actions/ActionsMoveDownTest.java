package uk.co.jpawlak.clicker.actions;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static uk.co.jpawlak.clicker.actions.FakeAction.actionsWithElements;
import static uk.co.jpawlak.clicker.actions.FakeAction.indices;

public class ActionsMoveDownTest {

    @Test
    public void movesDownSingleAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.moveDown(indices(1));

        assertThat(actions, sameBeanAs(actionsWithElements(0, 2, 1, 3)));
    }

    @Test
    public void movesDownMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4);

        actions.moveDown(indices(0, 1, 3));

        assertThat(actions, sameBeanAs(actionsWithElements(2, 0, 1, 4, 3)));
    }

    @Test
    public void doesNothingWhenArrayOfIndicesToMoveDownIsEmpty() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.moveDown(indices());

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 2, 3)));
    }

    @Test
    public void doesNotLoseSelectionWhenMovingDownTheBottomAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        int[] newIndices = actions.moveDown(indices(3));

        assertThat(newIndices, sameBeanAs(indices(3)));
    }

    @Test
    public void movesDownOnlyThoseActionsWhichAreNotOnTheBottomAlready() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4, 5, 6);

        actions.moveDown(indices(0, 3, 5, 6));

        assertThat(actions, sameBeanAs(actionsWithElements(1, 0, 2, 4, 3, 5, 6)));
    }

    @Test
    public void doesNotLoseSelectionOfTheBottomActionsWhenMovingDownMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4, 5, 6);

        int[] newIndices = actions.moveDown(indices(0, 2, 5, 6));

        assertThat(newIndices, sameBeanAs(indices(1, 3, 5, 6)));
    }

}
