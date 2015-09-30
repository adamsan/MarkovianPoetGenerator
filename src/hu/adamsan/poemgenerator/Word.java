package hu.adamsan.poemgenerator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Word {

    private static final Random RAND = new Random();
    private String word;
    private Map<String, Integer> followingWords = new TreeMap<>();

    private int startWordCount = 0;
    private int endWordCount = 0;
    private int middleWordCount = 0;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void addFollowingWord(String following) {
        if (followingWords.containsKey(following)) {
            followingWords.put(following, followingWords.get(following) + 1);
        } else {
            followingWords.put(following, 1);
        }
    }

    public Map<String, Integer> getFollowingWords() {
        return followingWords;
    }

    public String getRandomFollower(int limit) {
        String following = "";
        if (limit < 1) {
            limit = followingWords.size();
        }
        List<Entry<String, Integer>> followers = getSortedFollowers(limit);
        limit = Math.min(followers.size(), limit);
        if (limit > 0) {
            int rand = RAND.nextInt(limit);
            return followers.get(rand).getKey();
        } else {
            return "";
        }
    }

    public List<Entry<String, Integer>> getSortedFollowers(int limit) {

        SortedSet<Entry<String, Integer>> reverseSorted = new TreeSet<>((a, b) -> {
            int compare = -1 * Integer.compare(a.getValue(), b.getValue());
            return compare == 0 ? a.getKey().compareTo(b.getKey()) : compare;
        });
        reverseSorted.addAll(followingWords.entrySet());

        List<Entry<String, Integer>> result = reverseSorted.stream().limit(limit).collect(Collectors.toList());

        return result;
    }

    public Word wasStartWord() {
        startWordCount += 1;
        return this;
    }

    public Word wasEndWord() {
        endWordCount += 1;
        return this;
    }

    public Word wasMiddleWord() {
        middleWordCount += 1;
        return this;
    }

    public int getStartWordCount() {
        return startWordCount;
    }

    public int getEndWordCount() {
        return endWordCount;
    }

    public int getMiddleWordCount() {
        return middleWordCount;
    }

    public int getOccurances() {
        return startWordCount + middleWordCount + endWordCount;
    }

    @Override
    public String toString() {
        return String.format("'%s' (%d)<%d-%d-%d>", word, followingWords.size(), startWordCount, middleWordCount, endWordCount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        return true;
    }

}
