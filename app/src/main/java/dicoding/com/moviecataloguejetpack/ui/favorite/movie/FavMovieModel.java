package dicoding.com.moviecataloguejetpack.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public class FavMovieModel extends ViewModel {
    private MovieRepository movieRepository;

    public FavMovieModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public void DeleteMovieFromFav(MovieEntity movieEntity) {
        movieRepository.updateFavMovie(movieEntity.getIdmovie(), movieEntity.getFavorite());
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getAllPagedMovie() {
        return movieRepository.getPagedFavoriteMovie();
    }
}
