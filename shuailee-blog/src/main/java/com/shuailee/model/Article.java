package com.shuailee.model;

public class Article {

    private Integer article_id;
    private String article_title;
    private String article_content;
    private Integer article_category;
    private String article_tags;
    private String article_keywords;
    private Integer article_views;
    private Integer article_comments;
    private Integer article_likes;
    private Integer article_can_comments;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getArticle_views() {
        return article_views;
    }

    public void setArticle_views(Integer article_views) {
        this.article_views = article_views;
    }

    public Integer getArticle_comments() {
        return article_comments;
    }

    public void setArticle_comments(Integer article_comments) {
        this.article_comments = article_comments;
    }

    public Integer getArticle_likes() {
        return article_likes;
    }

    public void setArticle_likes(Integer article_likes) {
        this.article_likes = article_likes;
    }

    public Integer getArticle_category() {
        return article_category;
    }

    public void setArticle_category(Integer article_category) {
        this.article_category = article_category;
    }

    public String getArticle_tags() {
        return article_tags;
    }

    public void setArticle_tags(String article_tags) {
        this.article_tags = article_tags;
    }

    public String getArticle_keywords() {
        return article_keywords;
    }

    public void setArticle_keywords(String article_keywords) {
        this.article_keywords = article_keywords;
    }

    public Integer getArticle_can_comments() {
        return article_can_comments;
    }

    public void setArticle_can_comments(Integer article_can_comments) {
        this.article_can_comments = article_can_comments;
    }
}
