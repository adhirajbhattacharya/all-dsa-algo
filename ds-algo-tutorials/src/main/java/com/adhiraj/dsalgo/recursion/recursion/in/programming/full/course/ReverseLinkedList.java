package com.adhiraj.dsalgo.recursion.recursion.in.programming.full.course;

public class ReverseLinkedList {
    public static void main(String[] args) {
        Node list = new Node(1);
        list.next = new Node(2);
        list.next.next = new Node(3);
        list.next.next.next = new Node(4);
        System.out.println(list);
        System.out.println(reverse(list));
    }

    public static Node reverse(Node list) {
        if (list == null || list.next == null) return list;

        Node reverseHead = reverse(list.next);
        list.next.next = list;
        list.next = null;
        return reverseHead;
    }
}
