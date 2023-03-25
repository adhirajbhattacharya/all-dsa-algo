package com.adhiraj.algorithmsi.week2.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<I> implements Iterable<I> {

  private I[] stack;
  private int size;

  @SuppressWarnings("unchecked")
  public ArrayStack() {
    stack = (I[]) new Object[1];
  }

  public void push(I item) {
    if (size == stack.length) {
      resize(2 * stack.length);
    }
    stack[size++] = item;
  }

  public I pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    I item = stack[size - 1];
    stack[size--] = null;
    if (size > 0 && size <= stack.length / 4) {
      resize(stack.length / 2);
    }
    return item;
  }

  public I peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Stack underflow");
    }
    return stack[size - 1];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Iterator<I> iterator() {
    return new ReverseArrayIterator();
  }

  private class ReverseArrayIterator implements Iterator<I> {

    private int start;

    private ReverseArrayIterator() {
      start = size;
    }

    public boolean hasNext() {
      return start != 0;
    }

    public I next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No elements in stack for iteration");
      }
      return stack[--start];
    }

  }

  @SuppressWarnings("unchecked")
  private void resize(int capacity) {
    assert capacity >= size;

    I[] newStack = (I[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      newStack[i] = stack[i];
    }
    stack = newStack;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (I item : this)
      s.append(item + " ");
    return s.toString();
  }

}
