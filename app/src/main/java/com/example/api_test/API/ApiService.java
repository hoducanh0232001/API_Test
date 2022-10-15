package com.example.api_test.API;

import com.example.api_test.model.Currency;
import com.example.api_test.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    //link GET api : http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    // link POST api: https://jsonplaceholder.typicode.com/posts
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("api/live")
    Call<Currency> convertUsdToVnd(@Query("access_key") String access_key,
                                   @Query("currencies") String currencies,
                                   @Query("source") String source,
                                   @Query("format") int format);

    @GET("api/live")
    Call<Currency> convertUsdToVnd2(@QueryMap Map<String ,String> options);

    //Link API : http://api.layer.net/api/users/list
    @GET("api/users/list")
    Call<Currency> getListUser();

    //Link API : http://api.layer.net/api/group/1/users
    @GET("api/group/{id}/users")
    Call<Currency> getListUserFromGroup(@Path("id") int groupId);

    //Link API : http://api.layer.net/api/group/1/users?sort=desc
    @GET("api/group/{}/users")
    Call<Currency> getListUserFromGroup2(@Path("id")int groupId,
                                         @Query("sort") String sort);

    @POST("posts")
    Call<Post> sendPosts(@Body Post post);


}
