package info.kgeorgiy.ja.popov.i18n;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Tests {

    @Test
    public void testNumbers() {
        String text = "It is fifty 50 and 30 10 203 1005500 1 1 and 1 all of them.";
        Locale locale = Locale.GERMANY;
        StatisticsParameters statistic = StatisticsCategories.getNumStatistic(locale, text, BreakIterator.getWordInstance(locale), NumberFormat.getNumberInstance(locale), false);
        Assert.assertEquals(8, statistic.count);
        Assert.assertEquals(6, statistic.uniqueCount);
        Assert.assertEquals(1, statistic.minLength);
        Assert.assertEquals(7, statistic.maxLength);
        Assert.assertEquals("1", statistic.minValue);
        Assert.assertEquals("1005500", statistic.maxValue);
        Assert.assertEquals(125724.5, statistic.average, 0.0);
    }

    @Test
    public void testMoney() {
        String text = "It is fifty $50 and 30 $50 203 $1005500 1 1 and 1 all of them.";
        Locale locale = Locale.US;
        StatisticsParameters statistic = StatisticsCategories.getNumStatistic(locale, text, BreakIterator.getWordInstance(locale), NumberFormat.getCurrencyInstance(locale), true);
        Assert.assertEquals(3, statistic.count);
        Assert.assertEquals(2, statistic.uniqueCount);
        Assert.assertEquals(3, statistic.minLength);
        Assert.assertEquals(8, statistic.maxLength);
        Assert.assertEquals("$50", statistic.minValue);
        Assert.assertEquals("$1005500", statistic.maxValue);
        Assert.assertEquals(335200, statistic.average, 0.0);
    }


    @Test
    public void testDate() {
        String text = "Now 11/12/20 and it is fifty $50 and 30 $50 203 $1005500 1 1 and 1 all of them at 10/05/2000";
        Locale locale = Locale.FRANCE;
        StatisticsParameters statistic = StatisticsCategories.getDateStatistic(text, BreakIterator.getLineInstance(locale), DateFormat.getDateInstance(DateFormat.SHORT, locale));
        Assert.assertEquals(2, statistic.count);
        Assert.assertEquals(2, statistic.uniqueCount);
        Assert.assertEquals(8, statistic.minLength);
        Assert.assertEquals(10, statistic.maxLength);
        Assert.assertEquals("10/05/2000", statistic.minValue);
        Assert.assertEquals("11/12/20", statistic.maxValue);
        Assert.assertEquals(9, statistic.average, 0.0);
    }

    @Test
    public void testWords() {
        String text = "Now 11/12/20 and it is fifty $50 and 30 $50 203 $1005500 1 1 and 1 all of them at 10/05/2000";
        Locale locale = Locale.US;
        StatisticsParameters statistic = StatisticsCategories.getWordStatistic(locale, text, BreakIterator.getWordInstance(locale));
        Assert.assertEquals(11, statistic.count);
        Assert.assertEquals(9, statistic.uniqueCount);
        Assert.assertEquals(2, statistic.minLength);
        Assert.assertEquals(5, statistic.maxLength);
        Assert.assertEquals("all", statistic.minValue);
        Assert.assertEquals("them", statistic.maxValue);
        Assert.assertEquals((double) 32/11, statistic.average, 0.0);
    }

    @Test
    public void testSentence() {
        String text = "Hi. It is June. Or May. Or not. Or 26/05/20.\n Buy garage. Bye.";
        Locale locale = Locale.ENGLISH;
        StatisticsParameters statistic = StatisticsCategories.getWordStatistic(locale, text, BreakIterator.getSentenceInstance(locale));
        Assert.assertEquals(7, statistic.count);
        Assert.assertEquals(7, statistic.uniqueCount);
        Assert.assertEquals(3, statistic.minLength);
        Assert.assertEquals(13, statistic.maxLength);
        Assert.assertEquals("Buy garage.", statistic.minValue);
        Assert.assertEquals("Or not.", statistic.maxValue);
        Assert.assertEquals(8, statistic.average, 0.0);
    }

    @Test
    public void testJapan() {
        String text = "ねえ。 これはあなたであり、これは私です。 まで。 今日は05/26/20";
        Locale locale = Locale.JAPAN;
        StatisticsParameters statistic = StatisticsCategories.getWordStatistic(locale, text, BreakIterator.getSentenceInstance(locale));
        Assert.assertEquals(4, statistic.count);
        Assert.assertEquals(4, statistic.uniqueCount);
        Assert.assertEquals(3, statistic.minLength);
        Assert.assertEquals(17, statistic.maxLength);
        Assert.assertEquals("これはあなたであり、これは私です。", statistic.minValue);
        Assert.assertEquals("今日は05/26/20", statistic.maxValue);
        Assert.assertEquals(8.5, statistic.average, 0.0);
        statistic = StatisticsCategories.getDateStatistic(text, BreakIterator.getLineInstance(locale), DateFormat.getDateInstance(DateFormat.SHORT, locale));
        Assert.assertEquals(1, statistic.count);
        Assert.assertEquals(1, statistic.uniqueCount);
        Assert.assertEquals(8, statistic.minLength);
        Assert.assertEquals(8, statistic.maxLength);
        Assert.assertEquals("05/26/20", statistic.minValue);
        Assert.assertEquals("05/26/20", statistic.maxValue);
        Assert.assertEquals(8, statistic.average, 0.0);
        statistic = StatisticsCategories.getNumStatistic(locale, text, BreakIterator.getWordInstance(locale), NumberFormat.getCurrencyInstance(locale), true);
        Assert.assertEquals(0, statistic.count);
        Assert.assertEquals(0, statistic.uniqueCount);
        Assert.assertEquals(0, statistic.minLength);
        Assert.assertEquals(0, statistic.maxLength);
        Assert.assertNull(statistic.minValue);
        Assert.assertNull(statistic.maxValue);
        Assert.assertEquals(0, statistic.average, 0.0);
    }

    public static void main(String[] args) {
        System.exit(new JUnitCore().run(Tests.class).wasSuccessful() ? 0 : 1);
    }

}
