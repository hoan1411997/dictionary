package com.khanh.dictionary.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.khanh.dictionary.R;
import com.khanh.dictionary.adapter.ItemTypeAdapter;
import com.khanh.dictionary.model.VocabularyType;
import com.khanh.dictionary.presenter.MainPresenter;

import java.util.List;

public class DialogListType extends Dialog {
    Context context;
    OnItemClick onClickAccept;
    List<VocabularyType> list;
    MainPresenter mainPresenter;

    public DialogListType(@NonNull Context context, List<VocabularyType> list, OnItemClick onClickAccept, MainPresenter mainPresenter) {

        super(context);
        this.list = list;
        this.context = context;
        this.onClickAccept = onClickAccept;
        this.mainPresenter = mainPresenter;
        setupDialog();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setupDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_list_type);

        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        super.show();
        onclick();
    }

    RecyclerView recyclerView;
    ItemTypeAdapter itemAdapter;

    private void onclick() {
        recyclerView = findViewById(R.id.rsv_item);
        itemAdapter = new ItemTypeAdapter(list, getContext(), new ItemTypeAdapter.OnItemClick() {
            @Override
            public void onclick(int position) {
                Log.d("CLICK", "onclick: " + position);
                onClickAccept.onAccept(list.get(position).getId(), list.get(position).getName());
                dismiss();

            }

            @Override
            public void onEdit(int position) {
                Log.d("onEdit", "onEdit: " + position);

                final VocabularyType a = list.get(position);
                DialogCreate dialogCreate = new DialogCreate(getContext(), new DialogCreate.OnClickAccept() {

                    public void onAccept(String edt1, String ed2, String edt3) {

                        a.setCode(edt1);
                        a.setName(ed2);
                        a.setDesc(edt3);
                        itemAdapter.notifyDataSetChanged();
                        mainPresenter.saveData();

                        mainPresenter.notif();

                    }

                    @Override
                    public void onCancel() {
                        System.out.println("BB");
                    }
                });
                dialogCreate.show();
                dialogCreate.setText(a.getCode(), a.getName(), a.getDesc());


            }

            @Override
            public void onDel(int position) {
                Log.d("onDel", "onDel: " + position);


                mainPresenter.deleteType(list.get(position).getId());
                mainPresenter.saveData();
                list.remove(position);
                itemAdapter.notifyDataSetChanged();


            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(itemAdapter);
        findViewById(R.id.ic_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcreate();
            }
        });

    }

    void showcreate() {
        DialogCreate dialogCreate = new DialogCreate(getContext(), new DialogCreate.OnClickAccept() {

            public void onAccept(String edt1, String ed2, String edt3) {
                System.out.println(edt1 + ed2 + edt3);

                list.add(new VocabularyType(mainPresenter.maxkeyT(), edt1, ed2, edt3));
                mainPresenter.setV(mainPresenter.maxkeyT() + 1);
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancel() {
                System.out.println("BB");
            }
        });
        dialogCreate.show();

    }

    @Override
    public void dismiss() {
        super.dismiss();

    }

    OnItemClick onItemClick;

    public interface OnItemClick {
        void onAccept(int i, String name);


    }
}
