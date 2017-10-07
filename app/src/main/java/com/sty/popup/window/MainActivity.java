package com.sty.popup.window;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.sty.popup.window.adapter.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageButton ibDropdown;
    private EditText etInput;
    private ListView listView;
    private ArrayList<String> datas;
    private Context mContext;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ibDropdown = (ImageButton) findViewById(R.id.ib_dropdown);
        ibDropdown.setOnClickListener(this);
        etInput = (EditText) findViewById(R.id.et_input);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_dropdown:
                showPopupWindow();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i(TAG, "onItemClick: " + position);

        String str = datas.get(position);
        etInput.setText(str); //设置文本

        popupWindow.dismiss(); //消失popupWindow
    }

    private void showPopupWindow(){
        initListView();

        //显示下拉选择框
        popupWindow = new PopupWindow(listView, etInput.getWidth(), 1000);

        //设置点击外部区域，自动隐藏
        popupWindow.setOutsideTouchable(true); //外部可触摸
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); //设置空的背景

        //设置该控件可获取焦点，它默认是不可获取焦点的
        popupWindow.setFocusable(true);

        //显示在指定控件下方
        popupWindow.showAsDropDown(etInput, 5, -15);
    }

    private void initListView(){
        listView = new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.drawable.listview_background);
        listView.setOnItemClickListener(this);

        datas = new ArrayList<>();
        //创建一些数据
        for(int i = 0; i < 30; i++){
            datas.add((10000 + i) + "");
        }

        listView.setAdapter(new MyAdapter(mContext, datas, popupWindow));
    }

}
