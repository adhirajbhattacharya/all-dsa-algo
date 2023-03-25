package com.adhiraj.algorithmsi.week4.symboltable;

import com.adhiraj.algorithmsi.week2.queue.ArrayQueue;
import edu.princeton.cs.algs4.StdOut;

public class UnorderedSymbolTable<K, V> {
  
  private Node start;
  private int size;
  
  private class Node {
    private K key;
    private V value;
    private Node next;
    
    private Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
      if (obj == null) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      if (obj.getClass() == this.getClass()) {
        return false;
      }
      Node t = (Node) obj;
      return t.key.equals(this.key);
    }
    
    @Override
    public int hashCode() {
      return this.key.hashCode();
    }
  }
  
  public UnorderedSymbolTable() {
    // create an empty symbol table
  }
  
  public void put(K key, V val) {
    if (val == null) {
      delete(key);
      return;
    }
    if (start == null) {
      start = new Node(key, val);
      size++;
      return;
    }
    Node t = start;
    while (t != null) {
      if (t.key.equals(key)) {
        t.value = val;
        return;
      }
      t = t.next;
    }
    t = new Node(key, val);
    t.next = start;
    start = t;
    size++;
  }
  
  public V get(K key) {
    V val = null;
    Node t = start;
    while (t != null) {
      if (t.key.equals(key)) {
        val = t.value;
        break;
      }
      t = t.next;
    }
    return val;
  }
  
  public void delete(K key) {
    Node t = start;
    Node prev = null;
    while (t != null) {
      if (t.key.equals(key)) {
        if (t.equals(start)) {
          start = start.next;
          t.next = null;
          size--;
          return;
        } else {
          prev.next = t.next;
          t.next = null;
          size--;
          return;
        }
      }
      prev = t;
      t = t.next;
    }
  }
  
  public boolean contains(K key) {
    return get(key) != null;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  public Iterable<K> keys() {
    ArrayQueue<K> queue = new ArrayQueue<>();
    Node t = start;
    while (t != null) {
      queue.enqueue(t.key);
      t = t.next;
    }
    return queue;
  }
  
  public static void main(String[] args) {
    UnorderedSymbolTable<String, Integer> st = new UnorderedSymbolTable<>();
    String[] k = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
    for (int i = 0; i < k.length; i++) {
      String key = k[i];
      st.put(key, i);
    }
    for (String s : st.keys())
      StdOut.println(s + " " + st.get(s));
  }
}
