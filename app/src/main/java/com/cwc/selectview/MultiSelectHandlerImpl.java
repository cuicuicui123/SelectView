package com.cwc.selectview;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cuiweicong 2017/11/8
 */

public class MultiSelectHandlerImpl extends SelectHandler {
    private List<View> currentSelectViews;

    public MultiSelectHandlerImpl() {
        currentSelectViews = new ArrayList<>();
    }

    @Override
    public void handleSelectEvent(View v, int position) {
        if (currentSelectViews.contains(v)) {
            multiUnSelectOneView(v, position);
        } else {
            multiSelectOneView(v, position);
        }
    }

    private void multiUnSelectOneView(View v, int position) {
        v.setSelected(false);
        onItemSelectOrUnSelect(new SelectModel(v, position, false));
        if (currentSelectViews.contains(v)) {
            currentSelectViews.remove(v);
        }
    }

    /**
     * 多选添加一个选择
     */
    private void multiSelectOneView(View v, int position){
        v.setSelected(true);
        currentSelectViews.add(v);
        onItemSelectOrUnSelect(new SelectModel(v, position, true));
    }
}
