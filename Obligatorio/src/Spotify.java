import uy.edu.um.prog2.adt.HashCode.HashTable;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
            System.out.println("\n top " + i + "- ");
            System.out.println("\tNombre de la cancion: "+ hashTop.get(i2).getNombre());
            System.out.println("\tNombre del/los artista/s: " + hashTop.get(i2).getArtistas());
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
        for (int i=0;i<listaTop5.size();i++){
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
            System.out.println(i+1 + "- "+tempCancion.getNombre() + " aparece "+ arrayTop5[i].getValue() + " veces en el top 50");
        }
    }

    public void consultaMasArtistasTop50(String fechaInicio,String fechaFin){
        LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechaFinDate = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // Para aumentar la cantidad de dias se hace asi
        // LocalDate fechaFinal= fechaInicial.plusDays(CantDias);
        if (fechaInicioDate.isAfter(fechaFinDate)){
            System.out.println("error al darme las fechas");
        }
        //creamos un hash con la cantidad de veces que se repite el artista en el top 50
        Node<String, Integer> nodo = new Node<>(null, null);
        HashTableImpl<String, Integer> cantidadRepetidos = new HashTableImpl<>(nodo, 100);

        //buscaremos en todos los dias entre las dos fechas
//        no funciono
//        int difDias = (int) Duration.between(fechaInicioDate,fechaFinDate).toDays();

        int difDias = 0;
        while (fechaInicioDate.plusDays(difDias).isBefore(fechaFinDate)){
            difDias++;
        }
        for (int i = 0; i<difDias+1 ;i++) {
            LocalDate tempFecha = fechaInicioDate.plusDays(i);
            //para usar esta fecha en el hash, la pasamos a String
            String tempFechaString = tempFecha.toString();
            //conseguimos la lista de canciones en esa fecha
            MyLinkedListImpl<Cancion> cancionesFecha = ord.getHashFechas().get(tempFechaString);

            //por cada cancion de esta fecha, buscamos cuantas veces se repite cada artista
            for (int j = 0; j < cancionesFecha.size(); j++) {
                Cancion tempCancion = cancionesFecha.get(j);
                // separamos los artistas, donde cada uno esta en una posicion distinta del array
                String[] artistasArray = tempCancion.getArtistas().split(", ");

                //por cada artista del array
                for (String tempArtista : artistasArray) {
                    //si el artista no se encontro hasta ahora, lo agrega al hash y cuenta como 1 aparicion
                    if (!cantidadRepetidos.contains(tempArtista)) {
                        cantidadRepetidos.put(tempArtista, 1);
                    } else {
                        //si el artista ya se habia encontrado, aumenta en 1 la cantidad de apariciones
                        Integer cantidad = cantidadRepetidos.get(tempArtista);
                        cantidadRepetidos.changeValue(tempArtista, cantidad + 1);
                    }
                }
            }
        }

        //buscamos los 7 que mas veces aparecen
        //creamos una lista y agregamos los primeras 7 artistas que aparecen en el hash
        Node<String, Integer>[] listaHash = cantidadRepetidos.getArrayHash();
        MyLinkedListImpl<Node<String, Integer>> listaTop7 = new MyLinkedListImpl<>();
        int posInicial=0;
        for (int i = 0; listaTop7.size()<7; i++) {
            if (listaHash[i] != null) {
                listaTop7.add(listaHash[i]);
            }
            posInicial=i;
        }
        //recorremos los artistas
        for (int i = posInicial; i < listaHash.length; i++) {
            if (listaHash[i] != null) {
                Node<String, Integer> artistaActual = listaHash[i];
                Node<String, Integer> artistaMenor = listaTop7.get(0);
                for (int j = 1; j < 7; j++) {
                    //buscamos el artista que aparece menos veces, de entre los 7 que mas aparecen
                    if (artistaMenor.getValue() > listaTop7.get(j).getValue()) {
                        artistaMenor = listaTop7.get(j);
                    }
                }
                //si el artista actual aparece menos veces que el que menos aparece de los guardadas anteriormente,
                //eliminamos al anterior y agregamos al nuevo
                if (artistaActual.getValue() > artistaMenor.getValue()) {
                    listaTop7.remove(artistaMenor);
                    listaTop7.add(artistaActual);
                }
            }
        }
        //cargamos el top 7 a un array para despues hacer bubblesort
        Node<String,Integer>[] arrayTop7 = new Node[7];
        for (int i=0;i<7;i++){
            arrayTop7[i] = listaTop7.get(i);
        }

        //pruebo Bubblesort
        for (int i=1;i<arrayTop7.length-1;i++){
            for (int j=0;j< arrayTop7.length-i;j++){
                if (arrayTop7[j].getValue() < arrayTop7[j+1].getValue()){
                    Node<String, Integer> aux = arrayTop7[j];
                    arrayTop7[j]=arrayTop7[j+1];
                    arrayTop7[j+1] = aux;
                }
            }
        }

        for (int i=0;i<7;i++) {
            Node<String,Integer> artista = arrayTop7[i];
            System.out.println(i+1 +"- "+ artista.getKey()+ " aparece "+ artista.getValue() + " veces en el top 50");
        }

    }

    public void cantidadArtistaEnFecha(String artista,String fechaPedida){
        int cantidadAparecido=0;
            //conseguimos la lista de canciones en esa fecha
            MyLinkedListImpl<Cancion> cancionesFecha = ord.getHashFechas().get(fechaPedida);
            //por cada cancion de esta fecha, buscamos cuantas veces se repite cada artista
            for (int j = 0; j < cancionesFecha.size(); j++) {
                Cancion tempCancion = cancionesFecha.get(j);
                // separamos los artistas, donde cada uno esta en una posicion distinta del array
                String[] artistasArray = tempCancion.getArtistas().split(", ");

                //por cada artista del array
                for (String tempArtista : artistasArray) {
                    //si el artista no se encontro hasta ahora, lo agrega al hash y cuenta como 1 aparicion
                    if (tempArtista.equals(artista)) {
                        cantidadAparecido++;
                    }
                }
            }
        System.out.println(artista + " aparece " + cantidadAparecido + " veces");
    }

    public void cantidadTempo(String tempo1, String tempo2, String fecha1, String fecha2) {
        int contadorTempo=0;
        //guardamos los tempos como Integers
        Float tempo1Float = Float.parseFloat(tempo1);
        Float tempo2Float = Float.parseFloat(tempo2);
        //guardamos las fechas como fechas
        LocalDate fechaInicioDate = LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechaFinDate = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (fechaInicioDate.isAfter(fechaFinDate) || tempo1Float > tempo2Float) {
            System.out.println("error al darme los datos");
        }

        //creamos un hash con las canciones que revisamos, el Value del nodo no se va a modificar
        Node<String, Integer> nodo = new Node<>(null, null);
        HashTableImpl<String, Integer> cancionesRepetidas = new HashTableImpl<>(nodo, 100);

        //buscaremos en todos los dias entre las dos fechas
        int difDias = 0;
        while (fechaInicioDate.plusDays(difDias).isBefore(fechaFinDate)) {
            difDias++;
        }
        for (int i = 0; i < difDias + 1; i++) {
            LocalDate tempFecha = fechaInicioDate.plusDays(i);
            //para usar esta fecha en el hash, la pasamos a String
            String tempFechaString = tempFecha.toString();
            //conseguimos la lista de canciones en esa fecha
            MyLinkedListImpl<Cancion> cancionesFecha = ord.getHashFechas().get(tempFechaString);

            //por cada cancion de esta fecha
            for (int j = 0; j < cancionesFecha.size(); j++) {
                Cancion tempCancion = cancionesFecha.get(j);
                //si aun no revisamos esta cancion, la revisa
                if (!cancionesRepetidas.contains(tempCancion.getId())){
                    //la agregamos al hash de canciones revisadas
                    cancionesRepetidas.put(tempCancion.getId(),null);
                    Float tempoCancion = Float.parseFloat(tempCancion.getTempo());
                    //vemos si el tempo de la cancion esta entre los buscados
                    if (tempo1Float < tempoCancion && tempoCancion < tempo2Float){
                        //si cumple con esto, aumentamos en 1 la cantidad de canciones que cumplen lo requerido
                        contadorTempo++;
                    }
                }
            }
        }
        System.out.println("La cantidad de canciones que tienen un tempo entre " +
                tempo1 + " y " + tempo2 + " entre " + fecha1 + " y " + fecha2 + " es " + contadorTempo);
    }


    //constructor
    public Spotify() {
        this.ord= new OrdenarCSV();

    }


    public void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nElija su opcion");
        System.out.println(" 1: Top 10 canciones en un pais en dia dado");
        System.out.println(" 2: Top 5 canciones en más top 50 en un dia dado");
        System.out.println(" 3: Top 7 artistas en un rango de fechas");
        System.out.println(" 4: Cantidad de veces que aparece un artista en una fecha");
        System.out.println(" 5: Cantidad de canciones con tempo especifico en un rango de fechas");
        System.out.println(" 6: Salir del programa");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                scanner.reset();
                System.out.println("Ingrese el pais (para ver el top mundial ingresar \"mundial\")");
                String pais = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fecha = scanner.nextLine();
                if (pais.toLowerCase().equals("mundial")){
                    this.consultaTop10PaisFecha("",fecha);
                }else{
                    this.consultaTop10PaisFecha(pais,fecha);
                }
                this.menu();
                break;
            case "2":
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fechaConsulta = scanner.nextLine();
                this.consultaMasTop50(fechaConsulta);
                this.menu();
                break;
            case "3":
                scanner.reset();
                System.out.println("¿Entre cuáles dos fechas?, ingrese la primera (YYYY-MM-DD)");
                String fechaInicio = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la segunda fecha (YYYY-MM-DD)");
                String fechaFin = scanner.nextLine();
                this.consultaMasArtistasTop50(fechaInicio,fechaFin);
                this.menu();
                break;
            case "4":
                scanner.reset();
                System.out.println("Ingrese el artista");
                String artista = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fechaPedida = scanner.nextLine();
                this.cantidadArtistaEnFecha(artista,fechaPedida);
                this.menu();
                break;
            case "5":
                scanner.reset();
                System.out.println("¿Entre cuáles dos tempos?, ingrese el primero");
                String tempo1 = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese el segundo");
                String tempo2 = scanner.nextLine();
                System.out.println("¿Entre cuáles dos fechas?, ingrese la primera (YYYY-MM-DD)");
                String fecha1 = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la segunda fecha (YYYY-MM-DD)");
                String fecha2 = scanner.nextLine();
                this.cantidadTempo(tempo1,tempo2,fecha1,fecha2);
                this.menu();
                break;
            case "6":
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion inválida. Intente nuevamente");
                menu();
                break;
        }
    }


    public static void main(String[] args) {
        Spotify spotify = new Spotify();
        spotify.menu();
    }



}
