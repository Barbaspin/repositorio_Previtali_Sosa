import uy.edu.um.prog2.adt.HashCode.HashTable;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;


public class OrdenarCSV {

    //es un hash con key pais, dentro tiene hashes con key fecha y dentro tiene listas
    HashTable<String,HashTable<String,MyLinkedListImpl<MyLinkedListImpl<String>>>> hashPaises;
    MyLinkedListImpl<String> fila0;


    //esto para que sirve???
    //es una funcion que busca la posicion de un string en una lista de strings?(no sirve no?)
    //leer csv guarda en lista de listas de strings
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
        //cuando construimos una instancia de ordenar csv queremos que pase de tener los datos del csv en una lista de listas
        //tenerlo en un hash de hashes de listas, esto nos permite hacer las consultas
        //es el tipo de nodo que va dentro del hash de paises
        Node<String,HashTable<String,MyLinkedListImpl<MyLinkedListImpl<String>>>> nodoPaises = new Node<>(null,null);
        //se crea el hash
        this.hashPaises = new HashTableImpl<>(nodoPaises,150);
        //lee el csv y lo guarda en lista de listas de strings
        LeerCSV leerCSV = new LeerCSV();

        //obtenemos la lista
        MyLinkedListImpl<MyLinkedListImpl<String>> csvDatos =leerCSV.getDatos();
        this.fila0 = csvDatos.get(0);
        int posPais =buscarPosicion("country",fila0);
        int posFecha = buscarPosicion("snapshot_date",fila0);
        //obtenemos las posiciones de pais y fecha
        //vamos a ver cada fila y vamos a ver si existe un nodo que tenga como clave el pais


        for (int i=1;i<csvDatos.size();i++){
            //obtenemos la fila
            MyLinkedListImpl<String> fila = csvDatos.get(i);
            if (!hashPaises.contains(fila.get(posPais))){
                //como no existe el nodo con este pais, ese hash no tiene la fecha
                //para crear meterlo al hash nececsitamos un hash de fecha que tiene un nodo de fecha que tiene una lista de listas

//                MyLinkedListImpl<MyLinkedListImpl<String>> listaFilas = new MyLinkedListImpl<>();
                //se crea la lista de filas, y metemos esta fila
//                listaFilas.add(fila);
                //ahora creamos el nodo y lo utilizamos para crear el hash, y lo agregamos al hash
                Node<String,MyLinkedListImpl<MyLinkedListImpl<String>>> nodoFechaFila= new Node<>(null,null);
                HashTable<String,MyLinkedListImpl<MyLinkedListImpl<String>>> hashFecha = new HashTableImpl<>(nodoFechaFila,100);
//                hashFecha.put(fila.get(posFecha),listaFilas);
                //se supone que agrega al hash pais un hash fecha con una lista de listas a la que se le agrego la fila
                hashPaises.put(fila.get(posPais),hashFecha);
            }
            //si ya existe el pais en hash paises
            //chequeamos si existe la fecha en el hash fechas
//            System.out.println(posFecha);
//            System.out.println(fila.get(posFecha));
//            System.out.println(hashPaises.get(fila.get(posPais)).contains(fila.get(posFecha)));
            if (!hashPaises.get(fila.get(posPais)).contains(fila.get(posFecha))){
                //si no existe la fecha hay que hacerle un hash y meterlo
                Node<String,MyLinkedListImpl<MyLinkedListImpl<String>>> nodo = new Node<>(null,null);
                HashTable<String,MyLinkedListImpl<MyLinkedListImpl<String>>> hashFecha = new HashTableImpl<>(nodo,100);
                MyLinkedListImpl<MyLinkedListImpl<String>> listaFilas = new MyLinkedListImpl<>();
                //se crea la lista de filas, y metemos esta fila
//                listaFilas.add(fila);
                //creamos la lista de filas de esa fecha y la metemos al hash del pais
                hashPaises.get(fila.get(posPais)).put(fila.get(posFecha),listaFilas);
            }
            //ya sabemos que existe el hash de pais y el de la fecha
            //hay que meter esta fila a la lista de filas
            //metemos obtenemos el hash de fechas y le metemos a la lista de la fecha la fila
                hashPaises.get(fila.get(posPais)).get(fila.get(posFecha)).add(fila);

        }


    }

    public HashTable<String, HashTable<String, MyLinkedListImpl<MyLinkedListImpl<String>>>> getHashPaises() {
        return hashPaises;
    }

    public void setHashPaises(HashTable<String, HashTable<String, MyLinkedListImpl<MyLinkedListImpl<String>>>> hashPaises) {
        this.hashPaises = hashPaises;
    }

    public MyLinkedListImpl<String> getFila0() {
        return fila0;
    }

    public void setFila0(MyLinkedListImpl<String> fila0) {
        this.fila0 = fila0;
    }
}
