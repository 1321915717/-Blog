package blog.blog.com.entity;


public class ArticleAndCategory {
    private Article article;

    public  Article getArticle(){
        return  article;
    }
    public  void setArticle(Article article){

        this.article=article;
    }

    private  Category category;

    public  void  setCategory(Category category){this.category=category;}
    public  Category getCategory(){return category;}


}
