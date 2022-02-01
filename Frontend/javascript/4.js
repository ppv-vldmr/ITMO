/**
 * Возвращает новый emitter
 * @returns {Object}
 */
function getEmitter() {
    let state = []
    let data = []

    return {

        /**
         * Подписаться на событие
         * @param {String} event
         * @param {Object} context
         * @param {Function} handler
         */
        on: function (event, context, handler, several = null, through = null) {
            if (!state.includes(event)) {
                state.push(event)
                data.push([])
            }

            let eventIndex = state.indexOf(event)
            data[eventIndex].push([context, handler, 0, several, through, 0])

            // console.log(context, "\n", handler.toString(), "\n===========\n")

            return this
        },

        /**
         * Отписаться от события
         * @param {String} event
         * @param {Object} context
         */
        off: function (event, context) {
            let newState = [];
            let newData = []

            for (let i = 0; i < state.length; i++) {
                const handlers = data[i];

                if (state[i] === event || state[i].startsWith(event + '.')) {
                    newState.push(state[i])
                    newData.push(handlers.filter(cNx => cNx[0] !== context))
                } else {
                    newState.push(state[i])
                    newData.push(handlers)
                }
            }

            state = newState
            data = newData

            return this
        },

        /**
         * Уведомить о событии
         * @param {String} event
         */
        emit: function (event) {
            let suffixes = []

            if (typeof event === "string") {
                let sufs = []
                let sufsActions = []
                let afterSplit = event.split('.')

                for (let i = 0; i < afterSplit.length; i++) {
                    const tempRes = afterSplit.slice(0, i + 1).join('.');

                    if (state.includes(tempRes)) {
                        sufs.push(tempRes)
                        let index = -1
                        for (let j = 0; j < state.length; j++) {
                            if (state[j].startsWith(tempRes)) {
                                index = j
                                break;
                            }
                        }
                        sufsActions.push(data[index])
                    }
                }

                suffixes = sufs.reverse()
                sufsActions = sufsActions.reverse()

                // console.log(suffixes)

                for (let i = 0; i < suffixes.length; i++) {
                    const handlers = sufsActions[i]

                    for (let j = 0; j < handlers.length; j++) {
                        let cur = handlers[j]

                        let context = cur[0]
                        let handler = cur[1]

                        cur[2]++


                        if ((cur[3] === null && cur[4] === null) ||
                            (cur[3] !== null && cur[2] <= cur[3]) ||
                            (cur[4] !== null && cur[5] % cur[4] === 0)) {
                            handler.apply(context)

                            // console.log(context, handler.toString())
                        }

                        cur[5] = (cur[5] + 1) % cur[4]

                        handlers[j] = cur
                    }

                    sufsActions[i] = handlers
                }

                for (let i = 0; i < suffixes.length; i++) {
                    let index = state.indexOf(suffixes[i])
                    // for (let j = 0; j < state.length; i++) {
                    //     if (state[j].startsWith(suffixes[i])) {
                    //         index = j
                    //         break;
                    //     }
                    // }
                    data[index] = sufsActions[i]
                }

                return this
            } else {
                throw new TypeError('Only string allowed.')
            }
        },

        /**
         * Подписаться на событие с ограничением по количеству полученных уведомлений
         * @star
         * @param {String} event
         * @param {Object} context
         * @param {Function} handler
         * @param {Number} times – сколько раз получить уведомление
         */
        several: function (event, context, handler, times) {
            if (times > 0) {
                return this.on(event, context, handler, times)
            } else {
                return this.on(event, context, handler)
            }
        },

        /**
         * Подписаться на событие с ограничением по частоте получения уведомлений
         * @star
         * @param {String} event
         * @param {Object} context
         * @param {Function} handler
         * @param {Number} frequency – как часто уведомлять
         */
        through: function (event, context, handler, frequency) {
            if (frequency > 1) {
                return this.on(event, context, handler, null, frequency)
            } else {
                return this.on(event, context, handler)
            }
        }
    };
}

module.exports = {
    getEmitter
};

