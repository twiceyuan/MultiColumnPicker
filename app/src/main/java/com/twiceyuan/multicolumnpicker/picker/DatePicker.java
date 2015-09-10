package com.twiceyuan.multicolumnpicker.picker;

import android.content.Context;

import com.twiceyuan.library.MultiColumnPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * Year and month picker
 */
public class DatePicker {

    OnSelected<String, String> mOnSelected;

    public void show(Context context) {
        MultiColumnPicker<String, String> picker = new MultiColumnPicker<>(context);
        picker.setLeftContent(getYears());
        picker.setOnLeftSelected((position, s) -> getMonth());
        //noinspection Convert2MethodRef
        picker.setOnRightSelected((s, s2) -> mOnSelected.onSelect(s + "年", s2 + "月"));
        picker.setMapLeftId(Integer::parseInt);
        picker.setMapLeftString(s -> s + "年");
        picker.setMapRightId(Integer::parseInt);
        picker.setMapRightString(s -> s + "月");
        picker.setLeftDefault((Object) 2015);
        picker.setWeight(1, 1);
        picker.setSize(400, 400);
        picker.show();
    }

    public List<String> getYears() {
        List<String> years = new ArrayList<>();
        for (int i = 1900; i < 2100; i++) {
            years.add(i + "");
        }
        return years;
    }

    public List<String> getMonth() {
        List<String> month = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            month.add(i + "");
        }
        return month;
    }

    public DatePicker setOnSelected(OnSelected<String, String> onSelected) {
        mOnSelected = onSelected;
        return this;
    }
}
