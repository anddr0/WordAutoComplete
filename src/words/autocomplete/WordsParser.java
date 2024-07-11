package words.autocomplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WordsParser {

    public ArrayList<String> parseWordsFromFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String cleanText = stringBuilder.toString().replaceAll("[^a-zA-Z\\s]", "");
            String[] parsedWordList = cleanText.split("\\s+");
            return new ArrayList<>(Arrays.asList(parsedWordList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> parseWordsFromText(String text) {
        String cleanText = text.replaceAll("[^a-zA-Z\\s]", "");
        String[] parsedWordList = cleanText.split("\\s+");
        return new ArrayList<>(Arrays.asList(parsedWordList));
    }
}
