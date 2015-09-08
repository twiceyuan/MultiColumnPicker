package com.twiceyuan.library;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class MultiColumnPicker<Left, Right> {

    private final String TAG = "MultiColumnPicker";

    private Context mContext;

    private OnLeftSelected<Left, Right> mOnLeftSelected;
    private OnRightSelected<Right> mOnRightSelected;
    private MapString<Left> mMapLeftString;
    private MapString<Right> mMapRightString;
    private MapId<Left> mMapLeftId;
    private MapId<Right> mMapRightId;

    private ListView mLvLeft;
    private ListView mLvRight;
    private View mRoot;
    private AlertDialog mDialog;

    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;

    private int mDefaultPosition = 0;

    public MultiColumnPicker(Context context) {
        mContext = context;
        mRoot = View.inflate(context, R.layout.dialog_picker, null);
        mLvLeft = (ListView) mRoot.findViewById(R.id.lv_left);
        mLvRight = (ListView) mRoot.findViewById(R.id.lv_right);
    }

    /**
     * 配置左侧内容
     */
    public MultiColumnPicker setLeftContent(final List<Left> lefts) {
        mLeftAdapter = new LeftAdapter(lefts);
        mLvLeft.setAdapter(mLeftAdapter);
        mLvLeft.setOnItemClickListener((parent, view, position, id) -> {
            mLvLeft.setItemChecked(position, true);
            if (mOnLeftSelected != null) {
                final List<Right> rights = mOnLeftSelected.onLeftSelected(position, lefts.get(position));
                mRightAdapter = new RightAdapter(rights);
                mLvRight.setAdapter(mRightAdapter);
                mLvRight.setOnItemClickListener((parent1, view1, position2, id1) -> {
                    if (mOnRightSelected != null) {
                        mOnRightSelected.onRightSelected(position2, rights.get(position2));
                        mDialog.dismiss();
                    }
                });
            }
        });
        return this;
    }

    /**
     * 配置左侧监听器（联动右侧适配器）
     */
    public MultiColumnPicker setOnLeftSelected(OnLeftSelected<Left, Right> onLeftSelected) {
        mOnLeftSelected = onLeftSelected;
        return this;
    }

    /**
     * 配置右侧监听器（结束回调结果）
     */
    public MultiColumnPicker setOnRightSelected(OnRightSelected<Right> onRightSelected) {
        mOnRightSelected = onRightSelected;
        return this;
    }

    public MultiColumnPicker setMapLeftString(MapString<Left> mapLeftString) {
        mMapLeftString = mapLeftString;
        return this;
    }

    public MultiColumnPicker setMapRightString(MapString<Right> mapRightString) {
        mMapRightString = mapRightString;
        return this;
    }

    public void setMapLeftId(MapId<Left> mapLeftId) {
        mMapLeftId = mapLeftId;
    }

    public void setMapRightId(MapId<Right> mapRightId) {
        mMapRightId = mapRightId;
    }

    /**
     * 设置默认值
     *
     * @param position 左列默认位置
     */
    public MultiColumnPicker setLeftDefault(int position) {
        mDefaultPosition = position;
        return this;
    }

    /**
     * 设置默认值
     *
     * @param defaultId 默认值 ID
     */
    public MultiColumnPicker setLeftDefault(Object defaultId) {
        if (mMapLeftId == null) {
            throw new NoSuchMethodError("没有配置 MapLeftId");
        }
        for (int i = 0; i < mLeftAdapter.getCount(); i++) {
            if (mMapLeftId.getId(mLeftAdapter.getItem(i)).equals(defaultId)) {
                mDefaultPosition = i;
                return this;
            }
        }
        Log.e(TAG, "没有找到 ID 为" + defaultId + "的选项");
        return this;
    }

    /**
     * 设置默认值
     *
     * @param defaultString 默认值的显示文字
     */
    public MultiColumnPicker setLeftDefault(String defaultString) {
        if (mMapLeftString == null) {
            throw new NoSuchMethodError("没有配置 MapLeftId");
        }
        for (int i = 0; i < mLeftAdapter.getCount(); i++) {
            if (mMapLeftString.getString(mLeftAdapter.getItem(i)).equals(defaultString)) {
                mDefaultPosition = i;
                return this;
            }
        }
        Log.e(TAG, "没有找到 String 为" + defaultString + "的选项");
        return this;
    }

    /**
     * 显示
     */
    public void show() {
        // 滚到默认值
        mLvLeft.performItemClick(mLvLeft.getChildAt(mDefaultPosition), mDefaultPosition, 0);
        mLvLeft.smoothScrollToPosition(mDefaultPosition);
        mDialog = new AlertDialog.Builder(mContext)
                .setView(mRoot)
                .show();
    }

    private class LeftAdapter extends BaseAdapter {

        List<Left> mLefts;

        public LeftAdapter(List<Left> lefts) {
            mLefts = lefts;
        }

        @Override
        public int getCount() {
            return mLefts.size();
        }

        @Override
        public Left getItem(int position) {
            return mLefts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_left, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            if (mMapLeftString != null) {
                textView.setText(mMapLeftString.getString(getItem(position)));
            }
            return convertView;
        }
    }

    private class RightAdapter extends BaseAdapter {

        List<Right> mRights;

        public RightAdapter(List<Right> rights) {
            mRights = rights;
        }

        @Override
        public int getCount() {
            return mRights.size();
        }

        @Override
        public Right getItem(int position) {
            return mRights.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            if (mMapRightString != null) {
                textView.setText(mMapRightString.getString(getItem(position)));
            }
            return convertView;
        }
    }
}
