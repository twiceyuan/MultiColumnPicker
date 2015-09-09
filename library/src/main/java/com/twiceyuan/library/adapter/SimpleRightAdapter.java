package com.twiceyuan.library.adapter;

import com.twiceyuan.library.R;
import com.twiceyuan.library.map.RightStringMapper;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 默认右列适配器
 */
public class SimpleRightAdapter<Data> extends SimpleColumnAdapter<Data> {

    private RightStringMapper<Data> mStringMapper;

    public SimpleRightAdapter(List<Data> dataList, RightStringMapper<Data> stringMapper) {
        super(dataList);
        mStringMapper = stringMapper;
    }

    @Override
    public String mapString(Data data) {
        return mStringMapper.mapRightString(data);
    }

    @Override
    public int provideItemLayout() {
        return R.layout.multicolomn_item_right;
    }

    @Override
    public int provideTextId() {
        return android.R.id.text1;
    }
}
