package com.adhiraj.dsaalgo.leetcode.week6;

import com.adhiraj.dsaalgo.leetcode.ListNode;

public class Lc876 {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast.next != null)
            return slow.next;
        return slow;
    }
}