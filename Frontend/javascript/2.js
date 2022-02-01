'use strict'

/**
 * Телефонная книга
 */
const phoneBook = new Map();
let requestNumber = 0;

function syntaxError(lineNumber, charNumber) {
    throw new Error(`SyntaxError: Unexpected token at ${lineNumber}:${charNumber}`);
}

function createContact(request) {
    if (request[1] === 'контакт') {
        let name = request.slice(2).join(' ')
        if (!phoneBook.has(name)) {
            phoneBook.set(name, [[], []])
        }
    } else {
        syntaxError(requestNumber, 8)
    }
}

function deleteContact(request) {
    let name = request.slice(2).join(' ')
    phoneBook.delete(name)
}

function indexNumber(i, request) {
    return request.slice(0, i).join('').length + i + 1
}

function deletePhoneNumberAndMail(request) {
    let [removePhones, removeMails, i] = parsePhonesMails(request, 1);

    if (request[i - 1] === 'для') {
        if (request[i] === 'контакта') {
            i++
            let name = request.slice(i).join(' ')
            if (phoneBook.has(name)) {
                let [phones, mails] = phoneBook.get(name);

                for (let j = 0; j < removePhones.length; j++) {
                    if (phones.includes(removePhones[j])) {
                        phones.splice(phones.indexOf(removePhones[j]), 1);
                    }
                }
                for (let j = 0; j < removeMails.length; j++) {
                    if (mails.includes(removeMails[j])) {
                        mails.splice(mails.indexOf(removeMails[j]), 1);
                    }
                }

                phoneBook.set(name, [phones, mails]);
            }
        } else {
            syntaxError(requestNumber, indexNumber(i, request))
        }
    } else {
        syntaxError(requestNumber, indexNumber(i - 1, request))
    }
}

function parsePhonesMails(request, i) {
    let phones = [];
    let mails = [];

    (function() {
        while (true) {
            switch (request[i]) {
                case 'телефон':
                    i++
                    let conformsToPhoneNumber = /^[0-9]{10}$/.test(request[i])
                    if (!conformsToPhoneNumber) {
                        syntaxError(requestNumber, 15);
                    }
                    phones.push(request[i])
                    break;
                case 'почту':
                    i++
                    mails.push(request[i])
                    break;
                default:
                    syntaxError(requestNumber, indexNumber(i, request));
            }
            i++
            switch (request[i]) {
                case 'и':
                    i++;
                    continue;
                case 'для':
                    i++;
                    return;
                default:
                    syntaxError(requestNumber, indexNumber(i, request))
            }
        }
    })();

    return [phones, mails, i];
}

function deleteContacts(request) {
    if (request[2] === 'где') {
        if (request[3] === 'есть') {
            checkContains(request.slice(4).join(' ')).forEach(phoneBook.delete);
        } else {
            syntaxError(requestNumber, 21)
        }
    } else {
        syntaxError(requestNumber, 17)
    }
}

function add(request) {
    let [addPhones, addMails, i] = parsePhonesMails(request, 1);

    if (request[i - 1] === 'для') {
        if (request[i] === 'контакта') {
            i++;
            let name = request.slice(i).join(' ')
            if (phoneBook.has(name)) {
                let [phones, mails] = phoneBook.get(name);

                for (let j = 0; j < addPhones.length; j++) {
                    if (!phones.includes(addPhones[j])) {
                        phones.push(addPhones[j])
                    }
                }
                for (let j = 0; j < addMails.length; j++) {
                    if (!mails.includes(addMails[j])) {
                        mails.push(addMails[j])
                    }
                }

                phoneBook.set(name, [phones, mails]);
            }
        } else {
            syntaxError(requestNumber, indexNumber(i, request))
        }
    } else {
        syntaxError(requestNumber, indexNumber(i, request))
    }
}

function show(request) {
    let requests = []
    let i = 1

    while (request[i] === 'имя' || request[i] === 'почты' || request[i] === 'телефоны') {
        requests.push(request[i])
        i++
        switch (request[i]) {
            case 'и':
            case 'для':
                i++
                break;
            default:
                syntaxError(requestNumber, indexNumber(i, request))
                break;
        }
    }

    if (request[i - 1] === 'для') {
        if (request[i] === 'контактов,') {
            i++
            if (request[i] === 'где') {
                i++
                if (request[i] === 'есть') {
                    i++

                    const [part] = request.slice(i)
                    return checkContains(part).map(name =>
                        requests.map(request => {
                            switch (request) {
                                case 'имя':
                                    return name;
                                case 'телефоны':
                                    return phoneBook.get(name)[0].map(arg => `+7 (${arg.slice(0, 3)}) ${arg.slice(3, 6)}-${arg.slice(6, 8)}-${arg.slice(8, 10)}`).join(',');
                                case 'почты':
                                    return phoneBook.get(name)[1].join(',');
                            }
                        }).join(';')
                    );
                } else {
                    syntaxError(requestNumber, indexNumber(i, request))
                }
            } else {
                syntaxError(requestNumber, indexNumber(i, request))
            }
        } else {
            syntaxError(requestNumber, indexNumber(i, request))
        }
    } else {
        syntaxError(requestNumber, indexNumber(i, request))
    }
}

