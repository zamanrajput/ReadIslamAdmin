package com.rajpoot.readislamadmin.Models;

public class HadeesModel {
    private String bodyArabic, bodyEnglish, bodyUrdu, reference, titleEnglish, titleUrdu;

    public HadeesModel(String bodyArabic, String bodyEnglish, String bodyUrdu, String reference, String titleEnglish, String titleUrdu) {
        this.bodyArabic = bodyArabic;
        this.bodyEnglish = bodyEnglish;
        this.bodyUrdu = bodyUrdu;
        this.reference = reference;
        this.titleEnglish = titleEnglish;
        this.titleUrdu = titleUrdu;
    }

    public HadeesModel() {

    }

    public String getBodyArabic() {
        return bodyArabic;
    }

    public void setBodyArabic(String bodyArabic) {
        this.bodyArabic = bodyArabic;
    }

    public String getBodyEnglish() {
        return bodyEnglish;
    }

    public void setBodyEnglish(String bodyEnglish) {
        this.bodyEnglish = bodyEnglish;
    }

    public String getBodyUrdu() {
        return bodyUrdu;
    }

    public void setBodyUrdu(String bodyUrdu) {
        this.bodyUrdu = bodyUrdu;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleUrdu() {
        return titleUrdu;
    }

    public void setTitleUrdu(String titleUrdu) {
        this.titleUrdu = titleUrdu;
    }
}