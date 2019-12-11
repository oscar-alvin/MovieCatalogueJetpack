package dicoding.com.moviecataloguejetpack.ui.favorite.tvshow;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public class FavTVModel extends ViewModel {
    private MovieRepository movieRepository;

    public FavTVModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public void DeleteTvFromFav(TvEntity tvEntity){
        movieRepository.updateFavTV(tvEntity.getIdtv(), tvEntity.getFavorite());
    }

    public LiveData<Resource<PagedList<TvEntity>>> getAllPagedTv(){
        return movieRepository.getPagedFavoriteTv();
    }
}
