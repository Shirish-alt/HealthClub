package com.example.healthclub_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.healthclub_app.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class FullScreen extends YouTubeBaseActivity{

    private final String API_KEY="AIzaSyAZwrLny62oIDj9oo8Wuz_LwTy8ze4OGr8";
    String url;
    YouTubePlayerView youtubeplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        youtubeplayer=findViewById( R.id.youtubeplayer );

        url=getIntent().getStringExtra( "vid_id" );
        Log.e("Url",url);

        youtubeplayer.initialize( API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo( url );
                    youTubePlayer.setPlayerStyle( YouTubePlayer.PlayerStyle.DEFAULT );

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText( FullScreen.this, "Err:"+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}