package com.cwc.selectview;

import android.view.View;

/**
 * @author Cuiweicong 2017/11/8
 */

public class SingleCanCancelHandlerImpl extends SelectHandler {
    private View currentSelectView;

    @Override
    public void handleSelectEvent(View v, int position) {
        if (v == currentSelectView) {
            unSelectView(v, position);
        } else {
            selectOneUnSelectCurrent(v, position);
        }
    }

    /**
     * 取消选择
     */
    private void unSelectView(View v, int position) {
        currentSelectView.setSelected(false);
        currentSelectView = null;
        onItemSelectOrUnSelect(new SelectModel(v, position, false));
    }

    /**
     * 单选，选择一个当前的取消选择
     */
    private void selectOneUnSelectCurrent(View v, int position) {
        if (currentSelectView != null) {
            currentSelectView.setSelected(false);
        }
        v.setSelected(true);
        currentSelectView = v;
        onItemSelectOrUnSelect(new SelectModel(v, position, true));
    }
}
