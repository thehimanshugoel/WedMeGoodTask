package himanshugoel.com.moviesexplorer.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import himanshugoel.com.moviesexplorer.Adapters.SearchAdapter;
import himanshugoel.com.moviesexplorer.Interfaces.Search;
import himanshugoel.com.moviesexplorer.Interfaces.SearchListener;
import himanshugoel.com.moviesexplorer.Models.DirectorSearchResponse;
import himanshugoel.com.moviesexplorer.R;
import himanshugoel.com.moviesexplorer.Service.MovieExplorerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Himanshu on 20-06-2017.
 */

public class SearchTab extends Fragment implements SearchListener {
    MovieExplorerService movieExplorerService;
    Call<DirectorSearchResponse[]> directorSearchResponseCall;
    Search search;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initViews(view);
        search = movieExplorerService.getRetrofit().create(Search.class);
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    private void searchDirectorFromServer(String searchText) {
        directorSearchResponseCall = search.search(searchText);
        directorSearchResponseCall.enqueue(new Callback<DirectorSearchResponse[]>() {
            @Override
            public void onResponse(Call<DirectorSearchResponse[]> call, Response<DirectorSearchResponse[]> response) {
                progressBar.setVisibility(View.GONE);
                List<DirectorSearchResponse> directorSearchResponseList = new ArrayList<>();
                if (response.body() != null) {
                    Collections.addAll(directorSearchResponseList, response.body());
                } else {
                    Toast.makeText(getActivity(), "No such director found", Toast.LENGTH_SHORT).show();
                }


                searchAdapter = new SearchAdapter(getActivity(), directorSearchResponseList);
                recyclerView.setAdapter(searchAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onFailure(Call<DirectorSearchResponse[]> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSearch(String search) {
        progressBar.setVisibility(View.VISIBLE);
        searchDirectorFromServer(search);

    }
}
