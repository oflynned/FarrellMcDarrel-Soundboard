package com.syzible.farrellmcdarrel;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    GridView gridView;

    ArrayList<ButtonHolder> buttons = new ArrayList<>();
    int[] sounds = {
            R.raw.comeawayouttathat,
            R.raw.cuppateayawill,
            R.raw.dontmindthatdogfly,
            R.raw.gimmethat,
            R.raw.hellomichael,
            R.raw.hupterfuck,
            R.raw.isaiddontmindthatfuckingdog,
            R.raw.nothalfthedogshepwas,
            R.raw.nothingouttheretobescaredof,
            R.raw.please,
            R.raw.spongecake,
            R.raw.whatsthat
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonHolder ComeAwayOuttaThat = new ButtonHolder("Come away outta that!", sounds[0], 0);
        ButtonHolder CuppaTaeYaWill = new ButtonHolder("Cuppa tae ya will?", sounds[1], 1);
        ButtonHolder DontMindFly = new ButtonHolder("Don't mind that dog Fly", sounds[2], 2);
        ButtonHolder GimmeThat = new ButtonHolder("Gimme that!", sounds[3], 3);
        ButtonHolder HelloMichael = new ButtonHolder("Hello Michael!", sounds[4], 4);
        ButtonHolder HupTerFuck = new ButtonHolder("Hup ter fuck!", sounds[5], 5);
        ButtonHolder ISaid = new ButtonHolder("I said don't mind that dog!", sounds[6], 6);
        ButtonHolder Shep = new ButtonHolder("Not half the dog Shep was...", sounds[7], 7);
        ButtonHolder NothingOutThere = new ButtonHolder("Nothing out there to be scared of", sounds[8], 8);
        ButtonHolder Please = new ButtonHolder("Please!", sounds[9], 9);
        ButtonHolder SpongeCake = new ButtonHolder("Sponge cake", sounds[10], 10);
        ButtonHolder WhatsThat = new ButtonHolder("What's that?!", sounds[11], 11);
        ButtonHolder Stop = new ButtonHolder("Stop", 0, 12);

        buttons.add(ComeAwayOuttaThat);
        buttons.add(CuppaTaeYaWill);
        buttons.add(DontMindFly);
        buttons.add(GimmeThat);
        buttons.add(HelloMichael);
        buttons.add(HupTerFuck);
        buttons.add(ISaid);
        buttons.add(Shep);
        buttons.add(NothingOutThere);
        buttons.add(Please);
        buttons.add(SpongeCake);
        buttons.add(WhatsThat);
        buttons.add(Stop);

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ButtonAdapter(MainActivity.this));
    }

    public void playAudioFile(int audioFile) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(MainActivity.this, audioFile);
        mediaPlayer.start();
    }

    public void stopAudioFile() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
        } else if (id == R.id.action_google_play) {
            String url = "market://search?q=pub:Syzible";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private class ButtonAdapter extends BaseAdapter {
        Context context;

        ButtonAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return buttons.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = new Button(context);
                RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                button.setLayoutParams(buttonParams);
            } else {
                button = (Button) convertView;
            }
            button.setText(buttons.get(position).getTitle());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttons.get(position).getID() == 12)
                        stopAudioFile();
                    else playAudioFile(buttons.get(position).getAudioFile());
                }
            });
            return button;
        }
    }

    private class ButtonHolder {
        String title;
        int id, audioFile;

        ButtonHolder(String title, int audioFile, int id) {
            this.title = title;
            this.audioFile = audioFile;
            this.id = id;
        }

        String getTitle() {
            return title;
        }

        int getAudioFile() {
            return audioFile;
        }

        int getID() {
            return id;
        }
    }
}