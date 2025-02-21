package personal.practice.educativeio.customdatastructures.myhashset_TODO;

public class BinarySearchTree {
    public TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public TreeNode find(TreeNode node, int value) {
        if (node == null || node.val == value)
            return node;
        return node.val > value ? find(node.left, value) : find(node.right, value);
    }

}
