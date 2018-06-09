package uk.co.jpawlak.clicker.actions;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static uk.co.jpawlak.clicker.actions.FakeAction.actionsWithElements;
import static uk.co.jpawlak.clicker.actions.FakeAction.indices;

public class ActionsCopyTest {

    @Test
    public void copiesSingleAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.copy(indices(2));

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 2, 3, 2)));
    }

    @Test
    public void copiesMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.copy(indices(0, 1, 3));

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 2, 3, 0, 1, 3)));
    }

    @Test
    public void doesNothingWhenArrayOfIndicesToCopyIsEmpty() {
        Actions actions = actionsWithElements(0, 1);

        actions.copy(indices());

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1)));
    }

    @Test
    public void selectsNewlyAddedActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        int[] newIndices = actions.copy(indices(1, 3));

        assertThat(newIndices, sameBeanAs(indices(4, 5)));
    }

}
