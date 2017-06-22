package himanshugoel.com.moviesexplorer.Views.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import himanshugoel.com.moviesexplorer.Database.MovieDataBaseHandler;
import himanshugoel.com.moviesexplorer.Models.DirectorSearchResponse;
import himanshugoel.com.moviesexplorer.R;

public class MovieDetails extends AppCompatActivity {
    ImageView imgMovie;
    DirectorSearchResponse directorSearchResponse;
    CollapsingToolbarLayout collapsingToolbar;
    AppBarLayout appBarLayout;
    ImageView imgBack;
    TextView txtCategory;
    RatingBar rating;
    TextView txtRuntime;
    TextView txtRealeaseYear;
    TextView txtShowCast;
    TextView txtSummary;
    TextView txtDirector;
    ImageView imgBookmark;
    boolean isBookmarked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        final MovieDataBaseHandler movieDataBaseHandler = new MovieDataBaseHandler(this);
        directorSearchResponse = (DirectorSearchResponse) getIntent().getSerializableExtra("MovieData");
        initViews();
        initDataToViews();
        getBookMarkedData(movieDataBaseHandler);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    //collapsed
                    imgBack.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.black));
                } else {
                    //expanded
                    imgBack.setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.white));


                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBookmarked) {
                    imgBookmark.setImageResource(R.mipmap.ic_bookmark_active_white);
                    movieDataBaseHandler.addMoviesData(directorSearchResponse);
                    isBookmarked = true;
                } else {
                    imgBookmark.setImageResource(R.mipmap.ic_bookmark__no_fill_24dp);
                    movieDataBaseHandler.deleteMovieData(directorSearchResponse);
                    isBookmarked = false;
                }
            }
        });
    }

    private void getBookMarkedData(MovieDataBaseHandler movieDataBaseHandler) {
        List<DirectorSearchResponse> directorSearchResponseList = movieDataBaseHandler.getAllMovieData();
        if (directorSearchResponseList != null) {
            for (int i = 0; i < directorSearchResponseList.size(); i++) {
                if (directorSearchResponse.getShowId().equals(directorSearchResponseList.get(i).getShowId())) {
                    imgBookmark.setImageResource(R.mipmap.ic_bookmark_active_white);
                    isBookmarked = true;
                }
            }
        }
    }

    private void initDataToViews() {
        Picasso.with(getApplicationContext()).load(directorSearchResponse.getPoster()).into(imgMovie);
        collapsingToolbar.setTitle(directorSearchResponse.getShowTitle());
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        txtCategory.setText(directorSearchResponse.getCategory());
        rating.setRating(Float.parseFloat(directorSearchResponse.getRating()));
        txtRuntime.setText(directorSearchResponse.getRuntime());
        txtRealeaseYear.setText(directorSearchResponse.getReleaseYear());
        txtShowCast.setText(directorSearchResponse.getShowCast());
        txtSummary.setText(directorSearchResponse.getSummary());
        txtDirector.setText(directorSearchResponse.getDirector());
    }

    private void initViews() {
        imgMovie = (ImageView) findViewById(R.id.imgMovie);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        rating = (RatingBar) findViewById(R.id.rating);
        txtRuntime = (TextView) findViewById(R.id.txtRuntime);
        txtRealeaseYear = (TextView) findViewById(R.id.txtReleaseYear);
        txtShowCast = (TextView) findViewById(R.id.txtShowCast);
        txtSummary = (TextView) findViewById(R.id.txtSummary);
        txtDirector = (TextView) findViewById(R.id.txtDirector);
        imgBookmark = (ImageView) findViewById(R.id.imgBookmark);


    }
}
