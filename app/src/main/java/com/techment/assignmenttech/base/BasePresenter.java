package com.techment.assignmenttech.base;

public class BasePresenter<V> {
    protected V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }
}
