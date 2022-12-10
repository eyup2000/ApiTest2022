package class06_pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {
    //POJO=> plain old Java object
    /*
    POJO içinde private degişkenler, getter ve setter i butun parametrelere sahip constructor ve parametreleri olmayan
    constructor ile toString() kullanılmalı.
     */
    //private degişken oluştur
    private Integer userId;
    private String title;
    private Boolean completed;

    // tüm paramaeterlere sahip constructor


    public JsonPlaceHolderPojo(Integer userId,String title,Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed =completed;
    }

    public JsonPlaceHolderPojo() {

    }
// getter and setter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    //toString olustur

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

