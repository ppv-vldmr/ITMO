package info.kgeorgiy.ja.popov.i18n;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.text.Collator;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static info.kgeorgiy.ja.popov.i18n.StatisticsCategories.*;
import static info.kgeorgiy.ja.popov.i18n.StatisticsWriter.writeStat;

public class TextStatistics {

    public static void main(final String[] args) {
        if (args == null || args.length != 4 || Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Expected 4 non-null args: <input locale> <output locale> <input file> <output file>");
            return;
        }
        final Locale inputLocale = getIOLocale(args[0].toLowerCase());
        final Locale outputLocale = getIOLocale(args[1].toLowerCase());
        if (inputLocale == null || outputLocale == null) {
            System.err.println("Don't have given locale");
            return;
        }
        // :NOTE: ??
        if (!(outputLocale.getLanguage().equals("en") || outputLocale.getLanguage().equals("ru"))) {
            System.err.println("Output locale isn't ru or en");
            return;
        }
        // :NOTE: ??
        final ResourceBundle outputBundle = ResourceBundle.
                getBundle("info.kgeorgiy.ja.popov.i18n.Bundle" + outputLocale.getLanguage().toUpperCase());

        final StatisticsParameters lineStatistic = new StatisticsParameters();
        final StatisticsParameters numStatistic;
        final StatisticsParameters dateStatistic;
        final StatisticsParameters moneyStatistic;
        final StatisticsParameters wordStatistic;
        final StatisticsParameters sentenceStatistic;

        // :NOTE: BufferedReader
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[2]), StandardCharsets.UTF_8))) {
            String word;
            final StringBuilder wordBuilder = new StringBuilder();
            lineStatistic.comparator = Collator.getInstance(inputLocale);

            while ((word = bufferedReader.readLine()) != null) {
                updateStatistic(lineStatistic, word);
                wordBuilder.append(word).append('\n');
            }

            lineStatistic.average /= lineStatistic.count;
            word = wordBuilder.toString();

            numStatistic = getNumStatistic(
                    inputLocale,
                    word,
                    BreakIterator.getWordInstance(inputLocale),
                    NumberFormat.getNumberInstance(inputLocale),
                    false
            );
            moneyStatistic = getNumStatistic(
                    inputLocale,
                    word,
                    BreakIterator.getWordInstance(inputLocale),
                    NumberFormat.getCurrencyInstance(inputLocale),
                    true
            );
            wordStatistic = getWordStatistic(
                    inputLocale,
                    word,
                    BreakIterator.getWordInstance(inputLocale)
            );
            sentenceStatistic = getWordStatistic(
                    inputLocale,
                    word,
                    BreakIterator.getSentenceInstance(inputLocale)
            );
            dateStatistic = getDateStatistic(
                    word,
                    BreakIterator.getLineInstance(inputLocale), // :NOTE: ??
                    DateFormat.getDateInstance(DateFormat.SHORT, inputLocale)
            );

            try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[3], StandardCharsets.UTF_8))) {
                final String answer = writeStat(outputBundle, lineStatistic, numStatistic, moneyStatistic, wordStatistic, sentenceStatistic, dateStatistic);
                bufferedWriter.write(answer);
            } catch (final IOException e) {
                System.err.println("Can't open output file");
            }
        } catch (final IOException e) {
            System.err.println("Can't open input file");
            System.err.println(args[2]);
        }
    }

    private static Locale getIOLocale(final String ioLocaleString) {
        for (final Locale locale : Locale.getAvailableLocales()) {
            if (locale.getISO3Language().equals(ioLocaleString)) {
                return locale;
            }
        }
        return null;
    }
}
