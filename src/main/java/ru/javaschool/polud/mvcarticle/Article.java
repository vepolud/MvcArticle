package ru.javaschool.polud.mvcarticle;

import com.sun.istack.NotNull;
import sun.security.util.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String articleHeader;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String articleText;

    public Article( String articleHeader, String articleText) {
        this.articleHeader = articleHeader;
        this.articleText = articleText;
    }

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
