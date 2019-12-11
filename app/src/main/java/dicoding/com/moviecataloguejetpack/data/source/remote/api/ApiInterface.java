package dicoding.com.moviecataloguejetpack.data.source.remote.api;

import dicoding.com.moviecataloguejetpack.data.source.remote.response.GetTrendingMovie;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.GetTrendingTV;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.MovieResponse;
import dicoding.com.moviecataloguejetpack.data.source.remote.response.TVResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("{pathmoviedb}/{tipe}/{timewindow}")
    Call<GetTrendingMovie> getTrending(
            @Path("pathmoviedb") String pathmdb,
            @Path("tipe") String tipe,
            @Path("timewindow") String timewindow,
            @Query("api_key") String apimoviedb
    );

    @GET("{pathmoviedb}/{tipe}/{timewindow}")
    Call<GetTrendingTV> getTrendingTV(
            @Path("pathmoviedb") String pathmdb,
            @Path("tipe") String tipe,
            @Path("timewindow") String timewindow,
            @Query("api_key") String apimoviedb
    );

    @GET("{pathmoviedb}/{idmoviedb}")
    Call<MovieResponse> getDetailsMov(
            @Path("pathmoviedb") String pathmdb,
            @Path("idmoviedb") int idmdb,
            @Query("api_key") String apimdb
    );

    @GET("{pathmoviedb}/{idmoviedb}")
    Call<TVResponse> getDetailsTV(
            @Path("pathmoviedb") String pathmdb,
            @Path("idmoviedb") int idmdb,
            @Query("api_key") String apimdb
    );
}
