package tests.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import words.autocomplete.WordsAutoCompleteManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordsAutoCompleteManagerTest {
    private WordsAutoCompleteManager manager;

    @BeforeEach
    public void setUp() {
        String text = "apple banana apricot berry cherry";
        manager = new WordsAutoCompleteManager(text, false);
    }

    @Test
    public void testSuggest() {
        List<String> suggestions = manager.suggest("ap");
        assertEquals(List.of("apple", "apricot"), suggestions);
    }

    @Test
    public void testSuggestWithMaxDepth() {
        List<String> suggestions = manager.suggest("a", 1);
        assertEquals(List.of("apple"), suggestions);
    }
}