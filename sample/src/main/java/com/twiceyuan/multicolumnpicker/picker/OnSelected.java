package com.twiceyuan.multicolumnpicker.picker;

/**
 * Created by twiceYuan on 9/9/15.
 * <p>
 * on selected listener
 */
public interface OnSelected<Left, Right> {
    void onSelect(Left left, Right right);
}
