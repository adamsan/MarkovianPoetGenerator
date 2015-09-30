package hu.adamsan.poemgenerator;

import java.util.ArrayList;
import java.util.List;

public class SonnetAnalyzer {

    private SonnetReader reader;

    private List<Word> words;
    private List<String> startingWords;
    private List<String> endingWords;

    public SonnetAnalyzer(SonnetReader reader) {
        this.reader = reader;
        words = new ArrayList<>();
        startingWords = new ArrayList<>();
        endingWords = new ArrayList<>();
    }

    public void analyze() {
        List<String> allLines = reader.readAllLines();
        for (String line : allLines) {
            if (!line.trim().isEmpty()) {
                analyzeLine(line.trim());
            }
        }
    }

    private void analyzeLine(String line) {
        String[] wordsInLine = line.split("\\s+");
        // System.out.println(Arrays.toString(wordsInLine));

        for (int i = 0; i < wordsInLine.length; i++) {
            String currentWord = wordsInLine[i];
            Word w = new Word(currentWord);
            int index = words.indexOf(w);
            if (index >= 0) {
                w = words.get(index);
            } else {
                words.add(w);
            }

            if (i == 0) {
                startingWords.add(currentWord);
                w.wasStartWord();
                String nextWord = wordsInLine[i + 1];
                w.addFollowingWord(nextWord);
            } else if (i == wordsInLine.length - 1) {
                w.wasEndWord();

            } else {
                endingWords.add(currentWord);
                w.wasMiddleWord();
                String nextWord = wordsInLine[i + 1];
                w.addFollowingWord(nextWord);
            }
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public List<String> getStartingWords() {
        return startingWords;
    }

    public List<String> getEndingWords() {
        return endingWords;
    }

}
