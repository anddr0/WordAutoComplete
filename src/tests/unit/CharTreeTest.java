package tests.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import words.autocomplete.CharNode;
import words.autocomplete.CharTree;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CharTreeTest {
    private CharTree charTree;

    @BeforeEach
    public void setUp() {
        List<String> words = List.of("apple", "banana", "apricot", "berry", "cherry");
        charTree = new CharTree(words);
    }

    @Test
    public void testInsertAndFindStartNode() {
        CharNode startNode = charTree.findStartNode("ap");
        assertNotNull(startNode);
    }

    @Test
    public void testFindStartNodeNonExistentPrefix() {
        CharNode startNode = charTree.findStartNode("z");
        assertNull(startNode);
    }
}
