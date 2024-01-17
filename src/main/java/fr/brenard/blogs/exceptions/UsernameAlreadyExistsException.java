package fr.brenard.blogs.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class UsernameAlreadyExistsException extends DataIntegrityViolationException {
    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }
}
