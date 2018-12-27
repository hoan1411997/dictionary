package com.khanh.dictionary.ui.activity;

import android.content.Context;

import com.khanh.dictionary.base.BaseView;
import com.khanh.dictionary.presenter.MainPresenter;


public interface MainView extends BaseView<MainPresenter> {
        Context getCxt();
        void next();
        void notif();

}