package com.rajpoot.readislamadmin.Models;

public class TasbehatModel {
    String No;
    String Refrence, TitleArabic, TitleEng, Meaning, NameEng;



    public TasbehatModel() {
    }

    public TasbehatModel(String no, String refrence, String titleArabic, String titleEng, String meaning, String nameEng) {
        No = no;
        Refrence = refrence;
        TitleArabic = titleArabic;
        TitleEng = titleEng;
        Meaning = meaning;
        NameEng = nameEng;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getRefrence() {
        return Refrence;
    }

    public void setRefrence(String refrence) {
        Refrence = refrence;
    }

    public String getTitleArabic() {
        return TitleArabic;
    }

    public void setTitleArabic(String titleArabic) {
        TitleArabic = titleArabic;
    }

    public String getTitleEng() {
        return TitleEng;
    }

    public void setTitleEng(String titleEng) {
        TitleEng = titleEng;
    }

    public String getMeaning() {
        return Meaning;
    }

    public void setMeaning(String meaning) {
        Meaning = meaning;
    }

    public String getNameEng() {
        return NameEng;
    }

    public void setNameEng(String nameEng) {
        NameEng = nameEng;
    }
}
