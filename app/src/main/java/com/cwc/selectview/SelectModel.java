package com.cwc.selectview;

import android.view.View;

/**
 * @author Cuiweicong 2017/11/8
 */

public class SelectModel {
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
