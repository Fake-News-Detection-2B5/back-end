package com.example.ai_service.entities;

import javax.persistence.*;

@Entity
@Table(name= "social_post_details")
public class SocialPostEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "score")
    private String score;

    public SocialPostEntity(Long id, String url, String score){
        this.id = id;
        this.url = url;
        this.score = score;
    }

    public SocialPostEntity(String url, String score){
        this.url = url;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocialPostEntity)) return false;

        SocialPostEntity that = (SocialPostEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getUrl().equals(that.getUrl())) return false;
        return getscore().equals(that.getScore());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUrl().hashCode();
        result = 31 * result + getScore().hashCode();
        return result;
    }
}
