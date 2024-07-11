package words.autocomplete;

import java.util.List;

public class CLIMenu {

    public void printMenu() {
        clearConsole();
        System.out.println("1. Suggest words with prefix");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    public void exploreSuggestions(List<String> suggestions) {
        System.out.println("Suggestions: ");
        for (String word: suggestions) {
            System.out.println(" - " + word);
        }
    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
