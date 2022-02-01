'use strict';

const chai = require('chai');
const chaiAsPromised = require('chai-as-promised');
const nock = require('nock');

chai.use(chaiAsPromised);

const expect = chai.expect;

const weather = require('../src/weather');

/**
 * Эмуляция запросов к API
 * @param {number[]} geoids Список идентификаторов городов
 * @param {number=1} times Количество повторений запросов
 */
const nockAPIRequests = (geoids, { presetName = 'default' } = {}) => {
  const apiHost = 'https://api.weather.yandex.ru';
  nock(apiHost)
    .persist()
    .get('/v2/forecast')
    .query(q => geoids.includes(parseInt(q.geoid)))
    .reply(200, uri => {
      const apiUrl = new URL(uri, apiHost);
      return require(`./__fixtures__/${presetName}/${apiUrl.searchParams.get('geoid')}.json`);
    });
}

describe('Планировщик маршрута', () => {
  const geoids = [2, 5, 7, 10, 11, 14, 213];

  describe('Простой маршрут', () => {
    beforeEach(() => {
      nockAPIRequests(geoids);
    });

    it('Должен правильно построить маршрут', async () => {
      expect(
        await weather
          .planTrip(geoids)
          .cloudy(1)
          .sunny(1)
          .build()
      ).to.deep.equal([
        { geoid: 11, day: 1 },
        { geoid: 7, day: 2 }
      ]);
    });

    it('Должен выкинуть исключение если нет маршрута', async () => {
      return expect(
        weather
          .planTrip(geoids)
          .sunny(3)
          .build()
      ).to.be.rejectedWith(Error, 'Не могу построить маршрут!');
    });
  });

  afterEach(() => nock.cleanAll());
});
