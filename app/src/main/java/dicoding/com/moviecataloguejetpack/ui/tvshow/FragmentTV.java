package dicoding.com.moviecataloguejetpack.ui.tvshow;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.ui.detail.DetailTV;
import dicoding.com.moviecataloguejetpack.viewmodel.ViewModelFactory;

public class FragmentTV extends Fragment {
    private RecyclerView recyclerTV;
    private ProgressBar progressBar;
    private TVAdapter tvAdapter;
    private FragmentTVModel fragmentTVModel;

    public FragmentTV() {
    }

    public static Fragment newInstance() {
        return new FragmentTV();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerTV = view.findViewById(R.id.list_tv);
        progressBar = view.findViewById(R.id.progressBarRVTV);

        RecyclerView.LayoutManager mlayourManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerTV.setLayoutManager(mlayourManager);
        recyclerTV.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            fragmentTVModel = obtainViewModel(getActivity());
            fragmentTVModel.getTVlist().observe(this, tvies -> {
                if (tvies != null) {
                    switch (tvies.status) {
                        case LOADING:
                            Log.e("tv", "loading");
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            Log.e("tv", "success");
                            progressBar.setVisibility(View.GONE);
                            tvAdapter = new TVAdapter(getActivity());
                            tvAdapter.setData(tvies.data);
                            recyclerTV.setAdapter(tvAdapter);
                            tvAdapter.setOnItemClickCallback(idtv -> {
                                Intent detTv = new Intent(getActivity(), DetailTV.class);
                                detTv.putExtra("extra_idtv", idtv);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getActivity().startActivity(detTv, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                                }else startActivity(detTv);
                            });
                        case ERROR:
                            Log.e("tv", "error "+tvies.message);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan saat memuat data", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }

    @NonNull
    private static FragmentTVModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FragmentTVModel.class);
    }
}
