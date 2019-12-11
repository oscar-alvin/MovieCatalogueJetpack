package dicoding.com.moviecataloguejetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.di.Injection;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailMovieModel;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailTVModel;
import dicoding.com.moviecataloguejetpack.ui.favorite.movie.FavMovieModel;
import dicoding.com.moviecataloguejetpack.ui.favorite.tvshow.FavTVModel;
import dicoding.com.moviecataloguejetpack.ui.movie.FragmentMovieModel;
import dicoding.com.moviecataloguejetpack.ui.tvshow.FragmentTVModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository mMovieRepository;

    private ViewModelFactory(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(FragmentMovieModel.class)) {
            //noinspection unchecked
            return (T) new FragmentMovieModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(FragmentTVModel.class)) {
            //noinspection unchecked
            return (T) new FragmentTVModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieModel.class)) {
            //noinspection unchecked
            return (T) new DetailMovieModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(DetailTVModel.class)) {
            //noinspection unchecked
            return (T) new DetailTVModel(mMovieRepository);
        } else if(modelClass.isAssignableFrom(FavMovieModel.class)){
            //noinspection unchecked
            return (T) new FavMovieModel(mMovieRepository);
        } else if(modelClass.isAssignableFrom(FavTVModel.class)){
            //noinspection unchecked
            return (T) new FavTVModel(mMovieRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
