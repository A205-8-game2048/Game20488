package com.lang.game20488;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends Activity {
    public MainActivity(){

        mainActivity=this;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tvscore = (TextView) findViewById(R.id.tvscore);
        tvbest=(TextView)findViewById(R.id.tvbest);
        restar=(Button)findViewById(R.id.restar);
        goToHome=(Button)findViewById(R.id.goToHome);
        soundSwitch=(Button)findViewById(R.id.soundSwitch);
        preferences= getSharedPreferences("file", MODE_APPEND);
        editor=preferences.edit();
        soundSwitchis=preferences.getBoolean("sound",true);
        if(soundSwitchis) {
            Sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            Sounds.put(1, Sound.load(this, R.raw.huadong, 1));
            soundSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.btsoundon));
        }else {
            Sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            Sounds.put(1, Sound.load(this, R.raw.huadong1, 1));
            soundSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.btsoundoff));
        }


    }

    public void cleaScore(){
        score=0;
        showScore();
    }
    public void showScore(){
        tvscore.setText(score+"");
        tvscore.setTextSize(30);

    }
    public void showBest(){
        tvbest.setTextSize(30);
        int best=preferences.getInt("best",0);
        if(best<=0){
            tvbest.setText(0+"");
        }
        tvbest.setText(best+"");
    }
    public void writeBest(){

        String currentStr=tvscore.getText().toString();
        int current=Integer.parseInt(currentStr);
        int best=preferences.getInt("best",0);
        if(current>best){
            editor.putInt("best",current);
            editor.commit();
        }
    }
    public void addScore(int sc){
        score+=sc;
        showScore();
    }
    public void goToHome(View view){
        Intent intent=new Intent(MainActivity.this,Home.class);
        startActivity(intent);

    }
    public void change(View view){
        soundSwitchis=!(preferences.getBoolean("sound",true));
        editor.putBoolean("sound", soundSwitchis);
        editor.commit();
        if(soundSwitchis) {
            Sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            Sounds.put(1, Sound.load(this, R.raw.huadong, 1));
            soundSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.btsoundon));
        }else {
            Sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            Sounds.put(1, Sound.load(this, R.raw.huadong1, 1));
            soundSwitch.setBackgroundDrawable(getResources().getDrawable(R.drawable.btsoundoff));
        }

    }
    public void restar(View view){
        cleaScore();
        GameView.getGameView().startGame();
    }
    TextView tvscore,tvbest;
    Button restar,goToHome,soundSwitch;
    int score;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SoundPool Sound;
    boolean soundSwitchis;
    HashMap<Integer,Integer> Sounds=new HashMap<>();
    protected static MainActivity mainActivity=null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }


}
