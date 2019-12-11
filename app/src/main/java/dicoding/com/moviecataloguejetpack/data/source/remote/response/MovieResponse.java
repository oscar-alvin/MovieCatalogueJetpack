package dicoding.com.moviecataloguejetpack.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse{
    @SerializedName("id")
    private int idMovie;
    @SerializedName("original_title")
    private String titleMovie;
    @SerializedName("genres")
    private List<Genre> genreMovie;
    @SerializedName("runtime")
    private int durasiMovie;
    @SerializedName("release_date")
    private String releaseMovie;
    @SerializedName("popularity")
    private Float popularity;
    @SerializedName("overview")
    private String detailMovie;
    @SerializedName("poster_path")
    private String posterMovie;
    @SerializedName("vote_average")
    private float point;

    public MovieResponse(int idMovie, String titleMovie, List<Genre> genreMovie, int durasiMovie, String releaseMovie,
                 Float popularity, String detailMovie, String posterMovie, float point) {
        this.idMovie = idMovie;
        this.titleMovie = titleMovie;
        this.genreMovie = genreMovie;
        this.durasiMovie = durasiMovie;
        this.releaseMovie = releaseMovie;
        this.popularity = popularity;
        this.detailMovie = detailMovie;
        this.posterMovie = posterMovie;
        this.point = point;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public List<Genre> getGenreMovie() {
        return genreMovie;
    }

    public int getDurasiMovie() {
        return durasiMovie;
    }

    public String getReleaseMovie() {
        return releaseMovie;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getDetailMovie() {
        return detailMovie;
    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public float getPoint() {
        return point;
    }


//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.idMovie);
//        dest.writeString(this.titleMovie);
//        dest.writeList(this.genreMovie);
//        dest.writeInt(this.durasiMovie);
//        dest.writeString(this.releaseMovie);
//        dest.writeValue(this.popularity);
//        dest.writeString(this.detailMovie);
//        dest.writeInt(this.posterMovie);
//        dest.writeFloat(this.point);
//    }
//
//    protected MovieResponse(Parcel in) {
//        this.idMovie = in.readInt();
//        this.titleMovie = in.readString();
//        this.genreMovie = in.readArrayList();
//        this.durasiMovie = in.readInt();
//        this.releaseMovie = in.readString();
//        this.popularity = (Float) in.readValue(Float.class.getClassLoader());
//        this.detailMovie = in.readString();
//        this.posterMovie = in.readInt();
//        this.point = in.readFloat();
//    }
//
//    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
//        @Override
//        public MovieResponse createFromParcel(Parcel source) {
//            return new MovieResponse(source);
//        }
//
//        @Override
//        public MovieResponse[] newArray(int size) {
//            return new MovieResponse[size];
//        }
//    };
}
