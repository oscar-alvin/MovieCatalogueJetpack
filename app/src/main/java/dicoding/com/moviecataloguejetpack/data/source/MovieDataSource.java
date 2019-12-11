package dicoding.com.moviecataloguejetpack.data.source;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public interface MovieDataSource {

    LiveData<Resource<List<MovieEntity>>> getMovies();

    LiveData<Resource<List<TvEntity>>> getTvies();

    LiveData<Resource<MovieEntity>> getMovContent(int idmov);

    LiveData<Resource<TvEntity>> getTvContent(int idtv);

//    LiveData<Resource<List<MovieEntity>>> getFavMovie();

    void updateFavMovie(int id, Boolean isFav);

//    LiveData<Resource<List<TvEntity>>> getFavTV();

    void updateFavTV(int id, Boolean isFav);

    LiveData<Resource<PagedList<MovieEntity>>> getPagedFavoriteMovie();

    LiveData<Resource<PagedList<TvEntity>>> getPagedFavoriteTv();
}
