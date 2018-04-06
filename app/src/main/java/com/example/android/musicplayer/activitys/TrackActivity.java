package com.example.android.musicplayer.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.Key;
import com.example.android.musicplayer.R;

public class TrackActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView playButton, pauseButton;
    private Intent intentIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_layout);

        TextView activityTitle = findViewById(R.id.title_text_bar);
        activityTitle.setText(getBaseContext().getResources().getString(R.string.track_titles_text));

        TextView homeButton = findViewById(R.id.home_button);
        homeButton.setText(getBaseContext().getResources().getString(R.string.home_titles_text));

        TextView albumButton = findViewById(R.id.album_button);
        //albumButton.setText(getBaseContext().getResources().getString(R.string.album_titles_text));

        intentIn = getIntent();

        //Sets up the TrackAktivity Views basd on the location the intentIn was coming from.
        if (intentIn.getExtras() != null){

            ImageView albumCover = findViewById(R.id.track_album_cover);
            if (intentIn.getExtras().containsKey(Key.INTENT_TRACK_COVER)){
                albumCover.setImageResource(intentIn.getIntExtra(Key.INTENT_TRACK_COVER, 0));
            } else {
                albumCover.setImageResource(intentIn.getIntExtra(Key.INTENT_COVER_IDENT_KEY, 0));
            }

            TextView trackTitle = findViewById(R.id.track_track_title);
            if (intentIn.getExtras().containsKey(Key.INTENT_TRACK_TITLE)){
                trackTitle.setText(intentIn.getStringExtra(Key.INTENT_TRACK_TITLE));
            } else {
                trackTitle.setText(intentIn.getStringExtra(Key.INTENT_TRACK_TITLE_KEY));
            }

            TextView trackArtist = findViewById(R.id.track_track_artist);
            if (intentIn.getExtras().containsKey(Key.INTENT_TRACK_ARTIST)){
                trackArtist.setText(intentIn.getStringExtra(Key.INTENT_TRACK_ARTIST));
            } else {
                trackArtist.setText(intentIn.getStringExtra(Key.INTENT_TRACK_ARTIST_KEY));
            }

            playButton = findViewById(R.id.track_play);
            if (intentIn.getExtras().containsKey(Key.INTENT_PLAY_VISIBILITY_KEY)){
                playButton.setVisibility(intentIn.getIntExtra(Key.INTENT_PLAY_VISIBILITY_KEY, 0));
            }

            pauseButton = findViewById(R.id.track_pause);
            if (intentIn.getExtras().containsKey(Key.INTENT_PAUSE_VISIBILITY_KEY)){
                pauseButton.setVisibility(intentIn.getIntExtra(Key.INTENT_PAUSE_VISIBILITY_KEY, 0));
            }
        }

        //Sets the main button image status mased on the intentIn was coming from.
        if (intentIn.getExtras().containsKey(Key.INTENT_TRACK_ARTIST)
                || intentIn.getExtras().containsKey(Key.INTENT_TRACK_TITLE)
                || intentIn.getExtras().containsKey(Key.INTENT_TRACK_COVER) && playButton.getVisibility() == View.VISIBLE){

            HomeActivity.playVisibility = View.VISIBLE;
            HomeActivity.pauseVisibility = View.GONE;

            HomeActivity.mHomePlay.setVisibility(HomeActivity.playVisibility);
            HomeActivity.mHomePause.setVisibility(HomeActivity.pauseVisibility);

            AlbumActivity.mAlbumPlay.setVisibility(HomeActivity.playVisibility);
            AlbumActivity.mAlbumPause.setVisibility(HomeActivity.pauseVisibility);
        }

        playButton.setOnClickListener(this);

        pauseButton.setOnClickListener(this);

        //In the givn time I can't make the buttom's behavior properly, so I hid it from the UI
        //Tha AlbumActivity is still reachable from the HomeActivity by clicking the Album title.
        //I commented out the code for later use.
        albumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intentOutAlbum = new Intent(TrackActivity.this, AlbumActivity.class);
                intentOutAlbum.putExtra(HomeActivity.CURRENT_ALBUM_KEY, intentIn.getIntExtra(HomeActivity.CURRENT_ALBUM_KEY, 0));
                startActivity(intentOutAlbum);*/
            }
        });

        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_button:
                Intent intentOutHome = new Intent(TrackActivity.this, HomeActivity.class);
                intentOutHome.putExtra("luckup", 0);
                startActivity(intentOutHome);
                break;
            //Handles the play button action.
            case R.id.track_play:
                HomeActivity.isTrackSelected = true;
                HomeActivity.playVisibility = View.GONE;
                HomeActivity.pauseVisibility = View.VISIBLE;

                playButton.setVisibility(HomeActivity.playVisibility);
                pauseButton.setVisibility(HomeActivity.pauseVisibility);

                if (intentIn.getExtras().containsKey(Key.INTENT_TRACK_COVER)){
                    HomeActivity.mAlbumCoverIdentifier = intentIn.getIntExtra(Key.INTENT_TRACK_COVER, 0);
                } else {
                    HomeActivity.mAlbumCoverIdentifier = intentIn.getIntExtra(Key.INTENT_COVER_IDENT_KEY, 0);
                }

                //Sets up the other two activitys footer text and button status. If the intentIn is from the AlbumActivity primary list.*/
                if (intentIn.getExtras().containsKey(Key.INTETN_ALBUM_TITLE)
                        || intentIn.getExtras().containsKey(Key.INTENT_TRACK_TITLE)){

                    HomeActivity.mAlbumTitleText = intentIn.getStringExtra(Key.INTETN_ALBUM_TITLE);
                    HomeActivity.mTrackTitleText = intentIn.getStringExtra(Key.INTENT_TRACK_TITLE);

                    HomeActivity.mAlbumTitle.setText(HomeActivity.mAlbumTitleText);
                    HomeActivity.mTrackTitle.setText(HomeActivity.mTrackTitleText);
                    HomeActivity.mHomePlay.setVisibility(HomeActivity.playVisibility);
                    HomeActivity.mHomePause.setVisibility(HomeActivity.pauseVisibility);

                    AlbumActivity.mAlbumTitle.setText(HomeActivity.mAlbumTitleText);
                    AlbumActivity.mTrackTitle.setText(HomeActivity.mTrackTitleText);
                    AlbumActivity.mAlbumPlay.setVisibility(HomeActivity.playVisibility);
                    AlbumActivity.mAlbumPause.setVisibility(HomeActivity.pauseVisibility);
                }

                //Sets up the HomeActivitys footer text and button status. if the intentIn is from the HomeActivity
                if (intentIn.getExtras().containsKey(Key.INTETN_ALBUM_TITLE_KEY)
                        || intentIn.getExtras().containsKey(Key.INTENT_TRACK_TITLE_KEY)){

                    HomeActivity.mAlbumTitleText = intentIn.getStringExtra(Key.INTETN_ALBUM_TITLE_KEY);
                    HomeActivity.mTrackTitleText = intentIn.getStringExtra(Key.INTENT_TRACK_TITLE_KEY);

                    HomeActivity.mAlbumTitle.setText(HomeActivity.mAlbumTitleText);
                    HomeActivity.mTrackTitle.setText(HomeActivity.mTrackTitleText);
                    HomeActivity.mHomePlay.setVisibility(HomeActivity.playVisibility);
                    HomeActivity.mHomePause.setVisibility(HomeActivity.pauseVisibility);

                    try{
                        AlbumActivity.mAlbumTitle.setText(Key.INTETN_ALBUM_TITLE_KEY);
                        AlbumActivity.mTrackTitle.setText(HomeActivity.mTrackTitleText);
                        AlbumActivity.mAlbumPlay.setVisibility(HomeActivity.playVisibility);
                        AlbumActivity.mAlbumPause.setVisibility(HomeActivity.pauseVisibility);
                    } catch (NullPointerException e){}
                }
                break;
            //Handles the pause button action.
            case R.id.track_pause:
                HomeActivity.playVisibility = View.VISIBLE;
                HomeActivity.pauseVisibility = View.GONE;

                playButton.setVisibility(HomeActivity.playVisibility);
                pauseButton.setVisibility(HomeActivity.pauseVisibility);

                HomeActivity.mHomePlay.setVisibility(HomeActivity.playVisibility);
                HomeActivity.mHomePause.setVisibility(HomeActivity.pauseVisibility);

                try{
                    AlbumActivity.mAlbumPlay.setVisibility(HomeActivity.playVisibility);
                    AlbumActivity.mAlbumPause.setVisibility(HomeActivity.pauseVisibility);
                } catch (NullPointerException e){}
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("play_state", HomeActivity.playVisibility);
        outState.putInt("pause_state", HomeActivity.pauseVisibility);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("play_state") && savedInstanceState.containsKey("pause_state")){
            HomeActivity.playVisibility = savedInstanceState.getInt("play_state");
            playButton.setVisibility(HomeActivity.playVisibility);
            HomeActivity.pauseVisibility = savedInstanceState.getInt("pause_state");
            pauseButton.setVisibility(HomeActivity.pauseVisibility);
        }
    }
}
