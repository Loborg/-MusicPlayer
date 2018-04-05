package com.example.android.musicplayer.models;

import android.content.Context;

import com.example.android.musicplayer.R;

import java.io.Serializable;

public class Track {

    private static final String DRAWABLE_TYPE = "drawable";
    private String mAlbumCoverIdentifierName;
    private String mAlbumArtist;
    private int mTrackTitleIdentifier;
    private int mAlbumTitleIdentifier;
    private Context mContext;

    public Track(Context context, String albumCoverIdentifierName, int trackTitleIdentifier, int albumTitleIdentifier){
        mContext = context;
        mAlbumCoverIdentifierName = albumCoverIdentifierName;
        mAlbumArtist = mContext.getResources().getString(R.string.performer);
        mTrackTitleIdentifier = trackTitleIdentifier;
        mAlbumTitleIdentifier = albumTitleIdentifier;
    }

    /**Returns the album cover drawable using the name of the image file whitout the .png postfix*/
    public int getAlbumCover(){
        return mContext.getResources().getIdentifier(mAlbumCoverIdentifierName, DRAWABLE_TYPE, mContext.getPackageName());
    }

    /**Returns the artist name from the string resource, the artist is alvas the same in this case.*/
    public String getAlbumArtist(){
        return mAlbumArtist;
    }

    public int getTrackTitle(){
        return mTrackTitleIdentifier;
    }

    public int getAlbumTitle(){
        return mAlbumTitleIdentifier;
    }
}
