package com.twiceyuan.library.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 单列内容适配器
 */
public abstract class ColumnAdapter<Data> extends BaseAdapter {

    private List<Data> mDataList;

    public ColumnAdapter(List<Data> dataList) {
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Data getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public abstract String mapString(Data data);
}
