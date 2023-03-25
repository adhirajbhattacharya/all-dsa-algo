package com.adhiraj.algorithmsi.week2.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<I> implements Iterable<I> {

  private Node head;
  private int count;

  public void push(I item) {
    Node oldHead = head;
    head = new Node(item);
    head.next = oldHead;
    count++;
    assert check();
  }

  public I pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    I item = head.item;
    head = head.next;
    count--;
    assert check();
    return item;
  }

  public I peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    return head.item;
  }

  public int size() {
    return count;
  }

  public boolean isEmpty() {
    return head == null;
  }

  private class Node {
    private I item;
    private Node next;

    private Node(I item) {
      this.item = item;
    }
  }

  public Iterator<I> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<I> {

    private Node start;

    private ListIterator() {
      this.start = head;
    }

    public boolean hasNext() {
      return start != null;
    }

    public I next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No elements in stack for iteration");
      }
      I item = start.item;
      start = start.next;
      return item;
    }

  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (I item : this)
      s.append(item + " ");
    return s.toString();
  }

  private boolean check() {

    if (count < 0) {
      return false;
    }
    if (count == 0) {
      if (head != null) {
        return false;
      }
    } else if (count == 1) {
      if (head == null || head.next != null) {
        return false;
      }
    } else {
      if (head == null || head.next == null) {
        return false;
      }
    }

    int numberOfNodes = 0;
    for (Node x = head; x != null; x = x.next) {
      numberOfNodes++;
    }

    return numberOfNodes == count;
  }

}
