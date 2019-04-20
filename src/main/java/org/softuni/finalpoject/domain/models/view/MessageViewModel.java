package org.softuni.finalpoject.domain.models.view;

import org.softuni.finalpoject.domain.entities.User;

public class MessageViewModel {

    private String title;
    private String content;
    private User author;

    public MessageViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
