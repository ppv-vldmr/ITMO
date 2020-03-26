package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

// INV: n ≥ 0 ∧ ∀i=1..n: a[i] ≠ null
//       n - size    a[1]..a[n] - queue
public interface Queue {

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    void enqueue(Object element);

    // PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    Object element();

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
    Object dequeue();

    // POST: ℝ = n ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    int size();

    // POST: ℝ = n > 0 ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    boolean isEmpty();

    // POST: n' = 0
    void clear();

    // PRE:  n > 0
    // POST: R = a[1],...,a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    Object[] toArray();

    // PRE:  n > 0
    // POST: R = "[a[1], ..., a[n]] -> string ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    String toStr();
}
