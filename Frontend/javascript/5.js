'use strict'

function makeStream(friends) {
    const names = []
    const friendsByNames = []
    const friendsByLevels = []
    const usedNames = []

    for (let i = 0; i < friends.length; i++) {
        names.push(friends[i].name)
        friendsByNames.push(i)
    }

    const stream = []
    for (let i = 0; i < friends.length; i++) {
        if (friends[i].best === true) {
            friends[i].level = 0
            usedNames.push(friends[i].name)
            stream.push(friends[i])
        }
    }

    while (stream.length > 0) {
        const first = stream.splice(0, 1)[0]

        if (!friendsByLevels[first.level]) {
            friendsByLevels[first.level] = [first]
        } else {
            friendsByLevels[first.level].push(first)
        }

        first.friends.forEach(friend => {
            const newLevelFriend = friends[friendsByNames[names.indexOf(friend)]]

            if (newLevelFriend) {
                if (usedNames.indexOf(newLevelFriend.name) === -1) {
                    usedNames.push(newLevelFriend.name)
                    newLevelFriend.level = first.level + 1
                    stream.push(newLevelFriend)
                }
            }
        })
    }

    const comparator = (f1, f2) => {
        if (f1.name < f2.name) {
            return -1
        } else {
            if (f1.name > f2.name) {
                return 1
            } else {
                return 0
            }
        }
    }

    for (let i = 0; i < friendsByLevels.length; i++) {
        friendsByLevels[i].sort(comparator)
    }

    const result = []
    for (let i = 0; i < friendsByLevels.length; i++) {
        for (let j = 0; j < friendsByLevels[i].length; j++) {
            result.push(friendsByLevels[i][j])
        }
    }

    return result
}

/**
 * Итератор по друзьям
 * @constructor
 * @param {Object[]} friends
 * @param {Filter} filter
 */
function Iterator(friends, filter) {
    if (filter instanceof Filter) {
        const stream = makeStream(friends).filter(f => filter.filter(f))
        let cur = 0

        this.next = function() {
            if (cur < stream.length) {
                let res = stream[cur]
                cur++
                return res
            } else {
                return null
            }
        }

        this.done = function() {
            return cur >= stream.length
        }
    } else {
        throw new TypeError('Only filter allowed as second argument')
    }
}

/**
 * Итератор по друзям с ограничением по кругу
 * @extends Iterator
 * @constructor
 * @param {Object[]} friends
 * @param {Filter} filter
 * @param {Number} maxLevel – максимальный круг друзей
 */
function LimitedIterator(friends, filter, maxLevel) {
    if (filter instanceof Filter) {
        const stream = makeStream(friends).filter(f => filter.filter(f) && f.level < maxLevel)
        let cur = 0

        this.next = function() {
            if (cur < stream.length) {
                let res = stream[cur]
                cur++
                return res
            } else {
                return null
            }
        }

        this.done = function() {
            return cur >= stream.length
        }
    } else {
        throw new TypeError('Only filter allowed as second argument')
    }
}

/**
 * Фильтр друзей
 * @constructor
 */
function Filter() {
    this.filter = _ => true
}

/**
 * Фильтр друзей
 * @extends Filter
 * @constructor
 */
function MaleFilter() {
    this.filter = (f) => {
        return f.gender === 'male'
    }
}

/**
 * Фильтр друзей-девушек
 * @extends Filter
 * @constructor
 */
function FemaleFilter() {
    this.filter = (f) => {
        return f.gender === 'female'
    }
}

LimitedIterator.prototype = Object.create(Iterator.prototype)
MaleFilter.prototype = Object.create(Filter.prototype)
FemaleFilter.prototype = Object.create(Filter.prototype)

LimitedIterator.prototype.constructor = LimitedIterator
MaleFilter.prototype.constructor = MaleFilter
FemaleFilter.prototype.constructor = FemaleFilter

exports.Iterator = Iterator;
exports.LimitedIterator = LimitedIterator;

exports.Filter = Filter;
exports.MaleFilter = MaleFilter;
exports.FemaleFilter = FemaleFilter;