package com.lichao.darwlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017-11-02.
 */

public class MyDrawSlideBar extends LinearLayout {

    private boolean  opened=false;
    float maxTranslationX;

    public MyDrawSlideBar(Context context) {
        this(context, null);
    }

    public MyDrawSlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SideBar);
            maxTranslationX = a.getDimension(R.styleable.SideBar_maxTranslationX, 0);
            a.recycle();
        }
    }

    public void setTouchY(float y, float slideOffset) {
        //遍历全部子控件  给每一个子控件进行偏移
        //如果slideOffset =1   侧滑菜单全部出来了
        opened=slideOffset==1;
        for (int i=0;i<getChildCount();i++) {
            View child= getChildAt(i);
            child.setPressed(false);
            //要判断  y坐落在哪一个子控件    松手的那一刻  进行回调  跳转其他页面
            boolean isHover=opened && y>child.getTop() && y<child.getBottom();
            if (isHover) {
                child.setPressed(true);
                //回调调用层
            }
            //偏移方法
            apply((ViewGroup)getParent(), child, y, slideOffset);
        }
    }

    public void apply(ViewGroup MyDrawSlideBar, View itemView, float touchY, float slideOffset) {
        float translationX=0;
        int centerY = itemView.getTop() + itemView.getHeight() / 2;
        float distance = Math.abs(touchY - centerY);
        float scale = distance / MyDrawSlideBar.getHeight()*3 ; // 距离中心点距离与 MyDrawSlideBar 的 1/3 对比
        translationX =   maxTranslationX - scale * maxTranslationX ;
        Log.i("tuch","maxTranslationX  "+maxTranslationX+"   touchY  "+ touchY+"   slideOffset  "+ slideOffset + "   偏移量  " + translationX * slideOffset);
        itemView.setTranslationX(translationX * slideOffset);
    }

    /**
     * 手指 弹起来
     */
    public void onMotionEventUp() {
        for (int i =0; opened && i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.isPressed()) {
                view.performClick();
                //回调操作
                return;
            }
        }
    }
}
