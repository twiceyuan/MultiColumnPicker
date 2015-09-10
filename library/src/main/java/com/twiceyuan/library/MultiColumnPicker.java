package com.twiceyuan.library;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.twiceyuan.library.adapter.ColumnAdapter;
import com.twiceyuan.library.adapter.SimpleLeftAdapter;
import com.twiceyuan.library.adapter.SimpleRightAdapter;
import com.twiceyuan.library.listener.OnLeftSelected;
import com.twiceyuan.library.listener.OnRightSelected;
import com.twiceyuan.library.map.LeftStringMapper;
import com.twiceyuan.library.map.MapId;
import com.twiceyuan.library.map.MapString;
import com.twiceyuan.library.map.RightStringMapper;

import java.util.List;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class MultiColumnPicker<Left, Right> implements LeftStringMapper<Left>, RightStringMapper<Right> {

    private final String TAG = "MultiColumnPicker";

    private Context mContext;

    private OnLeftSelected<Left, Right> mOnLeftSelected;
    private OnRightSelected<Left, Right> mOnRightSelected;
    private MapString<Left> mMapLeftString;
    private MapString<Right> mMapRightString;
    private MapId<Left> mMapLeftId;
    private MapId<Right> mMapRightId;
    private OnLeftAdapterSet<Left> mOnLeftAdapterSet;
    private OnRightAdapterSet<Right> mOnRightAdapterSet;
    private List<Left> mLeftData;

    private LinearLayout mContainer;
    private ListView mLvLeft;
    private ListView mLvRight;
    private View mRoot;
    private AlertDialog mDialog;

    private ColumnAdapter<Left> mLeftAdapter;
    private ColumnAdapter<Right> mRightAdapter;

    private int mDefaultPosition = 0;
    private DialogHelper.Size mSize;

    public MultiColumnPicker(Context context) {
        mContext = context;
        mRoot = View.inflate(context, R.layout.multicolomn_dialog_picker, null);
        mContainer = (LinearLayout) mRoot.findViewById(R.id.ll_container);
        mLvLeft = (ListView) mRoot.findViewById(R.id.lv_left);
        mLvRight = (ListView) mRoot.findViewById(R.id.lv_right);
    }

    /**
     * 配置左侧内容
     */
    public MultiColumnPicker setLeftContent(final List<Left> lefts) {
        mLeftData = lefts;
        mLvLeft.setOnItemClickListener((parent, view, position, id) -> {
            mLvLeft.setItemChecked(position, true);
            if (mOnLeftSelected != null) {
                final List<Right> rights = mOnLeftSelected.onLeftSelected(position, lefts.get(position));

                // 判断是否提供自定义 Adapter
                if (mOnRightAdapterSet != null) {
                    mRightAdapter = mOnRightAdapterSet.provideRightAdapter(this, rights);
                } else {
                    mRightAdapter = new SimpleRightAdapter<>(rights, this);
                }
                mLvRight.setAdapter(mRightAdapter);
                mLvRight.setOnItemClickListener((parent1, view1, position2, id1) -> {
                    if (mOnRightSelected != null) {
                        mOnRightSelected.onRightSelected(lefts.get(position), rights.get(position2));
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
    public MultiColumnPicker setOnRightSelected(OnRightSelected<Left, Right> onRightSelected) {
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
        for (int i = 0; i < mLeftData.size(); i++) {
            if (mMapLeftId.getId(mLeftData.get(i)).equals(defaultId)) {
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
        for (int i = 0; i < mLeftData.size(); i++) {
            if (mMapLeftString.getString(mLeftData.get(mDefaultPosition)).equals(defaultString)) {
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
        // 判断是否提供自定义 Adapter
        if (mOnLeftAdapterSet != null) {
            mLeftAdapter = mOnLeftAdapterSet.provideLeftAdapter(this, mLeftData);
        } else {
            mLeftAdapter = new SimpleLeftAdapter<>(mLeftData, this);
        }
        mLvLeft.setAdapter(mLeftAdapter);
        // 滚到默认值
        mLvLeft.performItemClick(mLvLeft.getChildAt(mDefaultPosition), mDefaultPosition, 0);
        mLvLeft.setSelection(mDefaultPosition);
        mDialog = new AlertDialog.Builder(mContext)
                .setView(mRoot)
                .show();
        if (mSize != null) {
            mSize.resize(mDialog);
        }
    }

    @Override
    public String mapLeftString(Left left) {
        if (mMapLeftString != null) {
            return mMapLeftString.getString(left);
        }
        return "";
    }

    @Override
    public String mapRightString(Right right) {
        if (mMapRightString != null) {
            return mMapRightString.getString(right);
        }
        return "";
    }

    /**
     * 配置权重比（默认 1:2）
     *
     * @param left  左列权重
     * @param right 右列权重
     */
    public MultiColumnPicker setWeight(int left, int right) {
        mContainer.setWeightSum(left + right);
        mLvLeft.setLayoutParams(
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, left));
        mLvRight.setLayoutParams(
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, right));
        return this;
    }

    /**
     * 设置对话框尺寸（默认都为 match_parent）
     *
     * @param width  宽度
     * @param height 高度
     */
    public MultiColumnPicker setSize(float width, float height) {
        // dp -> px
        int widthPx = DensityUtil.dip2px(mContext, width);
        int heightPx = DensityUtil.dip2px(mContext, height);

        // compare screen size and set screen size if the value too large
        int screenWidth = DensityUtil.screenWidth(mContext);
        int screenHeight = DensityUtil.screenHeight(mContext);
        widthPx = widthPx > screenWidth ? screenWidth : widthPx;
        heightPx = heightPx > screenHeight ? screenHeight : heightPx;
        mSize = new DialogHelper.Size(widthPx, heightPx);
        return this;
    }

    public interface OnLeftAdapterSet<Left> {
        ColumnAdapter<Left> provideLeftAdapter(LeftStringMapper<Left> mapper, List<Left> lefts);
    }

    public interface OnRightAdapterSet<Right> {
        ColumnAdapter<Right> provideRightAdapter(RightStringMapper<Right> mapper, List<Right> rights);
    }

    /**
     * 配置左侧适配器回调
     *
     * @param onLeftAdapterSet 左侧适配器回调
     */
    public void setLeftAdapter(OnLeftAdapterSet<Left> onLeftAdapterSet) {
        mOnLeftAdapterSet = onLeftAdapterSet;
    }

    /**
     * 配置右侧适配器回调
     *
     * @param onRightAdapterSet 右侧适配器回调
     */
    public void setRightAdapter(OnRightAdapterSet<Right> onRightAdapterSet) {
        mOnRightAdapterSet = onRightAdapterSet;
    }

    public ListView getLeftView() {
        return mLvLeft;
    }

    public ListView getRightView() {
        return mLvRight;
    }
}
