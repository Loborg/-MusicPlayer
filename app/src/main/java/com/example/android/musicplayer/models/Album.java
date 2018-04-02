package com.example.android.musicplayer.models;

import java.util.ArrayList;

public class Album {
    private int mAlbumTitleIdentifier;
    private ArrayList<Track> mTrackList;

    public Album(int albumTitle, ArrayList<Track> trackList){
        mAlbumTitleIdentifier = albumTitle;
        mTrackList = trackList;
    }

    public int getAlbumTitle(){
        return mAlbumTitleIdentifier;
    }

    public ArrayList<Track> getTrackList(){
        return mTrackList;
    }
}
