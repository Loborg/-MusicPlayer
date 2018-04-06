package com.example.android.musicplayer.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicplayer.Arrays;
import com.example.android.musicplayer.Key;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.adapters.TracksArrayAdapter;
import com.example.android.musicplayer.models.Track;

import static com.example.android.musicplayer.activitys.HomeActivity.*;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {

    public static TextView mTrackTitle;
    public static TextView mAlbumTitle;
    private ImageView mAlbumArrowUpp;
    public static ImageView mAlbumPlay;
    public static ImageView mAlbumPause;
    private TracksArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);

        TextView activityTitle = findViewById(R.id.title_text_bar);
        activityTitle.setText(getBaseContext().getResources().getString(R.string.album_titles_text));

        Intent intentIn = getIntent();

        Arrays arrays = new Arrays(this);

        if (intentIn.getExtras().containsKey(Key.CURRENT_ALBUM)){
            adapter = new TracksArrayAdapter(this, arrays.albumList.get(intentIn.getIntExtra(Key.CURRENT_ALBUM, 0)).getTrackList());
        } else if (intentIn.getExtras().containsKey(Key.CURRENT_ALBUM_KEY)){
            adapter = new TracksArrayAdapter(this, arrays.albumList.get(intentIn.getIntExtra(Key.CURRENT_ALBUM_KEY, 0)).getTrackList());
        }

        final ListView primaryListView = findViewById(R.id.primary_list);
        primaryListView.setAdapter(adapter);

        handleFooterElementsAndClickEvents();

        primaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track selectedTrack = (Track) parent.getItemAtPosition(position);

                TextView trackTitle = new TextView(getBaseContext());
                trackTitle.setText(selectedTrack.getTrackTitle());

                TextView albumTitle = new TextView(getBaseContext());
                albumTitle.setText(selectedTrack.getAlbumTitle());

                Intent intent = new Intent(AlbumActivity.this, TrackActivity.class);
                intent.putExtra(Key.INTENT_TRACK_TITLE, trackTitle.getText()); //Integer Identifier
                intent.putExtra(Key.INTENT_TRACK_ARTIST, selectedTrack.getAlbumArtist());
                intent.putExtra(Key.INTENT_TRACK_COVER, selectedTrack.getAlbumCover());
                intent.putExtra(Key.INTETN_ALBUM_TITLE, albumTitle.getText()); //Integer Identifier
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_arrow_upp:
                if (HomeActivity.isTrackSelected){
                    Intent intent = new Intent(AlbumActivity.this, TrackActivity.class);
                    //Only using class name to forfeit code readability.
                    intent.putExtra(Key.INTENT_COVER_IDENT_KEY, HomeActivity.mAlbumCoverIdentifier);
                    intent.putExtra(Key.INTENT_TRACK_TITLE_KEY, HomeActivity.mTrackTitleText);
                    intent.putExtra(Key.INTENT_TRACK_ARTIST_KEY, HomeActivity.mTrackArtistText);
                    intent.putExtra(Key.INTENT_PLAY_VISIBILITY_KEY, HomeActivity.playVisibility);
                    intent.putExtra(Key.INTENT_PAUSE_VISIBILITY_KEY, HomeActivity.pauseVisibility);
                    startActivity(intent);
                }
                break;
            case R.id.home_play:
                if (HomeActivity.isTrackSelected){
                    HomeActivity.playVisibility = View.GONE;
                    mAlbumPlay.setVisibility(playVisibility);
                    HomeActivity.pauseVisibility = View.VISIBLE;
                    mAlbumPause.setVisibility(pauseVisibility);
                } else {
                    Toast.makeText(AlbumActivity.this, getBaseContext().getResources().getString(R.string.no_track_selected_toast), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_pause:
                HomeActivity.playVisibility = View.VISIBLE;
                mAlbumPlay.setVisibility(playVisibility);
                HomeActivity.pauseVisibility = View.GONE;
                mAlbumPause.setVisibility(pauseVisibility);
                break;
        }
    }

    /**
     * In this method the <b>mAlbumArrowUpp</b> View onClickListener handles the Intenting of the TrackActivity bay adding the nececery data fot the trough the Intent's putExtra method.<br>
     * The <b>mAlbumPlay</b> and <b>mAlbumPause</b> Views onClickListener handles the behavior of the Play / Pause buttons located int the footer bar.<br>*/
    public void handleFooterElementsAndClickEvents(){

        mTrackTitle = findViewById(R.id.home_track_title);
        mTrackTitle.setText(HomeActivity.mTrackTitleText);

        mAlbumTitle = findViewById(R.id.home_album_title);
        mAlbumTitle.setText(HomeActivity.mAlbumTitleText);

        mAlbumArrowUpp = findViewById(R.id.home_arrow_upp);
        mAlbumArrowUpp.setOnClickListener(this);

        mAlbumPlay = findViewById(R.id.home_play);
        mAlbumPlay.setVisibility(HomeActivity.playVisibility);
        mAlbumPlay.setOnClickListener(this);

        mAlbumPause = findViewById(R.id.home_pause);
        mAlbumPause.setVisibility(HomeActivity.pauseVisibility);
        mAlbumPause.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        HomeActivity.mHomePlay.setVisibility(playVisibility);
        HomeActivity.mHomePause.setVisibility(pauseVisibility);
    }
}
