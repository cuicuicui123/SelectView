package com.cwc.selectview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Cuiweicong 2017/11/8
 */

public class SelectLinearLayout extends LinearLayout {
    private boolean multiSelectEnabled = false;
    private boolean selectCanCancel = false;
    private SelectHandler selectHandler;

    public SelectLinearLayout(Context context) {
        this(context, null);
    }

    public SelectLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        selectHandler = SelectHandlerFactory.createHandler(multiSelectEnabled, selectCanCancel);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 循环添加 onClick listener， 现在能想到的一个坑是：如果业务再添加 onClick listener 有可能会冲突
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child != null) {
                final int position = i;
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleSelectEvent(v, position);
                    }
                });
            }
        }
    }

    private void handleSelectEvent(View v, int position) {
        selectHandler.handleSelectEvent(v, position);
    }


    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        selectHandler.setOnItemSelectListener(onItemSelectListener);
    }
}
