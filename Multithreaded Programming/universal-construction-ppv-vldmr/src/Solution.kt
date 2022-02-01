/**
 * @author Arseny Lukonin
 */
class Solution : AtomicCounter {
    // объявите здесь нужные вам поля
    private val root = Node(0)
    private val last = ThreadLocal.withInitial { root }

    // вам наверняка потребуется дополнительный класс
    private class Node(val value: Int, val next: Consensus<Node> = Consensus())

    override fun getAndAdd(x: Int): Int {
        // напишите здесь код
        var flag = true
        var old = last.get().value
        while (flag) {
            old = last.get().value
            val current = Node(old + x)
            last.set(last.get().next.decide(current))
            if (last.get() == current){
                flag = false
            }
        }
        return old
    }
}