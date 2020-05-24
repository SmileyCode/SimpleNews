package ru.news.task.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "Heading can't be blank")
    private String heading;

    @NotBlank(message = "Maintext can't be blank")
    private String maintext;

    @Transient
    private String taglist;

    private boolean isArchived;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "article_tag",
            joinColumns = { @JoinColumn(name = "fk_article") },
            inverseJoinColumns = { @JoinColumn(name = "fk_tag") })
    private Set<Tag> tags = new HashSet<>();

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getTaglist() {
        return taglist;
    }

    public void setTaglist(String taglist) {
        this.taglist = taglist;
    }
}
