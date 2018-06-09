package uk.co.jpawlak.clicker.actions;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static uk.co.jpawlak.clicker.actions.FakeAction.actionsWithElements;
import static uk.co.jpawlak.clicker.actions.FakeAction.indices;

public class ActionsDeleteTest {

    @Test
    public void deletesSingleAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.deleteActions(indices(2));

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 3)));
    }

    @Test
    public void deletesMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4, 5, 6);

        actions.deleteActions(indices(0, 3, 6));

        assertThat(actions, sameBeanAs(actionsWithElements(1, 2, 4, 5)));
    }

    @Test
    public void doesNothingWhenArrayOfIndicesToDeleteIsEmpty() {
        Actions actions = actionsWithElements(0, 1);

        actions.deleteActions(indices());

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1)));
    }

}
