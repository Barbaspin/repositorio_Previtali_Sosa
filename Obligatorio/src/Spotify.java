import uy.edu.um.prog2.adt.HashCode.HashTable;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.awt.*;
import java.util.Scanner;

public class Spotify {
    private OrdenarCSV ord;
    //aca hacer lo de llamar a csv y al otro + hacer lo del menu
    //consultas
    public void consultaTop10PaisFecha(String pais,String fecha){
        //obtenemos la lista de filas
        MyLinkedListImpl<MyLinkedListImpl<String>> listaDeFilas = ord.hashPaises.get(pais).get(fecha);
        //creamos un hash donde meter el top 10
        Node<String,MyLinkedListImpl<String>> nodo = new Node<>(null,null);
        HashTable<String,MyLinkedListImpl<String>> hashTop = new HashTableImpl<>(nodo,50);
        //obtenemos la posicion de daily_rank
        int posRank =ord.buscarPosicion("daily_rank",ord.fila0);


        for (int i = 0; i < listaDeFilas.size(); i++) {
            //si la fila esta en el top 10 meterla a un hash
            if (listaDeFilas.get(i).get(posRank).compareTo("11") < 0){
                //si el rank es mas bajo que 11 entonces es top 10
                hashTop.put(listaDeFilas.get(i).get(posRank),listaDeFilas.get(i));
            }

        }
        //obtenemos las posiciones de nombre ,artista
        int posNombre =ord.buscarPosicion("name",ord.fila0);
        int posArtist =ord.buscarPosicion("artists",ord.fila0);
        for (int i = 1; i < 11; i++) {

            String i2 = String.valueOf(i);
            System.out.println("Nombre de la cancion: "+ hashTop.get(i2).get(posNombre));
            System.out.println("Nombre del/los artista/s: " + hashTop.get(i2).get(posArtist));
            System.out.println("Posicion del top: " + i);
        }

     



    }



    //constructor
    public Spotify() {
        this.ord= new OrdenarCSV();

    }


    public static void main(String[] args) {
        Spotify spotify = new Spotify();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliga su opcion");
        System.out.println(" 1: Top 10 canciones en un pais en dia dado");
        System.out.println(" 2: Top 10 canciones en un pais en dia dado");
        String opcion = scanner.nextLine();

        if (opcion.equals("1")) {
            scanner.reset();
            System.out.println("Ingrese el pais");
            String pais = scanner.nextLine();
            scanner.reset();
            System.out.println("Ingrese la fecha (YYYY-MM-DD)");
            String fecha = scanner.nextLine();
            spotify.consultaTop10PaisFecha(pais,fecha);
        }

        if (opcion.equals("2")) {}

    }



}
