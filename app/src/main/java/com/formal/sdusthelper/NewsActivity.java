package com.formal.sdusthelper;

import android.os.Bundle;

import com.formal.sdusthelper.baseactivity.BaseMainActivity;

/**
 * Created by Administrator on 2015/10/17.
 */
public class NewsActivity extends BaseMainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        INIT(R.layout.helper_news, "校园资讯");
    }

}
