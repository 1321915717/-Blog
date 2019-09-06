package blog.blog.com.entity;

import java.util.Date;

public class Comment {
    private Integer commentId;

    public Integer getCommentUid() {
        return commentUid;
    }

    public void setCommentUid(Integer commentUid) {
        this.commentUid = commentUid;
    }

    public WechatUser getWechatUser() {
        return wechatUser;
    }

    public void setWechatUser(WechatUser wechatUser) {
        this.wechatUser = wechatUser;
    }

    private  WechatUser wechatUser;
    private Integer commentUid;

    private Integer commentArticleId;

    private String commentContent;

    private String commentTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }


    public Integer getCommentArticleId() {
        return commentArticleId;
    }

    public void setCommentArticleId(Integer commentArticleId) {
        this.commentArticleId = commentArticleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}