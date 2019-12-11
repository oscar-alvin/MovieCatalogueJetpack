package dicoding.com.moviecataloguejetpack.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<MovieEntity> trendingMovies;

    private onMovieItemClick onMovieItemClick;

    public void setOnItemClickCallback(onMovieItemClick onMovieItemClick) {
        this.onMovieItemClick = onMovieItemClick;
    }

    public MovieAdapter(Context c) {
        this.context = c;
        trendingMovies = new ArrayList<>();
    }

    public void setData(List<MovieEntity> trendingMovieList) {
        if (trendingMovieList != null) {
            trendingMovies.clear();
            trendingMovies.addAll(trendingMovieList);
            notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle, mrelease, moverview, muserScore;
        public AppCompatImageView imgMovie;
        public AppCompatRatingBar ratingMov;

        public MyViewHolder(View view) {
            super(view);
            mtitle = view.findViewById(R.id.ltv_title);
            mrelease = view.findViewById(R.id.ltv_release);
            moverview = view.findViewById(R.id.ltv_overview);
            imgMovie = view.findViewById(R.id.liv_Movie);
            ratingMov = view.findViewById(R.id.ratingBar);
            muserScore = view.findViewById(R.id.tv_userscore);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MovieEntity trendingMovie = trendingMovies.get(holder.getAdapterPosition());
        holder.mtitle.setText(trendingMovie.getTitleMovie());
        holder.mrelease.setText(trendingMovie.getReleaseMovie());
        holder.moverview.setText(trendingMovie.getDetailMovie().equals("")
                ? "Detail Untuk Bahasa Ini Tidak Tersedia" : trendingMovie.getDetailMovie());
        holder.ratingMov.setRating(trendingMovie.getPoint());
        holder.muserScore.setText(String.valueOf(trendingMovie.getPoint()));
        Glide.with(context).load(ApiClient.PosterUrl + trendingMovie.getPosterMovie())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_dashboard_black_24dp))
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(holder.imgMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieItemClick.onItemClick(trendingMovie.getIdmovie());
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendingMovies.size();
    }

    public interface onMovieItemClick {
        void onItemClick(int idmoviedb);
    }
}
