package com.khanh.dictionary.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.khanh.dictionary.R;
import com.khanh.dictionary.util.Util;

public class DialogCreate extends Dialog {
    Context context;
    OnClickAccept onClickAccept;


    public DialogCreate(@NonNull Context context, OnClickAccept onClickAccept) {

        super(context);

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

        setContentView(R.layout.dialog_create);

        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
    }

    public void setText(String t1, String t2, String t3) {
        edt1.setText(t1);
        edt2.setText(t2);
        edt3.setText(t3);


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
    EditText edt2;
    EditText edt3;


    private void onclick() {


        edt1 = findViewById(R.id.edt_1);
        edt2 = findViewById(R.id.edt_2);
        edt3 = findViewById(R.id.edt_3);

        txt1 = findViewById(R.id.txt_1);
        txt2 = findViewById(R.id.txt_2);
        txt3 = findViewById(R.id.txt_3);
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");


        findViewById(R.id.btn_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkData()) {
                    onClickAccept.onAccept(edt1.getText().toString(), edt2.getText().toString(), edt3.getText().toString());
                    dismiss();
                }
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAccept.onCancel();
                dismiss();
            }
        });
    }


    private boolean checkData() {

        if (edt1.getText().toString().isEmpty() || edt3.getText().toString().isEmpty() || edt2.getText().toString().isEmpty()) {
            Util.showMessenger("Vui lòng nhập tên đầy đủ các trường. Nếu trống vui lòng ghi \"none\"", context);
            return false;
        }

        return true;
    }


    @Override
    public void dismiss() {
        super.dismiss();

    }


    public interface OnClickAccept {
        void onAccept(String edt1, String ed2, String edt3);

        void onCancel();
    }
}
