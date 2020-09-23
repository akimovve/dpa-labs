package ru.mirea.n02pr10;

public class AvlTree<T extends Comparable<T>> extends Tree<T> {

    public AvlTree() {}

    public AvlTree(T value) {
        super(value);
    }

    @Override
    protected Node insertNode(Node node, T value) {
        return balance(super.insertNode(node, value));
    }

    @Override
    protected Node remove(Node node, T value) {
        return balance(super.remove(node, value));
    }

    /**
     * Обновление значения высоты ветви
     */
    private void updateBranchHeight(Node node) {
        node.height = Math.max(getBranchHeight(node.left), getBranchHeight(node.right)) + 1;
    }

    private int getBranchHeight(Node node) {
        return (node == null) ? -1 : node.height;
    }

    private int compareBranchesLength(Node node) {
        return (node == null) ? 0 : getBranchHeight(node.right) - getBranchHeight(node.left);
    }

    /**
     * Балансировка ветвей для АВЛ-дерева
     */
    private Node balance(Node node) {
        if (node == null)
            return null;
        updateBranchHeight(node);
        int difference = compareBranchesLength(node);

        if (difference == 0 || Math.abs(difference) == 1)
            return node;

        // Левое вращение
        if (difference > 1) {
            if (getBranchHeight(node.right.right) <= getBranchHeight(node.right.left)) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        // Правое вращение
        if (getBranchHeight(node.left.left) <= getBranchHeight(node.left.right)) {
            node.left = leftRotate(node.left);
        }
        return rightRotate(node);
    }

    private Node leftRotate(Node node) {
        Node right = node.right;
        Node left = right.left;
        right.left = node;
        node.right = left;
        updateBranchHeight(node);
        updateBranchHeight(right);
        return right;
    }

    private Node rightRotate(Node node) {
        Node left = node.left;
        Node right = left.right;
        left.right = node;
        node.left = right;
        updateBranchHeight(node);
        updateBranchHeight(left);
        return left;
    }
}
