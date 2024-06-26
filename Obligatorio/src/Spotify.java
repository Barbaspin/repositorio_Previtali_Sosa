import Exceptions.DateOutOFBounds;
import Exceptions.DatosDesordenados;
import Exceptions.MalFormatoFecha;
import Exceptions.MalFormatoFloat;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Spotify {
    private final OrdenarCSV ord;
    private final LocalDate primerFecha;
    private final LocalDate ultimaFecha;



    //consultas
    public void consultaTop10PaisFecha(String pais,String fecha)
            throws DateOutOFBounds, MalFormatoFecha {
        //si la fecha se ingreso mal, salta la excepcion
        LocalDate testFecha = null;
        try {
             testFecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            throw new MalFormatoFecha();
        }

        if (!this.ord.getHashFechas().contains(fecha)) {
                throw new DateOutOFBounds();
        }
        //creamos un hash donde meter el top 10
        Node<String, Cancion> nodo = new Node<>(null, null);
        HashTableImpl<String, Cancion> hashTop = new HashTableImpl<>(nodo, 20);
        //conseguimos las canciones de esta fecha
        MyLinkedListImpl<Cancion> fechaTemp = ord.getHashFechas().get(fecha);
        int j = 0;
        for (int i = 0; i < fechaTemp.size() && j < 10; i++) {
            //obtenemos la posicion de daily_rank
            String posRank = fechaTemp.get(i).getRank();
            //si la fila esta en el top 10 meterla al hash con el top 10
            //si el rank es mas bajo que 11 entonces es top 10
            if ((Integer.parseInt(posRank) < 11)) {
                if ((fechaTemp.get(i).getPais().toLowerCase().equals(pais.toLowerCase()))) {
                    hashTop.put(posRank, fechaTemp.get(i));
                    j++;
                }
            }

        }

        if (hashTop.getCantidadAgregados()!=0) {
            //obtenemos las posiciones de nombre ,artista
            for (int i = 1; i < 11; i++) {
                String i2 = Integer.toString(i);
                System.out.println("Top " + i + "- ");
                System.out.println("\tNombre: " + hashTop.get(i2).getNombre());
                System.out.println("\tArtista/s: " + hashTop.get(i2).getArtistas());
                System.out.println("--------------------------------");

            }
        }else{
            //si no se encontro ninguna cancion, el pais no esta registrado
            System.out.println("El país no existe, intente nuevamente");
        }

    }


    public void consultaMasTop50(String fecha)
            throws DateOutOFBounds,MalFormatoFecha {
        //si la fecha se ingreso mal, salta la excepcion
        LocalDate testFecha = null;
        try {
            testFecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new MalFormatoFecha();
        }

        //si la fecha no esta en el hash, salta la excepcion
        if (!this.ord.getHashFechas().contains(fecha)) {
            throw new DateOutOFBounds();
        }
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
        int posInicial = 0;
        for (int i = 0; listaTop5.size() < 5; i++) {
            if (listaHash[i] != null) {
                listaTop5.add(listaHash[i]);
            }
            posInicial = i;
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
        Node<String, Integer>[] arrayTop5 = new Node[5];
        for (int i = 0; i < listaTop5.size(); i++) {
            arrayTop5[i] = listaTop5.get(i);
        }
        //pruebo Bubblesort
        for (int i = 1; i < arrayTop5.length; i++) {
            for (int j = 0; j < arrayTop5.length - i; j++) {
                if (arrayTop5[j].getValue() < arrayTop5[j + 1].getValue()) {
                    Node<String, Integer> aux = arrayTop5[j];
                    arrayTop5[j] = arrayTop5[j + 1];
                    arrayTop5[j + 1] = aux;
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
        for (int i = 0; i < 5; i++) {
            Cancion tempCancion = null;
            for (int j = 0; j < 5 && tempCancion == null; j++) {
                if (arrayTop5[i].getKey().equals(nuevaListaTop5.get(j).getId())) {
                    tempCancion = nuevaListaTop5.get(j);
                }
            }
            System.out.println(i + 1 + "- " + tempCancion.getNombre() + " aparece " + arrayTop5[i].getValue() + " veces en el top 50");
        }
    }

    public void consultaMasArtistasTop50(String fechaInicio,String fechaFin)
            throws DatosDesordenados,DateOutOFBounds,MalFormatoFecha{

        //si la fecha se ingreso mal, salta la excepcion
        LocalDate fechaInicioDate=null;
        LocalDate fechaFinDate=null;
        try {
            fechaInicioDate = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            fechaFinDate = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            throw new MalFormatoFecha();
        }


        if (fechaInicioDate.isBefore(primerFecha) || fechaFinDate.isAfter(ultimaFecha)){
            //si las fechas ingresadas estan fuera del rango registrado en el csv, salta la excepcion
                throw new DateOutOFBounds();
        }else if(fechaInicioDate.isAfter(fechaFinDate)){
            //si la primer fecha es posterior a la segunda, salta la excepcion
                throw new DatosDesordenados();
        }
        //creamos un hash con la cantidad de veces que se repite cada artista en el top 50
        Node<String, Integer> nodo = new Node<>(null, null);
        HashTableImpl<String, Integer> cantidadRepetidos = new HashTableImpl<>(nodo, 100);

        //buscaremos cual es la diferencia entre los dos dias
        int difDias = 0;
        while (fechaInicioDate.plusDays(difDias).isBefore(fechaFinDate)) {
            difDias++;
        }
        //iteramos por cada dia
        for (int i = 0; i < difDias + 1; i++) {
            LocalDate tempFecha = fechaInicioDate.plusDays(i);
            //para usar esta fecha en el hash, la pasamos a String
            String tempFechaString = tempFecha.toString();
            //si el dia se encuentra en el hash, pide la lista de canciones de ese dia
            if (this.ord.getHashFechas().contains(tempFechaString)) {
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
        }
        //buscaremos los 7 que mas veces aparecen

        //creamos una lista y agregamos los primeros 7 artistas que aparecen en el hash
        Node<String, Integer>[] listaHash = cantidadRepetidos.getArrayHash();
        MyLinkedListImpl<Node<String, Integer>> listaTop7 = new MyLinkedListImpl<>();
        int posInicial = 0;
        for (int i = 0; listaTop7.size() < 7; i++) {
            if (listaHash[i] != null) {
                listaTop7.add(listaHash[i]);
            }
            posInicial = i;
        }
        //recorremos el hash con los artistas
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
        Node<String, Integer>[] arrayTop7 = new Node[7];
        for (int i = 0; i < 7; i++) {
            arrayTop7[i] = listaTop7.get(i);
        }

        //pruebo Bubblesort
        for (int i = 1; i < arrayTop7.length; i++) {
            for (int j = 0; j < arrayTop7.length - i; j++) {
                if (arrayTop7[j].getValue() < arrayTop7[j + 1].getValue()) {
                    Node<String, Integer> aux = arrayTop7[j];
                    arrayTop7[j] = arrayTop7[j + 1];
                    arrayTop7[j + 1] = aux;
                }
            }
        }

        //mostramos los artistas desde el que más aparece al que menos
        for (int i = 0; i < 7; i++) {
            Node<String, Integer> artista = arrayTop7[i];
            System.out.println(i + 1 + "- " + artista.getKey() + " aparece " + artista.getValue() + " veces en el top 50");
        }

    }

    public void cantidadArtistaEnFecha(String artista,String fechaPedida)
            throws DateOutOFBounds,MalFormatoFecha{

        //si la fecha se ingreso mal, salta la excepcion
        LocalDate testFecha =null;
        try{
            testFecha = LocalDate.parse(fechaPedida, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch(Exception e){
            throw new MalFormatoFecha();
        }

        //si la fecha pedida no esta registrada, salta la excepcion
        if (!this.ord.getHashFechas().contains(fechaPedida)) {
            throw new DateOutOFBounds();
        }

        //iniciamos el contador en 0
        int cantidadAparecido = 0;
        //conseguimos la lista de canciones en esa fecha
        MyLinkedListImpl<Cancion> cancionesFecha = ord.getHashFechas().get(fechaPedida);

        //por cada cancion de esta fecha, buscamos cuantas veces se repite cada artista
        for (int j = 0; j < cancionesFecha.size(); j++) {
            Cancion tempCancion = cancionesFecha.get(j);
            // separamos los artistas, donde cada uno esta en una posicion distinta del array
            String[] artistasArray = tempCancion.getArtistas().split(", ");

            for (String tempArtista : artistasArray) {
                //si alguno de los artistas del array coincide con el que buscamos, incrementa el contador
                if (tempArtista.toLowerCase().equals(artista.toLowerCase())) {
                    cantidadAparecido++;
                }
            }
        }
        //mostramos cuantas veces aparece el artista solicitado
        System.out.println( artista + " aparece " + cantidadAparecido + " veces");
    }

    public void cantidadTempo(String tempo1, String tempo2, String fecha1, String fecha2)
            throws DateOutOFBounds,DatosDesordenados,MalFormatoFecha, MalFormatoFloat {
        //iniciamos el contador y los tempos para comparar en 0
        int contadorTempo = 0;
        float tempo1Float = 0;
        float tempo2Float = 0;

        //guardamos los tempos como float
        //si alguno no se puede pasar a float, salta la excepcion
        try {
             tempo1Float = Float.parseFloat(tempo1);
             tempo2Float = Float.parseFloat(tempo2);
        }catch (Exception e){
            throw new MalFormatoFloat();
        }

        //guardamos las fechas como fechas
        LocalDate fechaInicioDate = null;
        LocalDate fechaFinDate =null;
        //si la fecha se ingreso mal, salta la excepcion
        try{
             fechaInicioDate= LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
             fechaFinDate = LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            throw new MalFormatoFecha();
        }

        if (fechaInicioDate.isBefore(primerFecha) || fechaFinDate.isAfter(ultimaFecha)) {
            //si el rango de fechas busca fuera del rango de fechas, salta la excepcion
                throw new DateOutOFBounds();
        }
        if (fechaInicioDate.isAfter(fechaFinDate) || tempo2Float < tempo1Float) {
            //si el orden de las fechas o los tempos estan mal, salta la excepcion
            throw new DatosDesordenados();
        }
        //creamos un hash donde agregaremos las canciones que revisamos
        //el Value del nodo no se va a modificar, porque lo usamos para buscar rapidamente las canciones
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
            //revisamos que esta fecha esté contenida en el hash (es decir, que contenga una lista de canciones)
            if (ord.getHashFechas().contains(tempFechaString)){
                //conseguimos la lista de canciones en esa fecha
                MyLinkedListImpl<Cancion> cancionesFecha = ord.getHashFechas().get(tempFechaString);

                //por cada cancion de esta fecha
                for (int j = 0; j < cancionesFecha.size(); j++) {
                    Cancion tempCancion = cancionesFecha.get(j);
                    //si aun no revisamos esta cancion, la revisa
                    if (!cancionesRepetidas.contains(tempCancion.getId())) {
                        //la agregamos al hash de canciones revisadas
                        cancionesRepetidas.put(tempCancion.getId(), null);
                        float tempoCancion = Float.parseFloat(tempCancion.getTempo());
                        //vemos si el tempo de la cancion esta entre los buscados
                        if (tempo1Float < tempoCancion && tempoCancion < tempo2Float) {
                            //si cumple con esto, aumentamos en 1 la cantidad de canciones que cumplen lo requerido
                            contadorTempo++;
                        }
                    }
                }
            }
        }
        //mostramos el resultado
        System.out.println("La cantidad de canciones que tienen un tempo entre " +
                tempo1 + " y " + tempo2 +
                " entre " + fecha1 + " y " + fecha2 +
                " es " + contadorTempo);
    }


    //constructor
    public Spotify() {
        //el constructor llama a OrdenarCSV para guardar los datos
        this.ord = new OrdenarCSV();
        //guardamos la primer y ultima fechas encontradas con el constructor de OrdenarCSV
        this.primerFecha = ord.getFechaMinD();
        this.ultimaFecha = ord.getFechaMaxD();

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
                    try {
                        this.consultaTop10PaisFecha("",fecha);
                    } catch (DateOutOFBounds e) {
                        System.out.println("La fecha indicada no se encuentra entre los registros, intente nuevamente");
                    } catch (MalFormatoFecha e) {
                        System.out.println("Por favor ingresar la fecha con el formato correcto");
                    }
                }else{
                    try {
                        this.consultaTop10PaisFecha(pais,fecha);
                    } catch (DateOutOFBounds e) {
                        System.out.println("La fecha indicada no se encuentra entre los registros, intente nuevamente");
                    } catch (MalFormatoFecha e) {
                        System.out.println("Por favor ingresar la fecha con el formato indicado");
                    }
                }
                this.menu();
                break;
            case "2":
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fechaConsulta = scanner.nextLine();
                try {
                    this.consultaMasTop50(fechaConsulta);
                } catch (DateOutOFBounds e) {
                    System.out.println("La fecha indicada no se encuentra entre los registros, intente nuevamente");
                } catch (MalFormatoFecha e) {
                    System.out.println("Por favor ingresar la fecha con el formato indicado");
                }
                this.menu();
                break;
            case "3":
                scanner.reset();
                System.out.println("¿Entre cuáles dos fechas?, ingrese la primera (YYYY-MM-DD)");
                String fechaInicio = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la segunda fecha (YYYY-MM-DD)");
                String fechaFin = scanner.nextLine();
                try {
                    this.consultaMasArtistasTop50(fechaInicio,fechaFin);
                } catch (DatosDesordenados e) {
                    System.out.println("La primer fecha es mayor que la segunda, intente nuevamente");
                } catch (DateOutOFBounds e) {
                    System.out.println("La fecha indicada no se encuentra entre los registros, intente nuevamente");
                } catch (MalFormatoFecha e) {
                    System.out.println("Por favor ingresar la fecha con el formato indicado");
                }
                this.menu();
                break;
            case "4":
                scanner.reset();
                System.out.println("Ingrese el artista");
                String artista = scanner.nextLine();
                scanner.reset();
                System.out.println("Ingrese la fecha (YYYY-MM-DD)");
                String fechaPedida = scanner.nextLine();
                try {
                    this.cantidadArtistaEnFecha(artista,fechaPedida);
                } catch (DateOutOFBounds e) {
                    System.out.println("La fecha indicada no se encuentra entre los registros, intente nuevamente");
                } catch (MalFormatoFecha e) {
                    System.out.println("Por favor ingresar la fecha con el formato indicado");
                }
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
                try {
                    this.cantidadTempo(tempo1,tempo2,fecha1,fecha2);
                } catch (DateOutOFBounds e) {
                    System.out.println("La fecha indicada no se encuentra entre los registros, intente nuevamente");
                } catch (DatosDesordenados e) {
                    System.out.println("El primer dato es mayor que el segundo, intente nuevamente");
                } catch (MalFormatoFecha e) {
                    System.out.println("Por favor ingresar la fecha con el formato indicado");
                } catch (MalFormatoFloat e) {
                    System.out.println("Por favor ingresar el tempo como un número");
                }
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
        //se crea una instancia de la clase, y se llama al menu
        Spotify spotify = new Spotify();
        spotify.menu();
    }



}
