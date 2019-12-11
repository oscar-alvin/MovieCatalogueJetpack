package dicoding.com.moviecataloguejetpack.ui.movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public class FragmentMovieModel extends ViewModel {
    private MovieRepository movieRepository;

    public FragmentMovieModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<List<MovieEntity>>> getMovielist() {
        return movieRepository.getMovies();
    }

}
