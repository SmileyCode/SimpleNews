package ru.news.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.news.task.domain.Article;
import ru.news.task.domain.Tag;
import ru.news.task.exception.ArticleIdException;
import ru.news.task.repository.ArticleRepo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    TagService tagService;

    public Iterable<Article> findAll(Pageable pageable){
        return articleRepo.findByIsArchivedFalse(pageable).getContent();
    }

    public Iterable<Article> findArchived(Pageable pageable){
        return articleRepo.findByIsArchivedTrue(pageable).getContent();
    }

    public Article findById(Long id){
        return articleRepo.getById(id);
    }

    public Article saveOrUpdate(Article article){
        article.setArchived(false);
        if(article.getTaglist().length() > 0) {
            Set<String> tagsString = new HashSet<>(Arrays.asList(article.getTaglist().split(" ")));
            Set<Tag> tags = new HashSet<>();
            for (String tag : tagsString){
                Tag newtag = tagService.saveOrUpdate(tag);
                tags.add(newtag);
            }
            article.setTags(tags);
            article.setTaglist("");
        }
        return articleRepo.save(article);
    }

    public Iterable<Article> findArticleByTag(String tag, Pageable pageable){
        return articleRepo.findArticleByTag(tagService.findByName(tag), pageable).getContent();
    }

    public Article archive(Long id){
        Article article = findById(id);

        if(article == null){
            throw new ArticleIdException("Article " + id + " doesn't exist");
        }

        article.setArchived(true);
        return articleRepo.save(article);
    }

    public Article unarchive(Long id){
        Article article = findById(id);

        if(article == null){
            throw new ArticleIdException("Article " + id + " doesn't exist");
        }

        article.setArchived(false);
        return articleRepo.save(article);
    }
}
