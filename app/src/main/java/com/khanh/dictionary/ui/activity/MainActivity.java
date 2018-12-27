package com.khanh.dictionary.ui.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.khanh.dictionary.R;
import com.khanh.dictionary.adapter.ItemAdapter;
import com.khanh.dictionary.base.BaseActivity;
import com.khanh.dictionary.model.Vocabulary;
import com.khanh.dictionary.presenter.MainPresenter;
import com.khanh.dictionary.presenter.impl.MainPresenterImpl;
import com.khanh.dictionary.ui.dialog.DialogCreateItem;
import com.khanh.dictionary.ui.dialog.DialogListType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainView, View.OnClickListener {
    ItemAdapter itemAdapter;
    @BindView(R.id.rsv_item)
    RecyclerView recyclerView;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @OnClick(R.id.ic_search)
    void onSearch() {
        getPresenter().vocabularyArrayList().clear();
        for (int j = 0; j < getPresenter().classVocabularies().list.size(); j++) {
            if (getPresenter().classVocabularies().list.get(j).getType() == 1)
                if (getPresenter().classVocabularies().list.get(j).getContent().length() > 5)
                    getPresenter().vocabularyArrayList().add(getPresenter().classVocabularies().list.get(j));
        }
        notif();
        txtTitle.setText("Search" + "");
    }

    @OnClick(R.id.txt_title)
    void clickTitlle() {
        DialogListType dialogListType = new DialogListType(this, getPresenter().classVocabularyType().list, new DialogListType.OnItemClick() {
            @Override
            public void onAccept(int i, String name) {
                getPresenter().vocabularyArrayList().clear();
                for (int j = 0; j < getPresenter().classVocabularies().list.size(); j++) {
                    if (getPresenter().classVocabularies().list.get(j).getType() == i)
                        getPresenter().vocabularyArrayList().add(getPresenter().classVocabularies().list.get(j));
                }
                notif();
                txtTitle.setText(name + "");
            }


        }, getPresenter());
        dialogListType.show();
    }

    @OnClick(R.id.ic_add)
    void clickAdd() {

        DialogCreateItem dialogCreate = new DialogCreateItem(this, getPresenter().classVocabularyType().list, new DialogCreateItem.OnClickAccept() {
            @Override
            public void onAccept(String edt1, int ed2, String edt3) {
                Log.d("CLOCK", "onAccept: " + ed2 + edt1 + edt3);

                getPresenter().vocabularyArrayList().add(new Vocabulary(getPresenter().maxkeyV(), edt1, ed2, edt3));
                notif();


                getPresenter().classVocabularies().list.add(new Vocabulary(getPresenter().maxkeyV(), edt1, ed2, edt3));

                getPresenter().setV(getPresenter().maxkeyV() + 1);
                getPresenter().saveData();

            }

            @Override
            public void onCancel() {

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
        getPresenter().getData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }


    @Override
    public Context getCxt() {
        return this;
    }

    @Override
    public void next() {

        itemAdapter = new ItemAdapter(getPresenter().vocabularyArrayList(), this, getPresenter().classVocabularyType().list, new ItemAdapter.OnItemClick() {
            @Override
            public void onclick(int position) {
                Log.d("CLICK", "onclick: " + position);

            }

            @Override
            public void onEdit(int position) {
                Log.d("onEdit", "onEdit: " + position);
                final Vocabulary a = getPresenter().vocabularyArrayList().get(position);
                DialogCreateItem dialogCreate = new DialogCreateItem(MainActivity.this, getPresenter().classVocabularyType().list, new DialogCreateItem.OnClickAccept() {
                    @Override
                    public void onAccept(String edt1, int ed2, String edt3) {
                        Log.d("CLOCK", "onAccept: " + ed2 + edt1 + edt3);

                        a.setContent(edt1);
                        a.setType(ed2);
                        a.setDesc(edt3);
                        getPresenter().saveData();
                        notif();

                    }

                    @Override
                    public void onCancel() {

                    }
                });


                dialogCreate.show();
                dialogCreate.setText(a.getContent(), a.getType(), a.getDesc());

            }

            @Override
            public void onDel(int position) {
                Log.d("onDel", "onDel: " + position);
                int id = getPresenter().vocabularyArrayList().get(position).getId();
                getPresenter().vocabularyArrayList().remove(position);
                notif();

                for (int i = 0; i < getPresenter().classVocabularies().list.size(); i++) {
                    if (id == getPresenter().classVocabularies().list.get(i).getId()) {
                        getPresenter().classVocabularies().list.remove(i);
                        break;
                    }
                }

                getPresenter().saveData();

            }

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void notif() {
        itemAdapter.notifyDataSetChanged();
    }
}