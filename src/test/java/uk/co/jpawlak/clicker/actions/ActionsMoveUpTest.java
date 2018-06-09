package uk.co.jpawlak.clicker.actions;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static uk.co.jpawlak.clicker.actions.FakeAction.actionsWithElements;
import static uk.co.jpawlak.clicker.actions.FakeAction.indices;

public class ActionsMoveUpTest {

    @Test
    public void movesUpSingleAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.moveUp(indices(2));

        assertThat(actions, sameBeanAs(actionsWithElements(0, 2, 1, 3)));
    }

    @Test
    public void movesUpMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.moveUp(indices(1, 3));

        assertThat(actions, sameBeanAs(actionsWithElements(1, 0, 3, 2)));
    }

    @Test
    public void doesNothingWhenArrayOfIndicesToMoveUpIsEmpty() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.moveUp(indices());

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 2, 3)));
    }

    @Test
    public void doesNotLoseSelectionWhenMovingUpTheTopAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        int[] newIndices = actions.moveUp(indices(0));

        assertThat(newIndices, sameBeanAs(indices(0)));
    }

    @Test
    public void movesUpOnlyThoseActionsWhichAreNotOnTheTopAlready() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4, 5, 6);

        actions.moveUp(indices(0, 1, 4, 6));

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 2, 4, 3, 6, 5)));
    }

    @Test
    public void doesNotLoseSelectionOfTheTopActionsWhenMovingUpMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4, 5, 6);

        int[] newIndices = actions.moveUp(indices(0, 1, 4, 6));

        assertThat(newIndices, sameBeanAs(indices(0, 1, 3, 5)));
    }

}
