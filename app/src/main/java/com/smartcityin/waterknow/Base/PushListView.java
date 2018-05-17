package com.smartcityin.waterknow.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Author : Mr.老王
 * Created on 2018/5/9
 * E-mail : wkz123011@gmail.com
 */
public class PushListView extends ListView {
    public PushListView(Context context) {
        super(context);
    }

    public PushListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PushListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {//到头部了
            getParent().requestDisallowInterceptTouchEvent(false);//放行触摸
        } else {//没有到头部
            getParent().requestDisallowInterceptTouchEvent(true);//拦截触摸
        }
        return super.onInterceptTouchEvent(ev);
    }
}
