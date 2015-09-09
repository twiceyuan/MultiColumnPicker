package com.twiceyuan.multicolumnpicker.adapter;

import com.twiceyuan.library.adapter.AbsLeftAdapter;
import com.twiceyuan.library.map.LeftStringMapper;
import com.twiceyuan.multicolumnpicker.R;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 自定义左侧适配器
 */
public class CustomLeftAdapter<Data> extends AbsLeftAdapter<Data> {

    public CustomLeftAdapter(List<Data> datas, LeftStringMapper<Data> mapper) {
        super(datas, mapper);
    }

    @Override
    public int provideItemLayout() {
        return R.layout.item_custom_left;
    }

    @Override
    public int provideTextId() {
        return R.id.tv_left;
    }
}
