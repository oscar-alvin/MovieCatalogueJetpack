package dicoding.com.moviecataloguejetpack.ui.tvshow;

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
import dicoding.com.moviecataloguejetpack.data.source.local.entity.TvEntity;
import dicoding.com.moviecataloguejetpack.data.source.remote.api.ApiClient;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.MyViewHolder> {
    private Context context;
    private List<TvEntity> trendingTvList;

    private onMovieItemClick onMovieItemClick;

    public void setOnItemClickCallback(onMovieItemClick onMovieItemClick) {
        this.onMovieItemClick = onMovieItemClick;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle, tvrelease, tvoverview, muserScore;
        public AppCompatImageView imgTV;
        public AppCompatRatingBar ratingMov;

        public MyViewHolder(View view) {
            super(view);
            tvtitle = view.findViewById(R.id.ltv_title);
            tvrelease = view.findViewById(R.id.ltv_release);
            tvoverview = view.findViewById(R.id.ltv_overview);
            imgTV = view.findViewById(R.id.liv_Movie);
            ratingMov = view.findViewById(R.id.ratingBar);
            muserScore = view.findViewById(R.id.tv_userscore);
        }
    }

    public TVAdapter(Context c) {
        this.context = c;
        trendingTvList = new ArrayList<>();
    }

    public void setData(List<TvEntity> trendingList) {
        trendingTvList.clear();
        trendingTvList.addAll(trendingList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final TvEntity trendingTv = trendingTvList.get(holder.getAdapterPosition());

        holder.tvtitle.setText(trendingTv.getTitletv());
        holder.tvrelease.setText(trendingTv.getFirstAirDatetv());
        holder.tvoverview.setText(trendingTv.getDetailtv().equals("")
                ? "Detail Untuk Bahasa Ini Tidak Tersedia" : trendingTv.getDetailtv());
        holder.ratingMov.setRating(trendingTv.getPointtv());
        holder.muserScore.setText(String.valueOf(trendingTv.getPointtv()));
        Glide.with(context).load(ApiClient.PosterUrl + trendingTv.getPostertv())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_dashboard_black_24dp))
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(holder.imgTV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieItemClick.onItemClick(trendingTv.getIdtv());
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendingTvList.size();
    }

    public interface onMovieItemClick {
        void onItemClick(int idtv);
    }
}
