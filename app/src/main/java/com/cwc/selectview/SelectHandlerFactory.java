package com.cwc.selectview;

/**
 * @author Cuiweicong 2017/11/8
 */

public class SelectHandlerFactory {
    public static SelectHandler createHandler(boolean isMultiSelect, boolean canCancel){
        if (isMultiSelect) {
            return new MultiSelectHandlerImpl();
        } else if (canCancel) {
            return new SingleCanCancelHandlerImpl();
        } else {
            return new SingleSelectHandlerImpl();
        }
    }
}
