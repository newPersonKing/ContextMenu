package com.gy.smartbardemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Movie {
    @Id(autoincrement = true)
    private Long id;
    private String Url;
    private String MovieName;
    private String EMovieName;
    private String MovieContent;
    private String EMovieContent;
    private String code;

    public String getEMovieName() {
        return EMovieName;
    }

    public void setEMovieName(String EMovieName) {
        this.EMovieName = EMovieName;
    }

    public String getEMovieContent() {
        return EMovieContent;
    }

    public void setEMovieContent(String EMovieContent) {
        this.EMovieContent = EMovieContent;
    }

    @Generated(hash = 585196511)
    public Movie(Long id, String Url, String MovieName, String EMovieName,
            String MovieContent, String EMovieContent, String code) {
        this.id = id;
        this.Url = Url;
        this.MovieName = MovieName;
        this.EMovieName = EMovieName;
        this.MovieContent = MovieContent;
        this.EMovieContent = EMovieContent;
        this.code = code;
    }

    @Generated(hash = 1263461133)
    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getMovieContent() {
        return MovieContent;
    }

    public void setMovieContent(String movieContent) {
        MovieContent = movieContent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
