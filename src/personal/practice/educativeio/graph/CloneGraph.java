package personal.practice.educativeio.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    private static final HashMap<Node, Node> cloneMap = new HashMap<>();

    public static Node clone(Node root) {
        if (root == null)
            return root;

        if (cloneMap.containsKey(root))
            return cloneMap.get(root);

        Node clone = new Node(root.val);
        cloneMap.put(root, clone);

        for (Node neighbor : root.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }

        return clone;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
