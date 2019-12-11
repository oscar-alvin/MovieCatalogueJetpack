package dicoding.com.moviecataloguejetpack.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("genre_ids")
    private int id_genre;
    @SerializedName("name")
    private String genre;

    public Genre() {
    }

    public Genre(int id_genre, String genre) {
        this.id_genre = id_genre;
        this.genre = genre;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
