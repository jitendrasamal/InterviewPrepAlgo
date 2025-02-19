package personal.practice.educativeio.trie;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//https://www.educative.io/courses/grokking-coding-interview/replace-words
public class ReplaceWords {

    public static String replaceWords(String sentence, List<String> dictionary) {
        TrieNode root = new TrieNode(' ');
        dictionary.forEach(w -> buildTrie(w, root));

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String replace = findReplaceStr(root, words[i]);
            result.append(replace);
            if (i != words.length - 1) {
                result.append(" ");
            }
        }

        // Replace this placeholder return statement with your code
        return result.toString();
    }

    private static void buildTrie(String word, TrieNode node) {
        char[] chars = word.toCharArray();
        for (char c : chars) {
            node = node.children.computeIfAbsent(c, TrieNode::new);
        }
        node.isWord = true;
    }

    private static String findReplaceStr(TrieNode node, String string) {
        StringBuilder replaceStrBuilder = new StringBuilder();
        for (char c : string.toCharArray()) {
            node = node.children.getOrDefault(c, null);
            if (node == null)
                break;
            replaceStrBuilder.append(c);
            if (node.isWord) {
                return replaceStrBuilder.toString();
            }
        }
        return string;
    }

    private static class TrieNode {
        char c;
        SortedMap<Character, TrieNode> children = new TreeMap<>();
        boolean isWord = false;

        public TrieNode(char c) {
            this.c = c;
        }
    }

}
