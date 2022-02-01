import kotlin.math.absoluteValue

/**
 * В теле класса решения разрешено использовать только переменные делегированные в класс RegularInt.
 * Нельзя volatile, нельзя другие типы, нельзя блокировки, нельзя лазить в глобальные переменные.
 *
 * @author Popov Vladimir
 */
class Solution : MonotonicClock {
    private var v1 by RegularInt(0)
    private var v2 by RegularInt(0)
    private var v3 by RegularInt(0)

    private var w1 by RegularInt(0)
    private var w2 by RegularInt(0)

    override fun write(time: Time) {
        v1 = time.d1 // from left to right
        v2 = time.d2
        v3 = time.d3

        w2 = time.d2 // write from right to left
        w1 = time.d1
    }

    override fun read(): Time {
        val l1V = w1 // read from left to right
        val l2V = w2

        val v3V = v3 // read from right to left
        val v2V = v2
        val v1V = v1
        // he-he-he
        return Time(
            v1V,
            if (v1V == l1V)
                v2V
            else
                0,
            if (v1V == l1V && v2V == l2V)
                v3V
            else
                0)
    }
}