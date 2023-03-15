package com.adhiraj.dsaalgo.leetcode.week4;

import com.adhiraj.dsaalgo.leetcode.ListNode;

public class Lc206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = null;

        while (next != null) {
            ListNode tmp = next;
            next = next.next;
            tmp.next = head;
            head = tmp;
        }

        return head;
    }
}
