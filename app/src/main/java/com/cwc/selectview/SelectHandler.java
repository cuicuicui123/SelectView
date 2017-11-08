package com.cwc.selectview;

import android.view.View;

/**
 * @author Cuiweicong 2017/11/8
 */

public abstract class SelectHandler {
    protected OnItemSelectListener onItemSelectListener;

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    /**
     * 选中事件
     */
    protected void onItemSelectOrUnSelect(SelectModel selectModel) {
        if (onItemSelectListener != null) {
            onItemSelectListener.onItemSelect(selectModel);
        }
    }

    public abstract void handleSelectEvent(View v, int position);
}
