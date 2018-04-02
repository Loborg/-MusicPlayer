package com.example.android.musicplayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicplayer.activitys.AlbumActivity;
import com.example.android.musicplayer.models.Album;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.models.Track;

import java.util.ArrayList;

/**
 * Created by Loborg on 2018. 03. 24..
 */

public class AlbumsArrayAdapter extends ArrayAdapter<Album> {

    public AlbumsArrayAdapter(Context context, ArrayList<Album> albums){
        super (context, 0, albums);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.album_list_items, parent, false);
        }

        final Album currentAlbum = getItem(position);

        TextView albumTitle = listItemView.findViewById(R.id.album_title);
        albumTitle.setText(currentAlbum.getAlbumTitle());
        albumTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlbumActivity.class);
                getContext().startActivity(intent);
            }
        });

        TracksRecyclerAdapter recyclerAdapter = new TracksRecyclerAdapter(R.layout.track_list_items, currentAlbum.getTrackList());
        RecyclerView recyclerView = listItemView.findViewById(R.id.secondary_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(listItemView.getContext(), LinearLayout.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);

        return listItemView;
    }
}
