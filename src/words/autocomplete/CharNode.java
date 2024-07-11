package words.autocomplete;

import java.util.HashMap;
import java.util.Map;

public class CharNode {
    char character;
    boolean isEndOfWord;
    Map<Character, CharNode> children;

    public CharNode() {
        this.children = new HashMap<>();
    }

    public CharNode(char character) {
        this();
        this.character = character;
    }
}
