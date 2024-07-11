package words.autocomplete;

import java.util.List;
import java.util.Scanner;

public class WordsAutoCompleteManager {
    private final WordsParser wordsParser;
    private final WordsAutoComplete wordSuggester;
    private final CLIMenu menu;
    private final Scanner scanner;

    public WordsAutoCompleteManager(String text, boolean isFilePath) {
        wordsParser = new WordsParser();
        menu = new CLIMenu();
        this.scanner = new Scanner(System.in);
        wordSuggester = new WordsAutoComplete(createCharTree(text, isFilePath));
    }

    public WordsAutoCompleteManager(String text, boolean isFilePath, int defaultSuggestionMaxDepth) {
        wordsParser = new WordsParser();
        menu = new CLIMenu();
        this.scanner = new Scanner(System.in);
        wordSuggester = new WordsAutoComplete(createCharTree(text, isFilePath), defaultSuggestionMaxDepth);
    }

    public CharTree createCharTree(String text, boolean isFilePath) {
        if (isFilePath) {
            return new CharTree(wordsParser.parseWordsFromFile(text));
        }
        else {
            return new CharTree(wordsParser.parseWordsFromText(text));
        }
    }

    public void startProgram() {
        boolean running = true;
        while (running) {
            menu.printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleSuggest();
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public void handleSuggest() {
        System.out.print("Enter prefix: ");
        String prefix = scanner.nextLine();
        System.out.print("Enter maximum depth (or press Enter to use default): ");
        String maxDepthInput = scanner.nextLine();
        List<String> suggestions;

        if (maxDepthInput.isEmpty()) {
            suggestions = suggest(prefix);
        } else {
            int maxDepth = Integer.parseInt(maxDepthInput);
            suggestions = suggest(prefix, maxDepth);
        }

        menu.exploreSuggestions(suggestions);
        System.out.println("Press Enter to return to the menu");
        scanner.nextLine();
    }

    public List<String> suggest(String prefix, int maxDepth) {
        return wordSuggester.suggest(prefix, maxDepth);
    }

    public List<String> suggest(String prefix) {
        return wordSuggester.suggest(prefix);
    }

}
