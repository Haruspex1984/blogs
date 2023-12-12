package fr.brenard.blogs.tools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ForbiddenWordsVerifier {

    List<String> forbiddenWordsAsList = new ArrayList<>();


    public boolean titleIsForbidden(String content) {
        addForbiddenWordsToList();
        String[] words = content.split("\\s+");
        for (String word : words) {
            if (forbiddenWordsAsList.contains(word.toLowerCase()))
                return true;
        }
        return false;
    }

    private void addForbiddenWordsToList() {
        File forbiddenWordsAsTxtFile = new File("src/main/java/fr/brenard/blogs/configuration/ForbiddenWords.txt");
        try (Scanner scan = new Scanner(forbiddenWordsAsTxtFile)) {
            while (scan.hasNextLine()) {
                forbiddenWordsAsList.add(scan.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
