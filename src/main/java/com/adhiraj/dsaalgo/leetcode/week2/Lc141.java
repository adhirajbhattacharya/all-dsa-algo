package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.ListNode;

public class Lc141 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }

        return false;
    }
}

