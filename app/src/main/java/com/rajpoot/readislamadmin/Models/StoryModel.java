package com.rajpoot.readislamadmin.Models;

public class StoryModel {
    private String BodyEnglish,BodyUrdu,TitleEnglish,TitleUrdu;

    public StoryModel(String bodyEnglish, String bodyUrdu, String titleEnglish, String titleUrdu) {
        BodyEnglish = bodyEnglish;
        BodyUrdu = bodyUrdu;
        TitleEnglish = titleEnglish;
        TitleUrdu = titleUrdu;
    }

    public StoryModel() {
    }


    public String getBodyEnglish() {
        return BodyEnglish;
    }

    public void setBodyEnglish(String bodyEnglish) {
        BodyEnglish = bodyEnglish;
    }

    public String getBodyUrdu() {
        return BodyUrdu;
    }

    public void setBodyUrdu(String bodyUrdu) {
        BodyUrdu = bodyUrdu;
    }

    public String getTitleEnglish() {
        return TitleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        TitleEnglish = titleEnglish;
    }

    public String getTitleUrdu() {
        return TitleUrdu;
    }

    public void setTitleUrdu(String titleUrdu) {
        TitleUrdu = titleUrdu;
    }
}
