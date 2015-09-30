package hu.adamsan.poemgenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordTest {
    private static Word sut = new Word("sut");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        sut.addFollowingWord("alma");

        sut.addFollowingWord("korte");
        sut.addFollowingWord("korte");

        sut.addFollowingWord("barack");
        sut.addFollowingWord("barack");
        sut.addFollowingWord("barack");

        sut.addFollowingWord("cseresznye");
        sut.addFollowingWord("cseresznye");
        sut.addFollowingWord("cseresznye");
        sut.addFollowingWord("cseresznye");

        sut.addFollowingWord("dió");
        sut.addFollowingWord("dió");
        sut.addFollowingWord("dió");
        sut.addFollowingWord("dió");
        sut.addFollowingWord("dió");
        System.out.println(sut);
    }

    @Test
    public void getSortedFollowersShouldReturnSortedFollowersWithCorrectSize() {
        List<Entry<String, Integer>> followers = sut.getSortedFollowers(10);
        assertEquals(5, followers.size());
    }

    @Test
    public void getSortedFollowersShouldReturnSortedFollowersWithCorrectOrdering() {
        List<Entry<String, Integer>> followers = sut.getSortedFollowers(10);
        Integer value = followers.get(0).getValue();
        String key = followers.get(0).getKey();
        assertEquals("dió", key);
        assertEquals(new Integer(5), value);
    }

    @Test
    public void canFindWordInList() {
        List<Word> words = new ArrayList<>();
        words.add(new Word("alma"));

        int indexOf = words.indexOf(new Word("alma"));
        assertEquals(0, indexOf);

        words.get(indexOf).addFollowingWord("korte");
        System.out.println(words);
    }
}
