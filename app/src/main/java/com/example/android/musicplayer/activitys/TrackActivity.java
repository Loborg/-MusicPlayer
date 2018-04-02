package com.example.android.musicplayer.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicplayer.R;

public class TrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_layout);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            ImageView albumCover = findViewById(R.id.track_album_cover);
            if (intent.getExtras().containsKey(AlbumActivity.INTENT_TRACK_COVER)){
                albumCover.setImageResource(intent.getIntExtra(AlbumActivity.INTENT_TRACK_COVER, 0));
            } else {
                albumCover.setImageResource(intent.getIntExtra(HomeActivity.INTENT_COVER_IDENT_KEY, 0));
            }

            TextView trackTitle = findViewById(R.id.track_track_title);
            if (intent.getExtras().containsKey(AlbumActivity.INTENT_TRACK_TITLE)){
                trackTitle.setText(intent.getIntExtra(AlbumActivity.INTENT_TRACK_TITLE, 0));
            } else {
                trackTitle.setText(intent.getStringExtra(HomeActivity.INTENT_TRACK_TITLE_KEY));
            }

            TextView trackArtist = findViewById(R.id.track_track_artist);
            if (intent.getExtras().containsKey(AlbumActivity.INTENT_TRACK_ARTIST)){
                trackArtist.setText(intent.getStringExtra(AlbumActivity.INTENT_TRACK_ARTIST));
            } else {
                trackArtist.setText(intent.getStringExtra(HomeActivity.INTENT_TRACK_ARTIST_KEY));
            }
        }
    }
}
