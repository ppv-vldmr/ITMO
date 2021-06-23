package info.kgeorgiy.ja.popov.i18n;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class StatisticsWriter {
    private static void writeStatSection(
            final StringBuilder answer,
            final ResourceBundle outputBundle,
            final StatisticsParameters statistic,
            final String[] keys
    ) {
        final MessageFormat numberMessageFormat = new MessageFormat(outputBundle.getString("Number") + " {0}: {1} ");
        // :NOTE: Конкатенация
        answer.append(outputBundle.getString(keys[0])).append(":\n");
        final Object[] numberMessageFormatArgs = {outputBundle.getString(keys[1]), statistic.count};
        answer.append("\t").append(numberMessageFormat.format(numberMessageFormatArgs));

        final MessageFormat uniqueMessageFormat = new MessageFormat("({0})\n");
        final double[] numUnique = {0, 1, 2};
        final String[] wordUnique = {"{0} " + outputBundle.getString("uniques"), "{0}  " + outputBundle.getString(keys[2]), "{0}  " + outputBundle.getString("uniques")};
        final ChoiceFormat formUnique = new ChoiceFormat(numUnique, wordUnique);
        uniqueMessageFormat.setFormatByArgumentIndex(0, formUnique);
        final Object[] argUnique = {statistic.uniqueCount};
        answer.append(uniqueMessageFormat.format(argUnique));

        final MessageFormat minMaxMessageFormat = new MessageFormat("\t{0} {1}: {2}\n");
        Object[] argMinMaxMessageFormat = {outputBundle.getString(keys[3]), outputBundle.getString(keys[4]), statistic.minValue};
        answer.append(minMaxMessageFormat.format(argMinMaxMessageFormat));
        argMinMaxMessageFormat = new Object[]{outputBundle.getString(keys[5]), outputBundle.getString(keys[6]), statistic.maxValue};
        answer.append(minMaxMessageFormat.format(argMinMaxMessageFormat));

        final MessageFormat minMaxLengthMessageFormat = new MessageFormat("\t{0} {1} {2}: {3} ({4})\n");
        Object[] argMinMaxLengthMessageFormat = {outputBundle.getString(keys[7]), outputBundle.getString("length"), outputBundle.getString(keys[8]), statistic.minLength, statistic.minLengthString};
        answer.append(minMaxLengthMessageFormat.format(argMinMaxLengthMessageFormat));
        argMinMaxLengthMessageFormat = new Object[]{outputBundle.getString(keys[9]), outputBundle.getString("length"), outputBundle.getString(keys[10]), statistic.maxLength, statistic.maxLengthString};
        answer.append(minMaxLengthMessageFormat.format(argMinMaxLengthMessageFormat));

        answer.append("\t").append(keys[11]);
        answer.append(": ").append(statistic.average).append("\n");

    }

    static String writeStat(
            final ResourceBundle outputBundle,
            final StatisticsParameters lineStatistic,
            final StatisticsParameters numStatistic,
            final StatisticsParameters moneyStatistic,
            final StatisticsParameters wordStatistic,
            final StatisticsParameters sentenceStatistic,
            final StatisticsParameters dateStatistic
    ) {
        final StringBuilder answer = new StringBuilder();
        answer.append(outputBundle.getString("allStat")).append("\n");
        final MessageFormat numberMessageFormat = new MessageFormat(outputBundle.getString("Number") + " {0}: {1, number}\n");
        Object[] argNumMessageFormat = {outputBundle.getString("numbersParent"), numStatistic.count};
        answer.append("\t").append(numberMessageFormat.format(argNumMessageFormat));
        argNumMessageFormat = new Object[]{outputBundle.getString("moneyParent"), moneyStatistic.count};
        answer.append("\t").append(numberMessageFormat.format(argNumMessageFormat));
        argNumMessageFormat = new Object[]{outputBundle.getString("datesParent"), dateStatistic.count};
        answer.append("\t").append(numberMessageFormat.format(argNumMessageFormat));
        argNumMessageFormat = new Object[]{outputBundle.getString("sentencesParent"), sentenceStatistic.count};
        answer.append("\t").append(numberMessageFormat.format(argNumMessageFormat));
        argNumMessageFormat = new Object[]{outputBundle.getString("wordsParent"), wordStatistic.count};
        answer.append("\t").append(numberMessageFormat.format(argNumMessageFormat));
        argNumMessageFormat = new Object[]{outputBundle.getString("linesParent"), lineStatistic.count};
        answer.append("\t").append(numberMessageFormat.format(argNumMessageFormat));

        final String[] keysNumber = {"numberStatistic", "numbersParent", "uniqueNoun", "minNoun", "number", "maxNoun", "number", "minFemale", "numberParent", "maxFemale", "numberParent", outputBundle.getString("averageNoun") + " " + outputBundle.getString("number")};
        writeStatSection(answer, outputBundle, numStatistic, keysNumber);
        final String[] keysMoney = {"moneyStatistic", "moneyParent", "uniques", "minMany", "moneyMany", "maxMany", "moneyMany", "minFemale", "moneyParent", "maxFemale", "moneyParent", outputBundle.getString("averageNoun") + " " + outputBundle.getString("number") + " " + outputBundle.getString("money")};
        writeStatSection(answer, outputBundle, moneyStatistic, keysMoney);
        final String[] keysDates = {"dateStatistic", "datesParent", "uniqueFemale", "minFemale", "date", "maxFemale", "date", "minFemale", "dateParent", "maxFemale", "dateParent", outputBundle.getString("averageFemale") + " " + outputBundle.getString("length") + " " + outputBundle.getString("dateParent")};
        writeStatSection(answer, outputBundle, dateStatistic, keysDates);
        final String[] keysSentence = {"sentenceStatistic", "sentencesParent", "uniqueNoun", "minNoun", "sentence", "maxNoun", "sentence", "minFemale", "sentenceParent", "maxFemale", "sentenceParent", outputBundle.getString("averageFemale") + " " + outputBundle.getString("length") + " " + outputBundle.getString("sentenceParent")};
        writeStatSection(answer, outputBundle, sentenceStatistic, keysSentence);
        final String[] keysWords = {"wordStatistic", "wordsParent", "uniqueNoun", "minNoun", "word", "maxNoun", "word", "minFemale", "wordParent", "maxFemale", "wordParent", outputBundle.getString("averageFemale") + " " + outputBundle.getString("length") + " " + outputBundle.getString("wordParent")};
        writeStatSection(answer, outputBundle, wordStatistic, keysWords);
        final String[] keysLines = {"lineStatistic", "linesParent", "uniqueFemale", "minFemale", "line", "maxFemale", "line", "minFemale", "lineParent", "maxFemale", "lineParent", outputBundle.getString("averageFemale") + " " + outputBundle.getString("length") + " " + outputBundle.getString("lineParent")};
        writeStatSection(answer, outputBundle, lineStatistic, keysLines);
        return answer.toString();
    }
}
