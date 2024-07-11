package words.autocomplete;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

   public static void main(String[] args) {
        String relativePath = "src/words/autocomplete/most_common_words.txt";
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, relativePath);

        WordsAutoCompleteManager manager = new WordsAutoCompleteManager(filePath.toString(), true);
        manager.startProgram();
    }
}