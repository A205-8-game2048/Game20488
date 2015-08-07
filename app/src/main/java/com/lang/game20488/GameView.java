package com.lang.game20488;

/**
 * Created by lang on 2015/8/1.
 */
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by langalng on 2015/7/30.
 */
public class GameView extends  GridLayout {
    public GameView(Context context) {
        super(context);
        initGameView();
        gameView=this;
    }
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
        gameView=this;
    }
    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
        gameView=this;
    }
    public  void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xaabbada0);

        setOnTouchListener(new View.OnTouchListener() {
            private float starX, starY, offsetx, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        starX = event.getX();
                        starY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetx = event.getX() - starX;
                        offsetY = event.getY() - starY;
                        if (Math.abs(offsetx) > Math.abs(offsetY)) {
                            if (offsetx < -5) swipeLifte();
                            else if (offsetx > 5) swipeRight();
                        } else {
                            if (offsetY < -5) swipeUp();
                            else if (offsetY > 5) swipeDown();
                        }
                        break;
                }
                return true;
            }


        });

    }
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth=(Math.min(w,h)-10)/4;
        addCard(cardWidth, cardWidth);
        startGame();
    }
    public void addCard(int cardWidth,int cardHeight){
        Card c;
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                c=new Card(getContext());
                c.setNum(0);
                addView(c, cardWidth, cardHeight);
                cardMap[i][j]=c;
            }

        }

    }
    private void addRandomNum(){
        emptyPoints.clear();
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                if(cardMap[i][j].getNum()<=0){
                    emptyPoints.add(new Point(i,j));
                }
            }
        }
        Point p=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);

    }
    public void startGame(){
        MainActivity.getMainActivity().showScore();
        MainActivity.getMainActivity().showBest();

        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                cardMap[i][j].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
    }
    public void swipeLifte(){
        boolean flag=false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y+1; y1 < 4; y1++) {
                    if (cardMap[x][y1].getNum()>0) {

                        if (cardMap[x][y].getNum()<=0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);

                            y--;
                            flag=true;


                        }else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().Sound.play(1,1,1,0,0,1);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            flag=true;
                        }

                        break;

                    }
                }
            }
        }
        if(flag){
            addRandomNum();

            checkComplete();
        }
    }
    public void swipeRight(){
        boolean flag=false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardMap[x][y1].getNum() > 0) {

                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);

                            y++;
                            flag=true;
                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().Sound.play(1, 1, 1, 0, 0, 1);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            flag=true;
                        }

                        break;
                    }
                }
            }
        }
        if(flag){
            addRandomNum();

            checkComplete();
        }
    }
    public void swipeUp(){
        boolean flag=false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardMap[x1][y].getNum() > 0) {

                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);

                            x--;

                            flag=true;
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().Sound.play(1, 1, 1, 0, 0, 1);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            flag=true;
                        }

                        break;
                    }
                }
            }
        }
        if(flag){
            addRandomNum();

            checkComplete();
        }
    }
    public void swipeDown(){
        boolean flag=false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >=0; x--) {

                for (int x1 = x-1; x1 >=0; x1--) {
                    if (cardMap[x1][y].getNum()>0) {

                        if (cardMap[x][y].getNum()<=0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x++;
                            flag=true;

                        }else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().Sound.play(1, 1, 1, 0, 0, 1);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            flag=true;
                        }

                        break;
                    }
                }
            }
        }
        if(flag){
            addRandomNum();

            checkComplete();
        }

    }
    private void checkComplete(){
        boolean flag=true;
        ALL:
        for (int x = 0; x <4 ; x++) {
            for (int y = 0; y <4 ; y++) {
                if(cardMap[x][y].getNum()==0||
                        (x>0&&cardMap[x-1][y].equals(cardMap[x][y])||
                                (x<3&&cardMap[x+1][y].equals(cardMap[x][y])||
                                        (y>0&&cardMap[x][y-1].equals(cardMap[x][y])||
                                                (y<3&&cardMap[x][y+1].equals(cardMap[x][y])
                                                )
                                        )
                                )
                        )
                        ){
                    flag=false;
                    break ALL;

                }

            }

        }
        if(flag){
            MainActivity.getMainActivity().writeBest();
            new AlertDialog.Builder(getContext()).setTitle("啊哦").setMessage("游戏结束！").setPositiveButton("再来一次", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                    MainActivity.getMainActivity().cleaScore();
                }
            }).show();
        }
    }
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    protected static GameView gameView=null;
    public static GameView getGameView(){
        return gameView;
    }
    private Card[][] cardMap=new Card[4][4];
    private List<Point> emptyPoints=new ArrayList<Point>();
}
