package fr.brenard.blogs.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ForbiddenWordsVerifier {

    File forbiddenWordsAsTxtFile = new File("src/main/java/fr/brenard/blogs/configuration/ForbiddenWords.txt");
    Scanner scan = new Scanner(forbiddenWordsAsTxtFile);
    List<String> forbiddenWordsAsList = new ArrayList<>();

    public ForbiddenWordsVerifier() throws FileNotFoundException {
    }

    public boolean verifyForbiddenWords(String content) {
        addForbiddenWordsToList();
        for (String word : forbiddenWordsAsList
        ) {
            if (word.equals(content)) {
                return false;
            }
        }
        return true;
    }

    private void addForbiddenWordsToList() {
        while (scan.hasNextLine()) {
            forbiddenWordsAsList.add(scan.nextLine());
        }
    }


}
