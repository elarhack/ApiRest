package com.cuscoup.androidcollapsingtoolbar.Retrofit;


import com.cuscoup.androidcollapsingtoolbar.Model.Post;

import java.util.List;
import io.reactivex.Observable;

import retrofit2.http.GET;

/**
 * Created by elarsoft on 08/08/2018.
 */

public interface IMyAPI {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
