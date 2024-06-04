import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;


public class OrdenarCSV {

    //es un hash de arboles de hash de listas
    //verificar si el string de MyBinarySearchTreeImpl es string o fecha
    private HashTableImpl<String,MyBinarySearchTreeImpl<String,HashTableImpl<String,MyLinkedListImpl<String>>>> arbolesPais;

    public int buscarPosicion(String valor, MyLinkedListImpl<String> listaBuscar){
        int i=0;
        boolean encontrado = false;
        while(!encontrado && i!=listaBuscar.size()){
            if (listaBuscar.get(i).equals(valor)){
                encontrado=true;
            }else{
                i++;
            }
        }
        return i;
    }

    //constructor
    public OrdenarCSV(){

        //mete los datos en la lista de listas (filas de canciones)
        LeerCSV datosOG = new LeerCSV();

        MyLinkedListImpl<String> primerFila = datosOG.getDatos().get(0);
        int posicionPais=buscarPosicion("country",primerFila);

        Node<String,MyBinarySearchTreeImpl<String,HashTableImpl<String,MyLinkedListImpl<String>>>> nodoHash;
        Node<String,MyLinkedListImpl<String>> nodoO = new Node<>("",new MyLinkedListImpl<>());
        MyBinarySearchTreeImpl<String,HashTableImpl<String,MyLinkedListImpl<String>>> arbol = new MyBinarySearchTreeImpl<>("",new HashTableImpl<>(nodoO,1));
        nodoHash=new Node<>("",arbol);
        arbolesPais=new HashTableImpl<>(nodoHash,100);
        //se crea el hash de arboles de hashes
        for (int i =0;i<datosOG.getDatos().size() ;i++) {
            //si no existe el arbol o el hash hay que crarlo
            if (!arbolesPais.contains(datosOG.getDatos().get(i).get(posicionPais))){
                HashTableImpl<String,MyLinkedListImpl<String>> hashNuevo;
                Node<String,MyLinkedListImpl<String>> nuvNodo;
                nuvNodo = new Node<>(datosOG.getDatos().get(i).get(posicionPais),datosOG.getDatos().get(i));
                hashNuevo = new HashTableImpl<>(nuvNodo,100);
                hashNuevo.put(datosOG.getDatos().get(i).get(posicionPais),datosOG.getDatos().get(i));
                MyBinarySearchTreeImpl<String,HashTableImpl<String,MyLinkedListImpl<String>>> nuevo;
                nuevo=new MyBinarySearchTreeImpl<>(datosOG.getDatos().get(i).get(posicionPais),hashNuevo);

                //no se ni que hice

            }

        }

    }

    public static void main(String[] args) {
        OrdenarCSV datosOG = new OrdenarCSV();

    }

}
