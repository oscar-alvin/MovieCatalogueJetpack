package dicoding.com.moviecataloguejetpack.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;
import dicoding.com.moviecataloguejetpack.utils.MovieHelper;
import dicoding.com.moviecataloguejetpack.viewmodel.ViewModelFactory;

public class DetailTV extends AppCompatActivity {
    private ImageView posterTV;
    private TextView seasons, genre, durasi, firstAir, sinopsis, point;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DetailTVModel detailTVModel;
    public static final String EXTRA_IDTV = "extra_idtv";
    private Boolean add2Fav;
    private TvEntity toSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tv);

        Toolbar toolbar = findViewById(R.id.toolbarTV);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        posterTV = findViewById(R.id.iv_posterTV);
        seasons = findViewById(R.id.tv_seasonsTV);
        genre = findViewById(R.id.tv_genreTV);
        durasi = findViewById(R.id.tv_durasiTV);
        firstAir = findViewById(R.id.tv_firstAirTV);
        sinopsis = findViewById(R.id.tv_ketmTV);
        point = findViewById(R.id.tv_pointTV);

        final int idtv = getIntent().getIntExtra(DetailTV.EXTRA_IDTV, 0);

        detailTVModel = obtainViewModel(this);
        detailTVModel.setIdtv(idtv);
        detailTVModel.getTvShowById.observe(this, tvEntityResource -> {
            if (tvEntityResource.data != null) setDataTV(tvEntityResource.data);
        });

    }

    void setDataTV(final TvEntity tvShow) {
        toSave = tvShow;
        collapsingToolbarLayout.setTitle(tvShow.getTitletv());
        Glide.with(this).load(ApiClient.PosterUrl + tvShow.getPostertv())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_dashboard_black_24dp))
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(posterTV);
        if (!tvShow.getGenretv().equals(""))
            genre.setText(tvShow.getGenretv());
        else genre.setText("Memuat data...");
        if (tvShow.getDurasitv() > 0)
            durasi.setText(MovieHelper.convertDuration2String(this, tvShow.getDurasitv()));
        else durasi.setText("Memuat data...");
        if (tvShow.getNumberSeasonstv() > 0)
            seasons.setText(String.valueOf(tvShow.getNumberSeasonstv()));
        else seasons.setText("Memuat data...");

        firstAir.setText(tvShow.getFirstAirDatetv());
        sinopsis.setText(tvShow.getDetailtv().equals("") ? "Detail Untuk Bahasa Ini Tidak Tersedia" : tvShow.getDetailtv());
        point.setText(String.valueOf(tvShow.getPointtv()));
        add2Fav = tvShow.getFavorite();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        if (add2Fav == true) {
            menu.getItem(0).setIcon(R.drawable.ic_favorite_full_24dp);
            add2Fav = true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.action_addFav:
                if (add2Fav == false)
                    addFavoriteMovie();
                item.setIcon(R.drawable.ic_favorite_full_24dp);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFavoriteMovie() {
        toSave.setFavorite(true);
        detailTVModel.insertFavTV(toSave);
        Toast.makeText(this, "Sukses menambah ke favorite tv", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private static DetailTVModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailTVModel.class);
    }
}
