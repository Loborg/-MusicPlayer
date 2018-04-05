package com.example.android.musicplayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.R;
import com.example.android.musicplayer.models.Track;

import java.io.Serializable;
import java.util.ArrayList;

public class TracksArrayAdapter extends ArrayAdapter<Track> {

    public TracksArrayAdapter(@NonNull Context context, ArrayList<Track> tracks) {
        super(context, 0, tracks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.track_list_items_album_activity, parent, false);
        }

        Track currentTrack = getItem(position);

        ImageView albumCover = listItemView.findViewById(R.id.album_cover);
        albumCover.setImageResource(currentTrack.getAlbumCover());

        TextView trackTitle = listItemView.findViewById(R.id.track_title);
        trackTitle.setText(currentTrack.getTrackTitle());

        TextView trackArtist = listItemView.findViewById(R.id.track_artist);
        trackArtist.setText(currentTrack.getAlbumArtist());

        return listItemView;
    }
}
