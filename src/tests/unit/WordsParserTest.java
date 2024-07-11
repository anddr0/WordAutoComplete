package tests.unit;

import org.junit.jupiter.api.Test;
import words.autocomplete.WordsParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordsParserTest {
    private final WordsParser parser = new WordsParser();

    @Test
    public void testParseWordsFromText() {
        String text = "apple, banana! apricot? berry; cherry.";
        List<String> words = parser.parseWordsFromText(text);
        assertEquals(List.of("apple", "banana", "apricot", "berry", "cherry"), words);
    }
}
