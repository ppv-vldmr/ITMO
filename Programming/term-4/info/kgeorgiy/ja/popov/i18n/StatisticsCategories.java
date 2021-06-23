package info.kgeorgiy.ja.popov.i18n;

import java.text.*;
import java.util.Comparator;
import java.util.Locale;

public class StatisticsCategories {

    static void updateStatistic(final StatisticsParameters lineStatistic, final String word) {
        if (word.isEmpty()) {
            return;
        }
        lineStatistic.count++;
        lineStatistic.average += word.length();
        lineStatistic.uniqueSet.add(word);
        lineStatistic.uniqueCount = lineStatistic.uniqueSet.size();
        minMaxStat(lineStatistic, word, word);
    }

    @SuppressWarnings("unchecked")
    static void minMaxStat(final StatisticsParameters statistic, final String word, final String word2) {
        if (statistic.minLengthString == null || statistic.minLength > word.length()) {
            statistic.minLength = word.length();
            statistic.minLengthString = word;
        }
        if (statistic.maxLengthString == null || statistic.maxLength < word.length()) {
            statistic.maxLength = word.length();
            statistic.maxLengthString = word;
        }
        if (statistic.minValue == null) {
            statistic.minValue = word2;
        } else {
            if (statistic.comparator.compare(statistic.minValue, word2) > 0) {
                statistic.minValue = word2;
            }
        }
        if (statistic.maxValue == null) {
            statistic.maxValue = word2;
        } else {
            if (statistic.comparator.compare(word2, statistic.maxValue) > 0) {
                statistic.maxValue = word2;
            }
        }
    }

    static StatisticsParameters getWordStatistic(final Locale locale, final String text, final BreakIterator breakIterator) {
        final StatisticsParameters statistic = new StatisticsParameters();
        statistic.comparator = Collator.getInstance(locale);
        breakIterator.setText(text);
        int beginInd = breakIterator.first();
        int endInd = breakIterator.next();
        while (endInd != BreakIterator.DONE) {
            String word = text.substring(beginInd, endInd);
            beginInd = endInd;
            endInd = breakIterator.next();
            if (word.isEmpty()) continue;
            if (word.length() == 1 && ((int) word.charAt(0)) < 65) {
                continue;
            }
            if (Character.isWhitespace(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
            }
            try {
                NumberFormat.getNumberInstance(locale).parse(word);
                continue;
            } catch (final ParseException ignored) {

            }
            try {
                NumberFormat.getCurrencyInstance(locale).parse(word);
                continue;
            } catch (final ParseException ignored) {

            }
            updateStatistic(statistic, word);
        }
        if (statistic.count != 0) statistic.average /= statistic.count;
        return statistic;
    }

    private static String getNextWord(final String text, final int beginInd, final int endInd) {
        String word = text.substring(beginInd, endInd);
        if (word.isEmpty()) {
            return "";
        }
        if (Character.isWhitespace(word.charAt(word.length() - 1))) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    static StatisticsParameters getNumStatistic(final Locale locale, final String text, final BreakIterator breakIterator, final NumberFormat numberFormat, final boolean isMoney) {
        final StatisticsParameters statistic = new StatisticsParameters();
        // :NOTE: Лишняя работа
        statistic.comparator = (Comparator<String>) (a, b) -> {
            try {
                return Double.compare(numberFormat.parse(a).doubleValue(), numberFormat.parse(b).doubleValue());
            } catch (final ParseException e) {
                return 0;
            }
        };
        // :NOTE: Одно слово
        breakIterator.setText(text);
        int beginInd = breakIterator.first();
        int endInd = breakIterator.next();
        while (endInd != BreakIterator.DONE) {
            final String word = getNextWord(text, beginInd, endInd);
            beginInd = endInd;
            endInd = breakIterator.next();
            Number numWord;
            try {
                numWord = numberFormat.parse(word);
            } catch (final ParseException ignored) {
                continue;
            }
            try {
                numWord = NumberFormat.getCurrencyInstance(locale).parse(word);
                if (!isMoney) {
                    continue;
                }
            } catch (final ParseException ignored) {

            }
            statistic.count++;
            statistic.average += numWord.doubleValue();
            statistic.uniqueSet.add(numWord.toString());
            statistic.uniqueCount = statistic.uniqueSet.size();
            minMaxStat(statistic, word, word);
        }
        if (statistic.count != 0) statistic.average /= statistic.count;
        return statistic;
    }

    static StatisticsParameters getDateStatistic(final String text, final BreakIterator breakIterator, final DateFormat dateFormat) {
        final StatisticsParameters statistic = new StatisticsParameters();
        // :NOTE: Нетранзитивный
        statistic.comparator = (Comparator<String>) (a, b) -> {
            try {
                return dateFormat.parse(a).compareTo(dateFormat.parse(b));
            } catch (final ParseException e) {
                return 0;
            }
        };
        breakIterator.setText(text);
        int beginInd = breakIterator.first();
        int endInd = breakIterator.next();
        while (endInd != BreakIterator.DONE) {
            final String word = getNextWord(text, beginInd, endInd);
            beginInd = endInd;
            endInd = breakIterator.next();
            try {
                dateFormat.parse(word);
            } catch (final ParseException ignored) {
                continue;
            }
            updateStatistic(statistic, word);
        }
        if (statistic.count != 0) statistic.average /= statistic.count;
        return statistic;
    }
}
