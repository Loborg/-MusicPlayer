package com.example.android.musicplayer.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.musicplayer.Arrays;
import com.example.android.musicplayer.R;
import com.example.android.musicplayer.adapters.TracksArrayAdapter;
import com.example.android.musicplayer.models.Track;

public class AlbumActivity extends AppCompatActivity {

    public static final String INTENT_TRACK_TITLE = "track_title";
    public static final String INTENT_TRACK_ARTIST = "track_artist";
    public static final String INTENT_TRACK_COVER = "track_cover";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);

        TextView activityTitle = findViewById(R.id.title_text_bar);
        activityTitle.setText(getBaseContext().getResources().getString(R.string.album_titles_text));

        Arrays arrays = new Arrays(this);
        TracksArrayAdapter adapter = new TracksArrayAdapter(this, arrays.trackList_2);
        final ListView primaryListView = findViewById(R.id.primary_list);
        primaryListView.setAdapter(adapter);

        primaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track selectedTrack = (Track) parent.getItemAtPosition(position);

                Intent intent = new Intent(AlbumActivity.this, TrackActivity.class);
                intent.putExtra(INTENT_TRACK_TITLE, selectedTrack.getTrackTitle());
                intent.putExtra(INTENT_TRACK_ARTIST, selectedTrack.getAlbumArtist());
                intent.putExtra(INTENT_TRACK_COVER, selectedTrack.getAlbumCover());
                startActivity(intent);
            }
        });
    }
}
