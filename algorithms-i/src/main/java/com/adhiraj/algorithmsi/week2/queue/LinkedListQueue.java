package com.adhiraj.algorithmsi.week2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<I> implements Iterable<I> {

  private Node head;
  private Node tail;
  private int count;

  public void enqueue(I item) {
    Node node = new Node(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      tail = tail.next;
    }
    count++;
    assert check();
  }

  public I dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    }
    I item = head.item;
    head = head.next;
    count--;
    if (isEmpty()) {
      tail = null;
    }
    assert check();
    return item;
  }

  public I peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue underflow");
    }
    return head.item;
  }

  public int size() {
    return count;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public Iterator<I> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<I> {

    private Node start;

    private ListIterator() {
      start = head;
    }

    public boolean hasNext() {
      return start != null;
    }

    public I next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No elements in queue for iteration");
      }
      I item = start.item;
      start = start.next;
      return item;
    }
  }

  private class Node {
    private I item;
    private Node next;

    private Node(I item) {
      this.item = item;
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
      if (head != null || tail != null) {
        return false;
      }
    } else if (count == 1) {
      if (head == null || tail == null || head != tail || head.next == null) {
        return false;
      }
    } else {
      if (head == null || tail == null || head == tail || head.next == null || tail.next != null) {
        return false;
      }

      Node lastNode = head;
      while (lastNode.next != null) {
        lastNode = lastNode.next;
      }
      if (tail != lastNode) {
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
