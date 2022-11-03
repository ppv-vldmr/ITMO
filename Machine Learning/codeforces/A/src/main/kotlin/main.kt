fun main() {
    val (_, _, k) = readLine()!!.split(" ").map { it.toInt() }  //парсим кол-во частей
    val map = mutableMapOf<Int, MutableList<Int>>()  //мапа под классы, храним "номер класса : объекты класса"
    val res = mutableListOf<MutableList<Int>>()  //мапа под результат, храним "номер части : объекты части"
    for (i in 0 until k) {
        res.add(i, mutableListOf())
    }
    readLine()!!.split(" ").map(Integer::parseInt).forEachIndexed{ index, it ->
        map.computeIfPresent(it - 1) { _,value -> //если такой класс уже встречался, добавляем индекс
            value.add(index + 1)
            return@computeIfPresent value
        }
        map.putIfAbsent(it - 1, mutableListOf(index + 1)) //если класс новый, запоминаем его с индексом первого элемента
    }
    var itter = 0
    map.forEach {(_, values) ->
        values.forEach {
            res[itter % k].add(it) //равномерно распределяем все объекты между частями
            itter++ //за счет модуля добавляем в каждую часть циклично, из-за чего не будет разницы >1 объекта по кол-ву
        }
    }
    res.forEach {  //выводим
        print("${it.size} ")
        it.forEach {
            print("$it ")
        }
        println("")
    }
}