package com.cuscoup.androidcollapsingtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cuscoup.androidcollapsingtoolbar.Adapter.PostAdapter;
import com.cuscoup.androidcollapsingtoolbar.Model.Post;
import com.cuscoup.androidcollapsingtoolbar.Retrofit.IMyAPI;
import com.cuscoup.androidcollapsingtoolbar.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    IMyAPI myAPI;
    RecyclerView recycler_posts;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init api
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);

        //view
        recycler_posts = (RecyclerView)findViewById(R.id.recycler_posts);
        recycler_posts.setHasFixedSize(true);
        recycler_posts.setLayoutManager(new LinearLayoutManager(this));
        fetchData();
    }

    private void fetchData() {
      compositeDisposable.add(myAPI.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>(){
                    @Override
                    public void accept(List<Post> posts) throws Exception{
                        displayData(posts);
                    }
                }));
    }
    private void displayData(List<Post> posts){
        PostAdapter adapter = new PostAdapter(this,posts);
        recycler_posts.setAdapter(adapter);
    }

    @Override
    protected  void onStop(){
        super.onStop();
    }

}
