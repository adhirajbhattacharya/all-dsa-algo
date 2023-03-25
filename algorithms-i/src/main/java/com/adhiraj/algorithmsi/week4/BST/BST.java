package com.adhiraj.algorithmsi.week4.BST;

import java.util.NoSuchElementException;
import com.adhiraj.algorithmsi.week2.queue.ArrayQueue;

public class BST<K extends Comparable<K>, V> {
  
  private Node root;
  
  private class Node {
    private final K key;
    private V value;
    private Node left;
    private Node right;
    private int size;
    
    public Node(K key, V value) {
      super();
      this.key = key;
      this.value = value;
      this.size = 1;
    }
    
  }
  
  public int size() {
    return size(root);
  }
  
  private int size(Node node) {
    if (node == null)
      return 0;
    return node.size;
  }
  
  public boolean isEmpty() {
    return size(root) == 0;
  }
  
  public void put(K key, V value) {
    validateObject(key);
    if (value == null) {
      delete(key);
      return;
    }
    
    root = put(root, key, value);
  }
  
  private Node put(Node node, K key, V value) {
    if (node == null)
      return new Node(key, value);
    
    int cmp = key.compareTo(node.key);
    if (cmp == 0)
      node.value = value;
    else if (cmp > 0)
      node.right = put(node.right, key, value);
    else
      node.left = put(node.left, key, value);
    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }
  
  public V get(K key) {
    validateObject(key);
    return get(root, key);
  }
  
  private V get(Node node, K key) {
    if (node == null)
      return null;
    int cmp = key.compareTo(node.key);
    if (cmp < 0)
      return get(node.left, key);
    else if (cmp > 0)
      return get(node.right, key);
    else
      return node.value;
  }
  
  public K floor(K key) {
    validateObject(key);
    
    Node floor = floor(root, key);
    if (floor == null)
      return null;
    return floor.key;
  }
  
  private Node floor(Node node, K key) {
    if (node == null)
      return null;
    
    int cmp = key.compareTo(node.key);
    if (cmp == 0)
      return node;
    if (cmp < 0)
      return floor(node.left, key);
    
    Node check = floor(node.right, key);
    if (check == null)
      return node;
    return check;
  }
  
  public K ceiling(K key) {
    validateObject(key);
    
    Node ceiling = ceiling(root, key);
    if (ceiling == null)
      return null;
    return ceiling.key;
  }
  
  private Node ceiling(Node node, K key) {
    if (node == null)
      return null;
    
    int cmp = key.compareTo(node.key);
    if (cmp == 0)
      return node;
    if (cmp > 0)
      return ceiling(node.right, key);
    
    Node check = ceiling(node.left, key);
    if (check == null)
      return node;
    return check;
  }
  
  public int rank(K key) {
    validateObject(key);
    
    return rank(root, key);
  }
  
  private int rank(Node node, K key) {
    if (node == null)
      return 0;
    int cmp = key.compareTo(node.key);
    if (cmp == 0)
      return size(node.left);
    if (cmp < 0)
      return rank(node.left, key);
    return 1 + size(node.left) + rank(node.right, key);
    
  }
  
  public void deleteMin() {
    if (isEmpty())
      throw new NoSuchElementException();
    root = deleteMin(root);
  }
  
  private Node deleteMin(Node node) {
    if (node.left == null)
      return node.right;
    node.left = deleteMin(node.left);
    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }
  
  public void deleteMax() {
    if (isEmpty())
      throw new NoSuchElementException();
    root = deleteMax(root);
  }
  
  private Node deleteMax(Node node) {
    if (node.right == null)
      return node.left;
    node.right = deleteMax(node.right);
    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }
  
  public void delete(K key) {
    validateObject(key);
    root = delete(root, key);
  }
  
  private Node delete(Node node, K key) {
    if (node == null) {
      return null;
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = delete(node.left, key);
    } else if (cmp > 0) {
      node.right = delete(node.right, key);
    } else {
      if (node.left == null)
        return node.right;
      else if (node.right == null)
        return node.left;
      Node t = node;
      node = min(t.right);
      node.right = deleteMin(t.right);
      node.left = t.left;
    }
    
    node.size = 1 + size(node.left) + size(node.right);
    return node;
  }
  
  public K min() {
    if (isEmpty())
      throw new NoSuchElementException();
    return min(root).key;
  }
  
  private Node min(Node node) {
    if (node.left == null)
      return node;
    return min(node.left);
  }
  
  public K max() {
    if (isEmpty())
      throw new NoSuchElementException();
    return max(root).key;
  }
  
  private Node max(Node node) {
    if (node.right == null)
      return node;
    return max(node.right);
  }
  
  public boolean contains(K key) {
    return get(key) != null;
  }
  
  public Iterable<K> iterator() {
    ArrayQueue<K> queue = new ArrayQueue<>();
    inorderKeyQueue(root, queue);
    return queue;
  }
  
  private void inorderKeyQueue(Node node, ArrayQueue<K> queue) {
    if (node == null)
      return;
    inorderKeyQueue(node.left, queue);
    queue.enqueue(node.key);
    inorderKeyQueue(node.right, queue);
  }
  
  private void validateObject(Object o) {
    if (o == null)
      throw new IllegalArgumentException();
  }
}
