package org.softuni.finalpoject.domain.models.service;

import org.hibernate.validator.constraints.Length;
import org.softuni.finalpoject.constants.Constants;
import org.softuni.finalpoject.domain.entities.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MessageServiceModel extends BaseServiceModel {

    private String title;
    private String content;
    private User author;

    public MessageServiceModel() {
    }

    @NotNull(message = Constants.TITLE_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.TITLE_MUST_NOT_BE_EMPTY)
    @Length(min = 2, message = Constants.TITLE_MUST_BE_AT_LEAST_2_CHARACTERS_LONG)
    @Length(max = 20, message = Constants.TITLE_MUST_BE_MAX_20_CHARACTERS_LONG)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(message = Constants.CONTENT_MUST_NOT_BE_NULL)
    @NotEmpty(message = Constants.CONTENT_MUST_NOT_BE_EMPTY)
    @Length(min = 10, message = Constants.CONTENT_MUST_BE_AT_LEAST_10_CHARACTERS_LONG)
    @Length(max = 100, message = Constants.CONTENT_MUST_BE_MAX_100_CHARACTERS_LONG)
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
