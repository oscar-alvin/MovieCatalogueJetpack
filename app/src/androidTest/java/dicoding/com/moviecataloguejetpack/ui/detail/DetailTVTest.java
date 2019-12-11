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
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailTVTest {
    private Context context;
    private TvEntity dummyTVshow = FakeDataDummy.getTVshow(82856);

    @Rule
    public ActivityTestRule<DetailTV> activityRule = new ActivityTestRule<DetailTV>(DetailTV.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            context = targetContext;
            Intent result = new Intent(targetContext, DetailTV.class);
            result.putExtra(DetailTV.EXTRA_IDTV, dummyTVshow.getIdtv());
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
    public void LoadDetailTV(){
        onView(withId(R.id.toolbarTV)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_posterTV)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_genreTV)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_durasiTV)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_firstAirTV)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_ketmTV)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_seasonsTV)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_pointTV)).check(matches(isDisplayed()));
    }
}
