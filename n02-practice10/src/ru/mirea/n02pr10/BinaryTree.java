package ru.mirea.n02pr10;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> extends Tree<T> {

    public BinaryTree() {}

    public BinaryTree(T value) {
        super(value);
    }

    /**
     * Составление отсортированного списка из дерева
     */
    public List<T> getSortedArray() {
        List<T> sortedArray = new ArrayList<>();
        getSortedArray(parent, sortedArray, 0);
        return sortedArray;
    }

    private int getSortedArray(Node node, List<T> list, int i) {
        if (node.left != null)
            i = getSortedArray(node.left, list, i);
        list.add(i++, node.value);
        if (node.right != null)
            i = getSortedArray(node.right, list, i);
        return i;
    }
}
