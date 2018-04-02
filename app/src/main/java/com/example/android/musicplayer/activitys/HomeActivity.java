package com.example.android.musicplayer.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicplayer.Arrays;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.adapters.*;

public class HomeActivity extends AppCompatActivity {

    public static final String INSTANCE_SAVE_TRACK_SELECTED_KEY = "kTS";
    public static final String INSTANCE_SAVE_ALBUM_TITLE_KEY = "kISAT";
    public static final String INSTANCE_SAVE_TRACK_TITLE_KEY = "kISTT";
    public static final String INSTANCE_SAVE_PLAY_VISIBILITY_KEY = "kISPlV";
    public static final String INSTANCE_SAVE_PAUSE_VISIBILITY_KEY = "kISPaV";
    public static final String INTENT_COVER_IDENT_KEY = "kIC";
    public static final String INTENT_TRACK_TITLE_KEY = "kITT";
    public static final String INTENT_TRACK_ARTIST_KEY = "kITA";
    private static TextView mTrackTitle;
    private static TextView mAlbumTitle;
    private ImageView mHomeArrowUpp;
    private static ImageView mHomePlay;
    private static ImageView mHomePause;
    private static boolean isTrackSelected = false;

    private static int mAlbumCoverIdentifier;
    private static String mTrackArtistText, mTrackTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);

        TextView activityTitle = findViewById(R.id.title_text_bar);
        activityTitle.setText(getBaseContext().getResources().getString(R.string.home_titles_text));

        //Populate the common ListView whit the album title, an the RecyclerView which is populated whit the tracks details.
        Arrays arrays = new Arrays(this);
        AlbumsArrayAdapter adapter = new AlbumsArrayAdapter(this, arrays.albumList);
        final ListView primariListView = findViewById(R.id.primary_list);
        primariListView.setAdapter(adapter);

        mTrackTitle = findViewById(R.id.home_track_title);
        mAlbumTitle = findViewById(R.id.home_album_title);

        handleClickEvents();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(INSTANCE_SAVE_TRACK_SELECTED_KEY, isTrackSelected);
        outState.putString(INSTANCE_SAVE_ALBUM_TITLE_KEY, mAlbumTitle.getText().toString());
        outState.putString(INSTANCE_SAVE_TRACK_TITLE_KEY, mTrackTitle.getText().toString());
        outState.putInt(INSTANCE_SAVE_PLAY_VISIBILITY_KEY, mHomePlay.getVisibility());
        outState.putInt(INSTANCE_SAVE_PAUSE_VISIBILITY_KEY, mHomePause.getVisibility());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (!savedInstanceState.containsKey(null)){
            if (savedInstanceState.containsKey(INSTANCE_SAVE_TRACK_SELECTED_KEY)) {
                isTrackSelected = savedInstanceState.getBoolean(INSTANCE_SAVE_TRACK_SELECTED_KEY);
            }
            if (savedInstanceState.containsKey(INSTANCE_SAVE_ALBUM_TITLE_KEY)) {
                mAlbumTitle.setText(savedInstanceState.getString(INSTANCE_SAVE_ALBUM_TITLE_KEY));
            }
            if (savedInstanceState.containsKey(INSTANCE_SAVE_TRACK_TITLE_KEY)) {
                mTrackTitle.setText(savedInstanceState.getString(INSTANCE_SAVE_TRACK_TITLE_KEY));
            }
            if (savedInstanceState.containsKey(INSTANCE_SAVE_PAUSE_VISIBILITY_KEY) && savedInstanceState.containsKey(INSTANCE_SAVE_PLAY_VISIBILITY_KEY)){
                mHomePlay.setVisibility(savedInstanceState.getInt(INSTANCE_SAVE_PLAY_VISIBILITY_KEY));
                mHomePause.setVisibility(savedInstanceState.getInt(INSTANCE_SAVE_PAUSE_VISIBILITY_KEY));
            }
        }
    }

    /**
     * Set the text of the common layout. This method is used in the TrachHolder class’s onClickListener to populate the title TextViews.
     * @param trackTitle The title of the selected track
     * @param albumTitle The title of the selected track's album.
     */
    public static void setTextToCommonAlbumAndTrackTitle(String trackTitle, String albumTitle){
        isTrackSelected = true;
        mTrackTitle.setText(trackTitle);
        mAlbumTitle.setText(albumTitle);
        if (mHomePause.getVisibility() == View.VISIBLE){
            mHomePause.setVisibility(View.GONE);
            mHomePlay.setVisibility(View.VISIBLE);
        }
    }

    public static void getResourceForTrackActivity(int albumCoverIdentifier, String trackTitleText, String trackArtistText){
        mAlbumCoverIdentifier = albumCoverIdentifier;
        mTrackTitleText = trackTitleText;
        mTrackArtistText = trackArtistText;
    }

    public void handleClickEvents(){
        mHomeArrowUpp = findViewById(R.id.home_arrow_upp);
        mHomeArrowUpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrackSelected){
                    Intent intent = new Intent(HomeActivity.this, TrackActivity.class);
                    intent.putExtra(INTENT_COVER_IDENT_KEY, mAlbumCoverIdentifier);
                    intent.putExtra(INTENT_TRACK_TITLE_KEY, mTrackTitleText);
                    intent.putExtra(INTENT_TRACK_ARTIST_KEY, mTrackArtistText);
                    startActivity(intent);
                }
            }
        });

        mHomePlay = findViewById(R.id.home_play);
        mHomePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrackSelected){
                    mHomePlay.setVisibility(View.GONE);
                    mHomePause.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(HomeActivity.this, getBaseContext().getResources().getString(R.string.no_track_selected_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mHomePause = findViewById(R.id.home_pause);
        mHomePause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomePlay.setVisibility(View.VISIBLE);
                mHomePause.setVisibility(View.GONE);
            }
        });
    }
}
