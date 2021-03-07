package com.company.entities;

public class Criminals {
    private int CriId;
    private String CriName;
    private String article;
    private int CriAge;
    private boolean wanted;

    public Criminals() {
    }
    public Criminals(int CriId, String CriName, String article, int CriAge, boolean wanted) {
       setCriId(CriId);
       setCriName(CriName);
       setArticle(article);
       setCriAge(CriAge);
       setWanted(wanted);
    }
    public Criminals(String CriName, String article, int CriAge, boolean wanted) {
        setCriName(CriName);
        setArticle(article);
        setCriAge(CriAge);
        setWanted(wanted);
    }

    public int getCriId() {
        return CriId;
    }

    public void setCriId(int criId) {
        CriId = criId;
    }

    public String getCriName() {
        return CriName;
    }

    public void setCriName(String criName) {
        CriName = criName;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getCriAge() {
        return CriAge;
    }

    public void setCriAge(int criAge) {
        CriAge = criAge;
    }

    public boolean isWanted() {
        return wanted;
    }

    public void setWanted(boolean wanted) {
        this.wanted = wanted;
    }

    public String crimeInfo(){
        return "The criminal "+getCriName()+" was convicted of "+getArticle();
    }

    @Override
    public String toString() {
        return "Criminals{" +
                "CriId=" + CriId +
                ", CriName='" + CriName + '\'' +
                ", article='" + article + '\'' +
                ", CriAge=" + CriAge +
                ", wanted=" + wanted +
                '}';
    }
}