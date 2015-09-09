package com.twiceyuan.library.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 默认单列适配器
 *
 * 需要继承者提供：1. 布局文件 2. 布局中显示文字的 ID
 */
public abstract class SimpleColumnAdapter<Data> extends ColumnAdapter<Data> {

    public SimpleColumnAdapter(List<Data> dataList) {
        super(dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(provideItemLayout(), parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(provideTextId());
        textView.setText(mapString(getItem(position)));
        return convertView;
    }

    @Override
    public abstract String mapString(Data data);

    /**
     * @return 提供布局文件
     */
    public abstract int provideItemLayout();

    /**
     * @return 提供布局中显示文字的 View ID
     */
    public abstract int provideTextId();
}
