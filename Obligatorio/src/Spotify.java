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
        //creamos un hash donde meter el top 10
        Node<String,Cancion> nodo = new Node<>(null,null);
        HashTable<String,Cancion> hashTop = new HashTableImpl<>(nodo,20);
        //obtenemos la posicion de daily_rank
        MyLinkedListImpl<Cancion> fechaTemp = ord.getHashFechas().get(fecha);
        int j =0;
        for (int i = 0; i < fechaTemp.size() && j<10 ; i++) {
            String posRank = fechaTemp.get(i).getRank();
            //si la fila esta en el top 10 meterla a un
            if ((Integer.parseInt(posRank) < 11)){
                if((fechaTemp.get(i).getPais().equals(pais))){
                    //si el rank es mas bajo que 11 entonces es top 10
                    hashTop.put(posRank,fechaTemp.get(i));
                    j++;
                }
            }

        }
        //obtenemos las posiciones de nombre ,artista

        for (int i = 1; i < 11; i++) {
//            System.out.println("Top" + i + ": " + hashTop.get(i2).getNombre());
            String i2 = Integer.toString(i);
            System.out.println("\nNombre de la cancion: "+ hashTop.get(i2).getNombre());
            System.out.println("Nombre del/los artista/s: " + hashTop.get(i2).getArtistas());
            System.out.println("Posicion del top: " + i);
        }

    }


    public void consultaMasTop50(String fecha){
        MyLinkedListImpl<Cancion> cancionesFecha =ord.getHashFechas().get(fecha);
        Node<String,Integer> nodo = new Node<>(null,null);
        HashTableImpl<String,Integer> cantidadRepetidos = new HashTableImpl<>(nodo,100);
        for (int i = 0; i < cancionesFecha.size(); i++){
            Cancion tempCancion = cancionesFecha.get(i);
            if (!cantidadRepetidos.contains(tempCancion.getId())){
                cantidadRepetidos.put(tempCancion.getId(),1);
            }else{
                Integer prueba = cantidadRepetidos.get(tempCancion.getId());
                prueba++;

            }
        }
        Node<String,Integer>[] listaHash =cantidadRepetidos.getArrayHash();
        MyLinkedListImpl<Node<String,Integer>> listaTop5 = new MyLinkedListImpl<>();
        for (int i = 0; i<5; i++) {
            if (listaHash[i] != null){
            listaTop5.add(listaHash[i]);
        }}



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
        System.out.println(" 2: Top 5 canciones en más top 50");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                scanner.reset();
                System.out.println("Ingrese el pais");
                String pais = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fecha = scanner.nextLine();
                spotify.consultaTop10PaisFecha(pais,fecha);
            case "2":
            case "3":
            case "4":
            case "5":
        }
    }



}
