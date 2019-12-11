package dicoding.com.moviecataloguejetpack.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVResponse {
    @SerializedName("id")
    private int idTV;
    @SerializedName("original_name")
    private String titleTV;
    @SerializedName("overview")
    private String detailTV;
    @SerializedName("vote_average")
    private float point;
    @SerializedName("poster_path")
    private String posterTV;
    @SerializedName("genres")
    private List<Genre> genreTV;
    @SerializedName("episode_run_time")
    private List<Integer> durasiTV;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("number_of_seasons")
    private int NumberSeasons;
    @SerializedName("popularity")
    private float popularity;

    public TVResponse() {
    }

    public TVResponse(int idTV, String titleTV, String detailTV, float point, String posterTV,
                  List<Genre> genreTV, List<Integer> durasiTV, String firstAirDate, int numberSeasons, float popularity) {
        this.idTV = idTV;
        this.titleTV = titleTV;
        this.detailTV = detailTV;
        this.point = point;
        this.posterTV = posterTV;
        this.genreTV = genreTV;
        this.durasiTV = durasiTV;
        this.firstAirDate = firstAirDate;
        NumberSeasons = numberSeasons;
        this.popularity = popularity;
    }

    public int getIdTV() {
        return idTV;
    }

    public String getTitleTV() {
        return titleTV;
    }

    public String getDetailTV() {
        return detailTV;
    }

    public float getPoint() {
        return point;
    }

    public String getPosterTV() {
        return posterTV;
    }

    public List<Genre> getGenreTV() {
        return genreTV;
    }

    public List<Integer> getDurasiTV() {
        return durasiTV;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public int getNumberSeasons() {
        return NumberSeasons;
    }

    public float getPopularity() {
        return popularity;
    }

}
