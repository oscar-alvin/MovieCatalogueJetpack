package dicoding.com.moviecataloguejetpack.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTrendingMovie {
    @SerializedName("page")
    String status;
    @SerializedName("results")
    List<MovieResponse> listMovies;

    public List<MovieResponse> getListTrendingMovies() {
        return listMovies;
    }

}
