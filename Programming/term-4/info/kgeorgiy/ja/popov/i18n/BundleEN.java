package info.kgeorgiy.ja.popov.i18n;

import java.util.ListResourceBundle;

public class BundleEN extends ListResourceBundle {

    protected Object[][] getContents() {
        return WORDS;
    }

    private static final Object[][] WORDS = {
            {"allStat", "All statistics"},
            {"sentenceStatistic", "Sentence statistics"},
            {"lineStatistic", "Line statistics"},
            {"wordStatistic", "Word statistics"},
            {"numberStatistic", "Number statistics"},
            {"moneyStatistic", "Money statistics"},
            {"dateStatistic", "Date statistics"},
            {"sentence", "sentence"},
            {"sentenceParent", "sentence"},
            {"sentencesParent", "sentences"},
            {"line", "line"},
            {"lineParent", "line"},
            {"lines", "lines"},
            {"linesParent", "lines"},
            {"length", "length of "},
            {"uniqueMale", "unique"},
            {"uniqueFemale", "unique"},
            {"uniqueNoun", "unique"},
            {"uniques", "uniques"},
            {"Number", "Number of"},
            {"number", "number"},
            {"numberParent", "number"},
            {"numbersParent", "numbers"},
            {"word","word"},
            {"wordParent", "word"},
            {"wordsParent", "words"},
            {"moneyMany", "money"},
            {"money", "money"},
            {"moneyParent", "money"},
            {"date", "date"},
            {"dateParent", "date"},
            {"datesParent", "dates"},
            {"minNoun", "Minimal"},
            {"minFemale", "Minimal"},
            {"minMany", "Minimal"},
            {"maxNoun", "Maximal"},
            {"maxFemale", "Maximal"},
            {"maxMany", "Maximal"},
            {"averageFemale", "Average"},
            {"averageNoun", "Average"}
    };
}
