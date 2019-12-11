package dicoding.com.moviecataloguejetpack.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshow")
public class TvEntity {
    @PrimaryKey
    @NonNull
    private int idtv;
    private String titletv;
    private String detailtv;
    private String backDroptv;
    private float pointtv;
    private String homepagetv;
    private String postertv;
    private String genretv;
    private int durasitv;
    private String firstAirDatetv;
    private int NumberSeasonstv;
    private float popularitytv;
    private Boolean isFavorite;

    public TvEntity(){
    }

    public TvEntity(int idtv, String titletv, String detailtv, String backDroptv, float pointtv, String homepagetv, String postertv, String genretv, int durasitv, String firstAirDatetv, int numberSeasonstv, float popularitytv, Boolean isFavorite) {
        this.idtv = idtv;
        this.titletv = titletv;
        this.detailtv = detailtv;
        this.backDroptv = backDroptv;
        this.pointtv = pointtv;
        this.homepagetv = homepagetv;
        this.postertv = postertv;
        this.genretv = genretv;
        this.durasitv = durasitv;
        this.firstAirDatetv = firstAirDatetv;
        NumberSeasonstv = numberSeasonstv;
        this.popularitytv = popularitytv;
        this.isFavorite = isFavorite;
    }

    public int getIdtv() {
        return idtv;
    }

    public void setIdtv(int idtv) {
        this.idtv = idtv;
    }

    public String getTitletv() {
        return titletv;
    }

    public void setTitletv(String titletv) {
        this.titletv = titletv;
    }

    public String getDetailtv() {
        return detailtv;
    }

    public void setDetailtv(String detailtv) {
        this.detailtv = detailtv;
    }

    public String getBackDroptv() {
        return backDroptv;
    }

    public void setBackDroptv(String backDroptv) {
        this.backDroptv = backDroptv;
    }

    public float getPointtv() {
        return pointtv;
    }

    public void setPointtv(float pointtv) {
        this.pointtv = pointtv;
    }

    public String getHomepagetv() {
        return homepagetv;
    }

    public void setHomepagetv(String homepagetv) {
        homepagetv = homepagetv;
    }

    public String getPostertv() {
        return postertv;
    }

    public void setPostertv(String postertv) {
        this.postertv = postertv;
    }

    public String getGenretv() {
        return genretv;
    }

    public void setGenretv(String genretv) {
        this.genretv = genretv;
    }

    public int getDurasitv() {
        return durasitv;
    }

    public void setDurasitv(int durasitv) {
        this.durasitv = durasitv;
    }

    public String getFirstAirDatetv() {
        return firstAirDatetv;
    }

    public void setFirstAirDatetv(String firstAirDatetv) {
        this.firstAirDatetv = firstAirDatetv;
    }

    public int getNumberSeasonstv() {
        return NumberSeasonstv;
    }

    public void setNumberSeasonstv(int numberSeasonstv) {
        NumberSeasonstv = numberSeasonstv;
    }

    public float getPopularitytv() {
        return popularitytv;
    }

    public void setPopularitytv(float popularitytv) {
        this.popularitytv = popularitytv;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
