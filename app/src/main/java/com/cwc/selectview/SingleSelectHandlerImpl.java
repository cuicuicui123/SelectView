package com.cwc.selectview;

import android.view.View;

/**
 * @author Cuiweicong 2017/11/8
 */

public class SingleSelectHandlerImpl extends SelectHandler {
    private View currentSelectView;

    @Override
    public void handleSelectEvent(View v, int position) {
        if (v != currentSelectView) {
            if (currentSelectView != null) {
                currentSelectView.setSelected(false);
            }
            v.setSelected(true);
            currentSelectView = v;
            onItemSelectOrUnSelect(new SelectModel(v, position, true));
        }
    }
}
