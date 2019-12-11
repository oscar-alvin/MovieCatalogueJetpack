package dicoding.com.moviecataloguejetpack.ui.favorite;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;
import dicoding.com.moviecataloguejetpack.EspressoIdlingResource;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.testing.SingleFragmentActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FragmentFavoriteTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    private FragmentFavorite fragFavorite = new FragmentFavorite();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourcey());
        activityRule.getActivity().setFragment(fragFavorite);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @Test
    public void loadMovieFragment() {
        onView(withId(R.id.viewpager)).check(matches(isDisplayed()));
    }
}