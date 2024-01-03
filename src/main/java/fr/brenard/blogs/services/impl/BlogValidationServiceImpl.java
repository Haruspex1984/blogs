package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.exceptions.ForbiddenWordsException;
import fr.brenard.blogs.services.BlogValidationService;
import fr.brenard.blogs.tools.ForbiddenWordsVerifier;
import org.springframework.stereotype.Service;

@Service
public class BlogValidationServiceImpl implements BlogValidationService {
    @Override
    public void verifyTitle(String title) throws ForbiddenWordsException {
        if (ForbiddenWordsVerifier.containsForbiddenWords(title)) {
            throw new ForbiddenWordsException("Ce titre contient un mot non autorisé");
        }
    }







}
