package ru.javaschool.polud.mvcarticle;

public class Article {
    private int id;
    private String articleHeader;
    private String articleText;

    public Article(int id, String articleHeader, String articleText) {
        this.id = id;
        this.articleHeader = articleHeader;
        this.articleText = articleText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleHeader() {
        return articleHeader;
    }

    public void setArticleHeader(String articleHeader) {
        this.articleHeader = articleHeader;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }
}
