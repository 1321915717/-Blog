package blog.blog.com.entity;

import java.util.Date;

public class UserD {

    private Integer  udId;
    private  Integer udArticleId;
    private  Integer udUserId;
    private String  udCreateTime;

    public Integer getUdArticleId() {
        return udArticleId;
    }

    public void setUdArticleId(Integer udArticleId) {
        this.udArticleId = udArticleId;
    }

    public Integer getUdId() {
        return udId;
    }

    public void setUdId(Integer udId) {
        this.udId = udId;
    }



    public Integer getUdUserId() {
        return udUserId;
    }

    public void setUdUserId(Integer udUserId) {
        this.udUserId = udUserId;
    }

    public String getUdCreateTime() {
        return udCreateTime;
    }

    public void setUdCreateTime(String udCreateTime) {
        this.udCreateTime = udCreateTime;
    }


}
