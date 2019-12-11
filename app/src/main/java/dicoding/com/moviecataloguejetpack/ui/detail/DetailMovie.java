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
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;
import dicoding.com.moviecataloguejetpack.utils.MovieHelper;
import dicoding.com.moviecataloguejetpack.viewmodel.ViewModelFactory;

public class DetailMovie extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView posterMovie;
    private TextView tahun, genre, durasi, release, sinopsis, point;
    private DetailMovieModel detailMovieModel;
    public static final String EXTRA_IDMOV = "extra_idmovie";
    private Boolean add2Fav;
    private MovieEntity toSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailmovie);

        Toolbar toolbar = findViewById(R.id.toolbarMov);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        tahun = findViewById(R.id.tv_tahun);
        posterMovie = findViewById(R.id.iv_posterMov);
        genre = findViewById(R.id.tv_genre);
        durasi = findViewById(R.id.tv_durasi);
        release = findViewById(R.id.tv_release);
        sinopsis = findViewById(R.id.tv_ketm);
        point = findViewById(R.id.tv_point);

        final int idmovie = getIntent().getIntExtra(DetailMovie.EXTRA_IDMOV, 0);
        detailMovieModel = obtainViewModel(this);
        detailMovieModel.setIdMovie(idmovie);
        detailMovieModel.liveMovie.observe(this, movieEntityResource -> {
            if (movieEntityResource.data != null) setDataMovie(movieEntityResource.data);
        });
    }

    void setDataMovie(final MovieEntity movie) {
        toSave = movie;
        collapsingToolbarLayout.setTitle(movie.getTitleMovie());
        if (!movie.getGenreMovie().equals(""))
            genre.setText(movie.getGenreMovie());
        else genre.setText("Memuat data...");
        if (movie.getDurasiMovie() > 0)
            durasi.setText(MovieHelper.convertDuration2String(this, movie.getDurasiMovie()));
        else durasi.setText("Memuat data...");

        release.setText(movie.getReleaseMovie());
        Glide.with(this).load(ApiClient.PosterUrl + movie.getPosterMovie())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_dashboard_black_24dp))
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(posterMovie);

        sinopsis.setText(movie.getDetailMovie().equals("") ? "Detail Untuk Bahasa Ini Tidak Tersedia" : movie.getDetailMovie());
        tahun.setText(movie.getReleaseMovie().substring(0, 4));
        point.setText(String.valueOf(movie.getPoint()));
        add2Fav = movie.getFavorite();
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
        detailMovieModel.insertFavMovie(toSave);
        Toast.makeText(this, "Sukses menambah ke favorite movie", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private static DetailMovieModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailMovieModel.class);
    }
}
