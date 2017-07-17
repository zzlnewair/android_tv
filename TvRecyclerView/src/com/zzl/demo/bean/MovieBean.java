
package com.zzl.demo.bean;



/**
 
 * @ClassName: MovieBean 
 * @Description:  
 * @author zzl
 * 
 
 */
public class MovieBean {

    
    private String title;
   
    private String poster;
   
    private String infotext;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfotext() {
        return infotext;
    }

    public void setInfotext(String infotext) {
        this.infotext = infotext;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "DetailInfo{" +
                "mTitle='" + title + '\'' +
                ", mTextDesc=" + infotext +
                ", mPostImageUrl='" + poster + '\'' +
                '}';
    }
}
