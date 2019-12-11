package dicoding.com.moviecataloguejetpack.detail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailTVModel;
import dicoding.com.moviecataloguejetpack.utils.FakeDataDummy;
import dicoding.com.moviecataloguejetpack.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTVModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTVModel detailTVModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private TvEntity dummyTV = FakeDataDummy.generateDummyTV().get(0);
    private int tvId = dummyTV.getIdtv();
    private Resource<TvEntity> dummyDetails = Resource.success(FakeDataDummy.getTVshow(tvId));


    @Before
    public void setUp() {
        detailTVModel = new DetailTVModel(movieRepository);
        detailTVModel.setIdtv(tvId);
        detailTVModel.insertFavTV(dummyTV);
    }

    @Test
    public void getDetailTV(){
        MutableLiveData<Resource<TvEntity>> tvEntities = new MutableLiveData<>();
        tvEntities.setValue(dummyDetails);

        when(movieRepository.getTvContent(tvId)).thenReturn(tvEntities);

        Observer<Resource<TvEntity>> observer = mock(Observer.class);

        detailTVModel.getTvShowById.observeForever(observer);

        verify(observer).onChanged(dummyDetails);
    }
}
