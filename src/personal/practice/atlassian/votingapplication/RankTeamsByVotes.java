package personal.practice.atlassian.votingapplication;


import java.util.*;

/*
https://leetcode.com/problems/rank-teams-by-votes/description/
 */
public class RankTeamsByVotes {
    public static void main(String[] args) {
        System.out.println(rankTeams(new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"})); // "ACB"
        System.out.println(rankTeams(new String[]{"WXYZ","XYZW"})); // "XWYZ"
        System.out.println(rankTeams(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"})); // "ZMNAGUEDSJYLBOPHRQICWFXTVK"
    }

    public static String rankTeams(String[] votes) {
        if (votes == null || votes.length == 0) {
            return "";
        }
        if (votes.length == 1) {
            return votes[0];
        }
        int numberOfCandidates = votes[0].length();
        Map<Character, int[]> candidateRanks = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                int[] candidateRank = candidateRanks.computeIfAbsent(vote.charAt(i), k -> new int[numberOfCandidates]);
                ++candidateRank[i];
            }
        }
        SortedSet<Character> sortedCandidates = new TreeSet<>((a, b) -> {
            for (int i = 0; i < numberOfCandidates; i++) {
                if (candidateRanks.get(a)[i] != candidateRanks.get(b)[i]) {
                    return candidateRanks.get(b)[i] - candidateRanks.get(a)[i];
                }
            }
            return a - b;
        });
        sortedCandidates.addAll(candidateRanks.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        sortedCandidates.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
