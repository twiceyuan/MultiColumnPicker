package com.twiceyuan.library.listener;

/**
 * Created by twiceYuan on 9/7/15.
 *
 * 右侧被选择
 */
public interface OnRightSelected<Left, Right> {
    void onRightSelected(Left left, Right right);
}
