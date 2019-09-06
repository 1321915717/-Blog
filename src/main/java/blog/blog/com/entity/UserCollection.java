package blog.blog.com.entity;

import java.util.Date;

public class UserCollection {

        private  Integer ucId;
        private  Integer ucArticleId;

    public Integer getUcId() {
        return ucId;
    }

    public void setUcId(Integer ucId) {
        this.ucId = ucId;
    }

    public Integer getUcArticleId() {
        return ucArticleId;
    }

    public void setUcArticleId(Integer ucArticleId) {
        this.ucArticleId = ucArticleId;
    }

    public Integer getUcUserId() {
        return ucUserId;
    }

    public void setUcUserId(Integer ucUserId) {
        this.ucUserId = ucUserId;
    }

    public String getUcCreateTime() {
        return ucCreateTime;
    }

    public void setUcCreateTime(String ucCreateTime) {
        this.ucCreateTime = ucCreateTime;
    }

    private  Integer ucUserId;
        private String ucCreateTime;
}
