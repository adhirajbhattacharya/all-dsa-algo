package com.adhiraj.dsalgo;

import java.util.HashMap;
import java.util.Map;

// LC-1366
public class RankTeamsByVotes {

    public String rankTeams(String[] votes) {
        if (votes.length == 1) return votes[0];
        int candidates = votes[0].length();
        int voters = votes.length;
        Map<Character, Vote> candidateVotes = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                char c = vote.charAt(i);
                Vote v = candidateVotes.get(c);
                if (v == null) {
                    v = new Vote(c, candidates);
                    candidateVotes.put(c, v);
                }
                v.incVote(i);
            }
        }

        candidateVotes.values().forEach(System.out::println);

        return candidateVotes.values().stream().sorted()
                .map(vote -> vote.candidate)
                .map(candidate -> String.valueOf(candidate))
                .reduce("", String::concat);
    }
}

class Vote implements Comparable<Vote> {
    char candidate;
    int[] rankedVotes;

    Vote(char candidate, int n) {
        this.candidate = candidate;
        rankedVotes = new int[n];
    }

    void incVote(int idx) {
        rankedVotes[idx] += 1;
    }

    public int compareTo(Vote other) {
        int cmp = 0;
        for (int i = 0; i < rankedVotes.length; i++) {
            cmp = Integer.compare(this.rankedVotes[i], other.rankedVotes[i]);
            if (cmp != 0) return cmp * (-1);
        }

        return Character.compare(this.candidate, other.candidate);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(candidate).append(" -> [ ");
        for (int i = 0; i < rankedVotes.length; i++) {
            sb.append("(").append(i).append(", ").append(rankedVotes[i]).append("), ");
        }
        return sb.append("]").toString();
    }
}