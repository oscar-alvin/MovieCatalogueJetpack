package dicoding.com.moviecataloguejetpack.tvshow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.ui.tvshow.FragmentTVModel;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FragmentTVModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private FragmentTVModel viewmodel;

    @Before
    public void setup() {
        viewmodel = new FragmentTVModel(movieRepository);
    }

    @Test
    public void getTVies() {
        Resource<List<TvEntity>> dummyTV = Resource.success(FakeDataDummy.generateDummyTV());

        MutableLiveData<Resource<List<TvEntity>>> tvies = new MutableLiveData<>();
        tvies.setValue(dummyTV);

        when(movieRepository.getTvies()).thenReturn(tvies);

        Observer<Resource<List<TvEntity>>> observer = mock(Observer.class);

        viewmodel.getTVlist().observeForever(observer);

        verify(observer).onChanged(dummyTV);
    }
}
