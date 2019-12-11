package dicoding.com.moviecataloguejetpack.detail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailMovieModel;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieModel detailMovieModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyMovie().get(0);
    private int movieId = dummyMovie.getIdmovie();
    private Resource<MovieEntity> dummyDetails = Resource.success(FakeDataDummy.getMovie(movieId));

    @Before
    public void setUp() {
        detailMovieModel = new DetailMovieModel(movieRepository);
        detailMovieModel.setIdMovie(movieId);
        detailMovieModel.insertFavMovie(dummyMovie);
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<Resource<MovieEntity>> movieEntities = new MutableLiveData<>();
        movieEntities.setValue(dummyDetails);

        when(movieRepository.getMovContent(movieId)).thenReturn(movieEntities);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);

        detailMovieModel.liveMovie.observeForever(observer);

        verify(observer).onChanged(dummyDetails);
    }
}
