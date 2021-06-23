package info.kgeorgiy.ja.popov.i18n;

import java.util.ListResourceBundle;

// :NOTE: ListResourceBundle
public class BundleRU extends ListResourceBundle {

    protected Object[][] getContents() {
        return WORDS;
    }

    private static final Object[][] WORDS = {
            {"allStat", "Сводная статистика"},
            {"sentenceStatistic", "Статистика по предложениям"},
            {"lineStatistic", "Статистика по строкам"},
            {"wordStatistic", "Статистика по словам"},
            {"numberStatistic", "Статистика по числам"},
            {"moneyStatistic", "Статистика по деньгам"},
            {"dateStatistic", "Статистика по датам"},
            {"sentence", "предложение"},
            {"sentenceParent", "предложения"},
            {"sentencesParent", "предложений"},
            {"line", "строка"},
            {"lineParent", "строки"},
            {"lines", "строки"},
            {"linesParent", "строк"},
            {"length", "длина"},
            {"uniqueMale", "уникальный"},
            {"uniqueFemale", "уникальная"},
            {"uniqueNoun", "уникальное"},
            {"uniques", "уникальных"},
            {"Number", "Число"},
            {"number", "число"},
            {"numberParent", "числа"},
            {"numbersParent", "чисел"},
            {"word","слово"},
            {"wordParent", "слова"},
            {"wordsParent", "слов"},
            {"moneyMany", "деньги"},
            {"money", "денег"},
            {"moneyParent", "денег"},
            {"date", "дата"},
            {"dateParent", "даты"},
            {"datesParent", "дат"},
            {"minNoun", "Минимальное"},
            {"minFemale", "Минимальная"},
            {"minMany", "Минимальные"},
            {"maxNoun", "Максимальное"},
            {"maxFemale", "Mаксимальная"},
            {"maxMany", "Mаксимальные"},
            {"averageFemale", "Средняя"},
            {"averageNoun", "Среднee"}
    };
}
