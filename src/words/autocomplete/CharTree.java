package words.autocomplete;

import java.util.List;
import java.util.ArrayList;

public class CharTree {
    CharNode root; // root node

    // -------------Dictionary Create Or Load constructors-------------
    public CharTree(List<String> wordList) {
        root = new CharNode();
        for (String word : wordList)
            insert(word);
    }

    public void insert(String word) {
        if (word.isEmpty() || word.isBlank())
            return;
        CharNode currentNode = root;
        for (char c : word.toCharArray()) {
            currentNode = currentNode.children.computeIfAbsent(c, CharNode::new);
        }
        currentNode.isEndOfWord = true;
    }

    public CharNode findStartNode(String prefix) {
        CharNode startNode = root;
        for (char c : prefix.toCharArray()) {
            startNode = startNode.children.get(c);
            if (startNode == null) return null; // returns null node because we don't have word with such prefix
        }
        return startNode;
    }

    // -----------------Exception Class-----------------
    public static class CharTreeException extends Exception {
        public CharTreeException(String message) {
            super(message);
        }
    }
    // -------------------------------------------------
}
