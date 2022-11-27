package com.rajpoot.readislamadmin.Models;

public class DuaModel {

    String ArabicBody, EnglishBody, EnglishTitle, UrduBody, UrduTitle;

    public DuaModel(String arabicBody, String englishBody, String englishTitle, String urduBody, String urduTitle) {
        ArabicBody = arabicBody;
        EnglishBody = englishBody;
        EnglishTitle = englishTitle;
        UrduBody = urduBody;
        UrduTitle = urduTitle;
    }

    public DuaModel() {
    }

    public String getArabicBody() {
        return ArabicBody;
    }

    public void setArabicBody(String arabicBody) {
        ArabicBody = arabicBody;
    }

    public String getEnglishBody() {
        return EnglishBody;
    }

    public void setEnglishBody(String englishBody) {
        EnglishBody = englishBody;
    }

    public String getEnglishTitle() {
        return EnglishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        EnglishTitle = englishTitle;
    }

    public String getUrduBody() {
        return UrduBody;
    }

    public void setUrduBody(String urduBody) {
        UrduBody = urduBody;
    }

    public String getUrduTitle() {
        return UrduTitle;
    }

    public void setUrduTitle(String urduTitle) {
        UrduTitle = urduTitle;
    }
}
