package com.example.android.musicplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.musicplayer.TrackHolder;
import com.example.android.musicplayer.models.Track;

import java.util.ArrayList;

public class TracksRecyclerAdapter extends RecyclerView.Adapter<TrackHolder> {
    private int seconderyListItemLayoutID;
    private ArrayList<Track> mItemList;

    public TracksRecyclerAdapter(int layoutID, ArrayList<Track> itemList){
        seconderyListItemLayoutID = layoutID;
        mItemList = itemList;
    }

    @Override
    public TrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(seconderyListItemLayoutID, parent, false);
        TrackHolder trackHolder = new TrackHolder(view);
        return trackHolder;
    }

    @Override
    public void onBindViewHolder(TrackHolder holder, int position) {
        int albumCoverIdentifier = mItemList.get(position).getAlbumCover();

        ImageView albumCover = holder.mAlbumCover;
        albumCover.setImageResource(albumCoverIdentifier);
        holder.mAlbumCoverIdentifier = albumCoverIdentifier;

        TextView trackTitle = holder.mTrackTitle;
        trackTitle.setText(mItemList.get(position).getTrackTitle());

        TextView trackArtist = holder.mTrackArtist;
        trackArtist.setText(mItemList.get(position).getAlbumArtist());

        TextView albumTitle = holder.mAlbumTitle;
        albumTitle.setText(mItemList.get(position).getAlbumTitle());
    }

    @Override
    public int getItemCount() {
        if (mItemList == null){
            return 0;
        } else {
            return mItemList.size();
        }
    }
}
