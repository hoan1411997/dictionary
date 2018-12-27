package com.khanh.dictionary.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.khanh.dictionary.R;
import com.khanh.dictionary.model.VocabularyType;
import com.khanh.dictionary.util.Util;

import java.util.ArrayList;

public class DialogCreateItem extends Dialog {
    Context context;
    OnClickAccept onClickAccept;
    ArrayList<VocabularyType> vocabularyTypes;

    public DialogCreateItem(@NonNull Context context, ArrayList<VocabularyType> vocabularyTypes, OnClickAccept onClickAccept) {

        super(context);
        this.vocabularyTypes = vocabularyTypes;
        this.context = context;
        this.onClickAccept = onClickAccept;
        setupDialog();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setupDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_create_item);

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

    TextView txt1;
    TextView txt2;
    TextView txt3;
    EditText edt1;
    Spinner spin;
    EditText edt3;

    int position = -1;
    String arr[];
    ArrayAdapter<String> adapter;

    private void onclick() {
        arr = new String[vocabularyTypes.size()];
        for (int i = 0; i < vocabularyTypes.size(); i++) {
            arr[i] = vocabularyTypes.get(i).getName();
        }

        edt1 = findViewById(R.id.edt_1);

        edt3 = findViewById(R.id.edt_3);

        txt1 = findViewById(R.id.txt_1);
        txt2 = findViewById(R.id.txt_2);
        txt3 = findViewById(R.id.txt_3);
        edt1.setText("");

        edt3.setText("");

        spin = findViewById(R.id.spinner1);
        adapter = new ArrayAdapter<String>
                (
                        getContext(),
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positions, long id) {
                position = positions;
                Log.d("CLOCK", "onItemSelected: " + positions);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        findViewById(R.id.btn_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkData()) {
                    onClickAccept.onAccept(edt1.getText().toString(), vocabularyTypes.get(position).getId(), edt3.getText().toString());
                    dismiss();
                }
            }
        });

        findViewById(R.id.btn_cancel).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickAccept.onCancel();
                        dismiss();
                    }
                });
    }


    private boolean checkData() {

        if (edt1.getText().toString().isEmpty() || edt3.getText().toString().isEmpty() || position < 0) {
            Util.showMessenger("Vui lòng nhập tên đầy đủ các trường. Nếu trống vui lòng ghi \"none\"", context);
            return false;
        }

        return true;
    }


    @Override
    public void dismiss() {
        super.dismiss();

    }

    public void setText(String t1, int pos, String t3) {
        edt1.setText(t1);
        edt3.setText(t3);

        for (int i = 0; i < vocabularyTypes.size(); i++) {
            if (vocabularyTypes.get(i).getId() == pos) position = i;
        }
        spin.setSelection(position);
    }

    public interface OnClickAccept {
        void onAccept(String edt1, int ed2, String edt3);

        void onCancel();
    }
}
