
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITE_ITEMS_COUNT = 2;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> mNeighbours;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours),
            withParent(allOf(withId(R.id.container),
                    childAtPosition(
                            withId(R.id.main_content),
                            1))),
            isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 1
        onView(allOf(withId(R.id.list_neighbours),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }


    @Test
    public void myNeighbourList_detailAction_shouldDisplay() {
        onView(allOf(withId(R.id.item_list_name),
            childAtPosition(
                    childAtPosition(
                            withId(R.id.list_neighbours),
                            0),
                    1),
            isDisplayed())).perform(click());
        onView(withId(R.id.id_nom2)).check(matches(isDisplayed()));
    }

    @Test
    public void myNeighbourList_onlyFavoriteNeighbour_shouldDisplay() {
        onView(allOf(withContentDescription("Favorites"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        1),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.list_neighbours),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).check(withItemCount(FAVORITE_ITEMS_COUNT));
    }


    @Test
    public void addNeighbour_inFavoriteList() {
        onView(withText("Caroline")).perform(click());

        onView(allOf(withId(R.id.imageButtonStar),
                childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        4),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.backButton),
                childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        1),
                isDisplayed())).perform(click());

        onView(allOf(withContentDescription("Favorites"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        1),
                isDisplayed())).perform(click());

       onView(allOf(withId(R.id.list_neighbours),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).check(withItemCount(FAVORITE_ITEMS_COUNT+1));
    }

    @Test
    public void removeNeighbour_inFavoriteList() {
        onView(allOf(withContentDescription("Favorites"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.tabs),
                                0),
                        1),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.item_list_delete_button),
                childAtPosition(
                        allOf(withId(R.id.item_list_neighbour),
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        0)),
                        2),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.list_neighbours),
                withParent(allOf(withId(R.id.container),
                        childAtPosition(
                                withId(R.id.main_content),
                                1))),
                isDisplayed())).check(withItemCount(FAVORITE_ITEMS_COUNT-1));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}