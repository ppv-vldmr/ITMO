'use strict';

const fetch = require('node-fetch');

const API_KEY = require('./key.json');

/**
 * @typedef {object} TripItem Город, который является частью маршрута.
 * @property {number} geoid Идентификатор города
 * @property {number} day Порядковое число дня маршрута
 */

class TripBuilder {

  constructor(geoIds) {
    this.geoIds = geoIds
    this.plan = []
    this.maxDays = 7
  }

  /**
   * Метод, добавляющий условие наличия в маршруте
   * указанного количества солнечных дней
   * Согласно API Яндекс.Погоды, к солнечным дням
   * можно приравнять следующие значения `condition`:
   * * `clear`;
   * * `partly-cloudy`.
   * @param {number} daysCount количество дней
   * @returns {object} Объект планировщика маршрута
   */
  sunny(daysCount) {
    pushN(this.plan, daysCount, 's')
    return this;
  }

  /**
   * Метод, добавляющий условие наличия в маршруте
   * указанного количества пасмурных дней
   * Согласно API Яндекс.Погоды, к солнечным дням
   * можно приравнять следующие значения `condition`:
   * * `cloudy`;
   * * `overcast`.
   * @param {number} daysCount количество дней
   * @returns {object} Объект планировщика маршрута
   */
  cloudy(daysCount) {
    pushN(this.plan, daysCount, 'c')
    return this;
  }

  /**
   * Метод, добавляющий условие максимального количества дней.
   * @param {number} daysCount количество дней
   * @returns {object} Объект планировщика маршрута
   */
  max(daysCount) {
    this.maxDays = daysCount
    return this;
  }

  /**
   * Метод, возвращающий Promise с планируемым маршрутом.
   * @returns {Promise<TripItem[]>} Список городов маршрута
   */
  async build() {
    let forecasts = await fetchWeatherForecasts(this.geoIds)
    let loc2DaysSpent = new Map()
    forecasts.forEach(f => {
      loc2DaysSpent.set(f.geoId, 0)
    })

    const trip = this.tryBuildTrip(forecasts, loc2DaysSpent, 0, null, [])

    if (trip) {
      return trip
    }

    throw new Error('Не могу построить маршрут!')
  }

  tryBuildTrip(forecasts, loc2DaysSpent, dayNum, prevLocation, trip) {
    if (dayNum === this.plan.length) {
      return trip
    }

    for (let i = 0; i < forecasts.length; i++) {
      const geoId = forecasts[i].geoId;

      if (geoId !== prevLocation) {
        continue
      }

      const fcasted = forecasts[i].weatherData[dayNum]
      const daySpent = loc2DaysSpent.get(geoId)

      if (fcasted === this.plan[dayNum] && daySpent + 1 <= this.maxDays) {
        const tripCopy = [...trip]
        tripCopy.push({ geoid: geoId, day: dayNum + 1 })

        const loc2DaysSpentCopy = new Map()
        loc2DaysSpent.forEach((v, k) => {
          loc2DaysSpentCopy.set(k, v)
        })
        loc2DaysSpentCopy.set(geoId, loc2DaysSpentCopy.get(geoId) + 1)

        const maybeTrip = this.tryBuildTrip(forecasts, loc2DaysSpentCopy, dayNum + 1, geoId, tripCopy)

        if (maybeTrip) {
          return maybeTrip
        }
      }

    }

    for (let i = 0; i < forecasts.length; i++) {
      const geoId = forecasts[i].geoId

      if (geoId === prevLocation) {
        continue
      }

      const fcasted = forecasts[i].weatherData[dayNum]
      const daysSpent = loc2DaysSpent.get(geoId)

      if (fcasted === this.plan[dayNum]) {
        if (daysSpent === 0) {
          const tripCopy = [...trip]
          tripCopy.push({ geoid: geoId, day: dayNum + 1 })

          const loc2DaysSpentCopy = new Map()
          loc2DaysSpent.forEach((v, k) => {
            loc2DaysSpentCopy.set(k, v)
          })
          loc2DaysSpentCopy.set(geoId, loc2DaysSpent.get(geoId) + 1)

          const maybeTrip = this.tryBuildTrip(forecasts, loc2DaysSpentCopy, dayNum + 1, geoId, tripCopy)

          if (maybeTrip) {
            return maybeTrip
          }
        }
      }
    }
  }
}

async function fetchWeatherForecasts(ids) {
  const res = []

  for (let i = 0; i < ids.length; i++) {
    res.push(await fetchWeatherForecast(ids[i]))
  }

  return res
}

async function fetchWeatherForecast(id) {
  const r = await fetch(`https://api.weather.yandex.ru/v2/forecast?hours=false&geoid=${id}&limit=7`, {
    headers: {
      "X-Yandex-API-Key": API_KEY.key
    }
  })
  const rJson = await r.json()
  const weatherData = rJson.forecasts.map(d => {
    return mapWeather(d['parts']['day_short']['condition'])
  })
  return {
    geoId: id,
    weatherData: weatherData
  }
}

/**
 * Фабрика для получения планировщика маршрута.
 * Принимает на вход список идентификаторов городов, а
 * возвращает планировщик маршрута по данным городам.
 *
 * @param {number[]} geoids Список идентификаторов городов
 * @returns {TripBuilder} Объект планировщика маршрута
 * @see https://yandex.ru/dev/xml/doc/dg/reference/regions-docpage/
 */
function planTrip(geoids) {
  return new TripBuilder(geoids);
}

function pushN(arr, n, val) {
  for (let i = 0; i < n; i++) {
    arr.push(val);
  }
}

function mapWeather(f) {
  if (f === 'clear' || f === 'partly-cloudy') {
    return 's'
  }

  if (f === 'cloudy' || f === 'overcast') {
    return 'c'
  }
}

module.exports = {
  planTrip
};
