package com.adhiraj.dsalgo.recursion.recursion.in.programming.full.course;

public class MergeSortedLinkedLists {
    public static void main(String[] args) {

    }

    public static Node mergeSortedLinkedLists(Node sortedList1, Node sortedList2) {
        if (sortedList1 == null) return sortedList2;
        if (sortedList2 == null) return sortedList1;

        if (sortedList1.value <= sortedList2.value) {
            sortedList1.next = mergeSortedLinkedLists(sortedList1.next, sortedList2);
            return sortedList1;
        }

        sortedList2.next = mergeSortedLinkedLists(sortedList1, sortedList2.next);
        return sortedList2;
    }
}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        next = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node tmp = this;
        sb.append("HEAD->");
        sb.append(tmp.value);
        while (tmp.next != null) {
            sb.append("->");
            tmp = tmp.next;
            sb.append(tmp.value);
        }
        sb.append("->END");
        return sb.toString();
    }
}
