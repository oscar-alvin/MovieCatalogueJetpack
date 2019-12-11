package dicoding.com.moviecataloguejetpack.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public class DetailTVModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<Integer> idtv = new MutableLiveData<>();

    public DetailTVModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<TvEntity>> getTvShowById = Transformations.switchMap(idtv,
            mIdtv -> movieRepository.getTvContent(mIdtv));

    public void insertFavTV(TvEntity tvEntity){
        movieRepository.updateFavTV(tvEntity.getIdtv(), tvEntity.getFavorite());
    }

    public void setIdtv(int id){
        idtv.setValue(id);
    }
}
