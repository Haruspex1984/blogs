package fr.brenard.blogs.services;

import fr.brenard.blogs.exceptions.ForbiddenWordsException;
import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.models.forms.blogs.BlogForm;

public interface BlogValidationService {

    void verifyTitle(String title) throws ForbiddenWordsException;
}
