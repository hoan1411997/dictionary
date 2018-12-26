package com.khanh.dictionary.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.google.gson.Gson;
import com.khanh.dictionary.ApplicationContextSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 8/10/2017.
 */

public class Util {
    static Util ins;
    private static ProgressDialog progressDialog;
    private static Dialog dialog;
    Context context;
    public static final String KHONG = "không";
    public static final String MOT = "một";
    public static final String HAI = "hai";
    public static final String BA = "ba";
    public static final String BON = "bốn";
    public static final String NAM = "năm";
    public static final String SAU = "sáu";
    public static final String BAY = "bảy";
    public static final String TAM = "tám";
    public static final String CHIN = "chín";
    public static final String LAM = "lăm";
    public static final String LE = "lẻ";
    public static final String MUOI = "mươi";
    public static final String MUOIF = "mười";
    public static final String MOTS = "mốt";
    public static final String TRAM = "trăm";
    public static final String NGHIN = "nghìn";
    public static final String TRIEU = "triệu";
    public static final String TY = "tỷ";


    public static final String[] number = {KHONG, MOT, HAI, BA,
            BON, NAM, SAU, BAY, TAM, CHIN};

    public static void DisableKeyboard(EditText edt) {

        InputMethodManager imm = (InputMethodManager) ApplicationContextSingleton.getInstance().getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
    }



    public static void showMessenger(String string, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage(string);
        builder.setCancelable(false);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public static String creatFileName(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMdd_hhmss_aaa");
        String fileName = simpleDateFormat.format(System.currentTimeMillis()) + format;
        return fileName;
    }

    public static Util getIns() {
        if (ins == null) {
            ins = new Util();

        }
        return ins;
    }




    public void hideLoadding() {

        if (dialog == null) return;
        if (dialog.isShowing())   dialog.hide();
    }



    public static boolean isGpsEnable(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static HashMap<String, String> jsonToMap(String t) throws JSONException {
        try {
            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject jObject = new JSONObject(t);
            Iterator<?> keys = jObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = jObject.getString(key);
                map.put(key, value);

            }
            return map;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static String toJson(Object o) {
        Gson gson = new Gson();
        String json = gson.toJson(o);
        return json;
    }


}
