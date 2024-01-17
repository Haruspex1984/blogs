package fr.brenard.blogs.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Nom d'utilisateur ou mot de passe invalide");
    }

}
