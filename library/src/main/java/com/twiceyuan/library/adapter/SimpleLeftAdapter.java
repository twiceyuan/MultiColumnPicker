package com.twiceyuan.library.adapter;

import com.twiceyuan.library.R;
import com.twiceyuan.library.map.LeftStringMapper;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 默认左列适配器
 */
public class SimpleLeftAdapter<Data> extends SimpleColumnAdapter<Data> {

    private LeftStringMapper<Data> mStringMapper;

    public SimpleLeftAdapter(List<Data> dataList, LeftStringMapper<Data> stringMapper) {
        super(dataList);
        mStringMapper = stringMapper;
    }

    @Override
    public String mapString(Data data) {
        return mStringMapper.mapLeftString(data);
    }

    @Override
    public int provideItemLayout() {
        return R.layout.multicolomn_item_left;
    }

    @Override
    public int provideTextId() {
        return android.R.id.text1;
    }
}
