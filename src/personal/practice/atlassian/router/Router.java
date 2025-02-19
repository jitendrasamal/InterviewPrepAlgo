package personal.practice.atlassian.router;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-question/5994275/Atalassian-or-SDE2-or-Reject/
 */
public class Router {
    private final TrieNode root = new TrieNode();

    public static void main(String[] args) {
        Router router = new Router();
        router.addRoute("/foo", "foo");
        router.addRoute("/bar/*/baz", "bar");
        router.addRoute("/foo/baz", "foo");
        router.addRoute("/foo/*", "bar");

        router.addRoute("/*/*", "*_*");
        router.addRoute("/bar/*", "bar_*");

        System.out.println(router.callRoute("/bar")); // null
        System.out.println(router.callRoute("/bar/*")); // null

        System.out.println(router.callRoute("/foo")); // foo
        System.out.println(router.callRoute("/bar/a/baz")); // bar
        System.out.println(router.callRoute("/foo/baz")); // foo (exact match wins)
        System.out.println(router.callRoute("/foo/xyz")); // bar (wildcard match)
        System.out.println(router.callRoute("/foo/xyz/abc")); // null
        System.out.println(router.callRoute("/unknown")); // null
        System.out.println(router.callRoute("/unknown/unknown")); // *_*

    }

    public void addRoute(String path, String result) {
        String[] parts = path.split("/");
        TrieNode current = root;
        for (String part : parts) {
            current = current.children.computeIfAbsent(part, key -> new TrieNode());
        }
        current.value = result;
    }

    public String callRoute(String path) {
        String[] parts = path.split("/");
        TrieNode result = searchPath(parts, 0, root);
        return result != null ? result.value : null;
    }

    private TrieNode searchPath(String[] parts, int index, TrieNode node) {
        if (index == parts.length || node == null) return node;

        // exact match
        if (node.children.containsKey(parts[index])) {
            TrieNode result = searchPath(parts, index + 1, node.children.get(parts[index]));
            if (result != null) {
                return result;
            }
        }

        // wildcard
        if (node.children.containsKey("*")) {
            return searchPath(parts, index + 1, node.children.get("*"));
        }

        return null;
    }

    private static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        String value = null;
    }

}
