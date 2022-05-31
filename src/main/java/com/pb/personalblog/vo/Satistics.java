package com.pb.personalblog.vo;

/**
 * @author zhk
 * @date 2022/5/29 16:19
 * 公共页面底部统计数据实体类
 */
public class Satistics {
    int article;
    int visit;
    int comment;

    public Satistics() {
    }

    public Satistics(int article, int visit, int comment) {
        this.article = article;
        this.visit = visit;
        this.comment = comment;
    }

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

    @Override
    public String toString() {
        return "satistics{" +
                "article=" + article +
                ", visit=" + visit +
                ", comment=" + comment +
                '}';
    }
}
