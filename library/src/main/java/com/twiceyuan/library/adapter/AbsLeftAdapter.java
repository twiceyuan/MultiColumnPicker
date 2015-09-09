package com.twiceyuan.library.adapter;

import com.twiceyuan.library.map.LeftStringMapper;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 抽象左侧适配器
 */
public abstract class AbsLeftAdapter<Data> extends SimpleColumnAdapter<Data> {

    private LeftStringMapper<Data> mMapper;

    public AbsLeftAdapter(List<Data> dataList, LeftStringMapper<Data> mapper) {
        super(dataList);
        mMapper = mapper;
    }

    @Override
    public String mapString(Data data) {
        return mMapper == null ? "" : mMapper.mapLeftString(data);
    }
}
