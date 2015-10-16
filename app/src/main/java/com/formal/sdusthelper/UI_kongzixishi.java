package com.formal.sdusthelper;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.formal.sdusthelper.datatype.Kebiaoxinxi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pw.isdust.isdust.function.Kongzixishi;

/**
 * Created by wzq on 15/10/16.
 */
public class UI_kongzixishi extends ListActivity {
    //线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private String xiancheng_location;
    private int xiancheng_zhoushu;
    private boolean xiancheng_first;
    private Kebiaoxinxi xiancheng_Kebiaoxinxi[];
    private Kongzixishi mKongzixishi;

    private List<Map<String, Object>> listdata = new ArrayList<Map<String, Object>>();	//列表框的数据

    SimpleAdapter adapter;	//列表的适配器
    Context mContext;
    final android.os.Handler mHandler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                for (int i=0;i< xiancheng_Kebiaoxinxi.length;i++){
                    Map<String, Object> map;

                    map = new HashMap<String, Object>();
                    map.put("location", " "+ xiancheng_Kebiaoxinxi[i].getlocation());
                    map.put("zhoushu","     第"+ xiancheng_Kebiaoxinxi[i].getzhoushu()+"周");
                    map.put("xingqi","     "+xingqizhuanhuan(xiancheng_Kebiaoxinxi[i].getxinqi()));
                    map.put("jieci",jiecizhuanhuan(xiancheng_Kebiaoxinxi[i].getjieci()));
                    listdata.add(map);
                }
                adapter = new SimpleAdapter(mContext, listdata,
                        R.layout.kongzixishi_item, new String[] { "location", "zhoushu", "xingqi", "jieci"},
                        new int[] { R.id.textView_kongzixishi_location, R.id.textView_kongzixishi_zhoushu,
                                R.id.textView_kongzixishi_xingqi,	R.id.textView_kongzixishi_jieci});
                setListAdapter(adapter);	//捆绑适配器}
            }


        }

    };
    Runnable mRunnable_jiazaizixishi=new Runnable(){
        @Override
        public void run() {
            //listdata=null;
            mKongzixishi=new Kongzixishi();
            xiancheng_Kebiaoxinxi = mKongzixishi.huoquzixishi(xiancheng_location, xiancheng_zhoushu);

            Message message=new Message();
            message.what=0;
            mHandler.sendMessage(message);

        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kongzixishi_listview);
        mContext=this;
        xiancheng_first=false;
        TextView title_name = (TextView) findViewById(R.id.title_bar_name);
        title_name.setText("空自习室查询");
        xiancheng_location="J7-105室";
        xiancheng_zhoushu=5;
        executorService.execute(mRunnable_jiazaizixishi);










    }
    private String xingqizhuanhuan(int raw){
        switch (raw){
            case 1:return "星期一";
            case 2:return "星期二";
            case 3:return "星期三";
            case 4:return "星期四";
            case 5:return "星期五";
            case 6:return "星期六";
            case 7:return "星期日";

        }
        return "error_xingqizhuanhuan";
    }
    private String jiecizhuanhuan(int raw){
        switch (raw){
            case 1:return "第一、二节";
            case 2:return "第三、四节";
            case 3:return "第五、六节";
            case 4:return "第七、八节";
            case 5:return "第九、十节";

        }
        return "error_jiecizhuanhuan";
    }
}