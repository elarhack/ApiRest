package com.cuscoup.androidcollapsingtoolbar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuscoup.androidcollapsingtoolbar.Model.Post;
import com.cuscoup.androidcollapsingtoolbar.R;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by elarsoft on 08/08/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    Context context;

    public PostAdapter(Context context, List<Post> postlist) {
        this.context = context;
        this.postlist = postlist;
    }

    List<Post> postlist;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout,parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.txt_title.setText(String.valueOf(postlist.get(position).userId));
        holder.txt_content.setText(new StringBuilder(postlist.get(position).body.substring(0,20))
                .append("...").toString());


    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }
}
