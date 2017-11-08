package com.cwc.selectview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cuiweicong 2017/11/8
 */

public class SelectLinearLayout extends LinearLayout {
    private View currentSelectView;
    private List<View> currentSelectViews;
    private OnItemSelectListener onItemSelectListener;
    private boolean multiSelectEnabled = false;
    private boolean selectCanCancel = false;

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
        currentSelectViews = new ArrayList<>();
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
        if (multiSelectEnabled) {
            multiSelect(v, position);
        } else {
            singleSelect(v, position);
        }
    }

    /**
     * 多选
     */
    private void multiSelect(View v, int position) {
        if (v == currentSelectView) {
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
     * 单选
     */
    private void singleSelect(View v, int position) {
        if (selectCanCancel) {
            singleSelectCanCancel(v, position);
        } else {
            singleSelectCannotCancel(v, position);
        }
    }

    /**
     * 单选可以取消
     */
    private void singleSelectCanCancel(View v, int position) {
        if (v == currentSelectView) {
            unSelectView(v, position);
        } else {
            selectOneUnSelectCurrent(v, position);
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

    /**
     * 取消选择
     */
    private void unSelectView(View v, int position) {
        currentSelectView.setSelected(false);
        currentSelectView = null;
        onItemSelectOrUnSelect(new SelectModel(v, position, false));
    }

    /**
     * 选中事件
     */
    private void onItemSelectOrUnSelect(SelectModel selectModel) {
        if (onItemSelectListener != null) {
            onItemSelectListener.onItemSelect(selectModel);
        }
    }

    /**
     * 单选不能取消
     */
    private void singleSelectCannotCancel(View v, int position) {
        if (v != currentSelectView) {
            selectOneUnSelectCurrent(v, position);
        }
    }

    public View getCurrentSelectView() {
        return currentSelectView;
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }


    public static class SelectModel{
        View view;
        int position;
        boolean isSelect;

        public SelectModel(View view, int position, boolean isSelect) {
            this.view = view;
            this.position = position;
            this.isSelect = isSelect;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public interface OnItemSelectListener {
        void onItemSelect(SelectModel selectModel);
    }
}
