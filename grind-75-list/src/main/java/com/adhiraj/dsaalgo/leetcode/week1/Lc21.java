package com.adhiraj.dsaalgo.leetcode.week1;

import com.adhiraj.dsaalgo.leetcode.ListNode;

public class Lc21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val > list2.val) return mergeTwoLists(list2, list1);

        ListNode result = list1, prev = list1;

        list1 = list1.next;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev = list1;
                list1 = list1.next;
            } else {
                // if (list1.val >= list2.val)
                prev.next = list2;
                list2 = list2.next;
                prev = prev.next;
                prev.next = list1;
            }
        }

        if (list2 != null)
            prev.next = list2;

        return result;
    }
}
