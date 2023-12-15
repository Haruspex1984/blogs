package fr.brenard.blogs.services.impl;

import fr.brenard.blogs.models.entities.Blog;
import fr.brenard.blogs.tools.ForbiddenWordsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlogCRUDServiceImplTest {

    @Test
    void verifyBlogTitleContainsForbiddenWords() {
        assertTrue(ForbiddenWordsVerifier.containsForbiddenWords("Pisser"));
    }



}