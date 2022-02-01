'use strict';

/**
 * @param {Object} schedule Расписание Банды
 * @param {number} duration Время на ограбление в минутах
 * @param {Object} workingHours Время работы банка
 * @param {string} workingHours.from Время открытия, например, "10:00+5"
 * @param {string} workingHours.to Время закрытия, например, "18:00+5"
 * @returns {Object}
 */
function getAppropriateMoment(schedule, duration, workingHours) {
    // bank time
    // first interval -- 00:00-00:01
    const week = new Array(3 * 24 * 60).fill(true);
    const timeZone = parseTimeZone(workingHours.from.substring(5));

    // dd
    function parseWeek(date) {
        switch (date) {
            case 'ПН':
                return 0;
            case 'ВТ':
                return 1;
            case 'СР':
                return 2;
            case 'ЧТ':
                return 3;
            case 'ПТ':
                return 4;
            case 'СБ':
                return 5;
            case 'ВС':
                return 6;
            default:
                throw new Error('ХЗ');
        }
    }

    // hh:mm
    function parseTime(time) {
        let hours = parseInt(time.substring(0, 2), 10);
        let minutes = parseInt(time.substring(3, 5), 10);

        return (hours * 60) + minutes;
    }

    // +d
    function parseTimeZone(utc) {
        return parseInt(utc.substr(1));
    }

    function fillWeekIntervalByPerson(intervals) {
        for (let i = 0; i < intervals.length; i++) {
            // { from: 'ПН 12:00+5', to: 'ПН 17:00+5' }
            const interval = intervals[i];

            const getTime = s => {
                return parseWeek(s.substr(0, 2)) * 60 * 24 +
                    parseTime(s.substr(3, 8)) +
                    (timeZone - parseTimeZone(s.substr(8))) * 60;
            };

            let from = getTime(interval.from);
            let to = getTime(interval.to);

            // console.log('----    ' + from + ' ; ' + to)

            for (let j = Math.max(from, 0); j < to; j++) {
                week[j] = false;
            }
        }
    }

    function getInterval() {
        let bankHours = {
            from: parseTime(workingHours.from.substr(0, 5)),
            to: parseTime(workingHours.to.substr(0, 5)),
        }

        Object.values(schedule).forEach(fillWeekIntervalByPerson)

        const res = [];

        for (let i = 0; i < 3; i++) {
            let row = 0;
            const from = bankHours.from + i * 1440;
            const to = bankHours.to + i * 1440;

            for (let j = from; j < to; j++) {
                if (week[j]) {
                    row++;

                    if (row >= duration) {
                        res.push(j - duration + 1);
                    }
                } else {
                    row = 0;
                }
            }
        }

        return res;
    }

    const starts = getInterval();
    let cur = 0;

    return {
        /**
         * Найдено ли время
         * @returns {boolean}
         */
        exists() {
            return cur < starts.length;
        },

        /**
         * Возвращает отформатированную строку с часами
         * для ограбления во временной зоне банка
         *
         * @param {string} template
         * @returns {string}
         *
         * @example
         * ```js
         * getAppropriateMoment(...).format('Начинаем в %HH:%MM (%DD)') // => Начинаем в 14:59 (СР)
         * ```
         */
        format(template) {
            if (!this.exists()) {
                return "";
            }

            let time = starts[cur];

            let day = 'ПН'
            switch (Math.floor(time / 1440)) {
                case 1:
                    day = 'ВТ'
                    break;
                case 2:
                    day = 'СР'
                    break;
                case 3:
                    day = 'ЧТ'
                    break;
                case 4:
                    day = 'ПТ'
                    break;
                case 5:
                    day = 'СБ'
                    break;
                case 6:
                    day = 'ВС'
                    break;
            }
            let hour = Math.floor(time % 1440 / 60)
            let minute = Math.floor(time % 1440 % 60)

            // console.log(time)

            return template
                .replace(/%DD/gi, day)
                .replace(/%HH/gi, hour.toString().padStart(2, "0"))
                .replace(/%MM/gi, minute.toString().padStart(2, "0"))
        },

        /**
         * Попробовать найти часы для ограбления позже [*]
         * @note Не забудь при реализации выставить флаг `isExtraTaskSolved`
         * @returns {boolean}
         */
        tryLater() {
            const prev = cur;
            while (cur < starts.length && starts[cur] - starts[prev] < 30) {
                cur++;
            }
            if (this.exists()) {
                return true;
            } else {
                cur = prev;
                return false;
            }
        }
    };
}


module.exports = {
    getAppropriateMoment
};