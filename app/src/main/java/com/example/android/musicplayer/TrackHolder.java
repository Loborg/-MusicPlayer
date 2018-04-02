package com.example.android.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.activitys.HomeActivity;

/**
 * This is the holder Object for the TracksRecyclerAdapter.
 */

public class TrackHolder extends RecyclerView.ViewHolder {
    public ImageView mAlbumCover;
    public int mAlbumCoverIdentifier;
    public TextView mTrackTitle;
    public TextView mTrackArtist;
    public TextView mAlbumTitle;

    public TrackHolder(final View itemView){
        super(itemView);

        /*This listener method handles the click event of the track layout whitin the RecyclerView.
        * The method populats the HomeActyvit's album and track title TextViews*/
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.setTextToCommonAlbumAndTrackTitle(mTrackTitle.getText().toString(), mAlbumTitle.getText().toString());
                HomeActivity.getResourceForTrackActivity(mAlbumCoverIdentifier, mTrackTitle.getText().toString(), mTrackArtist.getText().toString());
            }
        });

        mAlbumCover = itemView.findViewById(R.id.album_cover);
        mTrackTitle = itemView.findViewById(R.id.track_title);
        mTrackArtist = itemView.findViewById(R.id.track_artist);
        mAlbumTitle = itemView.findViewById(R.id.album_title);
    }
}
