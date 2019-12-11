package dicoding.com.moviecataloguejetpack.ui.favorite.tvshow;

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
import dicoding.com.moviecataloguejetpack.ui.detail.DetailTV;
import dicoding.com.moviecataloguejetpack.viewmodel.ViewModelFactory;

public class FragFavTV extends Fragment {
    private FavTVModel favTVModel;
    private RecyclerView recyclerTV;
    private ProgressBar progressBar;
    private FavPagedTVAdapter favoritetvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerTV = view.findViewById(R.id.list_favorite);
        progressBar = view.findViewById(R.id.progressBarFav);

        favTVModel = obtainViewModel(getActivity());
        favoritetvAdapter = new FavPagedTVAdapter();
        favTVModel.getAllPagedTv().observe(this, favTvies -> {
            if(favTvies.data != null) {
                switch (favTvies.status){
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        favoritetvAdapter.submitList(favTvies.data);
                        favoritetvAdapter.notifyDataSetChanged();
                        favoritetvAdapter.setOnItemClickCallback(idtv -> {
                            Intent detTV = new Intent(getActivity(), DetailTV.class);
                            detTV.putExtra("extra_idtv", idtv);
                            startActivity(detTV);
                        });
                        favoritetvAdapter.setOnItemDelCallback(tv2del -> {
                            tv2del.setFavorite(false);
                            favTVModel.DeleteTvFromFav(tv2del);
                            Toast.makeText(getActivity(), "Sukses hapus TV dari favorite", Toast.LENGTH_SHORT).show();
                        });
                        progressBar.setVisibility(View.GONE);
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Terjadi Kesalahan saat memuat data", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        RecyclerView.LayoutManager mlayourManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerTV.setLayoutManager(mlayourManager);
        recyclerTV.setAdapter(favoritetvAdapter);
    }

    @NonNull
    private static FavTVModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavTVModel.class);
    }
}
