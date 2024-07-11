package words.autocomplete;

public class Main {

   public static void main(String[] args) {
        // Example usage
        String filepath = "src\\words\\autocomplete\\most_common_words.txt";
        WordsAutoCompleteManager manager = new WordsAutoCompleteManager(filepath, true);
        manager.startProgram();
    }
}