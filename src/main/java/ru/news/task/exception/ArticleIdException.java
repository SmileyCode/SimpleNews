package ru.news.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArticleIdException extends RuntimeException{

    public ArticleIdException(String message) {
        super(message);
    }
}
