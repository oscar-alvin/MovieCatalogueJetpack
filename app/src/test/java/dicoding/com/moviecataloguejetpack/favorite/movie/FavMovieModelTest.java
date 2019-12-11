package dicoding.com.moviecataloguejetpack.favorite.movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.ui.favorite.movie.FavMovieModel;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavMovieModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieRepository movieRepository = mock(MovieRepository.class);

    private FavMovieModel viewModel;

    @Before
    public void setup(){
        viewModel = new FavMovieModel(movieRepository);
    }

    @Test
    public void getFavMovieList() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> dummyCourse = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(movieRepository.getPagedFavoriteMovie()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getAllPagedMovie().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }
}