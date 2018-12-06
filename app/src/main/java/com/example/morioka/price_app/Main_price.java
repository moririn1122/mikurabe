package com.example.morioka.price_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main_price extends AppCompatActivity{

    WebView mWebView1, mWebView2;
    String str1 = null;
    String str2 = null;
    String str3 = "https://www.amazon.co.jp/gp/aw/s/ref=nb_sb_noss?k=";
    String str4 = "https://search.rakuten.co.jp/search/mall/";
    String urlStringzo = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll =new LinearLayout(this);
        setContentView(R.layout.activity_main);

        //検索ボタンの動作
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText4);
                String urlString = editText.getText().toString();

                mWebView1.loadUrl(str3+urlString);
                mWebView2.loadUrl(str4+urlString);

            }
        });

        //webview1のページを一つ戻す動作
        findViewById(R.id.backbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView1.canGoBack()){
                    mWebView1.goBack();
                }
            }
        });

        //webview2のページを一つ戻す動作
        findViewById(R.id.backbutton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView2.canGoBack()){
                    mWebView2.goBack();
                }
            }
        });

        //ソフトキーボードのエンターキーが押されたときの動作
        EditText editText = (EditText) findViewById(R.id.editText4);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //EnterKeyが押されたかを判定
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    //ソフトキーボードを閉じる
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        // Webビューの作成
        //WebView mWebView1_1 = (WebView) findViewById(R.id.webView01);
        mWebView1 = (WebView) findViewById(R.id.webView01);
        mWebView1.setVerticalScrollbarOverlay(true);
        mWebView1.setWebViewClient(new WebViewClient());
        mWebView1.loadUrl("https://www.amazon.co.jp/gp/aw/s/ref=nb_sb_noss?k=");  //URLの設定

        //WebView mWebView2_2 = (WebView) findViewById(R.id.webView02);
        mWebView2 = (WebView) findViewById(R.id.webView02);
        mWebView2.setVerticalScrollbarOverlay(true);
        mWebView2.setWebViewClient(new WebViewClient());
        mWebView2.loadUrl("https://search.rakuten.co.jp/search/mall/");  //URLの設定

        WebSettings settings1 = mWebView1.getSettings();
        settings1.setSupportMultipleWindows(true);
        settings1.setLoadsImagesAutomatically(true);
        settings1.setBuiltInZoomControls(true);
        settings1.setSupportZoom(true);

        WebSettings settings2 = mWebView2.getSettings();
        settings2.setSupportMultipleWindows(true);
        settings2.setLoadsImagesAutomatically(true);
        settings2.setBuiltInZoomControls(true);
        settings2.setSupportZoom(true);
    }

    //メニュー欄の設定
    public boolean onCreateOptionsMenu(Menu menu){  //メニューの項目の設定
        menu.add(Menu.NONE, 0, 0, "アマゾン");
        menu.add(Menu.NONE, 1, 1, "楽天");
        menu.add(Menu.NONE, 2, 2, "ヤフオク");
        menu.add(Menu.NONE, 3, 3, "メルカリ");
        menu.add(Menu.NONE, 4, 4, "Yahoo!ショッピング");
        menu.add(Menu.NONE, 5, 5, "ヨドバシカメラ");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem mi){  //メニュー選択時の処理
        AlertDialog.Builder db = new AlertDialog.Builder(Main_price.this); //ダイアログの生成

        switch(mi.getItemId()){  //メニュー選択時の具体的な処理
            case 0:
                str1 = "アマゾン";
                str2 = "https://www.amazon.co.jp/gp/aw/s/ref=nb_sb_noss?k=";
                break;
            case 1:
                str1 = "楽天";
                str2 = "https://search.rakuten.co.jp/search/mall/";
                break;
            case 2:
                str1 = "ヤフオク";
                str2 = "https://auctions.yahoo.co.jp/search/search?auccat=&tab_ex=commerce&ei=utf-8&aq=-1&oq=&sc_i=&fr=auc_top&p=";
                break;
            case 3:
                str1 = "メルカリ";
                str2 = "https://www.mercari.com/jp/search/?keyword=";
                break;
            case 4:
                str1 = "Yahoo!ショッピング";
                str2 = "https://shopping.yahoo.co.jp/search;_ylt=A2RmPGhXV_BbFF0ALA0uNthE?p=";
                break;
            case 5:
                str1 = "ヨドバシカメラ";
                str2 = "https://www.yodobashi.com/?word=";
                break;
        }

        db.setTitle("webページ変更");  //ダイアログのタイトル設定
        db.setMessage("画面位置を選択してください");  //ダイアログのメッセージ設定
        db.setPositiveButton("画面上部", new SampleDialogClickListener1());  //ダイアログの「Yes」ボタンとクリック時のリスナーの設定
        db.setNegativeButton("画面下部", new SampleDialogClickListener2()).show();  //ダイアログの「No」ボタンの設定と、ダイアログの表示
        return true;
    }

    class SampleDialogClickListener1 implements DialogInterface.OnClickListener{  //ダイアログのボタンクリック時のイベント処理
        public void onClick(DialogInterface d, int w){
            //new AlertDialog.Builder(ExSample.this).setTitle("表示画面の決定")  //ダイアログのタイトル、メッセージ、「Yes」ボタンの設定とダイアログの表示
            //        .setMessage("画面上部を"+str1+"しました。").setPositiveButton("OK", null).show();
            str3 = str2;
            mWebView1.loadUrl(str2);
        }
    }

    class SampleDialogClickListener2 implements DialogInterface.OnClickListener{  //ダイアログのボタンクリック時のイベント処理
        public void onClick(DialogInterface d, int w){
            //new AlertDialog.Builder(ExSample.this).setTitle("表示画面の決定")  //ダイアログのタイトル、メッセージ、「Yes」ボタンの設定とダイアログの表示
            //        .setMessage("画面下部を"+str1+"しました。").setPositiveButton("OK", null).show();
            str4 = str2;
            mWebView2.loadUrl(str2);
        }
    }
}



