package com.adhiraj.dsaalgo.leetcode.week6;

import com.adhiraj.dsaalgo.leetcode.GraphWithMap;
import com.adhiraj.dsaalgo.leetcode.UnionFind;

import java.util.*;

// Mark star
// This is using graph and connected components. See below for Union Find technique. Only question using UF.
public class Lc721  {
    public List<List<String>> accountsMerge(List<List<String>> accountList) {
        GraphWithMap g = new GraphWithMap();
        for (List<String> account : accountList) {
            String email1 = account.get(1);
            g.init(email1); // this is needed when there is only one email for an account
            for (int i = 2; i < account.size(); i++) {
                g.addEdge(email1, account.get(i));
            }
        }

        Set<String> visited = new HashSet();
        List<List<String>> mergedReturn = new ArrayList<>();

        for (List<String> account : accountList) {
            if (visited.contains(account.get(1)))
                continue;
            List<String> emails = new ArrayList<>();
            emails.add(account.get(0));
            dfs(g, visited, emails, account.get(1));
            mergedReturn.add(emails);
        }

        for (List<String> account : mergedReturn) {
            Collections.sort(account.subList(1, account.size()));
        }
        return mergedReturn;
    }

    private void dfs(GraphWithMap g, Set<String> visited, List<String> emails, String src) {
        visited.add(src);
        emails.add(src);
        for (String email : g.adj.get(src)) {
            if (visited.contains(email))
                continue;
            dfs(g, visited, emails, email);
        }
    }

    public static void main(String[] args) {
        List<List<String>> a = new ArrayList<>();
        a.add(new ArrayList<>());
        a.add(new ArrayList<>());
        a.add(new ArrayList<>());
        a.add(new ArrayList<>());

        a.get(0).add("John");
        a.get(0).add("j1@abc.com");
        a.get(0).add("j2@abc.com");
        a.get(1).add("John");
        a.get(1).add("j3@abc.com");
        a.get(1).add("j4@abc.com");
        a.get(2).add("John");
        a.get(2).add("j5@abc.com");
        a.get(2).add("j4@abc.com");
        a.get(3).add("John");
        a.get(3).add("j2@abc.com");
        a.get(3).add("j3@abc.com");

        System.out.println(new Lc721().accountsMerge(a));
    }
}

class Lc721AltUF {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, Integer> map = new HashMap<>();
        UnionFind uf = new UnionFind(accounts.size());

        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                Integer idx = map.get(email);
                if (!map.containsKey(email)) {
                    map.put(email, i);
                } else if (i != idx) {
                    uf.union(uf.find(i), uf.find(idx));
                }
            }
        }

        Map<Integer, List<String>> comp = new HashMap<>();

        for (String email : map.keySet()) {
            int j = map.get(email);
            int k = uf.find(j);
            List<String> emails = comp.get(k);
            if(emails == null) {
                emails = new ArrayList<>();
                comp.put(k, emails);
            }
            emails.add(email);
        }

        List<List<String>> merged = new ArrayList<>();

        for (int i : comp.keySet()) {
            List<String> emails = comp.get(i);
            Collections.sort(emails);
            emails.add(0, accounts.get(i).get(0));
            merged.add(emails);
        }

        return merged;
    }
}