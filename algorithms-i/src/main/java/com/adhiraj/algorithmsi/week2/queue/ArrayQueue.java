package com.adhiraj.algorithmsi.week2.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<I> implements Iterable<I> {

  private I[] queue;
  private int head;
  private int tail;
  private int count;

  @SuppressWarnings("unchecked")
  public ArrayQueue() {
    queue = (I[]) new Object[1];
    head = -1;
    tail = -1;
  }

  public void enqueue(I item) {
    if (count == queue.length) {
      resize(queue.length * 2);
    }
    if (head == -1 && tail == -1) {
      queue[++head] = item;
      tail = head;
    } else if (tail == queue.length - 1) {
      tail = 0;
      queue[tail] = item;
    } else {
      queue[++tail] = item;
    }
    count++;
  }

  public I dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }

    I item;
    if (head == tail) {
      item = queue[head];
      head = -1;
      tail = -1;
    } else if (head == queue.length - 1) {
      item = queue[head];
      head = 0;
    } else {
      item = queue[head];
      queue[head++] = null;
    }
    count--;
    if (count > 0 && count <= queue.length / 4) {
      resize(queue.length / 2);
    }
    return item;
  }

  public I peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    return queue[head];
  }

  public int size() {
    return count;
  }

  public boolean isEmpty() {
    return count == 0;

  }

  public Iterator<I> iterator() {
    return new ArrayIterator();
  }

  private class ArrayIterator implements Iterator<I> {

    private int i;

    public boolean hasNext() {
      return i != count;
    }

    public I next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No elements in queue for iteration");
      }
      I item = queue[(i + head) % queue.length];
      i++;
      return item;
    }
  }

  @SuppressWarnings("unchecked")
  private void resize(int capacity) {
    assert capacity >= count;

    I[] newQueue = (I[]) new Object[capacity];
    int j;
    for (j = 0; j < count; j++) {
      newQueue[j] = queue[(j + head) % queue.length];
    }
    queue = newQueue;
    head = 0;
    tail = j - 1;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (I item : this)
      s.append(item + " ");
    return s.toString();
  }

}
