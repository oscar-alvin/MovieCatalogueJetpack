package dicoding.com.moviecataloguejetpack.data.source;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import dicoding.com.moviecataloguejetpack.EspressoIdlingResource;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiInterface;
import dicoding.com.moviecataloguejetpack.data.source.local.LocalRepository;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.ApiResponse;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.GetTrendingMovie;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.GetTrendingTV;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.MovieResponse;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.TVResponse;
import dicoding.com.moviecataloguejetpack.vo.Resource;
import dicoding.com.moviecataloguejetpack.utils.AppExecutors;
import dicoding.com.moviecataloguejetpack.utils.MovieHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository implements MovieDataSource {
    private ApiInterface mApiInterface;
    private volatile static MovieRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;

    public MovieRepository(@NonNull LocalRepository localRepository, @NonNull AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.appExecutors = appExecutors;
        mApiInterface = ApiClient.cteateService(ApiInterface.class);
    }

    public static MovieRepository getInstance(@NonNull LocalRepository localRepository, @NonNull AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new MovieRepository(localRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors){
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size()==0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                final MutableLiveData<ApiResponse<List<MovieResponse>>> mutableLiveData = new MutableLiveData<>();
                EspressoIdlingResource.increment();
                Call<GetTrendingMovie> TrendingCall = mApiInterface.getTrending("trending", "movie",
                        "week", ApiClient.MyApi);
                TrendingCall.enqueue(new Callback<GetTrendingMovie>() {
                    @Override
                    public void onResponse(Call<GetTrendingMovie> call, Response<GetTrendingMovie> response) {
                        if (response.isSuccessful()) {
                            List<MovieResponse> responseList = new ArrayList<>();
                            responseList.addAll(response.body().getListTrendingMovies());
                            mutableLiveData.setValue(ApiResponse.success(responseList));
                            if (!EspressoIdlingResource.getEspressoIdlingResourcey().isIdleNow()) {
                                EspressoIdlingResource.decrement();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetTrendingMovie> call, Throwable t) {
                        Log.e("Retrofit Movie Fail", t.getMessage());
                    }
                });
                return mutableLiveData;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                ArrayList<MovieEntity> movList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    MovieResponse responseMov = data.get(i);
                    String genre = responseMov.getGenreMovie() == null ? "" : MovieHelper.List2String(responseMov.getGenreMovie());
                    Log.e("savecall ", genre);
                    MovieEntity movie = new MovieEntity(responseMov.getIdMovie(), responseMov.getTitleMovie(),
                            genre, responseMov.getDurasiMovie(),
                            responseMov.getReleaseMovie(), responseMov.getPopularity(),
                            responseMov.getDetailMovie(), responseMov.getPosterMovie(), "",
                            responseMov.getPoint(), "", "", false);
                    movList.add(movie);
                }
                localRepository.insertMovieList(movList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TvEntity>>> getTvies() {
        return new NetworkBoundResource<List<TvEntity>, List<TVResponse>>(appExecutors){
            @Override
            protected LiveData<List<TvEntity>> loadFromDB() {
                return localRepository.getAllTV();
            }

            @Override
            protected Boolean shouldFetch(List<TvEntity> data) {
                return (data == null) || (data.size()==0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVResponse>>> createCall() {
                final MutableLiveData<ApiResponse<List<TVResponse>>> mutableLiveData = new MutableLiveData<>();

                EspressoIdlingResource.increment();

                Call<GetTrendingTV> TrendingCall = mApiInterface.getTrendingTV("trending", "tv",
                        "week", ApiClient.MyApi);
                TrendingCall.enqueue(new Callback<GetTrendingTV>() {
                    @Override
                    public void onResponse(Call<GetTrendingTV> call, Response<GetTrendingTV>
                            response) {
                        if (response.isSuccessful()) {
                            List<TVResponse> responseList = new ArrayList<>();
                            responseList.addAll(response.body().getTrendingTV());
                            mutableLiveData.setValue(ApiResponse.success(responseList));
                            if (!EspressoIdlingResource.getEspressoIdlingResourcey().isIdleNow()) {
                                EspressoIdlingResource.decrement();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<GetTrendingTV> call, Throwable t) {
                        Log.e("Retrofit TV Fail", t.getMessage());
                    }
                });
                return mutableLiveData;
            }

            @Override
            protected void saveCallResult(List<TVResponse> data) {
                ArrayList<TvEntity> tvList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    TVResponse responseTV = data.get(i);
                    String genre = responseTV.getGenreTV() == null ? "" : MovieHelper.List2String(responseTV.getGenreTV());
                    int durasi = responseTV.getDurasiTV() == null ? 0 : responseTV.getDurasiTV().get(0);
                    Log.e("savecall ", genre+" | "+durasi);
                    TvEntity tv = new TvEntity(responseTV.getIdTV(), responseTV.getTitleTV(), responseTV.getDetailTV(),
                            "", responseTV.getPoint(), "", responseTV.getPosterTV(),
                            genre, durasi, responseTV.getFirstAirDate(),
                            responseTV.getNumberSeasons(), responseTV.getPopularity(), false);

                    tvList.add(tv);
                }
                localRepository.insertTvList(tvList);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovContent(final int idmov) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors){
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieById(idmov);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data.getDurasiMovie()<=0);
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                final MutableLiveData<ApiResponse<MovieResponse>> movieMutableLiveData = new MutableLiveData<>();

                EspressoIdlingResource.increment();

                Call<MovieResponse> MovieCall = mApiInterface.getDetailsMov("movie", idmov, ApiClient.MyApi);//, Locale.getDefault()
                MovieCall.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            MovieResponse movieResponse = response.body();
                            movieMutableLiveData.setValue(ApiResponse.success(movieResponse));

                            if (!EspressoIdlingResource.getEspressoIdlingResourcey().isIdleNow()) {
                                EspressoIdlingResource.decrement();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.e("Retrofit Fail", t.getMessage());
                    }
                });
                return movieMutableLiveData;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {
                String genre = data.getGenreMovie() == null ? "" : MovieHelper.List2String(data.getGenreMovie());
                MovieEntity movie = new MovieEntity(data.getIdMovie(), data.getTitleMovie(),
                        genre, data.getDurasiMovie(),
                        data.getReleaseMovie(), data.getPopularity(),
                        data.getDetailMovie(), data.getPosterMovie(), "",
                        data.getPoint(), "", "", false);
                localRepository.updateMovie(movie);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvEntity>> getTvContent(final int idtv) {
        return new NetworkBoundResource<TvEntity, TVResponse>(appExecutors){
            @Override
            protected LiveData<TvEntity> loadFromDB() {
                return localRepository.getTVById(idtv);
            }

            @Override
            protected Boolean shouldFetch(TvEntity data) {
                return (data.getDurasitv()<=0);
            }

            @Override
            protected LiveData<ApiResponse<TVResponse>> createCall() {
                final MutableLiveData<ApiResponse<TVResponse>> tvShowMutableLiveData = new MutableLiveData<>();

                EspressoIdlingResource.increment();

                Call<TVResponse> TvCall = mApiInterface.getDetailsTV("tv", idtv, ApiClient.MyApi);//Locale.getDefault()
                TvCall.enqueue(new Callback<TVResponse>() {
                    @Override
                    public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                        if (response.isSuccessful()) {
                            TVResponse tvResponse = response.body();
                            tvShowMutableLiveData.setValue(ApiResponse.success(tvResponse));
                            if (!EspressoIdlingResource.getEspressoIdlingResourcey().isIdleNow()) {
                                EspressoIdlingResource.decrement();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TVResponse> call, Throwable t) {
                        Log.e("Retrofit Fail", t.getMessage());
                    }
                });
                return tvShowMutableLiveData;
            }

            @Override
            protected void saveCallResult(TVResponse data) {
                String genre = data.getGenreTV() == null ? "" : MovieHelper.List2String(data.getGenreTV());
                int durasi = data.getDurasiTV() == null ? 0 : data.getDurasiTV().get(0);
                TvEntity tv = new TvEntity(data.getIdTV(), data.getTitleTV(), data.getDetailTV(),
                        "", data.getPoint(), "", data.getPosterTV(),
                        genre, durasi, data.getFirstAirDate(),
                        data.getNumberSeasons(), data.getPopularity(), false);
                localRepository.updateTv(tv);
            }
        }.asLiveData();

    }

    @Override
    public void updateFavMovie(int id, Boolean isFav){
        new AppExecutors().diskIO().execute(()-> {
            localRepository.updateFavMovie(id, isFav);
        });
    }

    @Override
    public void updateFavTV(int id, Boolean isFav){
        new AppExecutors().diskIO().execute(() ->{
            localRepository.updateFavTv(id, isFav);
        });
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getPagedFavoriteMovie() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors){
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getPagedFavMovie(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> getPagedFavoriteTv() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<TVResponse>>(appExecutors){
            @Override
            protected LiveData<PagedList<TvEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getPagedFavTv(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVResponse> data) {
            }
        }.asLiveData();
    }
}
