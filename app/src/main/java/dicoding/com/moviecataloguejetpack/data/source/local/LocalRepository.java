package dicoding.com.moviecataloguejetpack.data.source.local;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.room.MovieDao;

public class LocalRepository {
    private final MovieDao movieDao;

    private LocalRepository(MovieDao movieCatalogueDao) {
        this.movieDao = movieCatalogueDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(MovieDao movieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(movieDao);
        }
        return INSTANCE;
    }

    public void insertMovieList(List<MovieEntity> movieEntityList) {
        movieDao.insertMovies(movieEntityList);
    }

    public void updateMovie(MovieEntity newEntity){
        movieDao.updateMovie(newEntity);
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return movieDao.getListMovies();
    }

    public LiveData<MovieEntity> getMovieById(int idmov) {
        return movieDao.getItemMovie(idmov);
    }

    public void insertTvList(List<TvEntity> tvEntityList) {
        movieDao.insertTV(tvEntityList);
    }

    public void updateTv(TvEntity newEntity){
        movieDao.updateTV(newEntity);
    }

    public LiveData<List<TvEntity>> getAllTV() {
        return movieDao.getListTV();
    }

    public LiveData<TvEntity> getTVById(int idtv) {
        return movieDao.getItemTV(idtv);
    }

//    public LiveData<List<MovieEntity>> getFavMovie(){return movieDao.getFavoritMovie(true); }

    public void updateFavMovie(int id, Boolean isFav) { movieDao.updateFavoriteMovie(id, isFav);}

//    public LiveData<List<TvEntity>> getFavTv() { return movieDao.getFavoritTV(true); }

    public void updateFavTv(int id, Boolean isFav) { movieDao.updatetFavoriteTV(id, isFav);}

    public DataSource.Factory<Integer, MovieEntity> getPagedFavMovie(){return movieDao.getPagedFavoritMovie(true); }

    public DataSource.Factory<Integer, TvEntity> getPagedFavTv(){return movieDao.getPagedFavoritTV(true); }
}
