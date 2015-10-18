package com.formal.sdusthelper.baseactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.formal.sdusthelper.CardActivity;
import com.formal.sdusthelper.GoNetActivity;
import com.formal.sdusthelper.JiaowuActivity;
import com.formal.sdusthelper.LibraryActivity;
import com.formal.sdusthelper.LifeActivity;
import com.formal.sdusthelper.MainActivity;
import com.formal.sdusthelper.MyApplication;
import com.formal.sdusthelper.NewsActivity;
import com.formal.sdusthelper.R;
import com.formal.sdusthelper.SetActivity;
import com.formal.sdusthelper.SlideMenu;

/**
 * 主界面的父类
 * Created by Administrator on 2015/10/18.
 */
public class BaseMainActivity extends Activity{
    protected MyApplication isdustapp;	//通过app调全局变量
    protected SlideMenu slideMenu;    //侧边栏
    protected Context mContext;

    protected void INIT(int pageid,String title) {
        isdustapp = (MyApplication) this.getApplication();
        setContentView(pageid);
        mContext = this;
        slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
        TextView title_name = (TextView) findViewById(R.id.title_bar_name);
        title_name.setText(title);	//修改页面标题
    }   //初始化

    public void onTitleBarClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_menu_btn:
                if (slideMenu.isMainScreenShowing()) {
                    slideMenu.openMenu();
                } else {
                    slideMenu.closeMenu();
                }
                break;
        }
    }
    public void onMenuClick(View v) {
        //设置传递方向
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.slide_menu_home:
                intent.setClass(this, MainActivity.class);
                break;
            case R.id.slide_menu_cmcc:
                intent.setClass(this, GoNetActivity.class);
                break;
            case R.id.slide_menu_jiaowu:
                intent.setClass(this, JiaowuActivity.class);
                break;
            case R.id.slide_menu_library:
                intent.setClass(this, LibraryActivity.class);
                break;
            case R.id.slide_menu_card:
                intent.setClass(this, CardActivity.class);
                break;
            case R.id.slide_menu_life:
                intent.setClass(this, LifeActivity.class);
                break;
            case R.id.slide_menu_news:
                intent.setClass(this, NewsActivity.class);
                break;
            case R.id.slide_menu_set:
                intent.setClass(this, SetActivity.class);
                break;
        }
        //启动activity
        this.startActivity(intent);
        finish();
//        if (!slideMenu.isMainScreenShowing()) {
//            slideMenu.closeMenu();
//        }
    }

}
