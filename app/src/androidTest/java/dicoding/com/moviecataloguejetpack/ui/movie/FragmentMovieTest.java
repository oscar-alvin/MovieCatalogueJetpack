package dicoding.com.moviecataloguejetpack.ui.movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;
import dicoding.com.moviecataloguejetpack.EspressoIdlingResource;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.testing.SingleFragmentActivity;
import dicoding.com.moviecataloguejetpack.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FragmentMovieTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    private FragmentMovie fragmentMovie = new FragmentMovie();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourcey());
        activityRule.getActivity().setFragment(fragmentMovie);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @Test
    public void loadMovieFragment() {
        onView(withId(R.id.list_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.list_movie)).check(new RecyclerViewItemCountAssertion(20));
    }
}
