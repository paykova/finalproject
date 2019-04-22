package org.softuni.finalpoject.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.domain.entities.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MessageServiceModel extends BaseServiceModel {

    private String title;
    private String content;
    private User author;

    public MessageServiceModel() {
    }

    @NotNull(message = "Title must not be null!")
    @NotEmpty(message = "Title must not be empty!")
    @Length(min = 2, message = "Title must be at least 2 characters long!")
    @Length(max = 20, message = "Title must be maximum 20 characters long!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(message = "Content must not be null!")
    @NotEmpty(message = "Content must not be empty!")
    @Length(min = 10, message = "Content must be at least 10 characters long!")
    @Length(max = 100, message = "Content must be maximum 100 characters long!")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}