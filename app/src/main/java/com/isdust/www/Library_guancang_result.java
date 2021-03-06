package com.isdust.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.isdust.www.baseactivity.BaseSubPageActivity;
import com.isdust.www.datatype.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzq on 15/11/1.
 * isdust
 * Copyright (C) <2015>  <Wang Ziqiang,Leng Hanchao,Qing Wenkai,Huyang>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Library_guancang_result extends BaseSubPageActivity {

    ListView mListView;
    Context mContext;
    List<Book> mBooks;
    SimpleAdapter madapter;
    private MyApplication isdustapp;
    private List<Map<String, Object>> listdata = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        INIT(R.layout.activity_library_guancang_result, "查询结果");
        //MobclickAgent.onEvent(this, "Library_guancang");

        mListView=(ListView)findViewById(R.id.listview_library_result);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getLastVisiblePosition();
                adapterView.getFirstVisiblePosition();
                try {
                    TextView mTextView_library_bookrecnos=(TextView)view.findViewById(R.id.TextView_library_bookrecnos);
                    String bookrecnos=mTextView_library_bookrecnos.getText().toString().replace("书本编号：","");
                    Intent intent=new Intent();
                    intent.putExtra("bookrecnos", bookrecnos);
                    intent.setClass(mContext, Library_guancang_detail.class);
                    startActivity(intent);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            }
        );
        mContext=this;
        isdustapp=(MyApplication)getApplication();

        mBooks=isdustapp.getBooks();

        jiazaishuju();
    }
    public void jiazaishuju(){
        Map<String, Object> map;
        int len=mBooks.size();
        for (int i=0;i<len;i++){
            map = new HashMap<String, Object>();
            map.put("title",mBooks.get(i).getName());
            map.put("author","作者："+mBooks.get(i).getWriter());
            map.put("bookrecnos","书本编号："+mBooks.get(i).getbookrecno());
            map.put("suoshuhao","索书号："+mBooks.get(i).getSuoshuhao());
            listdata.add(map);

        }

        madapter = new SimpleAdapter(mContext, listdata,
                R.layout.activity_library_guancang_result_item, new String[] { "title", "author", "bookrecnos", "suoshuhao"},
                new int[] { R.id.TextView_library_title, R.id.TextView_library_id,
                        R.id.TextView_library_bookrecnos,	R.id.TextView_library_suoshuhao});
        mListView.setAdapter(madapter);	//捆绑适配器}

    }

}
