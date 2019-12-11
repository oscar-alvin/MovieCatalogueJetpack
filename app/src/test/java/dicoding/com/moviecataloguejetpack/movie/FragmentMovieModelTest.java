package dicoding.com.moviecataloguejetpack.movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.ui.movie.FragmentMovieModel;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FragmentMovieModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieRepository movieRepository = mock(MovieRepository.class);

    private FragmentMovieModel viewModel;

    @Before
    public void setUp() {
        viewModel = new FragmentMovieModel(movieRepository);
    }

    @Test
    public void getMovies() {
        Resource<List<MovieEntity>> dummyMovie = Resource.success(FakeDataDummy.generateDummyMovie());

        MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(movieRepository.getMovies()).thenReturn(movies);

        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getMovielist().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}
