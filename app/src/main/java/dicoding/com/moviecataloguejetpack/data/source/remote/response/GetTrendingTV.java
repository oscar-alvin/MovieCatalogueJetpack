package dicoding.com.moviecataloguejetpack.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTrendingTV {
    @SerializedName("page")
    String status;
    @SerializedName("results")
    List<TVResponse> listTVShow;

    public List<TVResponse> getTrendingTV() {
        return listTVShow;
    }

}
