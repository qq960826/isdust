package com.isdust.www.Card;

import android.app.Activity;
import android.content.Intent;

import com.isdust.www.R;
import com.isdust.www.baseactivity.BaseModule;

/**
 * Card的模型
 * Created by zor on 2016/9/27.
 */

public class CardModule extends BaseModule {


    public CardModule(int image_id, int name, int desc, int catagory) {
        super(image_id, name, desc, catagory);
    }

    private static class Holder {
        private static final CardModule INSTANCE = new CardModule(R.drawable.card,R.string.schoolcard_name,R.string.schoolcard_info,R.string.schoolcard_catgory);
    }

    public static final CardModule getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void lunchActivity(Activity activity) {
        activity.startActivity(new Intent(activity,CardActivity.class));
    }
}