package personal.practice.atlassian.filesystem;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
    TrieNode root;

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.createPath("/a", 1));
        System.out.println(fs.get("/a"));
    }

    public FileSystem() {
        root = new TrieNode("/", -1);
    }

    public boolean createPath(String path, int value) {
        TrieNode parent = getParentNode(path);
        String components[] = path.substring(1).split("/");
        if (parent == null || parent.children.containsKey(components[components.length - 1])) {
            return false;
        }
        parent.children.put(components[components.length - 1], new TrieNode("components[components.length-1]", value));
        return true;
    }

    private TrieNode getParentNode(String path) {
        String[] components = path.substring(1).split("/");
        int index = 0;
        TrieNode node = root;
        while (index < components.length - 1 && node != null) {
            node = node.children.get(components[index]);
            ++index;
        }
        return node;
    }

    public int get(String path) {
        TrieNode parent = getParentNode(path);
        String[] components = path.substring(1).split("/");
        if (parent == null || !parent.children.containsKey(components[components.length - 1])) {
            return -1;
        }
        return parent.children.get(components[components.length - 1]).value;
    }

    private static class TrieNode {
        String name;
        int value = -1;
        Map<String, TrieNode> children = new HashMap<>();

        public TrieNode(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

}
