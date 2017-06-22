package himanshugoel.com.moviesexplorer.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import himanshugoel.com.moviesexplorer.Models.DirectorSearchResponse;
import himanshugoel.com.moviesexplorer.R;
import himanshugoel.com.moviesexplorer.Utils.BlurTransform;
import himanshugoel.com.moviesexplorer.Views.Activities.MovieDetails;

/**
 * Created by Himanshu on 21-06-2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<DirectorSearchResponse> directorSearchResponseList;
    FragmentActivity activity;

    public SearchAdapter(FragmentActivity activity, List<DirectorSearchResponse> directorSearchResponseList) {
        this.activity = activity;
        this.directorSearchResponseList = directorSearchResponseList;

    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_director_search, parent, false));

    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, final int position) {
        holder.txtMovieName.setText(directorSearchResponseList.get(position).getShowTitle());
        holder.ratingBar.setRating(Float.parseFloat(directorSearchResponseList.get(position).getRating()));
        Picasso.with(activity).load(directorSearchResponseList.get(position).getPoster()).into(holder.imgMovie);
        Picasso.with(activity).load(directorSearchResponseList.get(position).getPoster()).transform(new BlurTransform(activity)).into(holder.imgBlurMovie);
        holder.searchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("MovieData", directorSearchResponseList.get(position));
                Intent intent = new Intent(activity, MovieDetails.class);
                intent.putExtras(bundle);

                activity.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return directorSearchResponseList.size();
    }

    public void updateList() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        ImageView imgBlurMovie;
        TextView txtMovieName;
        RatingBar ratingBar;
        CardView searchCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.imgMovie);
            imgBlurMovie = itemView.findViewById(R.id.imgBlurMovie);
            txtMovieName = itemView.findViewById(R.id.txtMovieName);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            searchCardView = itemView.findViewById(R.id.searchCardView);
        }
    }
}
