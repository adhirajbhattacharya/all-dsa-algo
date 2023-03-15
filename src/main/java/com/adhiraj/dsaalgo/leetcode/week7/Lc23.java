package com.adhiraj.dsaalgo.leetcode.week7;

import com.adhiraj.dsaalgo.leetcode.ListNode;

import java.util.PriorityQueue;

// below does not use pq, directly uses a merge sort kind of thing to do it
public class Lc23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> Integer.compare(a.val, b.val));

        ListNode merged = new ListNode(-1), head = merged; // head has initial dummy

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;
            pq.offer(lists[i]);
        }

        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            merged.next = curr;

            curr = curr.next;
            if (curr != null) pq.offer(curr);

            merged = merged.next;
            merged.next = null;
        }

        return head.next;
    }
}

class Lc23Alt {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        if (lists.length == 1) return lists[0];

        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int st, int en) {
        if (st == en) return lists[st];

        int mid = (st + en) / 2;

        ListNode leftMerged = mergeKLists(lists, st, mid);
        ListNode rightMerged = mergeKLists(lists, mid + 1, en);

        return merge(leftMerged, rightMerged);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode(-1), head = merged;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                merged.next = list1;
                list1 = list1.next;
                merged = merged.next;
                merged.next = null;
            } else {
                merged.next = list2;
                list2 = list2.next;
                merged = merged.next;
                merged.next = null;
            }
        }

        if (list1 != null) merged.next = list1;
        else if (list2 != null) merged.next = list2;

        return head.next;
    }
}
