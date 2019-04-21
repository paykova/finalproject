package org.softuni.finalpoject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found!")
public class NotFoundExceptions extends RuntimeException {

    private int statusCode;

    public NotFoundExceptions(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

