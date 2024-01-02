package fr.brenard.blogs.tools;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ForbiddenWordsVerifier {

    private static final List<String> forbiddenWordsList = new ArrayList<>();

    static {
        initializeForbiddenWordsList();
    }



    public static boolean containsForbiddenWords(String content) {
        String[] forbiddenWords = content.split("\\s+");
        for (String word : forbiddenWords) {
            if (forbiddenWordsList.contains(word.toLowerCase()))
                return true;
        }
        return false;
    }

    private static void initializeForbiddenWordsList() {
        File forbiddenWordsFile = new File(Constants.FORBIDDEN_WORDS_FILE_PATH);
        try (Scanner scan = new Scanner(forbiddenWordsFile)) {
            while (scan.hasNextLine()) {
                forbiddenWordsList.add(scan.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
