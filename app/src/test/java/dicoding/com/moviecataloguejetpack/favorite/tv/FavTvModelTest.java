package dicoding.com.moviecataloguejetpack.favorite.tv;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.ui.favorite.tvshow.FavTVModel;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavTvModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieRepository movieRepository = mock(MovieRepository.class);

    private FavTVModel viewModel;

    @Before
    public void setup(){
        viewModel = new FavTVModel(movieRepository);
    }

    @Test
    public void getFavMovieList() {
        MutableLiveData<Resource<PagedList<TvEntity>>> dummyCourse = new MutableLiveData<>();
        PagedList<TvEntity> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(movieRepository.getPagedFavoriteTv()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<TvEntity>>> observer = mock(Observer.class);

        viewModel.getAllPagedTv().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
    }

}