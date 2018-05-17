package com.smartcityin.waterknow.Base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smartcityin.waterknow.R;
/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */
public class BaseDialog extends Dialog {
    protected TextView tv_ed_phone;
    protected Button bt_determine;
    protected Button bt_cancel;
    protected TextView tv_content;
    protected TextView tv_title;

    public BaseDialog(@NonNull Context context) {
        super(context,R.style.CustomDialogStyle);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);  // 是否可以撤销
        setContentView(R.layout.register_dialog);
        tv_title = (TextView)this.findViewById(R.id.tv_title);
        tv_content=(TextView) this.findViewById(R.id.tv_content);
        tv_ed_phone=(TextView) this.findViewById(R.id.tv_ed_phone);
        bt_determine=(Button) this.findViewById(R.id.bt_determine);
        bt_cancel=(Button)this.findViewById(R.id.bt_cancel);
    }
    /**
     * 设置右键文字和点击事件
     */
    public void setRightButton(String rightStr, final OnClickerListenerRight listenerRight) {

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRight.setOnClicker();
            }
        });
        bt_cancel.setText(rightStr);
    }
    /**
     * 设置左键文字和点击事件
     *
     * @param leftStr 文字
     */
    public void setLeftButton(String leftStr, final OnClickerListenerLeft listenerLeft) {
        bt_determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerLeft.setOnClicker();
            }
        });
        bt_determine.setText(leftStr);
    }
    public  void setTvContent(String content){
        if (tv_content!=null&&content!=null){
            tv_content.setText(content);
            tv_content.setVisibility(View.VISIBLE);
        }
    }
    public void setTitle(String title){
       if (title!=null&&tv_title!=null){
           tv_title.setText(title);
           tv_title.setVisibility(View.VISIBLE);
       }
    }
    /**
     * 设置提示内容
     *
     * @param str 内容
     */
    public void setHintText(String str) {
        if (str!=null&&tv_ed_phone!=null){
            tv_ed_phone.setText(str);
            tv_ed_phone.setVisibility(View.VISIBLE);
        }
    }
    public interface OnClickerListenerRight{
        void setOnClicker();
    }
    public interface OnClickerListenerLeft{
        void setOnClicker();
    }
}
