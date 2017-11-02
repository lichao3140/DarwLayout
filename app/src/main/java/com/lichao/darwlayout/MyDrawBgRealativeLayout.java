package com.lichao.darwlayout;

import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017-11-02.
 */

public class MyDrawBgRealativeLayout extends RelativeLayout {
    //侧滑控件
    private MyDrawSlideBar myDrawSlideBar;
    //背景控件
    MyDrawBgView myDrawBgView;
    public MyDrawBgRealativeLayout(MyDrawSlideBar myDrawSlideBar) {
        super(myDrawSlideBar.getContext());
        init(myDrawSlideBar);
    }

    private void init(MyDrawSlideBar myDrawSlideBar) {
        this.myDrawSlideBar = myDrawSlideBar;
        //把sliderBay的   宽高转移到外面MyDrawBgRealativeLayout
        setLayoutParams(myDrawSlideBar.getLayoutParams());
        //背景先添加进去
        myDrawBgView = new MyDrawBgView(getContext());
        addView(myDrawBgView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //把slideBar  的背景颜色取出来    设置给 myDrawBgView   slideBar弄成透明
        myDrawBgView.setColor(myDrawSlideBar.getBackground());
        myDrawSlideBar.setBackgroundColor(Color.TRANSPARENT);
        addView(myDrawSlideBar,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    /**
     * 传递偏移Y
     * @param y
     * @param slideOffset
     */
    public void setTouchY(float y, float slideOffset) {
        myDrawBgView.setTouchY(y,slideOffset);
        myDrawSlideBar.setTouchY(y, slideOffset);
    }
}
