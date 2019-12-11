package dicoding.com.moviecataloguejetpack.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import dicoding.com.moviecataloguejetpack.EspressoIdlingResource;
import dicoding.com.moviecataloguejetpack.MainCatalogue;
import dicoding.com.moviecataloguejetpack.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainCatalogueTest {
    @Rule
    public ActivityTestRule<MainCatalogue> activityTestRule = new ActivityTestRule<>(MainCatalogue.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @Test
    public void toDetailMovieActivityTest() {
        onView(ViewMatchers.withId(R.id.list_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.list_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()));
    }

    @Test
    public void toDetailTVActivityTest() {
        onView(withId(R.id.navigation_tvshow)).perform(click());
        onView(withId(R.id.list_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_tv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tv_firstAirTV)).check(matches(isDisplayed()));
    }
}
