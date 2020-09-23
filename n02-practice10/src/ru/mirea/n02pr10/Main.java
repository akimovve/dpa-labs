package ru.mirea.n02pr10;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.print("Тестирование деревьев...");
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Tree<Integer> avlTree = new AvlTree<>();
        test(binaryTree);
        test(avlTree);
        binaryTree.getSortedArray().forEach(System.out::println);
    }

    private static void test(Tree<Integer> tree) {
        //int[] testArray = {11, 25, 21, 6, 4, 9, 67, 33, 6};
        //int[] testArray = {1, 25, 26, 27, 4, 67, 33};
        int[] testArray = {6, 12, 14, 13, 16, 2, 3, 1, 25, 26, 10, 4, 20, 7};
        Arrays.stream(testArray).forEach(tree::addNode);
        tree.addNode(15, 67, 1);
        System.out.println("\n\n\nИзначальное дерево...\n");
        tree.print();
        tree.remove(16);
        tree.remove(2);
        System.out.println("\n\nПосле удаления элементов...\n");
        tree.print();
    }
}
