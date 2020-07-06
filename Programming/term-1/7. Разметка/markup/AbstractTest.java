package markup;

import base.Asserts;
import base.TestCounter;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class AbstractTest {
    private TestCounter counter = new TestCounter();

    protected void run() {
        test();
        counter.printStatus(getClass());
    }

    protected abstract void test();

    protected void test(Consumer<StringBuilder> f, String expected, final Map<String, String> replacements) {
        for (final Map.Entry<String, String> entry : replacements.entrySet()) {
            expected = expected.replace(entry.getKey(), entry.getValue());
        }

        counter.nextTest();
        final StringBuilder sb = new StringBuilder();
        f.accept(sb);
        final String actual = sb.toString();
        Asserts.assertEquals("Result", expected, actual);
        counter.passed();
    }
}
