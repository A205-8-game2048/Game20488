package com.lang.game20488;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Home extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

    }

    public void goTo4x4(View view){
        Intent intent=new Intent(Home.this,MainActivity.class);
        startActivity(intent);

    }
    public void toabout(View view){
        Intent intent=new Intent(Home.this,about.class);
        startActivity(intent);
    }



}
