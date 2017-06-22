package himanshugoel.com.moviesexplorer.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import himanshugoel.com.moviesexplorer.Adapters.SearchAdapter;
import himanshugoel.com.moviesexplorer.Database.MovieDataBaseHandler;
import himanshugoel.com.moviesexplorer.Models.DirectorSearchResponse;
import himanshugoel.com.moviesexplorer.R;

/**
 * Created by Himanshu on 20-06-2017.
 */

public class FavouriteTab extends Fragment {
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    MovieDataBaseHandler movieDataBaseHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initViews(view);
        movieDataBaseHandler = new MovieDataBaseHandler(getActivity());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Activity Called", "activity");
        List<DirectorSearchResponse> directorSearchResponseList = movieDataBaseHandler.getAllMovieData();
        searchAdapter = new SearchAdapter(getActivity(), directorSearchResponseList);
        recyclerView.setAdapter(searchAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}
