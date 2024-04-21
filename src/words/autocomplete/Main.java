package words.autocomplete;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> wordList = List.of("car", "carpet", "jar", "java", "javascript", "internet");
        String text = "car, carpet, jar, java, javascript, internet";
        String filepath = "src/words/autocomplete/words.txt";
        CharTree charTree = new CharTree(filepath, true);
        System.out.println("Suggesting for [c]: " + charTree.suggest("c"));
        System.out.println("Suggesting for [car]: " + charTree.suggest("car"));
        System.out.println("Suggesting for [carp]: " + charTree.suggest("carp"));
        System.out.println("Suggesting for [jav]: " + charTree.suggest("jav"));
        System.out.println("Suggesting for [intern]: " + charTree.suggest("internet"));
        System.out.println("Suggesting for [foo]: " + charTree.suggest("foo"));
        // We also can clarify how many suggested words we want to get
        System.out.println("\nSuggesting for [c], but with {1} depth: " + charTree.suggest("c", 1));
    }
}