package ru.news.task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.news.task.domain.Article;
import ru.news.task.domain.Tag;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    Article getById(Long id);

    Page<Article> findByIsArchivedFalse(Pageable pageable);

    Page<Article> findByIsArchivedTrue(Pageable pageable);

    @Query("SELECT art FROM Article art JOIN art.tags t WHERE t = ?1 AND art.isArchived = false")
    Page<Article> findArticleByTag(Tag tag, Pageable pageable);

}