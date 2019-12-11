package dicoding.com.moviecataloguejetpack.ui.tvshow;

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

public class FragmentTVTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    private FragmentTV fragmentTV = new FragmentTV();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourcey());
        activityRule.getActivity().setFragment(fragmentTV);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @Test
    public void loadTVFragment() {
        onView(withId(R.id.list_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.list_tv)).check(new RecyclerViewItemCountAssertion(20));
    }
}
