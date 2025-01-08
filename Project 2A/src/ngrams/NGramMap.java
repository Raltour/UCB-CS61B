package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.TreeMap;


/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    public TreeMap<String, TimeSeries> wordsData;
    public TimeSeries countsData;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        this.wordsData = new TreeMap<>();
        this.countsData = new TimeSeries();
        In in = new In(wordsFilename);
        while(!in.isEmpty()) {
            String word = in.readString();
            int year = in.readInt();
            double num = in.readDouble();
            in.readLine();
            if (!this.wordsData.containsKey(word)) {
                this.wordsData.put(word, new TimeSeries());
            }
            this.wordsData.get(word).put(year, num);
        }
        in = new In(countsFilename);
        while(!in.isEmpty()) {
            String[] splitLine = in.readLine().split(",");
            in.readLine();
            this.countsData.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!this.wordsData.containsKey(word)) {
            return new TimeSeries();
        } else {
            return new TimeSeries(this.wordsData.get(word), startYear, endYear);
        }
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return this.countHistory(word, TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(this.countsData, TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!this.wordsData.containsKey(word)) {
            return new TimeSeries();
        } else {
            TimeSeries weightMap =  new TimeSeries(this.wordsData.get(word), startYear, endYear);
            return weightMap.dividedBy(this.countsData);
        }
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!this.wordsData.containsKey(word)) {
            return new TimeSeries();
        } else {
            return this.wordsData.get(word).dividedBy(this.countsData);
        }
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        TimeSeries totalSummed = new TimeSeries();
        for (String word : words) {
            TimeSeries wordMap = new TimeSeries(this.wordsData.get(word), startYear, endYear);
            totalSummed.plus(wordMap);
        }
        return totalSummed.dividedBy(this.countsData);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries totalSummed = new TimeSeries();
        for (String word : words) {
            TimeSeries wordMap = new TimeSeries(this.wordsData.get(word), TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
            totalSummed.plus(wordMap);
        }
        return totalSummed.dividedBy(this.countsData);
    }

}
