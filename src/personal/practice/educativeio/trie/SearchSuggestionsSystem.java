package personal.practice.educativeio.trie;


import java.util.*;

//https://leetcode.com/problems/search-suggestions-system/description/
//https://www.educative.io/courses/grokking-coding-interview/search-suggestions-system
public class SearchSuggestionsSystem {

    final int maxNumberOfSearch = 3;
    TrieNode root = new TrieNode(' ');

    public static void main(String[] args) {
        SearchSuggestionsSystem searchSuggestionsSystem = new SearchSuggestionsSystem();
        System.out.println(searchSuggestionsSystem.suggestedProducts(new String[]{"mobile", "mouse", "moneypot",
                "monitor", "mousepad"}, "mouse"));
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        buildTrie(products);
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            result.add(searchTrie(searchWord.substring(0, i +1)));
        }
        return result;
    }

    private void buildTrie(String[] products) {
        for (String prod : products) {
            buildTrie(prod);
        }
    }

    private List<String> searchTrie(String searchWord) {
        TrieNode node = root;
        for (int i = 0; i < searchWord.length() && node != null; i++) {
            node = node.children.getOrDefault(searchWord.charAt(i), null);
        }
        List<String> result = new ArrayList<>();
        buildWords(node, result, searchWord);
        return result;
    }

    private void buildWords(TrieNode node, List<String> result, String prefix) {
        if (node == null || result.size() >= maxNumberOfSearch)
            return;
        if (node.isWord) {
            result.add(prefix);
        }
        for (char c : node.children.keySet()) {
            buildWords(node.children.get(c), result, prefix + c);
        }
    }

    private void buildTrie(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (char c : chars) {
            node = node.children.computeIfAbsent(c, TrieNode::new);
        }
        node.isWord = true;
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
