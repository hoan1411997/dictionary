package com.khanh.dictionary.ui.activity;

import android.view.View;

import com.khanh.dictionary.R;
import com.khanh.dictionary.base.BaseActivity;
import com.khanh.dictionary.presenter.MainPresenter;
import com.khanh.dictionary.presenter.impl.MainPresenterImpl;
import com.khanh.dictionary.ui.dialog.DialogCreate;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainView, View.OnClickListener {


    @OnClick(R.id.ic_add)
    void clickAdd() {
        DialogCreate dialogCreate = new DialogCreate(this, new DialogCreate.OnClickAccept() {

            public void onAccept(String edt1, String ed2, String edt3) {
                System.out.println(edt1 + ed2 + edt3);
            }

            @Override
            public void onCancel() {
                System.out.println("BB");
            }
        });
        dialogCreate.show();
    }


    @Override
    public MainPresenter createPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeComponents() {
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }


}