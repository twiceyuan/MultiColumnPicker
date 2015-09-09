package com.twiceyuan.library;

import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * Dialog Params Helper
 */
public class DialogHelper {

    public static class Size {

        int mWidth;
        int mHeight;

        public Size(int width, int height) {
            mWidth = width;
            mHeight = height;
        }

        public void resize(AlertDialog dialog) {
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = mWidth;
            lp.height = mHeight;
            dialog.getWindow().setAttributes(lp);
        }
    }
}
