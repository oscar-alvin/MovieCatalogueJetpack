package dicoding.com.moviecataloguejetpack.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="movie")
public class MovieEntity {
    @PrimaryKey
    @NonNull
    private int idmovie;
    private String titleMovie;
    private String genreMovie;
    private int durasiMovie;
    private String releaseMovie;
    private Float popularity;
    private String detailMovie;
    private String posterMovie;
    private String backdropMovie;
    private float point;
    private String homepage;
    private String status;
    private Boolean isFavorite;

    public MovieEntity(int idmovie, String titleMovie, String genreMovie, int durasiMovie, String releaseMovie, Float popularity, String detailMovie, String posterMovie, String backdropMovie, float point, String homepage, String status, Boolean isFavorite) {
        this.idmovie = idmovie;
        this.titleMovie = titleMovie;
        this.genreMovie = genreMovie;
        this.durasiMovie = durasiMovie;
        this.releaseMovie = releaseMovie;
        this.popularity = popularity;
        this.detailMovie = detailMovie;
        this.posterMovie = posterMovie;
        this.backdropMovie = backdropMovie;
        this.point = point;
        this.homepage = homepage;
        this.status = status;
        this.isFavorite = isFavorite;
    }

    public int getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(int idmovie) {
        this.idmovie = idmovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getGenreMovie() {
        return genreMovie;
    }

    public void setGenreMovie(String genreMovie) {
        this.genreMovie = genreMovie;
    }

    public int getDurasiMovie() {
        return durasiMovie;
    }

    public void setDurasiMovie(int durasiMovie) {
        this.durasiMovie = durasiMovie;
    }

    public String getReleaseMovie() {
        return releaseMovie;
    }

    public void setReleaseMovie(String releaseMovie) {
        this.releaseMovie = releaseMovie;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getDetailMovie() {
        return detailMovie;
    }

    public void setDetailMovie(String detailMovie) {
        this.detailMovie = detailMovie;
    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public void setPosterMovie(String posterMovie) {
        this.posterMovie = posterMovie;
    }

    public String getBackdropMovie() {
        return backdropMovie;
    }

    public void setBackdropMovie(String backdropMovie) {
        this.backdropMovie = backdropMovie;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
