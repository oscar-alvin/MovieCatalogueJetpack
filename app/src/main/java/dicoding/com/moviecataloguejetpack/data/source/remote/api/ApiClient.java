package dicoding.com.moviecataloguejetpack.data.source.remote.api;

import dicoding.com.moviecataloguejetpack.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String PosterUrl = "https://image.tmdb.org/t/p/w185";
    public static final String MyApi = BuildConfig.TMDB_API_KEY;

    private static Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
