package com.khanh.dictionary.presenter;


import com.khanh.dictionary.base.BasePresenter;
import com.khanh.dictionary.model.ListVocabulary;
import com.khanh.dictionary.model.ListVocabularyType;
import com.khanh.dictionary.model.Vocabulary;
import com.khanh.dictionary.model.VocabularyType;

import java.util.ArrayList;

public interface MainPresenter extends BasePresenter {

    void getData();

    void saveData();

    void demo();
    ListVocabulary classVocabularies();
    ListVocabularyType classVocabularyType();


    public ArrayList<Vocabulary> vocabularyArrayList() ;
    public ArrayList<VocabularyType> typeArrayList() ;

    int maxkeyV();
    int maxkeyT();
    void setV(int i);
    void setT(int i);

    void deleteType(int i);

    void notif();

}