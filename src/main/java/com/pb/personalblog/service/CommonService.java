package com.pb.personalblog.service;

import com.pb.personalblog.pojo.Comment;

/**
 * @author zhk
 * @date 2022/5/30 17:24
 * 公共页面底部显示数据service
 */
public interface CommonService {
    /**
     * 获取文章总数
     *
     * @return
     */
    int getArticle();

    /**
     * 获取访客总数
     *
     * @return
     */
    int getVisit();

    /**
     * 获取评论总数
     *
     * @return
     */
    int getComment();
}
