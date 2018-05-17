package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseActivity;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.SslPinningWebViewClient;
import com.smartcityin.waterknow.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web)
    WebView web;
    private String webUrl;
    public static Switch pinningSwitch;
    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_details;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        tvTitle.setText("新闻详情");
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        webUrl = intent.getStringExtra("WebUrl");
        web();
        initweb();
    }

    private void web() {

    }

    private void initweb() {
        initssl();
        final WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许与JS交互
        web.loadUrl(webUrl);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//添加WebView缓存
        web.setWebViewClient(new WebViewClient(){
             @Override
             public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if(error.getPrimaryError() == android.net.http.SslError.SSL_INVALID ){// 校验过程遇到了bug
                                handler.proceed();
                            }else{
                                handler.cancel();
                            }
             }
        });
    }

    private void initssl() {
        SslPinningWebViewClient webViewClient = null;
        try {
            webViewClient = new SslPinningWebViewClient(new SslPinningWebViewClient.LoadedListener() {
                @Override
                public void Loaded(final String url) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            web.loadUrl(url);
                        }
                    });
                }

                @Override
                public void PinningPreventedLoading(final String host) {
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        web.setWebViewClient(webViewClient);
    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }
}
