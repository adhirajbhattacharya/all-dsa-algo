package com.adhiraj.algorithmsi.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] queue;
  private int size;

  public RandomizedQueue() {
    queue = (Item[]) new Object[1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    if (queue.length == size) {
      resize(2 * queue.length);
    }
    queue[size++] = item;
  }

  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    int rand = StdRandom.uniform(size);
    Item item;
    if (rand == size - 1) {
      item = queue[rand];
      queue[--size] = null;
    } else {
      item = queue[rand];
      queue[rand] = queue[--size];
      queue[size] = null;
    }
    if (size > 0 && size <= queue.length / 4) {
      resize(queue.length / 2);
    }
    return item;
  }

  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return queue[StdRandom.uniform(size)];
  }

  private void resize(int capacity) {
    if (capacity == 0 || capacity < size) {
      throw new IllegalArgumentException();
    }
    Item[] newQueue = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      newQueue[i] = queue[i];
    }
    queue = newQueue;
  }

  public Iterator<Item> iterator() {
    return new RandomIterator();
  }

  private class RandomIterator implements Iterator<Item> {

    private final Item[] items;
    private int n;

    private RandomIterator() {
      n = size;
      items = (Item[]) new Object[n];
      for (int i = 0; i < n; i++) {
        items[i] = queue[i];
      }
      StdRandom.shuffle(items);
    }

    public boolean hasNext() {
      return !(n == 0);
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = items[--n];
      items[n] = null;
      return item;
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    rq.enqueue(483);
    rq.dequeue();
    rq.enqueue(480);
  }

}
