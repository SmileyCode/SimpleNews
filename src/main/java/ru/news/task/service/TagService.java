package ru.news.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.news.task.domain.Tag;
import ru.news.task.repository.TagRepo;

@Service
public class TagService {
    @Autowired
    TagRepo tagRepo;

    public Tag saveOrUpdate(String tag){
        return (isTagExist(tag) ? tagRepo.findByName(tag) : tagRepo.save(new Tag(tag)));
    }

    public boolean isTagExist(String tag){
        try {
            tagRepo.findByName(tag).getName().length();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Tag findByName(String name){
        return tagRepo.findByName(name);
    }
}
