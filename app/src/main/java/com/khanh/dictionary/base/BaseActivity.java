package com.khanh.dictionary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter>
        extends AppCompatActivity
        implements BaseView<T> {
    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contentViewId = getContentViewId();
        if (contentViewId <= 0) {
            return;
        }
        setContentView(contentViewId);
        presenter = createPresenter();
        initializeComponents();
    }

    @Override
    public T getPresenter() {
        return presenter;
    }

    public abstract int getContentViewId();

    public abstract void initializeComponents();

}