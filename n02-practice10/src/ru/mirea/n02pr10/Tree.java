package ru.mirea.n02pr10;

import java.util.Arrays;

public abstract class Tree<T extends Comparable<T>> {
    protected Node parent;

    /**
     * Класс-узел, отвечающий за значения и ссылки на другие узлы
     */
    protected class Node {
        protected T value;
        protected Node left;
        protected Node right;
        protected int height;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public Tree() {}

    public Tree(T value) {
        parent = new Node(value);
    }

    /**
     * Добавление последовательности элементов в дерево
     */
    @SafeVarargs
    public final void addNode(T... values) {
        Arrays.stream(values).forEach(this::addNode);
    }

    public void addNode(T value) {
        parent = insertNode(parent, value);
    }
    /**
     * Добавление элемента в дерево
     */
    protected Node insertNode(Node node, T value) {
        if (node == null)
            return new Node(value);
        if (node.value.compareTo(value) > 0) {
            node.left = insertNode(node.left, value);
        } else if (node.value.compareTo(value) < 0) {
            node.right = insertNode(node.right, value);
        }
        return node;
    }

    /**
     * Удаление элемента из дерева
     */
    public void remove(T value) {
        parent = remove(parent, value);
    }

    protected Node remove(Node node, T value) {
        if (node == null || node.left == null && node.right == null)
            return null;
        if (node.value.compareTo(value) > 0) {
            node.left = remove(node.left, value);
            return node;
        }
        if (node.value.compareTo(value) < 0) {
            node.right = remove(node.right, value);
            return node;
        }
        // Только левая ветвь
        if (node.right == null) {
            node = node.left;
            return node;
        }
        // Только правая ветвь
        if (node.left == null) {
            node = node.right;
            return node;
        }
        // Обе ветви => ищем наим. значение в правой ветви
        Node min = minValueInRightBranch(node.right);
        node.value = min.value;
        node.right = remove(node.right, min.value);
        return node;
    }

    /**
     * Поиск наименьшего числа в левой ветви
     */
    protected Node minValueInRightBranch(Node node) {
        return (node.left != null) ? minValueInRightBranch(node.left) : node;
    }

    /**
     * Печать дерева на экран
     */
    public void print() {
        printTree(parent, 0);
    }

    private void printTree(Node node, int level) {
        if (node == null)
            return;
        printTree(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("	");
        }
        System.out.println(node + "〈");
        printTree(node.left, level + 1);
    }
}
