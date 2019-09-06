package blog.blog.com.entity;

import java.util.Date;
import java.util.List;

public class Article {
    private Integer articleId;

    private String articleTitle;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Integer articleStatus;

    private String articleCreateTime;

    private String articleUpdateTime;

    private String articleContent;

    public Integer getArticleGiveCount() {
        return articleGiveCount;
    }

    public void setArticleGiveCount(Integer articleGiveCount) {
        this.articleGiveCount = articleGiveCount;
    }

    private  Integer articleGiveCount;


    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    private List<Tag>  tagList;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private  Category category;



    public String getArticleImg() {
        return articleImg;
    }


    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    private  String articleImg;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(String articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(String articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}