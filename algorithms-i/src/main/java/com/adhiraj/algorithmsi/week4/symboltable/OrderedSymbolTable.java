package com.adhiraj.algorithmsi.week4.symboltable;

import com.adhiraj.algorithmsi.week2.queue.ArrayQueue;
import edu.princeton.cs.algs4.StdOut;

public class OrderedSymbolTable<K extends Comparable<K>, V> {
  
  private K[] keys;
  private V[] values;
  private int size;
  
  @SuppressWarnings("unchecked")
  public OrderedSymbolTable() {
    keys = (K[]) new Comparable[1];
    values = (V[]) new Object[1];
  }
  
  public void put(K key, V val) {
    if (val == null) {
      delete(key);
      return;
    }
    if (size == 0) {
      put(key, val, 0);
      return;
    }
    int rank = rank(key);
    if (rank < size && keys[rank].compareTo(key) == 0) {
      values[rank] = val;
      return;
    }
    put(key, val, rank);
  }
  
  private void put(K key, V val, int index) {
    if (size == keys.length)
      resize(2 * keys.length);
    for (int i = size; i > index; i--) {
      keys[i] = keys[i - 1];
      values[i] = values[i - 1];
    }
    keys[index] = key;
    values[index] = val;
    size++;
  }
  
  public V get(K key) {
    if (size == 0)
      return null;
    int rank = rank(key);
    if (rank < size && keys[rank].compareTo(key) == 0)
      return values[rank];
    return null;
  }
  
  public void delete(K key) {
    int rank = rank(key);
    if (rank < size && keys[rank].compareTo(key) == 0)
      delete(rank);
  }
  
  private void delete(int index) {
    size--;
    for (int i = index; i < size; i++) {
      keys[i] = keys[i + 1];
      values[i] = values[i + 1];
    }
    keys[size] = null;
    values[size] = null;
    if (size != 0 && size <= keys.length / 4)
      resize(keys.length / 2);
  }
  
  @SuppressWarnings("unchecked")
  private void resize(int capacity) {
    assert capacity > size;
    K[] k = (K[]) new Comparable[capacity];
    V[] v = (V[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      k[i] = keys[i];
      v[i] = values[i];
    }
    keys = k;
    values = v;
  }
  
  public boolean contains(K key) {
    int rank = rank(key);
    return contains(key, rank);
  }
  
  private boolean contains(K key, int rank) {
    return (rank < size && keys[rank].compareTo(key) == 0);
  }
  
  public K floor(K key) {
    int rank = rank(key);
    if (contains(key, rank))
      return keys[rank];
    if (rank > 0)
      return keys[rank - 1];
    return null;
  }
  
  public K ceiling(K key) {
    int rank = rank(key);
    if (rank < size)
      return keys[rank];
    return null;
  }
  
  private int rank(K key) {
    int lo = 0;
    int hi = size - 1;
    
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = keys[mid].compareTo(key);
      if (cmp == 0)
        return mid;
      else if (cmp > 0)
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    return lo;
  }
  
  public K min() {
    if (size == 0)
      throw new IllegalArgumentException();
    return keys[size - 1];
  }
  
  public K max() {
    if (size == 0)
      throw new IllegalArgumentException();
    return keys[0];
  }
  
  public void deleteMin() {
    if (size == 0)
      throw new IllegalArgumentException();
    delete(size - 1);
  }
  
  public void deleteMax() {
    if (size == 0)
      throw new IllegalArgumentException();
    delete(0);
  }
  
  public K select(int rank) {
    if (size == 0 || rank < 0 || rank > size - 1)
      throw new IllegalArgumentException();
    return keys[rank];
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  public Iterable<K> keys() {
    return keys(1, size);
  }
  
  public Iterable<K> keys(int lo, int hi) {
    if (lo > hi || lo < 1 || lo > size || hi < 1 || hi > size)
      throw new IllegalArgumentException();
    ArrayQueue<K> queue = new ArrayQueue<>();
    for (int i = lo; i <= hi; i++)
      queue.enqueue(keys[i - 1]);
    return queue;
  }
  
  public static void main(String[] args) {
    OrderedSymbolTable<String, Integer> st = new OrderedSymbolTable<>();
    String[] k = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
    for (int i = 0; i < k.length; i++) {
      String key = k[i];
      st.put(key, i);
    }
    for (String s : st.keys())
      StdOut.println(s + " " + st.get(s));
  }
  
}
