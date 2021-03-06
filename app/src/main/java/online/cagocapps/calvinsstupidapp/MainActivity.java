package online.cagocapps.calvinsstupidapp;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private int i = 0;
    private String path;
    private Button button;
    private Long length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        button = (Button) findViewById(R.id.button);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public void playVideo(View view) {
        if (!videoView.isPlaying()) {
            if (i == 0) {
                path = "android.resource://" + getPackageName() + "/" + R.raw.donteatme;
                length = (long) 1750;
            } else if (i == 1) {
                path = "android.resource://" + getPackageName() + "/" + R.raw.imalady;
                length = (long) 4000;
            } else if (i == 2) {
                path = "android.resource://" + getPackageName() + "/" + R.raw.peeingmyself;
                length = (long) 1750;
            } else if (i == 3) {
                path = "android.resource://" + getPackageName() + "/" + R.raw.scream;
                length = (long) 4000;
            } else {
                path = "android.resource://" + getPackageName() + "/" + R.raw.silentnight;
                length = (long) 115000;
            }
            MediaController mc = new MediaController(videoView.getContext());
            mc.setAnchorView(videoView);
            mc.setMediaPlayer(videoView);
            videoView.setVisibility(View.VISIBLE);
            button.setVisibility(View.INVISIBLE);

            videoView.setMediaController(mc);
            videoView.setVideoURI(Uri.parse(path));
            videoView.start();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    goBack();
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, length);
        }
    }

    private void goBack(){
        while (videoView.isPlaying()){}
        videoView.stopPlayback();
        videoView.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        i = i +1;
        if (i == 5) i = 0;
    }
}