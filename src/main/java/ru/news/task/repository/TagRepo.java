package ru.news.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.news.task.domain.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
