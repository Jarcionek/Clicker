package uk.co.jpawlak.clicker.actions;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class ActionsTest {

    @Test
    public void deletesSingleAction() {
        Actions actions = actionsWithElements(0, 1, 2, 3);

        actions.deleteActions(new int[] {2});

        assertThat(actions, sameBeanAs(actionsWithElements(0, 1, 3)));
    }

    @Test
    public void deletesMultipleActions() {
        Actions actions = actionsWithElements(0, 1, 2, 3, 4, 5, 6);

        actions.deleteActions(new int[] {0, 3, 6});

        assertThat(actions, sameBeanAs(actionsWithElements(1, 2, 4, 5)));
    }


    private static Actions actionsWithElements(int... elementsNames) {
        Actions actions = new Actions();
        for (int elementName : elementsNames) {
            actions.add(new FakeAction(String.valueOf(elementName)));
        }
        return actions;
    }

}