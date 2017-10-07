package com.sty.popup.window.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sty.popup.window.R;

import java.util.ArrayList;

/**
 * Created by Shi Tianyi on 2017/10/7/0007.
 */

public class MyAdapter extends BaseAdapter{

    private ArrayList<String> arrayList;
    private PopupWindow popupWindow;
    private Context context;

    public MyAdapter(Context context, ArrayList<String> arrayList, PopupWindow popupWindow){
        this.context = context;
        this.arrayList = arrayList;
        this.popupWindow = popupWindow;
    }

    @Override
    public int getCount() {
        if(arrayList != null && arrayList.size() > 0){
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view;
        if(convertView == null){
            view = View.inflate(context, R.layout.item_number, null);
        }else{
            view = convertView;
        }

        TextView tvNumber = (TextView) view.findViewById(R.id.tv_number);
        tvNumber.setText(arrayList.get(position));

        view.findViewById(R.id.ib_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(position);
                notifyDataSetChanged();

                if(arrayList.size() == 0){ //如果删除的是最后一条，隐藏popupWindow
                    popupWindow.dismiss();
                }
            }
        });

        return view;
    }
}
