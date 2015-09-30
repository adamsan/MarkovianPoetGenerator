package hu.adamsan.poemgenerator;

import java.util.List;
import java.util.Random;

public class SonnetGenerator {
    private static final Random RAND = new Random();

    private SonnetAnalyzer analyzer;
    public int desiredLines = 6;

    public SonnetGenerator(SonnetAnalyzer analyzer) {
        this.analyzer = analyzer;
        analyzer.analyze();
    }

    public String generateSonnet() {
        String generatedSonnet = "";
        for (int i = 0; i < desiredLines; i++) {
            String line = writeSonnetLine();
            generatedSonnet += line + "\n";
        }
        return generatedSonnet;
    }

    private String writeSonnetLine() {
        List<Word> words = analyzer.getWords();
        List<String> startingWords = analyzer.getStartingWords();

        String line = "";
        String currentWord = startingWords.get(RAND.nextInt(startingWords.size() - 1));
        int maxWords = 14;
        while (maxWords-- > 0 && !currentWord.isEmpty()) {
            int index = words.indexOf(new Word(currentWord));
            Word word = words.get(index);
            String follower = word.getRandomFollower(10);
            line += word.getWord() + " ";

            currentWord = follower;
        }

        return line.trim();
    }

    public int getDesiredLines() {
        return desiredLines;
    }

    public void setDesiredLines(int desiredLines) {
        this.desiredLines = desiredLines;
    }
}
