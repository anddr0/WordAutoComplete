package words.autocomplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CharTree {
    //  -----------------Node Class-----------------
    public static class CharNode {
        Map<Character, CharNode> children;
        char character;
        boolean isEndOfWord;

        public CharNode() { children = new HashMap<>(); }

        public CharNode(char character) {
            this.character = character;
            children = new HashMap<>();
        }

        public void insert(String word) {
            if (word.isEmpty() || word.isBlank())
                return;
            CharNode child = children.computeIfAbsent(word.charAt(0), CharNode::new);
            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isEndOfWord = true;
        }
    }
    //  ---------------------------------------------------

    CharNode root; //   root node

    //  -------------Dictionary Create Or Load constructors-------------
    public CharTree(List<String> wordList) {
        root = new CharNode();
        for (String word : wordList)
            root.insert(word);
    }

    public CharTree(String text, boolean isPath) {
        root = new CharNode();
        if (isPath) {
            processFile(text);
        } else {
            processText(text);
        }
    }

    private void processFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String cleanText = stringBuilder.toString().replaceAll("[^a-zA-Z\\s]", "");
            String[] wordList = cleanText.split("\\s+");
            for (String word : wordList) {
                root.insert(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processText(String text) {
        String cleanText = text.replaceAll("[^a-zA-Z\\s]", "");
        String[] wordList = cleanText.split("\\s+");
        for (String word : wordList) {
            root.insert(word);
        }
    }

    //  ---------------------------------------------------------------

    //  -------------Methods for word suggestion-------------
    public void suggestAllWords(CharNode root, List<String> list, StringBuffer curr, int maxDepth) {
        if (list.size() >= maxDepth) return;
        if (root.isEndOfWord)
            list.add(curr.toString());
        if (root.children == null || root.children.isEmpty())
            return;
        for (CharNode child : root.children.values()) {
            suggestAllWords(child, list, curr.append(child.character), maxDepth);
            curr.setLength(curr.length() - 1);
        }
    }

    public CharNode findStartNode(String prefix) {
        CharNode startNode = root;
        for (char c : prefix.toCharArray()) {
            startNode = startNode.children.get(c);
            if (startNode == null) return null; //  returns null node because we don't have word with such prefix
        }
        return startNode;
    }

    public void checkInputData(String prefix, int maxDepth) throws CharTreeException {
        if (prefix.isEmpty() || prefix.isBlank())
            throw new CharTreeException("Prefix is Null or Empty");
        if (maxDepth < 1)
            throw new CharTreeException("Suggesting list depth can be only >= 1");
    }

    public List<String> suggest(String prefix) { return suggest(prefix, Integer.MAX_VALUE); }

    public List<String> suggest(String prefix, int maxDepth) {
        try { checkInputData(prefix, maxDepth); }
        catch (CharTreeException e) { throw new RuntimeException(e); }

        CharNode startNode = findStartNode(prefix);
        List<String> list = new ArrayList<>();
        if (startNode == null) return list;
        suggestAllWords(startNode, list, new StringBuffer(prefix), maxDepth);
        return list;
    }
    //  ---------------------------------------------------

    //  -----------------Exception Class-----------------
    public static class CharTreeException extends Exception {
        public CharTreeException(String message) {
            super(message);
        }
    }//  -------------------------------------------------

}
