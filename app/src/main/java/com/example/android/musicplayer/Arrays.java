package com.example.android.musicplayer;

import android.content.Context;

import com.example.android.musicplayer.models.Album;
import com.example.android.musicplayer.models.Track;

import java.util.ArrayList;

public class Arrays {

    private static final String STRING_TYPE = "string";
    private static final String DRAWABLE_TYPE = "drawable";
    private static final int ALBUM_1_TRACKCOUNT = 13;
    private static final int ALBUM_2_TRACKCOUNT = 13;
    private static final int ALBUM_3_TRACKCOUNT = 14;
    private static final int ALBUM_4_TRACKCOUNT = 14;
    private static final int ALBUM_5_TRACKCOUNT = 17;
    private final String[] ALBUM_COVER_IDENTIFIER = {"naphoz_holddal", "fold_kaland_ilyesmi", "agy_asztal_teve", "sika_kasza_lec", "ul"};

    public ArrayList<Integer> albumTitle = new ArrayList<Integer>();
    public ArrayList<Integer> albumCover = new ArrayList<Integer>();
    public ArrayList<Integer> trackTitle = new ArrayList<Integer>();
    public ArrayList<Track> trackList_1 = new ArrayList<Track>();
    public ArrayList<Track> trackList_2 = new ArrayList<Track>();
    public ArrayList<Track> trackList_3 = new ArrayList<Track>();
    public ArrayList<Track> trackList_4 = new ArrayList<Track>();
    public ArrayList<Track> trackList_5 = new ArrayList<Track>();
    public ArrayList<Album> albumList = new ArrayList<Album>();

    private Context mContext;

    public Arrays(Context context){
        mContext = context;
        populateAlbumTitleAndCoverArrays();
        populateAlbumAndTrackList();
    }

    public void populateAlbumTitleAndCoverArrays(){
        for (int i = 0; i < ALBUM_COVER_IDENTIFIER.length; i++){
            String titlleIdentifierName = "album_" + String.valueOf(i+1);
            albumTitle.add(mContext.getResources().getIdentifier(titlleIdentifierName, STRING_TYPE, mContext.getPackageName()));
            albumCover.add(mContext.getResources().getIdentifier(ALBUM_COVER_IDENTIFIER[i], DRAWABLE_TYPE, mContext.getPackageName()));
        }
    }

    public void populateAlbumAndTrackList(){
        int albumCount = 0;
        int currentTrackCount = 0;
        //Naphoz holddal 13 track
        for (int trackCount = 0; trackCount < ALBUM_1_TRACKCOUNT; trackCount++) {
            String trackTitleIdentifierNameA = "trackA_" + String.valueOf(trackCount+1);
            trackTitle.add(mContext.getResources().getIdentifier(trackTitleIdentifierNameA, STRING_TYPE, mContext.getPackageName()));
            trackList_1.add(new Track(mContext, ALBUM_COVER_IDENTIFIER[albumCount], trackTitle.get(trackCount), albumTitle.get(albumCount)));
            currentTrackCount = trackTitle.size();
        }
        albumList.add(new Album(albumTitle.get(albumCount), trackList_1));
        albumCount++;

        //Föld kaland ilyesmi… 13 track
        for (int trackCount = 0; trackCount < ALBUM_2_TRACKCOUNT; trackCount++) {
            String trackTitleIdentifierNameB = "trackB_" + String.valueOf(trackCount+1);
            trackTitle.add(mContext.getResources().getIdentifier(trackTitleIdentifierNameB, STRING_TYPE, mContext.getPackageName()));
            trackList_2.add(new Track(mContext, ALBUM_COVER_IDENTIFIER[albumCount], trackTitle.get(currentTrackCount), albumTitle.get(albumCount)));
            currentTrackCount = trackTitle.size();
        }
        albumList.add(new Album(albumTitle.get(albumCount), trackList_2));
        albumCount++;

        //Ágy asztal tévé 14 track
        for (int trackCount = 0; trackCount < ALBUM_3_TRACKCOUNT; trackCount++) {
            String trackTitleIdentifierNameC = "trackC_" + String.valueOf(trackCount+1);
            trackTitle.add(mContext.getResources().getIdentifier(trackTitleIdentifierNameC, STRING_TYPE, mContext.getPackageName()));
            trackList_3.add(new Track(mContext, ALBUM_COVER_IDENTIFIER[albumCount], trackTitle.get(currentTrackCount), albumTitle.get(albumCount)));
            currentTrackCount = trackTitle.size();
        }
        albumList.add(new Album(albumTitle.get(albumCount), trackList_3));
        albumCount++;

        //Sika, kasza, léc 14 track
        for (int trackCount = 0; trackCount < ALBUM_4_TRACKCOUNT; trackCount++) {
            String trackTitleIdentifierNameD = "trackD_" + String.valueOf(trackCount+1);
            trackTitle.add(mContext.getResources().getIdentifier(trackTitleIdentifierNameD, STRING_TYPE, mContext.getPackageName()));
            trackList_4.add(new Track(mContext, ALBUM_COVER_IDENTIFIER[albumCount], trackTitle.get(currentTrackCount), albumTitle.get(albumCount)));
            currentTrackCount = trackTitle.size();
        }
        albumList.add(new Album(albumTitle.get(albumCount), trackList_4));
        albumCount++;

        //Ül 17 track
        for (int trackCount = 0; trackCount < ALBUM_5_TRACKCOUNT; trackCount++) {
            String trackTitleIdentifierNameE = "trackE_" + String.valueOf(trackCount+1);
            trackTitle.add(mContext.getResources().getIdentifier(trackTitleIdentifierNameE, STRING_TYPE, mContext.getPackageName()));
            trackList_5.add(new Track(mContext, ALBUM_COVER_IDENTIFIER[albumCount], trackTitle.get(currentTrackCount), albumTitle.get(albumCount)));
            currentTrackCount = trackTitle.size();
        }
        albumList.add(new Album(albumTitle.get(albumCount), trackList_5));
    }
}
