package hu.adamsan.poemgenerator;

public class Main {

    public static final String DEFAULT_SONNET_FILE = "sonnets.txt";

    /**
     * Markovian poem generator based on the following article:
     * http://cdawson.net/blog/2013/08/28/with-apologies-to-english-teachers-everywhere/
     * 
     * @param args
     */
    public static void main(String[] args) {
        SonnetReader reader = new SonnetReader(DEFAULT_SONNET_FILE);
        SonnetAnalyzer analyzer = new SonnetAnalyzer(reader);
        SonnetGenerator generator = new SonnetGenerator(analyzer);

        int sonnets = 3;
        times(sonnets, () -> {
            System.out.println(generator.generateSonnet());
            System.out.println();
        });
    }

    public static void times(int times, Runnable process) {
        while (times-- > 0) {
            process.run();
        }
    }

}
