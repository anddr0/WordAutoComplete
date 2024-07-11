package words.autocomplete;

import java.util.List;
import java.util.ArrayList;

public class WordsAutoComplete {
    private final CharTree charTree;
    private int defaultSuggestionMaxDepth = 5;

    public WordsAutoComplete(CharTree charTree) {
        this.charTree = charTree;
    }

    public WordsAutoComplete(CharTree charTree, int defaultSuggestionMaxDepth) {
        this.charTree = charTree;
        this.defaultSuggestionMaxDepth = defaultSuggestionMaxDepth;
    }

    public void checkInputData(String prefix, int maxDepth) throws CharTree.CharTreeException {
        if (prefix.isEmpty() || prefix.isBlank())
            throw new CharTree.CharTreeException("Prefix is Null or Empty");
        if (maxDepth < 1)
            throw new CharTree.CharTreeException("Suggesting list depth can be only >= 1");
    }

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

    public List<String> suggest(String prefix, int maxDepth) {
        try { checkInputData(prefix, maxDepth); }
        catch (CharTree.CharTreeException e) { throw new RuntimeException(e); }

        CharNode startNode = charTree.findStartNode(prefix);
        List<String> list = new ArrayList<>();
        if (startNode == null) return list;
        suggestAllWords(startNode, list, new StringBuffer(prefix), maxDepth);
        return list;
    }

    public List<String> suggest(String prefix) { return suggest(prefix, defaultSuggestionMaxDepth); }
}
