package himanshugoel.com.moviesexplorer.Service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Himanshu on 20-06-2017.
 */

public class MovieExplorerService {
    static MovieExplorerService movieExplorerService;
    Retrofit retrofit;

    private MovieExplorerService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(5, TimeUnit.MINUTES).connectTimeout(5, TimeUnit.MINUTES).build();
        retrofit = new Retrofit.Builder().baseUrl("http://netflixroulette.net/").
                addConverterFactory(JacksonConverterFactory.create()).client(client).build();
    }

    static MovieExplorerService getInstance() {
        if (movieExplorerService == null) {
            movieExplorerService = new MovieExplorerService();
        }
        return movieExplorerService;
    }

    static public Retrofit getRetrofit() {
        return MovieExplorerService.getInstance().getRetrofitAdapter();
    }

    public Retrofit getRetrofitAdapter() {
        return retrofit;
    }


}
