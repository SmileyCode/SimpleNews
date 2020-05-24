package ru.news.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.news.task.domain.Article;
import ru.news.task.service.ArticleService;
import ru.news.task.service.ErrorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/news")
@CrossOrigin
public class MainController {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<?> addArticle(@Valid @RequestBody Article article, BindingResult result){
        ResponseEntity<?> hasErrors = errorService.ErrorValidation(result);
        if(hasErrors!=null) return hasErrors;

        Article newArticle = articleService.saveOrUpdate(article);
        return new ResponseEntity<Article>(newArticle, HttpStatus.CREATED);
    }

    @GetMapping("/allarticles")
    public Iterable<Article> getAllArticles(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 20) Pageable pageable){
        return articleService.findAll(pageable);
    }

    @GetMapping("/bytag")
    public Iterable<Article> getAllByTag(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 20) Pageable pageable, @RequestParam String tag){
        return articleService.findArticleByTag(tag, pageable);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long id){
        Article response = articleService.findById(id);
        return new ResponseEntity<Article>(response, HttpStatus.OK);
    }

    @PostMapping("/archive/{id}")
    public ResponseEntity<?> archive(@PathVariable Long id){
        Article newArticle = articleService.archive(id);
        return new ResponseEntity<Article>(newArticle, HttpStatus.OK);
    }

    @PostMapping("/unarchive/{id}")
    public ResponseEntity<?> unarchive(@PathVariable Long id){
        Article newArticle = articleService.unarchive(id);
        return new ResponseEntity<Article>(newArticle, HttpStatus.OK);
    }

    @GetMapping("/archived")
    public Iterable<Article> getAllArchived(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, size = 20) Pageable pageable){
        return articleService.findArchived(pageable);
    }

}
