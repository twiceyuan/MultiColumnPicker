package com.twiceyuan.multicolumnpicker.picker;

import android.content.Context;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.twiceyuan.library.MultiColumnPicker;
import com.twiceyuan.multicolumnpicker.App;
import com.twiceyuan.multicolumnpicker.Business;
import com.twiceyuan.multicolumnpicker.adapter.CustomLeftAdapter;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * Business Picker
 */
public class BusinessPicker {

    OnSelected<Business, Business> mOnSelected;

    public void show(Context context, Business defaultBusiness) {
        MultiColumnPicker<Business, Business> picker = new MultiColumnPicker<>(context);
        picker.setLeftContent(getCategories());
        picker.setOnLeftSelected((position, business) -> getBusiness(business));
        picker.setOnRightSelected((business1, business2) -> mOnSelected.onSelect(business1, business2));
        picker.setMapLeftString(business -> business.name);
        picker.setMapRightString(business -> business.name);
        picker.setMapLeftId(business -> business.id);
        picker.setMapRightId(business -> business.id);
        picker.setLeftAdapter((mapper, businesses) ->
                new CustomLeftAdapter<>(businesses, mapper)); // 配置自定义适配器
        picker.getLeftView().setDividerHeight(0);
        if (defaultBusiness != null) {
            picker.setRightDefaultId(defaultBusiness.parent, defaultBusiness.id);
        }
        picker.show();
    }

    private List<Business> getCategories() {
        return App.db.query(QueryBuilder.create(Business.class).whereEquals("parent", "00"));
    }

    private List<Business> getBusiness(Business father) {
        return App.db.query(QueryBuilder.create(Business.class).whereEquals("parent", father.id));
    }

    public BusinessPicker setOnSelected(OnSelected<Business, Business> onSelected) {
        mOnSelected = onSelected;
        return this;
    }
}
