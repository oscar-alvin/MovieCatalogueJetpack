package dicoding.com.moviecataloguejetpack.ui.detail;

import android.content.Context;
import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;
import dicoding.com.moviecataloguejetpack.EspressoIdlingResource;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailMovieTest {
    private MovieEntity dummyMovie = FakeDataDummy.getMovie(423204);

    @Rule
    public ActivityTestRule<DetailMovie> activityRule = new ActivityTestRule<DetailMovie>(DetailMovie.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailMovie.class);
            result.putExtra(DetailMovie.EXTRA_IDMOV, dummyMovie.getIdmovie());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @Test
    public void loadDetailMovie() {
        onView(withId(R.id.toolbarMov)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_tahun)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_posterMov)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_durasi)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_ketm)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_point)).check(matches(isDisplayed()));
    }
}
