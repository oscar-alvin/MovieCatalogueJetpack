package dicoding.com.moviecataloguejetpack.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public class DetailMovieModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<Integer> idMovie = new MutableLiveData<>();

    public DetailMovieModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<MovieEntity>> liveMovie = Transformations.switchMap(idMovie,
            mIdmovie -> movieRepository.getMovContent(mIdmovie));

    public void insertFavMovie(MovieEntity movieEntity){
        movieRepository.updateFavMovie(movieEntity.getIdmovie(), movieEntity.getFavorite());
    }

    public void setIdMovie(int id){
        idMovie.setValue(id);
    }

}
