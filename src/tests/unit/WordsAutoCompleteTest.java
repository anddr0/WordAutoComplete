package tests.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import words.autocomplete.CharTree;
import words.autocomplete.WordsAutoComplete;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordsAutoCompleteTest {
    private WordsAutoComplete autoComplete;

    @BeforeEach
    public void setUp() {
        List<String> words = List.of("apple", "banana", "apricot", "berry", "cherry");
        CharTree charTree = new CharTree(words);
        autoComplete = new WordsAutoComplete(charTree);
    }

    @Test
    public void testSuggest() {
        List<String> suggestions = autoComplete.suggest("ap");
        assertEquals(List.of("apple", "apricot"), suggestions);
    }

    @Test
    public void testSuggestWithMaxDepth() {
        List<String> suggestions = autoComplete.suggest("a", 1);
        assertEquals(List.of("apple"), suggestions);
    }

    @Test
    public void testSuggestInvalidPrefix() {
        List<String> suggestions = autoComplete.suggest("z");
        assertEquals(List.of(), suggestions);
    }

    @Test
    public void testSuggestInvalidMaxDepth() {
        assertThrows(RuntimeException.class, () -> {
            autoComplete.suggest("a", 0);
        });
    }
}
