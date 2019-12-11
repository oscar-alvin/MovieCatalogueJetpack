package dicoding.com.moviecataloguejetpack.data.source;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.local.LocalRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;
import dicoding.com.moviecataloguejetpack.utils.InstantAppExecutors;
import dicoding.com.moviecataloguejetpack.utils.LiveDataTestUtil;
import dicoding.com.moviecataloguejetpack.utils.PagedListUtil;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository local = mock(LocalRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeMovieRepository fakeMovieRepository = new FakeMovieRepository(local, instantAppExecutors);

    private ArrayList<MovieEntity> movieResponses = FakeDataDummy.generateDummyMovie();
    private int movId = movieResponses.get(0).getIdmovie();
    private ArrayList<TvEntity> tvResponses = FakeDataDummy.generateDummyTV();
    private int tvId = tvResponses.get(0).getIdtv();
    private MovieEntity contentMovRes = FakeDataDummy.getMovie(movId);
    private TvEntity contentTVRes = FakeDataDummy.getTVshow(tvId);

    @Test
    public void getListMovies() {
        MutableLiveData<List<MovieEntity>> liveDataMovie = new MutableLiveData<>();
        liveDataMovie.setValue(FakeDataDummy.generateDummyMovie());

        when(local.getAllMovies()).thenReturn(liveDataMovie);
        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(fakeMovieRepository.getMovies());
        verify(local).getAllMovies();
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.data);
        assertNotNull(liveDataMovie);
        assertEquals(movieResponses.size(), result.data.size());
    }

    @Test
    public void getListTvies(){
        MutableLiveData<List<TvEntity>> liveDataTv = new MutableLiveData<>();
        liveDataTv.setValue(FakeDataDummy.generateDummyTV());

        when(local.getAllTV()).thenReturn(liveDataTv);
        Resource<List<TvEntity>> result = LiveDataTestUtil.getValue(fakeMovieRepository.getTvies());
        verify(local).getAllTV();
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.data);
        Assert.assertNotNull(liveDataTv);
        Assert.assertEquals(tvResponses.size(), result.data.size());
    }

    @Test
    public void getDetailMovies(){
        MutableLiveData<MovieEntity> liveData = new MutableLiveData<>();
        liveData.setValue(FakeDataDummy.getMovie(423204));

        when(local.getMovieById(423204)).thenReturn(liveData);
        Resource<MovieEntity> movie = LiveDataTestUtil.getValue(fakeMovieRepository.getMovContent(423204));
        verify(local).getMovieById(423204);
        assertNotNull(movie);
        assertNotNull(movie.data);
        assertEquals(contentMovRes.getDetailMovie(), movie.data.getDetailMovie());
    }

    @Test
    public void getDetailTV(){
        MutableLiveData<TvEntity> liveData = new MutableLiveData<>();
        liveData.setValue(FakeDataDummy.getTVshow(82856));

        when(local.getTVById(82856)).thenReturn(liveData);
        Resource<TvEntity> tv = LiveDataTestUtil.getValue(fakeMovieRepository.getTvContent(82856));
        verify(local).getTVById(82856);
        assertNotNull(tv);
        assertNotNull(tv.data);
        assertEquals(contentTVRes.getDetailtv(), tv.data.getDetailtv());
    }

    @Test
    public void getListFavMovies() {
        List<MovieEntity> listFavMov = FakeDataDummy.getFavMovie();
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getPagedFavMovie()).thenReturn(dataSourceFactory);
        fakeMovieRepository.getPagedFavoriteMovie();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(listFavMov));

        verify(local).getPagedFavMovie();
        Assert.assertNotNull(result.data);
        assertNotNull(listFavMov);
        assertEquals(listFavMov.size(), result.data.size());
    }

    @Test
    public void getListFavTvies(){
        List<TvEntity> listFavTv = FakeDataDummy.getFavTV();
        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getPagedFavTv()).thenReturn(dataSourceFactory);
        fakeMovieRepository.getPagedFavoriteTv();
        Resource<PagedList<TvEntity>> result = Resource.success(PagedListUtil.mockPagedList(listFavTv));

        verify(local).getPagedFavTv();
        Assert.assertNotNull(result.data);
        assertNotNull(listFavTv);
        assertEquals(listFavTv.size(), result.data.size());
    }
}
