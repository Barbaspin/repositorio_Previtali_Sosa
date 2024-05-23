package uy.edu.um.prog2.adt.binarySearchTree;

public interface MyBinarySearchTree <K extends Comparable<K>, T>{

    T find (K key);
    void insert(K key, T data);
    void delete (K key);

}
