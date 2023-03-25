package com.adhiraj.algorithmsi.week4.priorityqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<I extends Comparable<I>> implements Iterable<I> {
  
  private I[] queue;
  private int size;
  
  @SuppressWarnings("unchecked")
  public MaxPQ() {
    queue = (I[]) new Object[2];
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  public boolean isMaxHeap() {
    return isMaxHeap(1);
  }
  
  private boolean isMaxHeap(int k) {
    if (k >= size) {
      return true;
    }
    int left = 2 * k;
    int right = left + 1;
    if (left <= size && compare(k, left) < 0) {
      return false;
    }
    if (right <= size && compare(k, right) < 0) {
      return false;
    }
    return isMaxHeap(left) && isMaxHeap(right);
  }
  
  public void insert(I item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    if (size == queue.length - 1) {
      resize(2 * queue.length);
    }
    queue[++size] = item;
    swim(size);
  }
  
  private void swim(int k) {
    while (k > 1 && compare(k, k / 2) > 0) {
      exch(k, k / 2);
      k = k / 2;
    }
  }
  
  public I delMax() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    I item = queue[1];
    queue[1] = queue[size];
    queue[size--] = null;
    sink(1);
    if (size > 0 && size < (queue.length - 1) / 4) {
      resize(queue.length / 2);
    }
    return item;
  }
  
  private void sink(int k) {
    while (2 * k <= size) {
      int i = 2 * k;
      if (i < size && compare(i, i + 1) < 0) {
        i++;
      }
      if (compare(i, k) <= 0) {
        break;
      }
      exch(i, k);
      k = i;
    }
  }
  
  public Iterator<I> iterator() {
    return new HeapIterator();
  }
  
  private class HeapIterator implements Iterator<I> {
    
    private final I[] items;
    private int n;
    
    @SuppressWarnings("unchecked")
    private HeapIterator() {
      n = size;
      items = (I[]) new Object[n + 1];
      for (int i = 1; i <= n; i++) {
        items[i] = queue[i];
      }
    }
    
    public boolean hasNext() {
      return n > 0;
    }
    
    public I next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return items[n--];
    }
  }
  
  private void resize(int capacity) {
    if (capacity <= size) {
      throw new IllegalArgumentException();
    }
    @SuppressWarnings("unchecked")
    I[] newQueue = (I[]) new Object[capacity];
    for (int i = 1; i < +size; i++) {
      newQueue[i] = queue[i];
    }
  }
  
  private int compare(int k, int n) {
    return queue[k].compareTo(queue[n]);
  }
  
  private void exch(int k, int n) {
    I tmp = queue[k];
    queue[k] = queue[n];
    queue[n] = tmp;
  }
  
}
