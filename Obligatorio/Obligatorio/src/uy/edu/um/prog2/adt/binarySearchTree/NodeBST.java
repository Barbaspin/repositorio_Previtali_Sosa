package uy.edu.um.prog2.adt.binarySearchTree;
import java.util.Objects;

public class NodeBST<K extends Comparable<K>,T> {

    K key;
    T data;
    NodeBST <K, T> leftChild;
    NodeBST <K, T> rightChild;


    // constructor
    public NodeBST(K key, T data) {
        this.key = key;
        this.data = data;
    }

    //metodos
    public void deleteNodo(K key) {
    NodeBST<K,T> nodoABorrar = this.findNodo(key); // este es el nodo a borrar
    NodeBST<K,T> nodoPadre = this.findNodoPadre(key); // este es el padre del nodo a borrar
    if (nodoABorrar.getLeftChild()== null && nodoABorrar.getRightChild() == null) {
        //en el caso que no tiene hijos vamos a el padre y le removemos el hijo adecuado
        if(nodoPadre!=null &&nodoPadre.getKey().compareTo(key)<0){
            nodoPadre.setRightChild(null);
            // si la key del padre es menor que la que buscamos significa que es el hijo derecho
        }
        if(nodoPadre!=null &&nodoPadre.getKey().compareTo(key)>0){
            nodoPadre.setLeftChild(null);
            // si la key del padre es mayor que la del que buscamos significa que es el hijo izquierdo
        }
    }
    else {
        // en caso que si tenga hijos tenemos que "intercambiar los nodos"
    if (nodoABorrar.getLeftChild() != null /* && nodoABorrar.getRightChild() != null */) {
        //cuando tiene ambos hijos podemos elegir intercambiarlos con cualquiera (tomamos la izquierda)
        K keyABorrar = nodoABorrar.getKey();
        T dataABorrar = nodoABorrar.getData();
        //guardamos los datos del que vamos a borrar y se lo ponemos a el hijo izq
        nodoABorrar.setKey(nodoABorrar.getLeftChild().getKey());
        nodoABorrar.setData(nodoABorrar.getLeftChild().getData());
        nodoABorrar.getLeftChild().setData(dataABorrar);
        nodoABorrar.getLeftChild().setKey(keyABorrar);
        // el hijo ahora es el que vamos a borrar
        nodoABorrar.getLeftChild().deleteNodo(key);
    } else if (nodoABorrar.getLeftChild()==null) {
        //como el hijo izquierdo no existe lo cambiamos con el derecho
        K keyABorrar = nodoABorrar.getKey();
        T dataABorrar = nodoABorrar.getData();
        //guardamos los datos del que vamos a borrar y se lo ponemos a el hijo der
        nodoABorrar.setKey(nodoABorrar.getRightChild().getKey());
        nodoABorrar.setData(nodoABorrar.getRightChild().getData());
        nodoABorrar.getRightChild().setData(dataABorrar);
        nodoABorrar.getRightChild().setKey(keyABorrar);
        // el hijo ahora es el que vamos a borrar
        nodoABorrar.getRightChild().deleteNodo(key);
    }
//    else if (nodoABorrar.getRightChild()==null) {
//        K keyABorrar = nodoABorrar.getKey();
//        T dataABorrar = nodoABorrar.getData();
//        //guardamos los datos del que vamos a borrar y se lo ponemos a el hijo izq
//        nodoABorrar.setKey(nodoABorrar.getLeftChild().getKey());
//        nodoABorrar.setData(nodoABorrar.getLeftChild().getData());
//        nodoABorrar.getLeftChild().setData(dataABorrar);
//        nodoABorrar.getLeftChild().setKey(keyABorrar);
//        // el hijo ahora es el que vamos a borrar
//        nodoABorrar.getLeftChild().deleteNodo(key);
//    }
    }}


    public NodeBST<K,T> findNodoPadre(K key) {
        NodeBST<K,T> nodoTemp = new NodeBST<>(key,null);
        if (!this.equals(nodoTemp)){
            if (this.getLeftChild().getKey().equals(key) || this.getRightChild().getKey().equals(key)){
                    return this;
            }
            if (this.getKey().compareTo(key)<0 && this.getRightChild()!=null){
                // si  es menor que 0 significa que this.getkey es menor que key y entonces hay que ir a el hijo der
                if (this.getRightChild().findNodoPadre(key)!=null){
                    return this.getRightChild().findNodoPadre(key);
                }
            }
            if (this.getKey().compareTo(key)>0 && this.getLeftChild()!=null){
                // si  es mayor que 0 significa que this.getkey es mayor que key y entonces hay que ir a el hijo izq
                if (this.getLeftChild().findNodoPadre(key)!=null){
                    return this.getLeftChild().findNodoPadre(key);
                }
        }
        }
        return null;
    }


    public void insertNodo(NodeBST<K,T> tempNodo){
        if(this.findNodo(tempNodo.getKey())==null){
        if (tempNodo.getKey().compareTo(this.getKey())>0) {
//            aca vamos a el subarbol de la derecha, si la key es mayor que la del nodo actual
            NodeBST<K,T> hijoderecho = this.getRightChild();
            if (hijoderecho==null){
                //si no tiene hijo derecho, se agrega el nodo como su hijo derecho
                this.setRightChild(tempNodo);
            }else {
                //si ya tiene hijo derecho, vuelve a llamar a la funcion para insertar el nodo
                this.getRightChild().insertNodo(tempNodo);
            }
        }

        if(tempNodo.getKey().compareTo(this.getKey())<0){
//            aca vamos a el subarbol de la izquierda, ya que la key de tempNodo es menor que la del nodo actual
            NodeBST<K,T> hijoIzquierdo = this.getLeftChild();
            if (hijoIzquierdo==null){
                this.setLeftChild(tempNodo);
            }else {
                this.getLeftChild().insertNodo(tempNodo);
            }

        }
    }
    else{
        System.out.println("El nodo ya existe");
    }
    }


    public NodeBST<K,T> findNodo(K key) {
        if (this.getKey().equals(key)){
            return this;
        }
        NodeBST<K,T> hijoderecho =this.getRightChild();
        if(key.compareTo(this.getKey())>0 && hijoderecho!=null){
            //aca va al subarbol derecho ya que la key que se busca es mayor que la de la raiz
            return hijoderecho.findNodo(key);
        }

        NodeBST<K,T> hijoIzquierdo =this.getLeftChild();
        if(key.compareTo(this.getKey())<0 && hijoIzquierdo!=null){
            //aca va al subarbol izquierdo ya que la key de la raiz es mayor que la que se busca

            return hijoIzquierdo.findNodo(key);
        }
        return null;
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeBST<?, ?> nodeBST = (NodeBST<?, ?>) o;
        return Objects.equals(key, nodeBST.key);
    }

    //getters y setters
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeBST<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeBST<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeBST<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeBST<K, T> rightChild) {
        this.rightChild = rightChild;
    }
}