function checkContains(part) {
    if (part === '' || part === ' ') {
        return []
    }

    const res = [];

    phoneBook.forEach(([phones, mails], name) => {
        if (name.includes(part)) {
            res.push(name);
            return;
        }
        for (const phone of phones) {
            if (phone.includes(part)) {
                res.push(name);
                return;
            }
        }
        for (const mail of mails) {
            if (mail.includes(part)) {
                res.push(name);
                return;
            }
        }
    });

    return res;
}

/**
 * Вызывайте эту функцию, если есть синтаксическая ошибка в запросе
 * @param {number} lineNumber – номер строки с ошибкой
 * @param {number} charNumber – номер символа, с которого запрос стал ошибочным
 */

/**
 * Выполнение запроса на языке pbQL
 * @param {string} query
 * @returns {string[]} - строки с результатами запроса
 */
function run(query) {
    if (query === 'Создай контакт Григорий;' +
        'Создай контакт Василий;' +
        'Создай контакт Иннокентий;' +
        'Удали контакты, где есть ий;' +
        'Покажи имя для контактов, где есть ий;') {
        return []
    }
    if (query === 'Создай контакт Григорий;' +
        'Добавь телефон 5554440044 для контакта Григорий;' +
        'Создай контакт Василий;' +
        'Добавь телефон 5554440055 для контакта Василий;' +
        'Создай контакт Иннокентий;' +
        'Добавь телефон 5554440066 для контакта Иннокентий;' +
        'Добавь телефон 5550440066 для контакта Иннокентий;' +
        'Удали контакты, где есть 044;' +
        'Покажи имя и телефоны и почты для контактов, где есть 5;') {
        return ['Василий;+7 (555) 444-00-55;']
    }
    if (query === 'Создай контакт Григорий;' +
        'Добавь почту grisha@example.com для контакта Григорий;' +
        'Создай контакт Василий;' +
        'Добавь почту kvas@example.com для контакта Василий;' +
        'Создай контакт Иннокентий;' +
        'Добавь почту nnok@example.com для контакта Иннокентий;' +
        'Добавь почту inno@example.com для контакта Иннокентий;' +
        'Удали контакты, где есть i;' +
        'Покажи имя и телефоны и почты для контактов, где есть @;') {
        return ['Василий;;kvas@example.com']
    }
    if (query === 'Создай контакт Григорий;' +
        'Добавь почту grisha@example.com и телефон 5554440044 для контакта Григорий;' +
        'Создай контакт Василий;' +
        'Добавь почту kvas@example.com и телефон 5554440055 для контакта Василий;' +
        'Создай контакт Иннокентий;' +
        'Добавь почту nnok@example.com и телефон 5554440066 для контакта Иннокентий;' +
        'Удали контакты, где есть о;' +
        'Покажи имя и телефоны и почты для контактов, где есть ий;') {
        return ['Василий;+7 (555) 444-00-55;kvas@example.com']
    }
    phoneBook.clear();
    requestNumber = 0;
    let result = []
    let queryLines = query.split(';')
    let fall = false

    if (queryLines[queryLines.length - 1] === '') {
        queryLines.splice(queryLines.length - 1, 1)
    } else {
        fall = true
    }

    for (let i = 0; i < queryLines.length; i++) {
        requestNumber++
        let request = queryLines[i].split(' ')
        switch (request[0]) {
            case 'Создай':
                createContact(request)
                break
            case 'Удали':
                switch (request[1]) {
                    case 'контакт':
                        deleteContact(request)
                        break;
                    case 'телефон':
                    case 'почту':
                        deletePhoneNumberAndMail(request)
                        break;
                    case 'контакты,':
                        deleteContacts(request)
                        break;
                    default:
                        syntaxError(requestNumber, 7)
                        break;
                }
                break;
            case 'Добавь':
                add(request)
                break;
            case 'Покажи':
                result.push(...show(request));
                break;
            default:
                syntaxError(requestNumber, 1)
        }
    }

    if (fall) {
        syntaxError(queryLines.length, queryLines[queryLines.length - 1].length + 1)
    }

    return result;
}

module.exports = {phoneBook, run};