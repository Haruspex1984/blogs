package fr.brenard.blogs.exceptions;

public class ForbiddenWordsException extends RuntimeException{

    public ForbiddenWordsException(String message){
        super(message);
    }
}
