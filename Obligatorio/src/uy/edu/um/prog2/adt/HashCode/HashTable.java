package uy.edu.um.prog2.adt.HashCode;

public interface HashTable <K,V> {
    public void put(K key, V value);
    public boolean contains(K key);
    public void remove (K key);
    public V get(K key);
    public void changeValue(K key, V value);
}
