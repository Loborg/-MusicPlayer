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

/**
 * This is the fourth project for Udacity’s Android Basic Nanodegree Scholarship.<br>
 * The app represents a Music Structure application.<br>
 * The app can’t play music, as it is.<br>
 * The app is for education reasons only, all image material is from <a href="https://www.discogs.com/artist/165243">Discogs</a> website<p>
 * <b>Known issues:</b>
 * <p>
 * <li>	When the home button is pressed in the TrackActivity it resets the HomeActivity’s footer play button even it was set to show the pause button.</li><br>
 * <li>	There is no Album button on the TrackActivity because the given time, I can’t finish the proper behaviour of the intended button.<br>
 * The AlbumActivity is still reachable from the HomeActivity by pressing the album’s title.</li></p> */

public class HomeActivity extends AppCompatActivity {

    public static final String INSTANCE_SAVE_TRACK_SELECTED_KEY = "kTS";
    public static final String INSTANCE_SAVE_ALBUM_TITLE_KEY = "kISAT";
    public static final String INSTANCE_SAVE_TRACK_TITLE_KEY = "kISTT";
    public static final String INSTANCE_SAVE_PLAY_VISIBILITY_KEY = "kISPlV";
    public static final String INSTANCE_SAVE_PAUSE_VISIBILITY_KEY = "kISPaV";
    public static final String INTENT_COVER_IDENT_KEY = "kIC";
    public static final String INTENT_TRACK_TITLE_KEY = "kITT";
    public static final String INTENT_TRACK_ARTIST_KEY = "kITA";
    public static final String INTENT_PLAY_VISIBILITY_KEY = "kIPLV";
    public static final String INTENT_PAUSE_VISIBILITY_KEY = "kIPAV";
    public static final String INTETN_ALBUM_TITLE_KEY = "intetn_album_title_key";
    public static final String CURRENT_ALBUM_KEY = "kCA";
    public static TextView mTrackTitle;
    public static TextView mAlbumTitle;
    public static ImageView mHomePlay;
    public static ImageView mHomePause;
    public static boolean isTrackSelected;
    public static int mAlbumCoverIdentifier;
    public static String mTrackArtistText, mTrackTitleText, mAlbumTitleText;
    public static int playVisibility, pauseVisibility;
    public static int mTrackPostition, mAlbumPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);

        isTrackSelected = false;

        TextView activityTitle = findViewById(R.id.title_text_bar);
        activityTitle.setText(getBaseContext().getResources().getString(R.string.home_titles_text));

        //Populate the common ListView whit the album title, an the RecyclerView which is populated whit the tracks details.
        Arrays arrays = new Arrays(this);
        AlbumsArrayAdapter adapter = new AlbumsArrayAdapter(this, arrays.albumList);
        final ListView primariListView = findViewById(R.id.primary_list);
        primariListView.setAdapter(adapter);

        mTrackTitle = findViewById(R.id.home_track_title);
        mAlbumTitle = findViewById(R.id.home_album_title);

        //Sets the footer element if the intent is from the TrackActivity
        try{
            if (!getIntent().getExtras().containsKey(null)) {
                isTrackSelected = true;
                mTrackTitle.setText(mTrackTitleText);
                mAlbumTitle.setText(mAlbumTitleText);
                mHomePlay.setVisibility(playVisibility);
                mHomePause.setVisibility(pauseVisibility);
            }
        } catch (NullPointerException e){
            try{
                if (savedInstanceState.containsKey(null)){
                    playVisibility = View.VISIBLE;
                    pauseVisibility = View.GONE;
                }
            } catch (NullPointerException k){
                playVisibility = View.VISIBLE;
                pauseVisibility = View.GONE;
            }
        }

        handleFooterElementsAndClickEvents();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(INSTANCE_SAVE_TRACK_SELECTED_KEY, isTrackSelected);
        outState.putString(INSTANCE_SAVE_ALBUM_TITLE_KEY, mAlbumTitle.getText().toString());
        outState.putString(INSTANCE_SAVE_TRACK_TITLE_KEY, mTrackTitle.getText().toString());
        outState.putInt(INSTANCE_SAVE_PLAY_VISIBILITY_KEY, playVisibility);
        outState.putInt(INSTANCE_SAVE_PAUSE_VISIBILITY_KEY, pauseVisibility);
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
     * This method is used to get the necessary resource from the TrackHolder class's selected Track<br>
     * This method is used in the TrackHolder class’s onClickListener
     * @param albumCoverIdentifier This identifies the selected album cover
     * @param trackTitleText This is the text of the selected track's title
     * @param trackArtistText This is the text of the selected track's Artist
     * @param albumTitleText This is the text of the selected track's Album title
     * */
    public static void getResourceFromTrackHolder(int albumCoverIdentifier, String trackTitleText, String trackArtistText, String albumTitleText, int trackPosition){
        isTrackSelected = true;

        mAlbumCoverIdentifier = albumCoverIdentifier;
        mTrackTitleText = trackTitleText;
        mTrackArtistText = trackArtistText;
        mAlbumTitleText = albumTitleText;

        mTrackPostition = trackPosition;

        mTrackTitle.setText(mTrackTitleText);
        mAlbumTitle.setText(mAlbumTitleText);

        if (pauseVisibility == View.VISIBLE){
            pauseVisibility = View.GONE;
            mHomePause.setVisibility(pauseVisibility);
            playVisibility = View.VISIBLE;
            mHomePlay.setVisibility(playVisibility);
        }
    }

    /**
     * In this method the <b>mHomeArrowUpp</b> View onClickListener handles the Intenting of the TrackActivity bay adding the nececery data fot the trough the Intent's putExtra method.<br>
     * The <b>mHomePlay</b> and <b>mHomePause</b> Views onClickListener handles the behavior of the Play / Pause buttons located int the footer bar.<br>*/
    public void handleFooterElementsAndClickEvents(){

        ImageView mHomeArrowUpp;
        mHomeArrowUpp = findViewById(R.id.home_arrow_upp);
        mHomeArrowUpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrackSelected){
                    Intent intent = new Intent(HomeActivity.this, TrackActivity.class);
                    intent.putExtra(INTENT_COVER_IDENT_KEY, mAlbumCoverIdentifier);
                    intent.putExtra(INTENT_TRACK_TITLE_KEY, mTrackTitleText);
                    intent.putExtra(INTENT_TRACK_ARTIST_KEY, mTrackArtistText);
                    intent.putExtra(INTETN_ALBUM_TITLE_KEY, mAlbumTitleText);
                    intent.putExtra(INTENT_PLAY_VISIBILITY_KEY, playVisibility);
                    intent.putExtra(INTENT_PAUSE_VISIBILITY_KEY, pauseVisibility);
                    intent.putExtra(CURRENT_ALBUM_KEY, mAlbumPosition);
                    startActivity(intent);
                }
            }
        });

        mHomePlay = findViewById(R.id.home_play);
        mHomePlay.setVisibility(playVisibility);
        mHomePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrackSelected){
                    playVisibility = View.GONE;
                    mHomePlay.setVisibility(playVisibility);
                    pauseVisibility = View.VISIBLE;
                    mHomePause.setVisibility(pauseVisibility);
                } else {
                    Toast.makeText(HomeActivity.this, getBaseContext().getResources().getString(R.string.no_track_selected_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mHomePause = findViewById(R.id.home_pause);
        mHomePause.setVisibility(pauseVisibility);
        mHomePause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVisibility = View.VISIBLE;
                mHomePlay.setVisibility(playVisibility);
                pauseVisibility = View.GONE;
                mHomePause.setVisibility(pauseVisibility);
            }
        });
    }
}
