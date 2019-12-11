package dicoding.com.moviecataloguejetpack.ui.favorite.movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.testing.SingleFragmentActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FragFavMovieTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    private FragFavMovie fragFavMovie = new FragFavMovie();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(fragFavMovie);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadMovieFragment() {
        try {
            onView(withId(R.id.list_favorite)).check(matches(isDisplayed()));
        }catch (NoMatchingViewException ne){
            onView(withId(R.id.list_favorite)).check(matches(isDisplayed()));
        }
    }

}