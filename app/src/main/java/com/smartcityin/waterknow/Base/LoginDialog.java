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
 * Created on 2018/5/16
 * E-mail : wkz123011@gmail.com
 */
public class LoginDialog  extends Dialog{
    protected Button bt_determine;
    protected Button bt_cancel;
    protected TextView tv_content;

    public LoginDialog(@NonNull Context context) {
        super(context, R.style.CustomDialogStyle);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);  // 是否可以撤销
        setContentView(R.layout.login_dialog);
        tv_content=(TextView) this.findViewById(R.id.tv_content);
        bt_determine=(Button) this.findViewById(R.id.bt_determine);
        bt_cancel=(Button)this.findViewById(R.id.bt_cancel);
    }
    /**
     * 设置右键文字和点击事件
     */
    public void setRightButton(String rightStr, final BaseDialog.OnClickerListenerRight listenerRight) {

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
    public void setLeftButton(String leftStr, final BaseDialog.OnClickerListenerLeft listenerLeft) {
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
    public interface OnClickerListenerRight{
        void setOnClicker();
    }
    public interface OnClickerListenerLeft{
        void setOnClicker();
    }
}
