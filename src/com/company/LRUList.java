package com.company;

import java.util.*;

public class LRUList <E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    private final int len;

    //-----构造函数-----//

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(LRUList.Node<E> prev, E item, LRUList.Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public LRUList(int len) {
        this.len = len;
    }

    public boolean add(E item) {
        linkHead(item);
        if(size > len) {
            removeTail();
        }
        return true;
    }

    void linkHead(E item) {
        Node<E> tem_node = new Node<>(null,item,head);
        if(this.head == null) {
            this.head = this.tail = tem_node;
        }
        else {
            this.head.prev = tem_node;
            this.head = tem_node;
        }
        this.size ++ ;
    }

    void removeTail() {
        if(this.tail == null) {
            throw new NoSuchElementException();
        }
        else {
            this.tail.prev.next = null;
            Node<E> tem_node = this.tail;
            this.tail = this.tail.prev;

            tem_node.prev = null;
            tem_node.next = null;
            this.size--;
        }
    }

    void movToHead(Node<E> node) {
        if(node.prev != null) {
            if(node.next == null) {
                this.tail = node.prev;
            }
            else {
                node.next.prev= node.prev;
            }
            node.prev.next = node.next;
            node.prev= null;
            node.next = this.head;
            this.head = node;
        }
    }

    public E get(int index) {
        if(index > (size >> 1)) {
            Node<E> tem_node = this.tail;
            for (int i = 0; i < size - index; i++) {
                tem_node = tem_node.prev;
            }
            movToHead(tem_node);
            return tem_node.item;
        }
        else if (index <= (size >> 1)) {
            Node<E> tem_node = this.head;
            for (int i = 0; i < index; i++) {
                tem_node = tem_node.next;
            }
            movToHead(tem_node);
            return tem_node.item;
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return this.size;
    }

    public int len() {
        return this.len;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        int i = 0;
        for(Node<E> tem_node = this.head ; tem_node != null ; i++,tem_node = tem_node.next) {
            string.append(i + 1).append('.').append(tem_node.item);
            if(i%5 == 0 && i != 0) {
                string.append('\n');
            }
        }
        return  string.toString();
    }

}
