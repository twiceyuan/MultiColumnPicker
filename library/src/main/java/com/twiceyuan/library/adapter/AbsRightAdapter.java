package com.twiceyuan.library.adapter;

import com.twiceyuan.library.map.RightStringMapper;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * 抽象右侧适配器
 */
public abstract class AbsRightAdapter<Data> extends SimpleColumnAdapter<Data> {

    private RightStringMapper<Data> mMapper;

    public AbsRightAdapter(List<Data> dataList, RightStringMapper<Data> mapper) {
        super(dataList);
        mMapper = mapper;
    }

    @Override
    public String mapString(Data data) {
        return mMapper == null ? "" : mMapper.mapRightString(data);
    }
}
