import uy.edu.um.prog2.adt.HashCode.HashTable;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.awt.*;
import java.util.ArrayList;
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


    public void consultaMasTop50(String fecha) {
        //conseguimos la lista de canciones en esa fecha
        MyLinkedListImpl<Cancion> cancionesFecha = ord.getHashFechas().get(fecha);
        //creamos un hash con la cantidad de veces que se repite cada cancion en el top 50
        Node<String, Integer> nodo = new Node<>(null, null);
        HashTableImpl<String, Integer> cantidadRepetidos = new HashTableImpl<>(nodo, 100);
        for (int i = 0; i < cancionesFecha.size(); i++) {
            Cancion tempCancion = cancionesFecha.get(i);
            String idTemp = tempCancion.getId();
            //si la cancion no se encontro hasta ahora, la agrega al hash y cuenta como 1 aparicion
            if (!cantidadRepetidos.contains(idTemp)) {
                cantidadRepetidos.put(idTemp, 1);
            } else {
                //si la cancion ya se habia encontrado, aumenta en 1 la cantidad de apariciones
                Integer cantidad = cantidadRepetidos.get(idTemp);
                cantidadRepetidos.changeValue(idTemp, cantidad + 1);
            }
        }
        //buscamos las 5 que mas veces aparecen
        //creamos una lista y agregamos las primeras 5 canciones que aparecen en el hash
        Node<String, Integer>[] listaHash = cantidadRepetidos.getArrayHash();
        MyLinkedListImpl<Node<String, Integer>> listaTop5 = new MyLinkedListImpl<>();
        int posInicial=0;
        for (int i = 0; listaTop5.size()<5; i++) {
            if (listaHash[i] != null) {
                listaTop5.add(listaHash[i]);
            }
            posInicial=i;
        }
        //recorremos las canciones
        for (int i = posInicial; i < listaHash.length; i++) {
            if (listaHash[i] != null) {
                Node<String, Integer> cancionActual = listaHash[i];
                Node<String, Integer> cancionMenor = listaTop5.get(0);
                for (int j = 1; j < 5; j++) {
                    //buscamos la cancion que aparece menos veces, de entre las 5 que mas aparecen
                    if (cancionMenor.getValue() > listaTop5.get(j).getValue()) {
                        cancionMenor = listaTop5.get(j);
                    }
                }
                //si la cancion actual aparece mas veces que la que menos aparece de las guardadas anteriormente,
                //eliminamos la anterior y agregamos la nueva
                if (cancionActual.getValue() > cancionMenor.getValue()) {
                    listaTop5.remove(cancionMenor);
                    listaTop5.add(cancionActual);
                }
            }
        }
        //cargamos el top 5 a un array para despues hacer bubblesort
        Node<String,Integer>[] arrayTop5 = new Node[5];
        for (int i=0;i<5;i++){
            arrayTop5[i] = listaTop5.get(i);
        }

        //pruebo Bubblesort
        for (int i=1;i<arrayTop5.length-1;i++){
            for (int j=0;j< arrayTop5.length-i;j++){
                if (arrayTop5[j].getValue() < arrayTop5[j+1].getValue()){
                    Node<String, Integer> aux = arrayTop5[j];
                    arrayTop5[j]=arrayTop5[j+1];
                    arrayTop5[j+1] = aux;
                }
            }
        }

        MyLinkedListImpl<Cancion> nuevaListaTop5 = new MyLinkedListImpl<>();
        for (int i = 0; i < cancionesFecha.size() && nuevaListaTop5.size() < 5; i++) {
            //hacer algo para que busque por el id asi conseguimos los datos como nombre artistas etc
            String id = cancionesFecha.get(i).getId();
            if (id.equals(listaTop5.get(0).getKey()) || id.equals(listaTop5.get(1).getKey()) || id.equals(listaTop5.get(2).getKey()) ||
                    id.equals(listaTop5.get(3).getKey()) || id.equals(listaTop5.get(4).getKey())) {
                nuevaListaTop5.add(cancionesFecha.get(i));
            }
        }

        for (int i=0;i<5;i++) {
            Cancion tempCancion =null;
            for (int j=0;j<5 && tempCancion==null;j++){
                if (arrayTop5[i].getKey().equals(nuevaListaTop5.get(j).getId())){
                    tempCancion=nuevaListaTop5.get(j);
                }
            }
            System.out.println("\nNombre de la cancion: " + tempCancion.getNombre());
            System.out.println("Aparece "+ arrayTop5[i].getValue() + " veces en el top 50");

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
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fechaConsulta = scanner.nextLine();
                spotify.consultaMasTop50(fechaConsulta);
            case "3":
            case "4":
            case "5":
        }
    }



}
