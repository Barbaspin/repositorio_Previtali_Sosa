import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;

public class Main {
    public static void main(String[] args) {


        MyBinarySearchTreeImpl<String,Integer> arbol = new MyBinarySearchTreeImpl<>("2024-03-13",20);
        arbol.insert("2024-04-15",21);
        arbol.insert("2024-04-14",21);
        arbol.insert("2024-02-27",22);
        System.out.println(arbol.getRoot().getLeftChild().getKey());
        System.out.println(arbol.getRoot().getRightChild().getKey());
        System.out.println(arbol.getRoot().getRightChild().getLeftChild().getKey());
    }
}

