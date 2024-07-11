# Word-Auto-Complete Dictionary

This Java package provides functionality for building an auto-complete dictionary and suggesting word completions based on user input. The package includes a `CharTree` class that implements the auto-complete functionality using the `Prefix Tree`, along with a `WordsAutoCompleteManager` class demonstrating its usage.

![image](https://github.com/anddr0/WordAutoComplete/assets/59768574/4b2a4d85-d4cc-46b3-b569-967398de3e8e)


## Project Structure

- **words.autocomplete**: The main package containing all classes.
  - `WordsAutoCompleteManager`: Manages the auto-complete process and user interaction.
  - `WordsAutoComplete`: Handles suggestions based on the prefix.
  - `CLIMenu`: Provides a command-line interface for user interaction.
  - `CharTree`: Represents the prefix tree structure.
  - `CharNode`: Represents a node in the prefix tree.
  - `WordsParser`: Parses words from text or files.
- **tests.unit**: package with unit tests.
  - `CharTreeTest`: Tests for the `CharTree` class methods.
  - `WordsAutoCompleteTest`: Tests for the `WordsAutoComplete` class methods.
  - `WordsParserTest`: Tests for the `WordsParser` class methods.
  - `WordsAutoCompleteManagerTest`: Tests for the `WordsAutoCompleteManager` class methods. 

## How to Run the Program

### Running from Source Code

1. **Clone the Repository**: 
   ```bash
   git clone <repository-url>
   cd WordsAutoComplete
   ```

2. **Compile the Project**: 
   Make sure you have JDK 8 or higher installed.
   ```bash
   javac -d out -sourcepath src src/words/autocomplete/Main.java
   ```

3. **Run the Program**:
   ```bash
   java -cp out words.autocomplete.Main
   ```

### Running the JAR File

1. **Build the JAR File**: 
   Assuming you have already compiled the project.
   ```bash
   jar cfe WordsAutoComplete.jar words.autocomplete.Main -C out .
   ```

2. **Run the JAR File**:
   ```bash
   java -jar WordsAutoComplete.jar
   ```

## Testing

The project includes unit tests to ensure the functionality and reliability of the auto-complete feature. The tests are located in the `tests.unit` package.

### Running the Tests

1. **Navigate to the Project Directory**:
   ```bash
   cd WordsAutoComplete
   ```

2. **Compile the Tests**:
   Make sure you have JUnit 5 or higher installed.
   ```bash
   javac -d out -sourcepath tests tests/unit/*.java
   ```

3. **Run the Tests**:
   You can use any JUnit-compatible test runner. Here is an example using the command line:
   ```bash
   java -cp out:lib/junit-platform-console-standalone-1.8.1.jar org.junit.platform.console.ConsoleLauncher --scan-class-path
   ```

Each test class contains various test methods to verify the correctness of different functionalities, ensuring that the dictionary construction, word suggestion, and exception handling work as expected.

## Detailed Explanation of the Project

### 1. Building the Dictionary

To build the auto-complete dictionary, you can use the `createCharTree` method in the `WordsAutoCompleteManager` class:

- `createCharTree(String text, boolean isFilePath)`: Constructs the dictionary from either a string containing words or a file path.

Example usage:

```java
String text = "apple banana apricot berry cherry";
WordsAutoCompleteManager manager = new WordsAutoCompleteManager(text, false);
```

### 2. Finding Auto-Complete Candidates

Once the dictionary is built, you can use the `suggest` methods in the `WordsAutoCompleteManager` class to get auto-complete suggestions based on user input.

- `suggest(String prefix)`: Returns a list of auto-complete suggestions for the given prefix.
- `suggest(String prefix, int maxDepth)`: Returns a list of auto-complete suggestions for the given prefix, limited by the specified depth.

Example usage:

```java
List<String> suggestions = manager.suggest("ap");
System.out.println("Suggestions: " + suggestions);
```

### Dictionary Construction

The dictionary construction process involves parsing input text and building a prefix tree using the `CharTree` and `CharNode` classes. The `CharTree` class has methods for inserting words and finding the start node for a given prefix.

### Auto-Complete Algorithm

The auto-complete algorithm utilizes the prefix tree to efficiently find all words matching the given prefix. It recursively traverses the tree, starting from the start node (using the `findStartNode()` method), appending characters to the current word until reaching the end of a tree branch or the maximum depth limit.

### Exception Handling

The package includes a `CharTreeException` class to handle exceptions related to invalid input data or suggestions.

### Example

A sample `WordsAutoCompleteManager` class with class `CLIMenu` are provided to demonstrate how to use the package and its functionality.

### Performance Considerations

The package is designed to handle large dictionaries efficiently by utilizing an appropriate data structure for fast search operations.


