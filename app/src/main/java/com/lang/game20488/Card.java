package com.lang.game20488;

/**
 * Created by lang on 2015/8/1.
 */
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by langalng on 2015/7/30.
 */
public class Card extends FrameLayout{
    public Card(Context context) {
        super(context);
        label=new TextView(getContext());
        label.setBackgroundColor(0x33ffffff);
        label.setTextSize(40);
        label.setGravity(Gravity.CENTER);
        LayoutParams lp=new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);

        addView(label, lp);
        setNum(0);
    }
    public int num=0;

    public void setNum(int num) {
        this.num = num;
        setCardColor(num);
        if(num<=0){
            label.setText("");
        }else label.setText(""+num+"");
    }
    private void setCardColor(int num){
        switch (num){
            case 0:label.setBackgroundColor(0x33ffffff);
                break;
            case 2:label.setBackgroundColor(0x10EEB422);
                break;
            case 4:label.setBackgroundColor(0x20EEB422);
                break;
            case 8:label.setBackgroundColor(0x30EEB422);
                break;
            case 16:label.setBackgroundColor(0x40EEB422);
                break;
            case 32:label.setBackgroundColor(0x50EEB422);
                break;
            case 64:label.setBackgroundColor(0x60EEB422);
                break;
            case 128:label.setBackgroundColor(0x70EEB422);
                break;
            case 256:label.setBackgroundColor(0x80EEB422);
                break;
            case 512:label.setBackgroundColor(0x90EEB422);
                break;
            case 1024:label.setBackgroundColor(0xA0EEB422);
                break;
            case 2048:label.setBackgroundColor(0xB0EEB422);
                break;
            case 4096:label.setBackgroundColor(0xC0EEB422);
                break;
            case 8192:label.setBackgroundColor(0xD0EEB422);
                break;
            case 16384:label.setBackgroundColor(0xE0EEB422);
                break;
        }
    }

    public int getNum() {
        return num;
    }
    public boolean equals(Card c){
        return getNum()==c.getNum();
    }
    private TextView label;
}