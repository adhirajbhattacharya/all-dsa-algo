package com.adhiraj.algorithmsi.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private static final String ERROR_MESSAGE_UNDERFOW = "Underflow";
  private Node head;
  private Node tail;
  private int size;

  private class Node {
    private Item item;
    private Node prev;
    private Node next;
  
    private Node(Item item) {
      this.item = item;
    }
  }

  public Deque() {
    // Attributes will be defaulted to get empty deque
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    Node node = new Node(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      head.prev = node;
      node.next = head;
      head = node;
    }
    size++;
  }

  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    Node node = new Node(item);
    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    size++;
  }

  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException(ERROR_MESSAGE_UNDERFOW);
    }
    Item item = head.item;
    size--;
    if (isEmpty()) {
      tail = null;
      head = null;
    } else {
      Node tmp = head;
      head = head.next;
      tmp.next = null;
      head.prev = null;
    }
    return item;
  }

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException(ERROR_MESSAGE_UNDERFOW);
    }
    Item item = tail.item;
    size--;
    if (isEmpty()) {
      head = null;
      tail = null;
    } else {
      Node tmp = tail;
      tail = tail.prev;
      tmp.prev = null;
      tail.next = null;
    }
    return item;
  }

  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {

    private Node start;

    private DequeIterator() {
      start = head;
    }

    public boolean hasNext() {
      return start != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException(ERROR_MESSAGE_UNDERFOW);
      }
      Item item = start.item;
      start = start.next;
      return item;
    }

  }

  public static void main(String[] args) {
    // option test client
  }

}
