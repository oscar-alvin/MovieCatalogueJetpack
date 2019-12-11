package dicoding.com.moviecataloguejetpack.di;

import android.app.Application;

import dicoding.com.moviecataloguejetpack.data.source.MovieRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.LocalRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.room.MovieDatabase;
import dicoding.com.moviecataloguejetpack.utils.AppExecutors;

public class Injection {

    public static MovieRepository provideRepository(Application application) {

        MovieDatabase database = MovieDatabase.getINSTANCE(application);
        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
//        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();
        return MovieRepository.getInstance(localRepository, appExecutors);
    }
}
