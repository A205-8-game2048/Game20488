package com.lang.game20488;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 2015/8/3.
 */
public class about extends Activity {
    private LinearLayout aboutView;
    private ImageView aboutImage;
    private int num=0;
    private int[] images=new int[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutView=(LinearLayout)findViewById(R.id.aboutView);
        aboutImage=(ImageView)findViewById(R.id.aboutImage);
       // aboutImage.setImageDrawable(getResources().getDrawable(images[num]));
        images[0]=R.drawable.h1;
        images[1]=R.drawable.h2;
        images[2]=R.drawable.h3;


        aboutView.setOnTouchListener(new View.OnTouchListener() {

            private float startX, startY, offsetX, offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:

                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        //意图：水平方向
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            //意图：向左
                            if (offsetX < -5) {
                                if(num<2)
                                {
                                    num++;
                                    aboutImage.setImageResource(images[num]);
                                }

                            }
                            //意图：向右
                            else if (offsetX > 5) {
                                if(num>0){
                                    num--;
                                    aboutImage.setImageResource(images[num]);
                                }

                            }
                        }

                        break;
                }

                return true;
            }
        });
    }
}
