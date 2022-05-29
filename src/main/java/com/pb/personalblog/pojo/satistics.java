package com.pb.personalblog.pojo;

/**
 * @author zhk
 * @date 2022/5/29 16:19
 */
public class satistics {
    int article = 1;
    int visit = 2;
    int comment = 3;
    int word = 4;

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "satistics{" +
                "article=" + article +
                ", visit=" + visit +
                ", comment=" + comment +
                ", word=" + word +
                '}';
    }

    public void setWord(int word) {
        this.word = word;
    }
}
