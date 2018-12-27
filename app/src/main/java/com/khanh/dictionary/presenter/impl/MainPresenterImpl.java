package com.khanh.dictionary.presenter.impl;


import com.google.gson.Gson;
import com.khanh.dictionary.base.BasePresenterImpl;
import com.khanh.dictionary.model.ListVocabulary;
import com.khanh.dictionary.model.ListVocabularyType;
import com.khanh.dictionary.model.Vocabulary;
import com.khanh.dictionary.model.VocabularyType;
import com.khanh.dictionary.presenter.MainPresenter;
import com.khanh.dictionary.ui.activity.MainView;
import com.khanh.dictionary.util.Key;
import com.khanh.dictionary.util.PrefUtil;
import com.khanh.dictionary.util.Util;

import java.util.ArrayList;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    ListVocabulary classVocabularies;
    ListVocabularyType classVocabularyType;
    public ArrayList<Vocabulary> vocabularyArrayList = new ArrayList<>();
    public ArrayList<VocabularyType> typeArrayList = new ArrayList<>();
    private static final String TAG = "MainPresenterImpl";

    public MainPresenterImpl(MainView view) {
        super(view);
    }

    int maxkeyV = -1;
    int maxkeyT = -1;

    @Override
    public void getData() {
        demo();

        String voca = PrefUtil.getString(getView().getCxt(), Key.DATA_VOCABULARY, "");
        if (voca != null && voca != "") {
            Gson gson = new Gson();
            classVocabularies = gson.fromJson(voca, ListVocabulary.class);

        } else classVocabularies = new ListVocabulary();

        String type = PrefUtil.getString(getView().getCxt(), Key.DATA_TYPE, "");
        if (type != null && type != "") {
            Gson gson = new Gson();
            classVocabularyType = gson.fromJson(type, ListVocabularyType.class);

        } else classVocabularyType = new ListVocabularyType();

        vocabularyArrayList.addAll(classVocabularies.list);
        typeArrayList.addAll(classVocabularyType.list);

        for (int i = 0; i < classVocabularies.list.size(); i++) {
            if (classVocabularies.list.get(i).getId() > maxkeyV)
                maxkeyV = classVocabularies.list.get(i).getId();
        }
        for (int i = 0; i < classVocabularyType.list.size(); i++) {
            if (classVocabularyType.list.get(i).getId() > maxkeyT)
                maxkeyT = classVocabularyType.list.get(i).getId();
        }
        maxkeyV = maxkeyV + 1;
        maxkeyT = maxkeyT + 1;
        getView().next();

    }

    @Override
    public void saveData() {


        String voca = Util.toJson(classVocabularies);

        PrefUtil.saveString(getView().getCxt(), Key.DATA_VOCABULARY, voca);


        String type = Util.toJson(classVocabularyType);

        PrefUtil.saveString(getView().getCxt(), Key.DATA_TYPE, type);


    }

    @Override
    public void demo() {
        String id = PrefUtil.getString(getView().getCxt(), Key.DATA_STATE, "");
        if (id != "") return;
        classVocabularyType = new ListVocabularyType();
        classVocabularyType.list.add(new VocabularyType(1, "n", "Danh từ", "Danh từ"));

        saveData();
        PrefUtil.saveString(getView().getCxt(), Key.DATA_STATE, "1");

    }

    @Override
    public ListVocabulary classVocabularies() {
        return classVocabularies;
    }

    @Override
    public ListVocabularyType classVocabularyType() {
        return classVocabularyType;
    }

    @Override
    public ArrayList<Vocabulary> vocabularyArrayList() {
        return vocabularyArrayList;
    }

    @Override
    public ArrayList<VocabularyType> typeArrayList() {
        return typeArrayList;
    }

    @Override
    public int maxkeyV() {
        return maxkeyV;
    }

    @Override
    public int maxkeyT() {
        return maxkeyT;
    }

    @Override
    public void setV(int i) {
        maxkeyV = i;
    }

    @Override
    public void setT(int i) {
        maxkeyT = i;
    }

    @Override
    public void deleteType(int i) {
        int j = 0;

        while (j < classVocabularies.list.size()) {
            if (classVocabularies.list.get(j).getType() == i) {
                classVocabularies.list.remove(j);
            } else {
                j = j + 1;
            }
        }

        j = 0;

        while (j < vocabularyArrayList.size()) {
            if (vocabularyArrayList.get(j).getType() == i) {
                vocabularyArrayList.remove(j);
            } else {
                j = j + 1;
            }
        }


        getView().notif();
    }

    @Override
    public void notif() {
        getView().notif();
    }
}