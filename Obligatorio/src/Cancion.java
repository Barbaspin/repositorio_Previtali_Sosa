import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.util.Objects;

public class Cancion {

    private String id;
    private String nombre;
    private String artistas;
    private String rank;
    private String movimientoDiario;
    private String movimientoSemanal;
    private String pais;
    private String fecha;
    private String popularidad;
    private String esExplicito;
    private String duracionMs;
    private String album;
    private String fechaAlbum;
    private String bailabilidad;
    private String energia;
    private String llave;
    private String ruidosidad;
    private String modo;
    private String hablacion;
    private String acustica;
    private String instrumental;
    private String viveza;
    private String valence;
    private String tempo;
    private String time_sig;


    public Cancion(MyLinkedListImpl<String> lista) {
        this.id = lista.get(0);
        this.nombre =lista.get(1);
        this.artistas = lista.get(2);
        this.rank = lista.get(3);
        this.movimientoDiario = lista.get(4);
        this.movimientoSemanal = lista.get(5);
        this.pais = lista.get(6);
        this.fecha = lista.get(7);
        this.popularidad = lista.get(8);
        this.esExplicito = lista.get(9);
        this.duracionMs = lista.get(10);
        this.album = lista.get(11);
        this.fechaAlbum = lista.get(12);
        this.bailabilidad = lista.get(13);
        this.energia = lista.get(14);
        this.llave = lista.get(15);
        this.ruidosidad = lista.get(16);
        this.modo = lista.get(17);
        this.hablacion = lista.get(18);
        this.acustica = lista.get(19);
        this.instrumental = lista.get(20);
        this.viveza = lista.get(21);
        this.valence = lista.get(22);
        this.tempo = lista.get(23);
        this.time_sig = lista.get(24);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtistas() {
        return artistas;
    }

    public void setArtistas(String artistas) {
        this.artistas = artistas;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMovimientoDiario() {
        return movimientoDiario;
    }

    public void setMovimientoDiario(String movimientoDiario) {
        this.movimientoDiario = movimientoDiario;
    }

    public String getMovimientoSemanal() {
        return movimientoSemanal;
    }

    public void setMovimientoSemanal(String movimientoSemanal) {
        this.movimientoSemanal = movimientoSemanal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(String popularidad) {
        this.popularidad = popularidad;
    }

    public String getEsExplicito() {
        return esExplicito;
    }

    public void setEsExplicito(String esExplicito) {
        this.esExplicito = esExplicito;
    }

    public String getDuracionMs() {
        return duracionMs;
    }

    public void setDuracionMs(String duracionMs) {
        this.duracionMs = duracionMs;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFechaAlbum() {
        return fechaAlbum;
    }

    public void setFechaAlbum(String fechaAlbum) {
        this.fechaAlbum = fechaAlbum;
    }

    public String getBailabilidad() {
        return bailabilidad;
    }

    public void setBailabilidad(String bailabilidad) {
        this.bailabilidad = bailabilidad;
    }

    public String getEnergia() {
        return energia;
    }

    public void setEnergia(String energia) {
        this.energia = energia;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getRuidosidad() {
        return ruidosidad;
    }

    public void setRuidosidad(String ruidosidad) {
        this.ruidosidad = ruidosidad;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getHablacion() {
        return hablacion;
    }

    public void setHablacion(String hablacion) {
        this.hablacion = hablacion;
    }

    public String getAcustica() {
        return acustica;
    }

    public void setAcustica(String acustica) {
        this.acustica = acustica;
    }

    public String getInstrumental() {
        return instrumental;
    }

    public void setInstrumental(String instrumental) {
        this.instrumental = instrumental;
    }

    public String getViveza() {
        return viveza;
    }

    public void setViveza(String viveza) {
        this.viveza = viveza;
    }

    public String getValence() {
        return valence;
    }

    public void setValence(String valence) {
        this.valence = valence;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTime_sig() {
        return time_sig;
    }

    public void setTime_sig(String time_sig) {
        this.time_sig = time_sig;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cancion cancion = (Cancion) o;
        return Objects.equals(id, cancion.id);
    }

}
