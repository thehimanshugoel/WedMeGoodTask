package himanshugoel.com.moviesexplorer.Interfaces;

import himanshugoel.com.moviesexplorer.Models.DirectorSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Himanshu on 20-06-2017.
 */

public interface Search {
    @GET("api/api.php")
    Call<DirectorSearchResponse[]> search(@Query("director") String directorName);

}
