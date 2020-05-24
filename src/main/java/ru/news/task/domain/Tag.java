package ru.news.task.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "Name can't be blank")
    private String name;

    @ManyToMany(mappedBy="tags")
    @JsonIgnore
    private Set<Article> articleList = new HashSet<>();

    public Tag() {
    }

    public Tag(@NotBlank(message = "Name can't be blank") String name) {
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(Set<Article> articleList) {
        this.articleList = articleList;
    }
}
