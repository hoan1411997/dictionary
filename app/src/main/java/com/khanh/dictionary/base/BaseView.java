package com.khanh.dictionary.base;

public interface BaseView<T extends BasePresenter> {
    T getPresenter();

    T createPresenter();

}