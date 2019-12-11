package dicoding.com.moviecataloguejetpack.ui.favorite.movie;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;

public class FavPagedMovAdapter extends PagedListAdapter<MovieEntity, FavPagedMovAdapter.MyViewHolder> {
    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getIdmovie() == newItem.getIdmovie();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem == newItem;
                }
            };

    public FavPagedMovAdapter() {
        super(DIFF_CALLBACK);
    }

    private onFavMovieItemClick onMovieItemClick;
    private onDelFavMovieItemClick onDelFavMovieItemClick;

    public void setOnItemClickCallback(onFavMovieItemClick onMovieItemClick) {
        this.onMovieItemClick = onMovieItemClick;
    }

    public void setOnItemDelCallback(onDelFavMovieItemClick onItemDelCallback) {
        this.onDelFavMovieItemClick = onItemDelCallback;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle, mrelease, moverview, muserScore;
        public AppCompatImageView imgMovie;
        public AppCompatRatingBar ratingMov;
//        public ProgressBar progressBar;
        public ImageButton delFav;

        public MyViewHolder(View view) {
            super(view);
            mtitle = view.findViewById(R.id.ltv_title);
            mrelease = view.findViewById(R.id.ltv_release);
            moverview = view.findViewById(R.id.ltv_overview);
            imgMovie = view.findViewById(R.id.liv_Movie);
            ratingMov = view.findViewById(R.id.ratingBar);
            muserScore = view.findViewById(R.id.tv_userscore);
//            progressBar = view.findViewById(R.id.progressBarIV);
            delFav = view.findViewById(R.id.btn_delfav);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fav, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MovieEntity favMovie = getItem(position);
        holder.mtitle.setText(favMovie.getTitleMovie());
        holder.mrelease.setText(favMovie.getReleaseMovie());
        holder.moverview.setText(favMovie.getDetailMovie().equals("")
                ? "Detail Untuk Bahasa Ini Tidak Tersedia" : favMovie.getDetailMovie());
        holder.ratingMov.setRating(favMovie.getPoint());
        holder.muserScore.setText(String.valueOf(favMovie.getPoint()));

        Glide.with(holder.imgMovie.getContext()).load(ApiClient.PosterUrl + favMovie.getPosterMovie())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_dashboard_black_24dp))
                .error(R.drawable.ic_error_photo_24dp)
                .into(holder.imgMovie);

        holder.itemView.setOnClickListener(v -> onMovieItemClick.onItemClick(favMovie.getIdmovie()));
        holder.delFav.setOnClickListener(v -> onDelFavMovieItemClick.onDelete(favMovie));
    }

    public interface onFavMovieItemClick {
        void onItemClick(int idmoviedb);
    }

    public interface onDelFavMovieItemClick {
        void onDelete(MovieEntity movie);
    }
}
