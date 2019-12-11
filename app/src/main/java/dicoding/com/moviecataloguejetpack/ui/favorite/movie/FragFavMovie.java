package dicoding.com.moviecataloguejetpack.ui.favorite.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailMovie;
import dicoding.com.moviecataloguejetpack.viewmodel.ViewModelFactory;

public class FragFavMovie extends Fragment {
    private FavMovieModel favMovieModel;
    private RecyclerView recyclerMovie;
    private ProgressBar progressBar;
    private FavPagedMovAdapter favoriteMovAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerMovie = view.findViewById(R.id.list_favorite);
        progressBar = view.findViewById(R.id.progressBarFav);

        favMovieModel = obtainViewModel(getActivity());
        favoriteMovAdapter = new FavPagedMovAdapter();
        favMovieModel.getAllPagedMovie().observe(this, favMovies -> {
            if (favMovies.data != null) {
                switch (favMovies.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        favoriteMovAdapter.submitList(favMovies.data);
                        favoriteMovAdapter.notifyDataSetChanged();
                        favoriteMovAdapter.setOnItemClickCallback(idmoviedb -> {
                            Intent detMovie = new Intent(getActivity(), DetailMovie.class);
                            detMovie.putExtra("extra_idmovie", idmoviedb);
                            startActivity(detMovie);
                        });
                        favoriteMovAdapter.setOnItemDelCallback(movie2del -> {
                            movie2del.setFavorite(false);
                            favMovieModel.DeleteMovieFromFav(movie2del);
                            Toast.makeText(getActivity(), "Sukses hapus movie dari favorite", Toast.LENGTH_SHORT).show();
                        });
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Terjadi Kesalahan saat memuat data", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        RecyclerView.LayoutManager mlayourManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerMovie.setLayoutManager(mlayourManager);
        recyclerMovie.setAdapter(favoriteMovAdapter);
    }

    @NonNull
    private static FavMovieModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavMovieModel.class);
    }
}
