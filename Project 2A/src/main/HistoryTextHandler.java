package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;


public class HistoryTextHandler extends NgordnetQueryHandler {

    public NGramMap NGM;

    public HistoryTextHandler(NGramMap map) {
        super();
        this.NGM = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        StringBuilder response = new StringBuilder();
        for (String words : q.words()) {
            if (!(this.NGM.weightHistory(words, q.startYear(), q.endYear()).equals(new TimeSeries()))) {
                response.append(words).append(": {").append(this.NGM.weightHistory(words, q.startYear(), q.endYear()).toString()).append("}\n");
            }
        }
        return response.toString();
    }
}
