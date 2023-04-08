package org.example.profiler;

import java.io.PrintStream;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

class SignatureTree<T> {
    private final SignatureNode<T> head;

    public SignatureTree(String name) {
        this.head = new SignatureNode<>(name, null);
    }

    public void add(String name, T element) {
        final String[] path = name.split("[.]");
        SignatureNode<T> node = head;
        for (int i = 0; i < path.length; i++) {
            final T insertingElement = i == path.length - 1 ? element : null;
            node = node.children.computeIfAbsent(path[i], p -> new SignatureNode<>(p, insertingElement));
        }
    }

    public void print(PrintStream printStream) {
        printStream.println(head.id);
        head.children.values().forEach(child -> print(printStream, child, ""));
    }

    private void print(PrintStream printStream, SignatureNode<T> node, String prefix) {
        final String elementString = Optional.ofNullable(node.element).map(Objects::toString).orElse("");
        printStream.println(prefix + node.id + " " + elementString);
        node.children.values().forEach(child -> print(printStream, child, prefix + "*  "));
    }

    static class SignatureNode<T> {
        private final String id;
        private final T element;
        private final Map<String, SignatureNode<T>> children = new TreeMap<>();

        public SignatureNode(String id, T element) {
            this.id = id;
            this.element = element;
        }
    }
}
