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
        picker.setOnRightSelected((year, month) -> mOnSelected.onSelect(year, month));
        picker.setMapLeftId(year -> year.replace("年", ""));
        picker.setMapLeftString(year -> year);
        picker.setMapRightId(month -> month.replace("月", ""));
        picker.setMapRightString(month -> month);
        picker.setLeftDefaultString("1903年");
        picker.setWeight(1, 1);
        picker.setSize(400, 400);
        picker.show();
    }

    public List<String> getYears() {
        List<String> years = new ArrayList<>();
        for (int i = 1900; i < 2100; i++) {
            years.add(i + "年");
        }
        return years;
    }

    public List<String> getMonth() {
        List<String> month = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            month.add(i + "月");
        }
        return month;
    }

    public DatePicker setOnSelected(OnSelected<String, String> onSelected) {
        mOnSelected = onSelected;
        return this;
    }
}
