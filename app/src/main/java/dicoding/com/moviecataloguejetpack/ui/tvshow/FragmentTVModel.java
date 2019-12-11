package dicoding.com.moviecataloguejetpack.ui.tvshow;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.vo.Resource;

public class FragmentTVModel extends ViewModel {
    private MovieRepository movieRepository;

    public FragmentTVModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<List<TvEntity>>> getTVlist() {
        return movieRepository.getTvies();
    }
}
