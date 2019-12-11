package dicoding.com.moviecataloguejetpack.data.source.local.room;

import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;

@Dao
public interface MovieDao {
    @WorkerThread
    @Query("select * from movie")
    LiveData<List<MovieEntity>> getListMovies();

    @WorkerThread
    @Query("select * from movie where idmovie = :id")
    LiveData<MovieEntity> getItemMovie(int id);

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MovieEntity> entityList);

    @WorkerThread
    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovie(MovieEntity entity);

    //
    @WorkerThread
    @Query("select * from tvshow")
    LiveData<List<TvEntity>> getListTV();

    @WorkerThread
    @Query("select * from tvshow where idtv = :id")
    LiveData<TvEntity> getItemTV(int id);

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTV(List<TvEntity> entityList);

    @WorkerThread
    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTV(TvEntity entity);

    //Favorit
    @WorkerThread
    @Query("UPDATE movie SET isFavorite = :fav WHERE idmovie = :id")
    void updateFavoriteMovie(int id, Boolean fav);

//    @WorkerThread
//    @Query("select * from movie where isFavorite = :fav")
//    LiveData<List<MovieEntity>> getFavoritMovie(Boolean fav);

    @WorkerThread
    @Query("select * from movie where isFavorite = :fav")
    DataSource.Factory<Integer, MovieEntity> getPagedFavoritMovie(Boolean fav);

//    @WorkerThread
    @Query("UPDATE tvshow SET isFavorite = :fav where idtv = :id")
    void updatetFavoriteTV(int id, Boolean fav);

//    @WorkerThread
//    @Query("select * from tvshow  where isFavorite = :fav")
//    LiveData<List<TvEntity>> getFavoritTV(Boolean fav);

    @WorkerThread
    @Query("select * from tvshow  where isFavorite = :fav")
    DataSource.Factory<Integer, TvEntity> getPagedFavoritTV(Boolean fav);

}
