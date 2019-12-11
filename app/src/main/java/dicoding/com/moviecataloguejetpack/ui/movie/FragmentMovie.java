package dicoding.com.moviecataloguejetpack.ui.movie;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailMovie;
import dicoding.com.moviecataloguejetpack.viewmodel.ViewModelFactory;

public class FragmentMovie extends Fragment {
    private RecyclerView recyclerMovie;
    private ProgressBar progressBar;
    private MovieAdapter movieAdapter;
    private FragmentMovieModel fragmentMovieModel;

    public FragmentMovie() {
    }

    public static Fragment newInstance() {
        return new FragmentMovie();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerMovie = view.findViewById(R.id.list_movie);
        progressBar = view.findViewById(R.id.progressBarRVMovie);

        RecyclerView.LayoutManager mlayourManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerMovie.setLayoutManager(mlayourManager);
        recyclerMovie.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            fragmentMovieModel = obtainViewModel(getActivity());
            fragmentMovieModel.getMovielist().observe(this, movies -> {
                if(movies != null){
                    switch (movies.status){
                        case LOADING:
                            Log.e("movie", "loading");
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            Log.e("movie", "succc");
                            progressBar.setVisibility(View.GONE);
                            movieAdapter = new MovieAdapter(getActivity());
                            movieAdapter.setData(movies.data);
                            recyclerMovie.setAdapter(movieAdapter);

                            movieAdapter.setOnItemClickCallback(idmoviedb -> {
                                Intent detMovie = new Intent(getActivity(), DetailMovie.class);
                                detMovie.putExtra("extra_idmovie", idmoviedb);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getActivity().startActivity(detMovie, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                                }else startActivity(detMovie);
                            });
                            break;
                        case ERROR:
                            Log.e("movie", "err");
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan saat memuat data", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }

    @NonNull
    private static FragmentMovieModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FragmentMovieModel.class);
    }
}
