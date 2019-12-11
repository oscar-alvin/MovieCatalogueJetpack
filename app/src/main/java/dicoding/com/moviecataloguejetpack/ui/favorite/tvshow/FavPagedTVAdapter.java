package dicoding.com.moviecataloguejetpack.ui.favorite.tvshow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import dicoding.com.moviecataloguejetpack.R;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.MovieEntity;
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;

public class FavPagedTVAdapter extends PagedListAdapter<TvEntity, FavPagedTVAdapter.MyViewHolder> {
    private static DiffUtil.ItemCallback<TvEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.getIdtv() == newItem.getIdtv();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem == newItem;
                }
            };

    public FavPagedTVAdapter() {
        super(DIFF_CALLBACK);
    }

    private onFavTVItemClick onTVItemClick;
    private onDelFavTvItemClick onDelFavMovieItemClick;

    public void setOnItemClickCallback(onFavTVItemClick onTVItemClick) {
        this.onTVItemClick = onTVItemClick;
    }
    public void setOnItemDelCallback(onDelFavTvItemClick onItemDelCallback){
        this.onDelFavMovieItemClick = onItemDelCallback;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle, tvrelease, tvoverview, muserScore;
        public AppCompatImageView imgTV;
        public AppCompatRatingBar ratingMov;
//        public ProgressBar progressBar;
        public ImageButton delFav;

        public MyViewHolder(View view) {
            super(view);
            tvtitle = view.findViewById(R.id.ltv_title);
            tvrelease = view.findViewById(R.id.ltv_release);
            tvoverview = view.findViewById(R.id.ltv_overview);
            imgTV = view.findViewById(R.id.liv_Movie);
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
        final TvEntity favTV = getItem(position);
        holder.tvtitle.setText(favTV.getTitletv());
        holder.tvrelease.setText(favTV.getFirstAirDatetv());
        holder.tvoverview.setText(favTV.getDetailtv().equals("")
                ? "Detail Untuk Bahasa Ini Tidak Tersedia" : favTV.getDetailtv());
        holder.ratingMov.setRating(favTV.getPointtv());
        holder.muserScore.setText(String.valueOf(favTV.getPointtv()));
        Glide.with(holder.imgTV.getContext()).load(ApiClient.PosterUrl + favTV.getPostertv())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_dashboard_black_24dp))
                .error(R.drawable.ic_error_photo_24dp)
                .into(holder.imgTV);

        holder.itemView.setOnClickListener(v -> onTVItemClick.onItemClick(favTV.getIdtv()));
        holder.delFav.setOnClickListener(v -> onDelFavMovieItemClick.onDelete(favTV));
    }

    public interface onFavTVItemClick {
        void onItemClick(int idmoviedb);
    }

    public interface onDelFavTvItemClick {
        void onDelete(TvEntity tv);
    }
}
