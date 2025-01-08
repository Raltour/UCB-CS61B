package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;

public class HistoryHandler extends NgordnetQueryHandler {

    public NGramMap ngramMap;

    public HistoryHandler(NGramMap ngm) {
        super();
        ngramMap = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String word : q.words()) {
            lts.add(this.ngramMap.weightHistory(word));
        }

        XYChart chart = Plotter.generateTimeSeriesChart(q.words(), lts);
        return Plotter.encodeChartAsString(chart);
    }
}
