# Word-Auto-Complete Dictionary

This Java package provides functionality for building an auto-complete dictionary and suggesting word completions based on user input. The package includes a `CharTree` class that implements the auto-complete functionality using the `Prefix Tree`, along with a `Main` class demonstrating its usage.

## Usage

### 1. Building the Dictionary

To build the auto-complete dictionary, you can use one of the provided constructors of the `CharTree` class:

- `CharTree(List<String> wordList)`: Constructs the dictionary by directly passing a list of words.
- `CharTree(String text, boolean isPath)`: Constructs the dictionary from either a string containing words or a file path.

Example usage:

```java
List<String> wordList = List.of("car", "carpet", "jar", "java", "javascript", "internet");
String text = "car, carpet, jar, java, javascript, internet";
String filepath = "src/words/autocomplete/words.txt";
CharTree charTree = new CharTree(filepath, true);
```

### 2. Finding Auto-Complete Candidates

Once the dictionary is built, you can use the `suggest` method of the `CharTree` class to get auto-complete suggestions based on user input.

- `suggest(String prefix)`: Returns a list of auto-complete suggestions for the given prefix.
- `suggest(String prefix, int maxDepth)`: Returns a list of auto-complete suggestions for the given prefix, limited by the specified depth.

Example usage:

```java
System.out.println("Suggesting for [c]: " + charTree.suggest("c"));
System.out.println("Suggesting for [car]: " + charTree.suggest("car"));
System.out.println("Suggesting for [carp]: " + charTree.suggest("carp"));
System.out.println("Suggesting for [jav]: " + charTree.suggest("jav"));
System.out.println("Suggesting for [intern]: " + charTree.suggest("internet"));
System.out.println("Suggesting for [foo]: " + charTree.suggest("foo"));
System.out.println("\nSuggesting for [c], but with {1} depth: " + charTree.suggest("c", 1));
```

## Dictionary Construction

The dictionary construction process involves parsing input text and building a prefix tree. The `CharNode` class represents each node in the prefix tree.

## Auto-Complete Algorithm

The auto-complete algorithm utilizes the prefix tree to efficiently find all words matching the given prefix. It recursively traverses the tree, starting from start node (`findStartNode() method`), appending characters to the current word until reaching the end of a tree branch or the maximum depth limit.

## Exception Handling

The package includes a `CharTreeException` class to handle exceptions related to invalid input data or suggestions.

## Example

A sample `Main` class is provided to demonstrate how to use the package and its functionality.

## Performance Considerations

The package is designed to handle large dictionaries efficiently by utilizing an appropriate data structure for fast search operations.
