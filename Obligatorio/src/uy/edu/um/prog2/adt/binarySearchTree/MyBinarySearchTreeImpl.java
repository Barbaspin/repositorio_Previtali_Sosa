package uy.edu.um.prog2.adt.binarySearchTree;

public class MyBinarySearchTreeImpl<K extends Comparable<K>,T> implements MyBinarySearchTree<K,T>{
    NodeBST<K,T> root;


    //constructor
    public MyBinarySearchTreeImpl(K key, T data) {
        this.root = new NodeBST<>(key,data);
    }


    //metodos
    @Override
    public T find(K key) {
        NodeBST<K,T> resultado =root.findNodo(key);
        if (resultado==null){
            System.out.println("No se encontró el nodo");
            return null;
        }
        return resultado.getData();
    }

    @Override
    public void insert(K key, T data) {
        NodeBST<K,T> nodoTemp = new NodeBST<>(key,data);
        root.insertNodo(nodoTemp);
    }

    @Override
    public void delete(K key) {
        this.getRoot().deleteNodo(key);
    }




    //getters y setters

    public NodeBST<K, T> getRoot() {
        return root;
    }

    public void setRoot(NodeBST<K, T> root) {
        this.root = root;
    }
}
