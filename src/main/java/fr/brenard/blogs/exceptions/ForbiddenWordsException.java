package fr.brenard.blogs.exceptions;

public class ForbiddenWordsException extends Exception{

    public ForbiddenWordsException(String message){
        super(message);
    }
}
