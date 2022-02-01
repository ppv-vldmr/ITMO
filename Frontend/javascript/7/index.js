setTimeout(run, 0)

function debounce(f, timeoutAmount, runNow) {
    let timeout = null

    return function () {
        const callNow = runNow && !timeout
        const argsToUse = arguments

        setTimeout(
            function () {
                if (!runNow) {
                    f.apply(this, argsToUse)
                }
            },
            timeoutAmount
        )

        if (callNow) {
            f.apply(this, argsToUse)
        }
    }
}

function run() {
    const CODES = ['ArrowUp', 'ArrowDown', 'Enter']

    let currentSelectedItem = null

    function selectNewItem(updatedSelectedItem) {
        if (currentSelectedItem !== updatedSelectedItem) {

            if (currentSelectedItem) {
                currentSelectedItem.classList.remove('selected')
            }

            currentSelectedItem = updatedSelectedItem
            currentSelectedItem.classList.add('selected')
        }
    }

    const suggestValues = async (e) => {
        document.getElementById('suggested-items').innerHTML = ''

        const searchRequest = e.target.value

        if (searchRequest.length) {

            //получаем нужные аэропорты
            let suggestedValues = (await (await fetch('https://raw.githubusercontent.com/algolia/datasets/master/airports/airports.json')).json())
                .filter(value =>
                    searchRequest.split(' ')
                        .some(w => value.iata_code.toLowerCase().startsWith(w.toLowerCase()) || value.city.toLowerCase().startsWith(w.toLowerCase())))

            //выводим их
            suggestedValues
                .map(value => {
                    const currentValue = document.createElement('li')
                    currentValue.addEventListener(
                        'mouseenter',
                        (e) => { selectNewItem(e.target) }
                    )
                    currentValue.addEventListener(
                        'click',
                        (e) => {
                            document.getElementById('suggested-items').innerHTML = ''
                            document.getElementById('search').value = e.target.innerHTML
                        }
                    )
                    currentValue.innerHTML = `${value.city} (${value.iata_code})`
                    return currentValue
                })
                .slice(0, 20)
                .forEach(value => document.getElementById('suggested-items').appendChild(value))
        }
    }

    let upMapper
    let downMapper
    function onKeydownHandler(e) {
        console.log('Entered code:' + e.code)

        switch (e.code) {
            case CODES[0]: {
                upMapper = (s => s.lastElementChild)
                downMapper = (x => x.previousElementSibling)
                break;
            }
            case CODES[1]: {
                upMapper = (s => s.firstElementChild)
                downMapper = (x => x.nextElementSibling)
                break;
            }
            case CODES[2]: {
                if (currentSelectedItem) {
                    document.getElementById('suggested-items').innerHTML = ''
                    document.getElementById('search').value = currentSelectedItem.innerHTML
                }
                return
            }
            default:
                return;
        }

        let mapperToSelect = upMapper(document.getElementById('suggested-items'))

        if (currentSelectedItem !== null) {
            selectNewItem(downMapper(currentSelectedItem) || mapperToSelect)
        } else if (mapperToSelect) {
            selectNewItem(mapperToSelect)
        }
    }

    document.getElementById('search').addEventListener(
        'keydown',
        onKeydownHandler)
    document.getElementById('search').addEventListener(
        'input',
        debounce(suggestValues, 200))
}