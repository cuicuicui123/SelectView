package com.cwc.selectview;

import android.content.Context;
import android.content.res.TypedArray;
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

    public SelectLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectLinearLayout, defStyleAttr, 0);
        multiSelectEnabled = a.getBoolean(R.styleable.SelectLinearLayout_multi_select_enabled, false);
        selectCanCancel = a.getBoolean(R.styleable.SelectLinearLayout_select_can_cancel, false);
        a.recycle();
        init();
    }


    public SelectLinearLayout(Context context) {
        super(context);
        init();
    }

    public SelectLinearLayout(Context context, boolean multiSelectEnabled, boolean selectCanCancel) {
        super(context);
        this.multiSelectEnabled = multiSelectEnabled;
        this.selectCanCancel = selectCanCancel;
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
