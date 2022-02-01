'use strict';

const API_KEY = require('./key.json');
const fetch = require('node-fetch');

class TripBuilder {

  constructor(geoIds) {
    this.geoIds = geoIds
    this.tripPlan = []
    this.maxDaysAmount = 7
  }

  sunny(daysCount) {
    let cnt = 0
    while (cnt < daysCount) {
      this.tripPlan.push('s');
      cnt++
    }
    return this;
  }

  cloudy(daysCount) {
    let cnt = 0
    while (cnt < daysCount) {
      this.tripPlan.push('c');
      cnt++
    }
    return this;
  }

  max(daysCount) {
    this.maxDaysAmount = daysCount
    return this;
  }

  async build() {
    const daysInLocation = [...Array(this.geoIds.length).fill(0)]

    const trip = this.tryBuildTrip(
      (await updateForecasts(this.geoIds)),
      daysInLocation,
      0,
      null,
      []
    )

    if (trip) {
      return trip
    } else {
      throw new Error('Не могу построить маршрут!')
    }
  }

  tryBuildTrip(weatherInLocation, daysInLocation, dayNum, prevLocation, trip) {
    if (dayNum === this.tripPlan.length) {
      return trip
    } else {
      for (let i = 0; i < weatherInLocation.length; i++) {
        const geoId = weatherInLocation[i].geoId;

        if (geoId !== prevLocation) {
          continue
        }

        if (this.tripPlan[dayNum] === weatherInLocation[i].weatherData[dayNum] &&
            daysInLocation[i] + 1 <= this.maxDaysAmount) {

          const testTrip = trip.slice()
          testTrip.push({ geoid: geoId, day: dayNum + 1 })

          let copyDays = daysInLocation.slice()
          copyDays[i]++

          const maybeTrip = this.tryBuildTrip(weatherInLocation, copyDays, dayNum + 1, geoId, testTrip)

          if (maybeTrip) {
            return maybeTrip
          }
        }
      }

      for (let i = 0; i < weatherInLocation.length; i++) {
        const geoId = weatherInLocation[i].geoId;

        if (geoId === prevLocation) {
          continue
        }

        if (this.tripPlan[dayNum] === weatherInLocation[i].weatherData[dayNum] &&
            daysInLocation[i] === 0) {

          const testTrip = trip.slice()
          testTrip.push({ geoid: geoId, day: dayNum + 1 })

          let copyDays = daysInLocation.slice()
          copyDays[i]++

          const maybeTrip = this.tryBuildTrip(weatherInLocation, copyDays, dayNum + 1, geoId, testTrip)

          if (maybeTrip) {
            return maybeTrip
          }
        }
      }
    }
  }
}

async function updateForecasts(ids) {
  const res = []

  let cnt = 0
  while (cnt < ids.length) {
    res.push(await updateForecastByGeoid(ids[cnt]))
    cnt++
  }

  return res
}

async function updateForecastByGeoid(id) {
  const weatherData = (await
      (await fetch(
        `https://api.weather.yandex.ru/v2/forecast?geoid=${id}&limit=7&hours=false`,
        { headers: {
            "X-Yandex-API-Key": API_KEY.key}}))
        .json()
  ).forecasts.map(f => {
    if (f['parts']['day_short']['condition'] === 'cloudy') {
      return 'c'
    } else {
      if (f['parts']['day_short']['condition'] === 'overcast') {
        return 'c'
      } else {
        if (f['parts']['day_short']['condition'] === 'clear') {
          return 's'
        } else {
          if (f['parts']['day_short']['condition'] === 'partly-cloudy') {
            return 's'
          }
        }
      }
    }
  })
  return {
    geoId: id,
    weatherData: weatherData
  }
}

function planTrip(geoids) {
  return new TripBuilder(geoids);
}

module.exports = {
  planTrip
};
